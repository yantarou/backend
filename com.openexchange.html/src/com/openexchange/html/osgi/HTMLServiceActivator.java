/*
 *
 *    OPEN-XCHANGE legal information
 *
 *    All intellectual property rights in the Software are protected by
 *    international copyright laws.
 *
 *
 *    In some countries OX, OX Open-Xchange, open xchange and OXtender
 *    as well as the corresponding Logos OX Open-Xchange and OX are registered
 *    trademarks of the OX Software GmbH group of companies.
 *    The use of the Logos is not covered by the GNU General Public License.
 *    Instead, you are allowed to use these Logos according to the terms and
 *    conditions of the Creative Commons License, Version 2.5, Attribution,
 *    Non-commercial, ShareAlike, and the interpretation of the term
 *    Non-commercial applicable to the aforementioned license is published
 *    on the web site http://www.open-xchange.com/EN/legal/index.html.
 *
 *    Please make sure that third-party modules and libraries are used
 *    according to their respective licenses.
 *
 *    Any modifications to this package must retain all copyright notices
 *    of the original copyright holder(s) for the original code used.
 *
 *    After any such modifications, the original and derivative code shall remain
 *    under the copyright of the copyright holder(s) and/or original author(s)per
 *    the Attribution and Assignment Agreement that can be located at
 *    http://www.open-xchange.com/EN/developer/. The contributing author shall be
 *    given Attribution for the derivative code and a license granting use.
 *
 *     Copyright (C) 2016-2020 OX Software GmbH
 *     Mail: info@open-xchange.com
 *
 *
 *     This program is free software; you can redistribute it and/or modify it
 *     under the terms of the GNU General Public License, Version 2 as published
 *     by the Free Software Foundation.
 *
 *     This program is distributed in the hope that it will be useful, but
 *     WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *     or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *     for more details.
 *
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc., 59
 *     Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */

package com.openexchange.html.osgi;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import net.htmlparser.jericho.Config;
import net.htmlparser.jericho.LoggerProvider;
import com.openexchange.config.ConfigurationService;
import com.openexchange.dispatcher.DispatcherPrefixService;
import com.openexchange.html.HtmlService;
import com.openexchange.html.internal.HtmlServiceImpl;
import com.openexchange.html.internal.WhitelistedSchemes;
import com.openexchange.html.internal.filtering.FilterMaps;
import com.openexchange.html.internal.jericho.JerichoParser;
import com.openexchange.html.internal.jsoup.JsoupParser;
import com.openexchange.html.internal.parser.handler.HTMLImageFilterHandler;
import com.openexchange.html.services.ServiceRegistry;
import com.openexchange.java.Streams;
import com.openexchange.osgi.HousekeepingActivator;
import com.openexchange.proxy.ProxyRegistry;
import com.openexchange.threadpool.ThreadPoolService;
import com.openexchange.tools.stream.UnsynchronizedByteArrayInputStream;

/**
 * {@link HTMLServiceActivator} - Activator for HTML interface.
 *
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a>
 */
public class HTMLServiceActivator extends HousekeepingActivator {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(HTMLServiceActivator.class);

    @Override
    protected Class<?>[] getNeededServices() {
        return new Class<?>[] { ConfigurationService.class, DispatcherPrefixService.class, ThreadPoolService.class };
    }

    @Override
    protected void handleAvailability(final Class<?> clazz) {
        apply((ConfigurationService) getService(clazz));
    }

    @Override
    protected void handleUnavailability(final Class<?> clazz) {
        restore();
    }

