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

package com.openexchange.control.console;

import java.util.List;
import java.util.Map;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import com.openexchange.control.internal.BundleNotFoundException;

/**
 * {@link ListServices} - The console handler for <code>&quot;listservices&quot;</code> command.
 *
 * @author <a href="mailto:sebastian.kauss@open-xchange.com">Sebastian Kauss</a>
 */
public final class ListServices extends AbstractConsoleHandler {

    protected String bundleName;

    /**
     * Initializes a new {@link ListServices} with specified arguments and performs {@link #listServices() list services}.
     *
     * @param args The command-line arguments
     */
    public ListServices(final String args[]) {
        try {
            init(args, true);
            listServices();
        } catch (final Exception exc) {
            final Throwable cause = exc.getCause();
            if (cause != null) {
                if (cause instanceof BundleNotFoundException) {
                    System.out.println(cause.getMessage());
                } else {
                    exc.printStackTrace();
                }
            } else {
                exc.printStackTrace();
            }
            exit();
        } finally {
            try {
                close();
            } catch (final Exception exc) {
                System.out.println("closing all connections failed: " + exc);
                exc.printStackTrace();
            }
        }
    }

    public void listServices() throws Exception {
        final ObjectName objectName = getObjectName();
        final MBeanServerConnection mBeanServerConnection = getMBeanServerConnection();
        final List<Map<String, Object>> serviceList = (List<Map<String, Object>>) mBeanServerConnection.invoke(
            objectName,
            "services",
            new Object[] {},
            new String[] {});
        for (int a = 0; a < serviceList.size(); a++) {
            final Map<String, Object> data = serviceList.get(a);
            System.out.println("service: " + data.get("service") + " registered by: " + data.get("registered_by"));
            if (data.containsKey("bundles")) {
                final List<String> usedByBundles = (List<String>) data.get("bundles");
                if (usedByBundles.size() > 0) {
                    System.out.println("used by bundles: ");
                    for (int b = 0; b < usedByBundles.size(); b++) {
                        System.out.println(" - " + usedByBundles.get(b).toString());
                    }
                }
            }
        }
    }

    public static void main(final String args[]) {
        new ListServices(args);
    }

    @Override
    protected void showHelp() {
        System.out.println("listservices (-h <jmx host> -p <jmx port> -l (optional) <jmx login> -pw (optional) <jmx password>)");
    }

    @Override
    protected void exit() {
        System.exit(1);
    }

    @Override
    protected String[] getParameter() {
        return DEFAULT_PARAMETER;
    }
}
