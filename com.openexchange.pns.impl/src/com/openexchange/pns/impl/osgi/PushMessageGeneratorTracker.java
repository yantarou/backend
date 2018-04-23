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
 *    trademarks of the OX Software GmbH. group of companies.
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

package com.openexchange.pns.impl.osgi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.slf4j.Logger;
import com.openexchange.exception.OXException;
import com.openexchange.pns.PushMessageGenerator;
import com.openexchange.pns.PushMessageGeneratorRegistry;

/**
 * {@link PushMessageGeneratorTracker} - The tracker for transports.
 *
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a>
 * @since v7.8.3
 */
public final class PushMessageGeneratorTracker implements ServiceTrackerCustomizer<PushMessageGenerator, PushMessageGenerator>, PushMessageGeneratorRegistry {

    private final ConcurrentMap<String, PushMessageGenerator> generators;
    private final BundleContext context;

    /**
     * Initializes a new {@link PushMessageGeneratorTracker}.
     */
    public PushMessageGeneratorTracker(BundleContext context) {
        super();
        this.generators = new ConcurrentHashMap<>(4, 0.9F, 1);;
        this.context = context;
    }

    @Override
    public PushMessageGenerator addingService(ServiceReference<PushMessageGenerator> reference) {
        Logger logger = org.slf4j.LoggerFactory.getLogger(PushMessageGeneratorTracker.class);

        PushMessageGenerator generator = context.getService(reference);
        if (null == generators.putIfAbsent(generator.getClient(), generator)) {
            logger.info("Successfully registered push message generator for client '{}'", generator.getClient());
            return generator;
        }

        logger.error("Failed to register push message generator for class {}. There is already such a generator for client '{}'.", generator.getClass().getName(), generator.getClient());
        context.ungetService(reference);
        return null;
    }

    @Override
    public void modifiedService(ServiceReference<PushMessageGenerator> reference, PushMessageGenerator generator) {
        // Nothing
    }

    @Override
    public void removedService(ServiceReference<PushMessageGenerator> reference, PushMessageGenerator generator) {
        Logger logger = org.slf4j.LoggerFactory.getLogger(PushMessageGeneratorTracker.class);

        if (null != generators.remove(generator.getClient())) {
            logger.info("Successfully unregistered push message generator for client '{}'", generator.getClient());
            return;
        }

        context.ungetService(reference);
    }

    @Override
    public PushMessageGenerator getGenerator(String client) throws OXException {
        if (null == client) {
            return null;
        }
        return generators.get(client);
    }

}