    @Override
    public void startBundle() throws Exception {
        try {
            Services.setServiceLookup(this);
            HTMLImageFilterHandler.PREFIX.set(getService(DispatcherPrefixService.class));
            /*
             * Configure
             */
            ConfigurationService configService = getService(ConfigurationService.class);
            apply(configService);
            /*
             * Service trackers
             */
            track(ProxyRegistry.class, new ProxyRegistryCustomizer(context));
            /*
             * Open trackers
             */
            openTrackers();
            /*
             * Other start-up stuff
             */
            Config.LoggerProvider = LoggerProvider.DISABLED;
            FilterMaps.loadWhitelist();
            WhitelistedSchemes.initInstance(configService);
        } catch (final Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    @Override
    public void stopBundle() throws Exception {
        try {
            /*
             * Other shut-down stuff
             */
            WhitelistedSchemes.dropInstance();
            FilterMaps.resetWhitelist();
            JerichoParser.shutDown();
            JsoupParser.shutDown();
            /*
             * Close trackers
             */
            cleanUp();
            /*
             * Restore
             */
            restore();
            HTMLImageFilterHandler.PREFIX.set(null);
            Services.setServiceLookup(null);
        } catch (final Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    private void apply(final ConfigurationService configurationService) {
        ServiceRegistry.getInstance().addService(ConfigurationService.class, configurationService);
        /*
         * Initialize maps
         */
        final Map<Character, String> htmlCharMap;
        final Map<String, Character> htmlEntityMap;
        {
            final Object[] maps = getHTMLEntityMaps(configurationService.getFileByName("HTMLEntities.properties"));
            htmlCharMap = (Map<Character, String>) maps[0];
            htmlEntityMap = (Map<String, Character>) maps[1];
            /*
             * Add mapping for '\'' (apos) character
             */
            htmlEntityMap.put("#39", Character.valueOf('\''));
            htmlCharMap.put(Character.valueOf('\''), "#39");
        }
        /*
         * Register HTML service
         */
        registerService(HtmlService.class, new HtmlServiceImpl(htmlCharMap, htmlEntityMap), null);
    }

    public static Object[] getHTMLEntityMaps(final File htmlEntityFile) {
        final Map<Character, String> htmlCharMap = new HashMap<>();
        final Map<String, Character> htmlEntityMap = new HashMap<>();
        final Properties htmlEntities = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(htmlEntityFile);
            htmlEntities.load(in);
        } catch (final IOException e) {
            LOG.error("", e);
            return getDefaultHTMLEntityMaps();
        } finally {
            Streams.close(in);
            in = null;
        }
        /*
         * Build up map
         */
        final Iterator<Map.Entry<Object, Object>> iter = htmlEntities.entrySet().iterator();
        final int size = htmlEntities.size();
        for (int i = 0; i < size; i++) {
            final Map.Entry<Object, Object> entry = iter.next();
            final Character c = Character.valueOf((char) Integer.parseInt((String) entry.getValue()));
            htmlEntityMap.put((String) entry.getKey(), c);
            htmlCharMap.put(c, (String) entry.getKey());
        }
        return new Object[] { htmlCharMap, htmlEntityMap };
    }

    public static Object[] getDefaultHTMLEntityMaps() {
        final Map<Character, String> htmlCharMap = new HashMap<>();
        final Map<String, Character> htmlEntityMap = new HashMap<>();
        final Properties htmlEntities = new Properties();
        try {
            final StringBuilder entityMap = new StringBuilder(1024);
            entityMap.append("# character map for HTML emails\n");
            entityMap.append("weierp=8472\n");
            entityMap.append("supe=8839\n");
            entityMap.append("image=8465\n");
            entityMap.append("ecirc=234\n");
            entityMap.append("Otilde=213\n");
            entityMap.append("uacute=250\n");
            entityMap.append("diams=9830\n");
            entityMap.append("ntilde=241\n");
            entityMap.append("dArr=8659\n");
            entityMap.append("Ecirc=202\n");
            entityMap.append("ograve=242\n");
            entityMap.append("yacute=253\n");
            entityMap.append("times=215\n");
            entityMap.append("iuml=239\n");
            entityMap.append("rArr=8658\n");
            entityMap.append("micro=181\n");
            entityMap.append("rceil=8969\n");
            entityMap.append("plusmn=177\n");
            entityMap.append("there4=8756\n");
            entityMap.append("nabla=8711\n");
            entityMap.append("lsaquo=8249\n");
            entityMap.append("rang=9002\n");
            entityMap.append("Iuml=207\n");
            entityMap.append("real=8476\n");
            entityMap.append("sup3=179\n");
            entityMap.append("sube=8838\n");
            entityMap.append("acirc=226\n");
            entityMap.append("sup2=178\n");
            entityMap.append("sup1=185\n");
            entityMap.append("lsquo=8216\n");
            entityMap.append("Acirc=194\n");
            entityMap.append("sect=167\n");
            entityMap.append("notin=8713\n");
            entityMap.append("radic=8730\n");
            entityMap.append("ocirc=244\n");
            entityMap.append("oplus=8853\n");
            entityMap.append("euml=235\n");
            entityMap.append("Oacute=211\n");
            entityMap.append("rfloor=8971\n");
            entityMap.append("rdquo=8221\n");
            entityMap.append("Ocirc=212\n");
            entityMap.append("Igrave=204\n");
            entityMap.append("minus=8722\n");
            entityMap.append("trade=8482\n");
            entityMap.append("szlig=223\n");
            entityMap.append("Agrave=192\n");
            entityMap.append("forall=8704\n");
            entityMap.append("laquo=171\n");
            entityMap.append("cedil=184\n");
            entityMap.append("Euml=203\n");
            entityMap.append("ensp=8194\n");
            entityMap.append("Egrave=200\n");
            entityMap.append("otilde=245\n");
            entityMap.append("lowast=8727\n");
            entityMap.append("uml=168\n");
            entityMap.append("perp=8869\n");
            entityMap.append("int=8747\n");
            entityMap.append("nbsp=160\n");
            entityMap.append("Oslash=216\n");
            entityMap.append("Ugrave=217\n");
            entityMap.append("auml=228\n");
            entityMap.append("part=8706\n");
            entityMap.append("gt=62\n");
            entityMap.append("ouml=246\n");
            entityMap.append("ge=8805\n");
            entityMap.append("para=182\n");
            entityMap.append("empty=8709\n");
            entityMap.append("Auml=196\n");
            entityMap.append("isin=8712\n");
            entityMap.append("ang=8736\n");
            entityMap.append("uarr=8593\n");
            entityMap.append("agrave=224\n");
            entityMap.append("Ouml=214\n");
            entityMap.append("and=8743\n");
            entityMap.append("cap=8745\n");
            entityMap.append("exist=8707\n");
            entityMap.append("oline=8254\n");
            entityMap.append("egrave=232\n");
            entityMap.append("rsquo=8217\n");
            entityMap.append("oacute=243\n");
            entityMap.append("frac34=190\n");
            entityMap.append("larr=8592\n");
            entityMap.append("amp=38\n");
            entityMap.append("lrm=8206\n");
            entityMap.append("Atilde=195\n");
            entityMap.append("iquest=191\n");
            entityMap.append("infin=8734\n");
            entityMap.append("reg=174\n");
            entityMap.append("igrave=236\n");
            entityMap.append("sbquo=8218\n");
            entityMap.append("ucirc=251\n");
            entityMap.append("Ucirc=219\n");
            entityMap.append("yuml=255\n");
            entityMap.append("copy=169\n");
            entityMap.append("nsub=8836\n");
            entityMap.append("prime=8242\n");
            entityMap.append("raquo=187\n");
            entityMap.append("Ccedil=199\n");
            entityMap.append("Prime=8243\n");
            entityMap.append("hearts=9829\n");
            entityMap.append("oslash=248\n");
            entityMap.append("ugrave=249\n");
            entityMap.append("harr=8596\n");
            entityMap.append("brvbar=166\n");
            entityMap.append("Dagger=8225\n");
            entityMap.append("equiv=8801\n");
            entityMap.append("quot=34\n");
            entityMap.append("ordm=186\n");
            entityMap.append("deg=176\n");
            entityMap.append("bull=8226\n");
            entityMap.append("alefsym=8501\n");
            entityMap.append("frac14=188\n");
            entityMap.append("frac12=189\n");
            entityMap.append("ordf=170\n");
            entityMap.append("Iacute=205\n");
            entityMap.append("sim=8764\n");
            entityMap.append("zwnj=8204\n");
            entityMap.append("lfloor=8970\n");
            entityMap.append("otimes=8855\n");
            entityMap.append("rsaquo=8250\n");
            entityMap.append("Aacute=193\n");
            entityMap.append("uuml=252\n");
            entityMap.append("ndash=8211\n");
            entityMap.append("clubs=9827\n");
            entityMap.append("sup=8835\n");
            entityMap.append("atilde=227\n");
            entityMap.append("spades=9824\n");
            entityMap.append("sum=8721\n");
            entityMap.append("not=172\n");
            entityMap.append("loz=9674\n");
            entityMap.append("curren=164\n");
            entityMap.append("shy=173\n");
            entityMap.append("Eacute=201\n");
            entityMap.append("or=8744\n");
            entityMap.append("thinsp=8201\n");
            entityMap.append("sdot=8901\n");
            entityMap.append("aring=229\n");
            entityMap.append("sub=8834\n");
            entityMap.append("uArr=8657\n");
            entityMap.append("pound=163\n");
            entityMap.append("bdquo=8222\n");
            entityMap.append("Aring=197\n");
            entityMap.append("Uuml=220\n");
            entityMap.append("darr=8595\n");
            entityMap.append("Uacute=218\n");
            entityMap.append("cong=8773\n");
            entityMap.append("Ntilde=209\n");
            entityMap.append("ccedil=231\n");
            entityMap.append("aelig=230\n");
            entityMap.append("lArr=8656\n");
            entityMap.append("emsp=8195\n");
            entityMap.append("rarr=8594\n");
            entityMap.append("Ograve=210\n");
            entityMap.append("lceil=8968\n");
            entityMap.append("thorn=254\n");
            entityMap.append("Yacute=221\n");
            entityMap.append("euro=8364\n");
            entityMap.append("permil=8240\n");
            entityMap.append("dagger=8224\n");
            entityMap.append("ni=8715\n");
            entityMap.append("cent=162\n");
            entityMap.append("ne=8800\n");
            entityMap.append("cup=8746\n");
            entityMap.append("lang=9001\n");
            entityMap.append("asymp=8776\n");
            entityMap.append("THORN=222\n");
            entityMap.append("aacute=225\n");
            entityMap.append("AElig=198\n");
            entityMap.append("crarr=8629\n");
            entityMap.append("acute=180\n");
            entityMap.append("ETH=208\n");
            entityMap.append("iexcl=161\n");
            entityMap.append("icirc=238\n");
            entityMap.append("eacute=233\n");
            entityMap.append("divide=247\n");
            entityMap.append("eth=240\n");
            entityMap.append("hArr=8660\n");
            entityMap.append("ldquo=8220\n");
            entityMap.append("Icirc=206\n");
            entityMap.append("macr=175\n");
            entityMap.append("rlm=8207\n");
            entityMap.append("yen=165\n");
            entityMap.append("iacute=237\n");
            entityMap.append("hellip=8230\n");
            entityMap.append("middot=183\n");
            entityMap.append("prop=8733\n");
            entityMap.append("lt=60\n");
            entityMap.append("frasl=8260\n");
            entityMap.append("mdash=8212\n");
            entityMap.append("zwj=8205\n");
            entityMap.append("prod=8719\n");
            entityMap.append("le=8804");
            htmlEntities.load(new UnsynchronizedByteArrayInputStream(String.valueOf(entityMap.toString()).getBytes()));
        } catch (final IOException e) {
            /*
             * Cannot occur
             */
            LOG.error("", e);
        }
        /*
         * Build up map
         */
        final Iterator<Map.Entry<Object, Object>> iter = htmlEntities.entrySet().iterator();
        final int size = htmlEntities.size();
        for (int i = 0; i < size; i++) {
            final Map.Entry<Object, Object> entry = iter.next();
            final Character c = Character.valueOf((char) Integer.parseInt((String) entry.getValue()));
            htmlEntityMap.put((String) entry.getKey(), c);
            htmlCharMap.put(c, (String) entry.getKey());
        }
        return new Object[] { htmlCharMap, htmlEntityMap };
    }

    public static Properties getTidyConfiguration(final String tidyConfigFilename) {
        final Properties properties = new Properties();
        boolean useDefaultConfig = true;
        if (null != tidyConfigFilename) {
            try {
                final InputStream in = new FileInputStream(tidyConfigFilename);
                try {
                    properties.load(in);
                    useDefaultConfig = false;
                } finally {
                    Streams.close(in);
                }
            } catch (final FileNotFoundException e) {
                LOG.warn("Missing JTidy configuration file \"{}\"", tidyConfigFilename);
            } catch (final IOException e) {
                LOG.warn("I/O error while reading JTidy configuration from file \"{}\"", tidyConfigFilename);
            }
        }
        if (useDefaultConfig) {
            LOG.warn("Using default JTidy configuration");
            try {
                final StringBuilder defaultConfig = new StringBuilder(512);
                defaultConfig.append("indent=no\n");
                defaultConfig.append("indent-spaces=0\n");
                defaultConfig.append("wrap=0\n");
                defaultConfig.append("markup=yes\n");
                defaultConfig.append("clean=yes\n");
                defaultConfig.append("output-xml=no\n");
                defaultConfig.append("input-xml=no\n");
                defaultConfig.append("show-warnings=yes\n");
                defaultConfig.append("numeric-entities=yes\n");
                defaultConfig.append("quote-marks=yes\n");
                defaultConfig.append("quote-nbsp=yes\n");
                defaultConfig.append("quote-ampersand=no\n");
                defaultConfig.append("break-before-br=no\n");
                defaultConfig.append("uppercase-tags=yes\n");
                defaultConfig.append("uppercase-attributes=yes\n");
                defaultConfig.append("#smart-indent=no\n");
                defaultConfig.append("output-xhtml=yes\n");
                defaultConfig.append("char-encoding=latin1");
                properties.load(new ByteArrayInputStream(String.valueOf(defaultConfig.toString()).getBytes()));
            } catch (final UnsupportedEncodingException e) {
                /*
                 * Cannot occur
                 */
                LOG.error("", e);
            } catch (final IOException e) {
                /*
                 * Cannot occur
                 */
                LOG.error("", e);
            }
        }
        return properties;
    }

    private void restore() {
        cleanUp();
        ServiceRegistry.getInstance().removeService(ConfigurationService.class);
    }

    /**
     * Gets the messages used by JTidy as an input stream.
     *
     * @param tidyMessagesFilename The file name for the file containing the tidy messages
     * @return The messages used by JTidy as an input stream
     */
    public static InputStream getTidyMessages(final String tidyMessagesFilename) {
        /*
         * Load from file
         */
        if (null != tidyMessagesFilename) {
            try {
                return new BufferedInputStream(new FileInputStream(tidyMessagesFilename), 65536);
            } catch (final IOException e) {
                LOG.warn("File providing JTidy messages could not be found: {}", tidyMessagesFilename, e);
            }
        }
        LOG.warn("Using default JTidy messages");
        final StringBuilder tidyMsgs = new StringBuilder(4096);
        tidyMsgs.append("anchor_not_unique={0} Anchor \"{1}\" already defined\n");
        tidyMsgs.append("apos_undefined=Named Entity &apos; only defined in XML/XHTML\n");
        tidyMsgs.append("attr_value_not_lcase={0} attribute value \"{1}\" for \"{2}\" must be lower case for XHTML\n");
        tidyMsgs.append("# to be translated\n");
        tidyMsgs.append("backslash_in_uri={0} URI reference contains backslash. Typo?\n");
        tidyMsgs.append("bad_argument=Warning - missing or malformed argument \"{1}\" for option \"{0}\"\n");
        tidyMsgs.append("bad_attribute_value={0} attribute \"{1}\" has invalid value \"{2}\"\n");
        tidyMsgs.append("bad_cdata_content='<' + '/' + letter not allowed here\n");
        tidyMsgs.append("bad_comment_chars=expecting -- or >\n");
        tidyMsgs.append("bad_tree=Panic - tree has lost its integrity\n");
        tidyMsgs.append("bad_xml_comment=XML comments can't contain --\n");
        tidyMsgs.append("badaccess_frames=Pages designed using frames presents problems for\\u000apeople who are either blind or using a browser that\\u000adoesn't support frames. A frames-based page should always\\u000ainclude an alternative layout inside a NOFRAMES element.\n");
        tidyMsgs.append("badaccess_missing_image_alt=The alt attribute should be used to give a short description\\u000aof an image; longer descriptions should be given with the\\u000alongdesc attribute which takes a URL linked to the description.\\u000aThese measures are needed for people using non-graphical browsers.\n");
        tidyMsgs.append("badaccess_missing_image_map=Use client-side image maps in preference to server-side image\\u000amaps as the latter are inaccessible to people using non-\\u000agraphical browsers. In addition, client-side maps are easier\\u000ato set up and provide immediate feedback to users.\n");
        tidyMsgs.append("badaccess_missing_link_alt=For hypertext links defined using a client-side image map, you\\u000aneed to use the alt attribute to provide a textual description\\u000aof the link for people using non-graphical browsers.\n");
        tidyMsgs.append("badaccess_missing_summary=The table summary attribute should be used to describe\\u000athe table structure. It is very helpful for people using\\u000anon-visual browsers. The scope and headers attributes for\\u000atable cells are useful for specifying which headers apply\\u000ato each table cell, enabling non-visual browsers to provide\\u000aa meaningful context for each cell.\n");
        tidyMsgs.append("badaccess_summary=For further advice on how to make your pages accessible\\u000asee \"{0}\". You may also want to try\\u000a\"http://www.cast.org/bobby/\" which is a free Web-based\\u000aservice for checking URLs for accessibility.\n");
        tidyMsgs.append("badchars_summary=Characters codes for the Microsoft Windows fonts in the range\\u000a128 - 159 may not be recognized on other platforms. You are\\u000ainstead recommended to use named entities, e.g. &trade; rather\\u000athan Windows character code 153 (0x2122 in Unicode). Note that\\u000aas of February 1998 few browsers support the new entities.\"\n");
        tidyMsgs.append("badform_summary=You may need to move one or both of the <form> and </form>\\u000atags. HTML elements should be properly nested and form elements\\u000aare no exception. For instance you should not place the <form>\\u000ain one table cell and the </form> in another. If the <form> is\\u000aplaced before a table, the </form> cannot be placed inside the\\u000atable! Note that one form can't be nested inside another!\n");
        tidyMsgs.append("badlayout_using_body=You are recommended to use CSS to specify page and link colors\n");
        tidyMsgs.append("badlayout_using_font=You are recommended to use CSS to specify the font and\\u000aproperties such as its size and color. This will reduce\\u000athe size of HTML files and make them easier to maintain\\u000acompared with using <FONT> elements.\n");
        tidyMsgs.append("badlayout_using_layer=The Cascading Style Sheets (CSS) Positioning mechanism\\u000ais recommended in preference to the proprietary <LAYER>\\u000aelement due to limited vendor support for LAYER.\n");
        tidyMsgs.append("badlayout_using_nobr=You are recommended to use CSS to control line wrapping.\\u000aUse \"white-space: nowrap\" to inhibit wrapping in place\\u000aof inserting <NOBR>...</NOBR> into the markup.\n");
        tidyMsgs.append("badlayout_using_spacer=You are recommended to use CSS for controlling white\\u000aspace (e.g. for indentation, margins and line spacing).\\u000aThe proprietary <SPACER> element has limited vendor support.\n");
        tidyMsgs.append("cant_be_nested={0} can''t be nested\n");
        tidyMsgs.append("coerce_to_endtag=<{0}> is probably intended as </{0}>\n");
        tidyMsgs.append("content_after_body=content occurs after end of body\n");
        tidyMsgs.append("discarding_unexpected=discarding unexpected {0}\n");
        tidyMsgs.append("doctype_after_tags=<!DOCTYPE> isn't allowed after elements\n");
        tidyMsgs.append("doctype_given={0}: Doctype given is \"{1}\"\n");
        tidyMsgs.append("dtype_not_upper_case=SYSTEM, PUBLIC, W3C, DTD, EN must be upper case\n");
        tidyMsgs.append("duplicate_frameset=repeated FRAMESET element\n");
        tidyMsgs.append("element_not_empty={0} element not empty or not closed\n");
        tidyMsgs.append("emacs_format={0}:{1,number}:{2,number}:\n");
        tidyMsgs.append("encoding_mismatch=specified input encoding ({0}) does not match actual input encoding ({1})\n");
        tidyMsgs.append("entity_in_id=no entities allowed in id attribute, discarding \"&\"\n");
        tidyMsgs.append("error=Error: \n");
        tidyMsgs.append("escaped_illegal_uri={0} escaping malformed URI reference\n");
        tidyMsgs.append("expected_equalsign={0} unexpected '=', expected attribute name\n");
        tidyMsgs.append("fixed_backslash={0} converting backslash in URI to slash\n");
        tidyMsgs.append("forced_end_anchor=Warning: <a> is probably intended as </a>\n");
        tidyMsgs.append("general_info=To learn more about JTidy see http://jtidy.sourceforge.net\\u000aPlease report bugs at http://sourceforge.net/tracker/?group_id=13153&atid=113153\\u000aHTML & CSS specifications are available from http://www.w3.org/\\u000aLobby your company to join W3C, see http://www.w3.org/Consortium\n");
        tidyMsgs.append("hello_message=Tidy (vers {0, date}) Parsing \"{1}\"\n");
        tidyMsgs.append("help_text={0} [option...] [file...]\\u000aUtility to clean up and pretty print HTML/XHTML/XML\\u000asee http://www.w3.org/People/Raggett/tidy/\\u000a\\u000aOptions for JTidy released on {1, date}\\u000aProcessing directives\\u000a---------------------\\u000a  -indent  or -i    to indent element content\\u000a  -omit    or -o    to omit optional end tags\\u000a  -wrap <column>    to wrap text at the specified <column> (default is 68)\\u000a  -upper   or -u    to force tags to upper case (default is lower case)\\u000a  -clean   or -c    to replace FONT, NOBR and CENTER tags by CSS\\u000a  -bare    or -b    to strip out smart quotes and em dashes, etc.\\u000a  -numeric or -n    to output numeric rather than named entities\\u000a  -errors  or -e    to only show errors\\u000a  -quiet   or -q    to suppress nonessential output\\u000a  -xml              to specify the input is well formed XML\\u000a  -asxml            to convert HTML to well formed XHTML\\u000a  -asxhtml          to convert HTML to well formed XHTML\\u000a  -ashtml           to force XHTML to well formed HTML\\u000a  -slides           to burst into slides on H2 elements\\u000a\\u000aCharacter encodings\\u000a-------------------\\u000a  -raw              to output values above 127 without conversion to entities\\u000a  -ascii            to use US-ASCII for output, ISO-8859-1 for input\\u000a  -latin1           to use ISO-8859-1 for both input and output\\u000a  -iso2022          to use ISO-2022 for both input and output\\u000a  -utf8             to use UTF-8 for both input and output\\u000a  -mac              to use MacRoman for input, US-ASCII for output\\u000a  -utf16le          to use UTF-16LE for both input and output\\u000a  -utf16be          to use UTF-16BE for both input and output\\u000a  -utf16            to use UTF-16 for both input and output\\u000a  -win1252          to use Windows-1252 for input, US-ASCII for output\\u000a  -big5             to use Big5 for both input and output\\u000a  -shiftjis         to use Shift_JIS for both input and output\\u000a  -language <lang>  to set the two-letter language code <lang> (for future use)\\u000a\\u000aFile manipulation\\u000a-----------------\\u000a  -config <file>    to set configuration options from the specified <file>\\u000a  -f      <file>    to write errors to the specified <file>\\u000a  -modify or -m     to modify the original input files\\u000a\\u000aMiscellaneous\\u000a-------------\\u000a  -version  or -v   to show the version of Tidy\\u000a  -help, -h or -?   to list the command line options\\u000a  -help-config      to list all configuration options\\u000a  -show-config      to list the current configuration settings\\u000a\\u000aYou can also use --blah for any configuration option blah\\u000a\\u000aInput/Output default to stdin/stdout respectively\\u000aSingle letter options apart from -f may be combined\\u000aas in:  tidy -f errs.txt -imu foo.html\\u000aFor further info on HTML see http://www.w3.org/MarkUp\n");
        tidyMsgs.append("id_name_mismatch={0} id and name attribute value mismatch\n");
        tidyMsgs.append("illegal_char=Warning: replacing illegal character code {0,number}\n");
        tidyMsgs.append("illegal_nesting={0} shouldn''t be nested\n");
        tidyMsgs.append("illegal_uri_reference={0} improperly escaped URI reference\n");
        tidyMsgs.append("inconsistent_namespace=html namespace doesn't match content\n");
        tidyMsgs.append("inconsistent_version=html doctype doesn't match content\n");
        tidyMsgs.append("inserting_tag=inserting implicit <{0}>\n");
        tidyMsgs.append("invalid_char={0,choice,0#replacing|1#discarding} invalid character code {1}\n");
        tidyMsgs.append("invalid_ncr={0,choice,0#replacing|1#discarding} invalid numeric character reference {1}\n");
        tidyMsgs.append("invalid_sgml_chars_summary=Character codes 128 to 159 (U+0080 to U+009F) are not allowed in HTML;\\u000aeven if they were, they would likely be unprintable control characters.\\u000aTidy assumed you wanted to refer to a character with the same byte value in the \\u000a{0,choice,0#specified|1#Windows-1252|2#MacRoman} encoding and replaced that reference with the Unicode equivalent.\n");
        tidyMsgs.append("invalid_utf16={0,choice,0#replacing|1#discarding} invalid UTF-16 surrogate pair (char. code {1})\n");
        tidyMsgs.append("invalid_utf16_summary=Character codes for UTF-16 must be in the range: U+0000 to U+10FFFF.\\u000aThe definition of UTF-16 in Annex C of ISO/IEC 10646-1:2000 does not allow the\\u000amapping of unpaired surrogates. For more information please refer to\\u000ahttp://www.unicode.org/unicode and http://www.cl.cam.ac.uk/~mgk25/unicode.html\n");
        tidyMsgs.append("invalid_utf8={0,choice,0#replacing|1#discarding} invalid UTF-8 bytes (char. code {1})\n");
        tidyMsgs.append("invalid_utf8_summary=Character codes for UTF-8 must be in the range: U+0000 to U+10FFFF.\\u000aThe definition of UTF-8 in Annex D of ISO/IEC 10646-1:2000 also\\u000aallows for the use of five- and six-byte sequences to encode\\u000acharacters that are outside the range of the Unicode character set;\\u000athose five- and six-byte sequences are illegal for the use of\\u000aUTF-8 as a transformation of Unicode characters. ISO/IEC 10646\\u000adoes not allow mapping of unpaired surrogates, nor U+FFFE and U+FFFF\\u000a(but it does allow other noncharacters). For more information please refer to\\u000ahttp://www.unicode.org/unicode and http://www.cl.cam.ac.uk/~mgk25/unicode.html\n");
        tidyMsgs.append("invaliduri_summary=URIs must be properly escaped, they must not contain unescaped\\u000acharacters below U+0021 including the space character and not\\u000aabove U+007E. Tidy escapes the URI for you as recommended by\\u000aHTML 4.01 section B.2.1 and XML 1.0 section 4.2.2. Some user agents\\u000ause another algorithm to escape such URIs and some server-sided\\u000ascripts depend on that. If you want to depend on that, you must\\u000aescape the URI by your own. For more information please refer to\\u000ahttp://www.w3.org/International/O-URL-and-ident.html\n");
        tidyMsgs.append("joining_attribute={0} joining values of repeated attribute \"{1}\"\n");
        tidyMsgs.append("line_column=line {0,number} column {1,number} - \n");
        tidyMsgs.append("malformed_comment=adjacent hyphens within comment\n");
        tidyMsgs.append("malformed_doctype=expected \"html PUBLIC\" or \"html SYSTEM\"\n");
        tidyMsgs.append("missing_attr_value={0} attribute \"{1}\" lacks value\n");
        tidyMsgs.append("missing_attribute={0} lacks \"{1}\" attribute\n");
        tidyMsgs.append("missing_body=Can't create slides - document is missing a body element.\n");
        tidyMsgs.append("missing_doctype=missing <!DOCTYPE> declaration\n");
        tidyMsgs.append("missing_endtag_before=missing </{0}> before {1}\n");
        tidyMsgs.append("missing_endtag_for=missing </{0}>\n");
        tidyMsgs.append("missing_imagemap={0} should use client-side image map\n");
        tidyMsgs.append("missing_quotemark={0} attribute with missing trailing quote mark\n");
        tidyMsgs.append("missing_semicolon=Warning: entity \"{0}\" doesn''t end in '';''\n");
        tidyMsgs.append("missing_semicolon_ncr=numeric character reference \"{0}\" doesn't end in \";\"\n");
        tidyMsgs.append("missing_starttag=missing <{0}>\n");
        tidyMsgs.append("missing_title_element=inserting missing 'title' element\n");
        tidyMsgs.append("needs_author_intervention=This document has errors that must be fixed before\\u000ausing HTML Tidy to generate a tidied up version.\n");
        tidyMsgs.append("nested_emphasis=nested emphasis {0}\n");
        tidyMsgs.append("nested_quotation=nested q elements, possible typo\n");
        tidyMsgs.append("newline_in_uri={0} discarding newline in URI reference\n");
        tidyMsgs.append("no_warnings=no warnings or errors were found\n");
        tidyMsgs.append("noframes_content={0} not inside ''noframes'' element\n");
        tidyMsgs.append("non_matching_endtag=replacing unexpected {0} by </{1}>\n");
        tidyMsgs.append("num_warnings={0,choice,0#no warnings|1#1 warning|1<{0,number,integer} warnings}, {1,choice,0#no errors|1#1 error|2#{1,number,integer} errors} were found!\n");
        tidyMsgs.append("obsolete_element=replacing obsolete element {0} by {1}\n");
        tidyMsgs.append("proprietary_attr_value={0} proprietary attribute value \"{1}\"\n");
        tidyMsgs.append("proprietary_attribute={0} proprietary attribute \"{1}\"\n");
        tidyMsgs.append("proprietary_element={0} is not approved by W3C\n");
        tidyMsgs.append("repeated_attribute={0} dropping value \"{1}\" for repeated attribute \"{2}\"\n");
        tidyMsgs.append("replacing_element=replacing element {0} by {1}\n");
        tidyMsgs.append("report_version={0}: Document content looks like {1}\n");
        tidyMsgs.append("slides_found={0,number} Slides found\n");
        tidyMsgs.append("suspected_missing_quote=missing quotemark for attribute value\n");
        tidyMsgs.append("tag_not_allowed_in={0} isn''t allowed in <{1}> elements\n");
        tidyMsgs.append("too_many_elements=too many {0} elements\n");
        tidyMsgs.append("too_many_elements_in=too many {0} elements in <{1}>\n");
        tidyMsgs.append("trim_empty_element=trimming empty {0}\n");
        tidyMsgs.append("unescaped_ampersand=Warning: unescaped & which should be written as &amp;\n");
        tidyMsgs.append("unescaped_element=unescaped {0} in pre content\n");
        tidyMsgs.append("unexpected_end_of_file=end of file while parsing attributes {0}\n");
        tidyMsgs.append("unexpected_endtag=unexpected </{0}>\n");
        tidyMsgs.append("unexpected_endtag_in=unexpected </{0}> in <{1}>\n");
        tidyMsgs.append("unexpected_gt={0} missing ''>'' for end of tag\n");
        tidyMsgs.append("unexpected_quotemark={0} unexpected or duplicate quote mark\n");
        tidyMsgs.append("unknown_attribute=unknown attribute \"{0}\"\n");
        tidyMsgs.append("unknown_element={0} is not recognized!\n");
        tidyMsgs.append("unknown_entity=Warning: unescaped & or unknown entity \"{0}\"\n");
        tidyMsgs.append("unknown_file={0}: can''t open file \"{1}\"\n");
        tidyMsgs.append("unknown_option=Warning - unknown option: {0}\n");
        tidyMsgs.append("unrecognized_option=unrecognized option -{0} use -help to list options\n");
        tidyMsgs.append("using_br_inplace_of=using <br> in place of {0}\n");
        tidyMsgs.append("vendor_specific_chars_summary=It is unlikely that vendor-specific, system-dependent encodings\\u000awork widely enough on the World Wide Web; you should avoid using the \\u000a{0,choice,0#specified|1#Windows-1252|2#MacRoman} character encoding, instead you are recommended to\\u000ause named entities, e.g. &trade;.\n");
        tidyMsgs.append("warning=Warning: \n");
        tidyMsgs.append("xml_attribute_value={0} has XML attribute \"{1}\"\n");
        tidyMsgs.append("xml_id_sintax=ID \"{0}\" uses XML ID syntax\n");
        return new ByteArrayInputStream(String.valueOf(tidyMsgs.toString()).getBytes());
    }

}
