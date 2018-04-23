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

package com.openexchange.jump.osgi;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import com.openexchange.config.ConfigurationService;
import com.openexchange.config.PropertyFilter;
import com.openexchange.jump.Endpoint;
import com.openexchange.jump.JumpService;
import com.openexchange.jump.internal.EndpointImpl;
import com.openexchange.jump.internal.JumpServiceImpl;
import com.openexchange.osgi.HousekeepingActivator;


/**
 * {@link JumpActivator}
 *
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a>
 */
public final class JumpActivator extends HousekeepingActivator {

    /**
     * Initializes a new {@link JumpActivator}.
     */
    public JumpActivator() {
        super();
    }

    @Override
    protected void startBundle() throws Exception {
        final Logger logger = org.slf4j.LoggerFactory.getLogger(JumpActivator.class);
        logger.info("Starting bundle \"com.openexchange.jump\"");
        try {
            // Collect configured end-points
            final ConfigurationService configService = getService(ConfigurationService.class);
            final String prefix = "com.openexchange.jump.endpoint.";
            final Map<String, String> cEndpoints = configService.getProperties(new PropertyFilter() {

                @Override
                public boolean accept(String name, String value) {
                    return name.startsWith(prefix);
                }
            });

            // Only keep their system name as key
            final Map<String, Endpoint> endpoints = new LinkedHashMap<String, Endpoint>(cEndpoints.size());
            final int prefixLen = prefix.length();
            for (Entry<String, String> entry : cEndpoints.entrySet()) {
                final String name = entry.getKey();
                final EndpointImpl endpoint = new EndpointImpl(name.length() > prefixLen ? name.substring(prefixLen) : name, entry.getValue());
                endpoints.put(endpoint.getSystemName(), endpoint);
            }

            // Create service instance
            final JumpServiceImpl jumpServiceImpl = new JumpServiceImpl(endpoints, context);
            // As tracker for end-points
            rememberTracker(jumpServiceImpl);
            openTrackers();
            // As service
            registerService(JumpService.class, jumpServiceImpl, null);

            logger.info("Bundle \"com.openexchange.jump\" successfully started");
        } catch (final Exception e) {
            logger.error("Failed starting bundle \"com.openexchange.jump\"");
            throw e;
        }
    }

    @Override
    protected Class<?>[] getNeededServices() {
        return new Class<?>[] { ConfigurationService.class };
    }

}
