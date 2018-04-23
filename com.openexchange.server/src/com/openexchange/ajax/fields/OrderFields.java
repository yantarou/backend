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

package com.openexchange.ajax.fields;

import static com.openexchange.tools.Collections.newHashMap;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import com.openexchange.groupware.search.Order;

/**
 * Class for converting AJAX GUI order strings into an Order object and vice
 * versa.
 * @author <a href="mailto:marcus@open-xchange.org">Marcus Klein</a>
 */
public final class OrderFields {

    private static final Map<Order, String> WRITE_MAP;

    private static final Map<String, Order> PARSE_MAP;

    /**
     * Prevent instantiation.
     */
    private OrderFields() {
        super();
    }

    /**
     * Converts the order enum into the corresponding GUI string.
     * @param order order enum value.
     * @return the corresponding GUI string.
     */
    public static String write(final Order order) {
        final String retval;
        switch (order) {
        case NO_ORDER:
            retval = null;
            break;
        default:
            retval = WRITE_MAP.get(order);
        }
        return retval;
    }

    /**
     * Parses the order string of the GUI.
     * @param order order string sent by GUI.
     * @return parsed {@link Order} or <code>null</code> if the order string
     * can't be parsed.
     */
    public static Order parse(final String order) {
        final Order retval;
        if (null == order || !PARSE_MAP.containsKey(order)) {
            retval = Order.NO_ORDER;
        } else {
            retval = PARSE_MAP.get(order);
        }
        return retval;
    }

    static {
        WRITE_MAP = new EnumMap<Order, String>(Order.class);
        WRITE_MAP.put(Order.ASCENDING, "asc");
        WRITE_MAP.put(Order.DESCENDING, "desc");
        final Order[] values = Order.values();
        final Map<String, Order> tmp = newHashMap(values.length);
        for (final Order order : values) {
            tmp.put(WRITE_MAP.get(order), order);
        }
        PARSE_MAP = Collections.unmodifiableMap(tmp);
    }
}
