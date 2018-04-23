/*
 *
 *    OPEN-XCHANGE - "the communication and information enviroment"
 *
 *
 *    All intellectual property rights in the Software are protected by
 *    international copyright laws.
 *
 *
 *    OPEN-XCHANGE is a trademark of Netline Internet Service GmbH and all
 *    other brand and product names are or may be trademarks of, and are
 *    used to identify products or services of, their respective owners.
 *
 *    Please make sure that third-party modules and libraries are used
 *    according to their respective licenses.
 *
 *    Any modifications to this package must retain all copyright notices
 *    of the original copyright holder(s) for the original code used.
 *
 *    After any such modifications, the original code will still remain
 *    copyrighted by the copyright holder(s) or original author(s).
 *
 *
 *     Copyright (C) 1998 - 2005 Netline Internet Service GmbH
 *     mail:	                 info@open-xchange.com
 *
 *
 *     This program is free software; you can redistribute it and/or modify it
 *     under the terms of the GNU General Public License as published by the Free
 *     Software Foundation; either version 2 of the License, or (at your option)
 *     any later version.
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
 *
 */

package com.openexchange.webdav.xml.parser;

import java.util.List;
import org.jdom2.Element;
import com.openexchange.resource.ResourceGroup;
import com.openexchange.webdav.xml.XmlServlet;

/**
 * {@link ResourceGroupParser} - The WebDAV/XML resource group parser.
 *
 * @author <a href="mailto:sebastian.kauss@open-xchange.com">Sebastian Kauss</a>
 */

public class ResourceGroupParser extends DataParser {

    /**
     * Initializes a new {@link ResourceGroupParser}.
     */
    public ResourceGroupParser() {
        super();
    }

    /**
     * Parses specified resource group element into given resource group.
     *
     * @param resourceGroup The resource group to fill
     * @param eProp The resource group element to parse
     */
    public void parse(final ResourceGroup resourceGroup, final Element eProp) {
        resourceGroup.setId(getValueAsInt(eProp.getChild("uid", XmlServlet.NS)));

        parseMembers(resourceGroup, eProp.getChild("members", XmlServlet.NS));
    }

    /**
     * Parses specified resource group members element into given resource group.
     *
     * @param resourceGroup The resource group to fill
     * @param eMembers The resource group members element to parse
     */
    public void parseMembers(final ResourceGroup resourceGroup, final Element eMembers) {
        final List<?> memberList = eMembers.getChildren("memberuid", XmlServlet.NS);

        final int[] member = new int[memberList.size()];

        for (int a = 0; a < memberList.size(); a++) {
            member[a] = Integer.parseInt(((Element) memberList.get(a)).getValue());
        }

        resourceGroup.setMember(member);
    }
}
