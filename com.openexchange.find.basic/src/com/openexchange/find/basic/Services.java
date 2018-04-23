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
package com.openexchange.find.basic;

import java.util.concurrent.atomic.AtomicReference;
import com.openexchange.config.ConfigurationService;
import com.openexchange.contact.ContactService;
import com.openexchange.exception.OXException;
import com.openexchange.file.storage.composition.IDBasedFileAccessFactory;
import com.openexchange.file.storage.composition.IDBasedFolderAccessFactory;
import com.openexchange.file.storage.registry.FileStorageServiceRegistry;
import com.openexchange.folderstorage.FolderService;
import com.openexchange.groupware.infostore.InfostoreSearchEngine;
import com.openexchange.mail.service.MailService;
import com.openexchange.mailaccount.MailAccountStorageService;
import com.openexchange.resource.ResourceService;
import com.openexchange.server.ServiceExceptionCode;
import com.openexchange.server.ServiceLookup;
import com.openexchange.threadpool.ThreadPoolService;

/**
 * {@link Services}
 *
 * @author <a href="mailto:steffen.templin@open-xchange.com">Steffen Templin</a>
 * @since 7.6.0
 */
public class Services {

    private static final AtomicReference<ServiceLookup> LOOKUP = new AtomicReference<ServiceLookup>();

    private Services() {
        super();
    }

    public static ContactService getContactService() throws OXException {
        return requireService(ContactService.class);
    }

    public static ResourceService getResourceService() throws OXException {
        return requireService(ResourceService.class);
    }

    public static FolderService getFolderService() throws OXException {
        return requireService(FolderService.class);
    }

    public static MailService getMailService() throws OXException {
        return requireService(MailService.class);
    }

    public static MailAccountStorageService getMailAccountStorageService() throws OXException {
        return requireService(MailAccountStorageService.class);
    }

    public static ThreadPoolService getThreadPoolService() throws OXException {
        return requireService(ThreadPoolService.class);
    }

    public static IDBasedFileAccessFactory getIdBasedFileAccessFactory() throws OXException {
        return requireService(IDBasedFileAccessFactory.class);
    }

    public static IDBasedFolderAccessFactory getIdBasedFolderAccessFactory() throws OXException {
        return requireService(IDBasedFolderAccessFactory.class);
    }

    public static ConfigurationService getConfigurationService() throws OXException {
        return requireService(ConfigurationService.class);
    }

    public static InfostoreSearchEngine getInfostoreSearchEngine() throws OXException {
        return requireService(InfostoreSearchEngine.class);
    }

    public static FileStorageServiceRegistry getFileStorageServiceRegistry() throws OXException {
        return requireService(FileStorageServiceRegistry.class);
    }

    public static void setServiceLookup(ServiceLookup lookup) {
        LOOKUP.set(lookup);
    }

    public static <T> T requireService(Class<T> clazz) throws OXException {
        T service = getServiceLookup().getService(clazz);
        if (null == service) {
            throw ServiceExceptionCode.absentService(clazz);
        }
        return service;
    }

    private static ServiceLookup getServiceLookup() {
        ServiceLookup serviceLookup = LOOKUP.get();
        if (serviceLookup == null) {
            throw new IllegalStateException("ServiceLookup was null!");
        }

        return serviceLookup;
    }

}
