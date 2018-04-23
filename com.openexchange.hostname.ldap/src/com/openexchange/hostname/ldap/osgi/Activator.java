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

package com.openexchange.hostname.ldap.osgi;

import com.openexchange.caching.CacheService;
import com.openexchange.config.ConfigurationService;
import com.openexchange.exception.OXException;
import com.openexchange.groupware.notify.hostname.HostnameService;
import com.openexchange.hostname.ldap.LDAPHostnameCache;
import com.openexchange.hostname.ldap.LDAPHostnameService;
import com.openexchange.hostname.ldap.configuration.LDAPHostnameProperties;
import com.openexchange.hostname.ldap.configuration.Property;
import com.openexchange.net.ssl.SSLSocketFactoryProvider;
import com.openexchange.osgi.HousekeepingActivator;

/**
 * The activator for <i>"com.openexchange.hostname.ldap"</i> bundle
 */
public class Activator extends HousekeepingActivator {

    private static transient final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Activator.class);

    /**
     * Initializes a new {@link Activator}.
     */
    public Activator() {
        super();
    }

    @Override
    protected Class<?>[] getNeededServices() {
        return new Class<?>[] { CacheService.class, ConfigurationService.class, SSLSocketFactoryProvider.class };
    }

    @Override
    protected void startBundle() throws Exception {
        try {
            Services.setServiceLookup(this);

            ConfigurationService configService = getService(ConfigurationService.class);
            checkConfiguration(configService);
            activateCaching(configService, getService(CacheService.class));
            LDAPHostnameCache.getInstance().outputSettings();

            // Register hostname service to modify hostnames in direct links, this will also initialize the cache class
            registerService(HostnameService.class, new LDAPHostnameService(this), null);
        } catch (Throwable t) {
            LOG.error("", t);
            throw t instanceof Exception ? (Exception) t : new Exception(t);
        }

    }

    @Override
    protected void stopBundle() throws Exception {
        // Stop hostname service
        try {
            deactivateCaching();
            super.stopBundle();
            Services.setServiceLookup(null);
        } catch (final Throwable t) {
            LOG.error("", t);
            throw t instanceof Exception ? (Exception) t : new Exception(t);
        }
    }

    private void activateCaching(ConfigurationService configService, CacheService cacheService) throws OXException {
        final String cacheConfigFile = LDAPHostnameProperties.getProperty(configService, Property.cache_config_file);
        cacheService.loadConfiguration(cacheConfigFile.trim());
    }

    private void checkConfiguration(ConfigurationService configService) throws OXException {
        LDAPHostnameProperties.check(configService, Property.values(), LDAPHostnameCache.REGION_NAME);
    }

    private void deactivateCaching() {
        CacheService cacheService = getService(CacheService.class);
        if (null != cacheService) {
            try {
                cacheService.freeCache(LDAPHostnameCache.REGION_NAME);
            } catch (final OXException e) {
                LOG.error("", e);
            }
        }

    }

}
