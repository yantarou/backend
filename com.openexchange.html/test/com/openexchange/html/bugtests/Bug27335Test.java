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

package com.openexchange.html.bugtests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;
import com.openexchange.html.AbstractSanitizing;

/**
 * {@link Bug27335Test}
 *
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a>
 */
public class Bug27335Test extends AbstractSanitizing {
     @Test
     public void testFormatURL1() {
        String content = "blah http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275 blah";

        String test = getHtmlService().formatURLs(content, "aaa");

        final String regex = "<a[^>]+href=\"([^\"]+)\"[^>]+>(.*?)</a>";
        final Matcher m = Pattern.compile(regex).matcher(test);

        Assert.assertTrue("Anchor not found", m.find());

        String group1 = m.group(1);
        String group2 = m.group(2);
        Assert.assertNotNull(group1);
        Assert.assertNotNull(group2);

        Assert.assertEquals("Unexpected URL", "http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275", group1);
        Assert.assertEquals("Unexpected URL", "http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275", group2);
    }

     @Test
     public void testFormatURL2() {
        String content = "blah (http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275) blah";

        String test = getHtmlService().formatURLs(content, "aaa");

        final String regex = "<a[^>]+href=\"([^\"]+)\"[^>]+>(.*?)</a>";
        final Matcher m = Pattern.compile(regex).matcher(test);

        Assert.assertTrue("Anchor not found", m.find());

        String group1 = m.group(1);
        String group2 = m.group(2);
        Assert.assertNotNull(group1);
        Assert.assertNotNull(group2);

        Assert.assertEquals("Unexpected URL", "http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275", group1);
        Assert.assertEquals("Unexpected URL", "http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275", group2);
    }

     @Test
     public void testFormatURL3() {
        String content = "blah [http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275] blah";

        String test = getHtmlService().formatURLs(content, "aaa");

        final String regex = "<a[^>]+href=\"([^\"]+)\"[^>]+>(.*?)</a>";
        final Matcher m = Pattern.compile(regex).matcher(test);

        Assert.assertTrue("Anchor not found", m.find());

        String group1 = m.group(1);
        String group2 = m.group(2);
        Assert.assertNotNull(group1);
        Assert.assertNotNull(group2);

        Assert.assertEquals("Unexpected URL", "http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275", group1);
        Assert.assertEquals("Unexpected URL", "http://www.ox.io/blub/blab.php?foo=bar&[showUid]=1275", group2);
    }

     @Test
     public void testFormatURL4() {
        String content = "echo \"Re: http://support.open-xchange.com/~karl/hidden/itil/data/Page_1_1_1.htm (change Management)\"";

        String test = getHtmlService().formatURLs(content, "aaa");

        final String regex = "<a[^>]+href=\"([^\"]+)\"[^>]+>(.*?)</a>";
        final Matcher m = Pattern.compile(regex).matcher(test);

        Assert.assertTrue("Anchor not found", m.find());

        String group1 = m.group(1);
        String group2 = m.group(2);
        Assert.assertNotNull(group1);
        Assert.assertNotNull(group2);

        Assert.assertEquals("Unexpected URL", "http://support.open-xchange.com/~karl/hidden/itil/data/Page_1_1_1.htm", group1);
        Assert.assertEquals("Unexpected URL", "http://support.open-xchange.com/~karl/hidden/itil/data/Page_1_1_1.htm", group2);
    }
}