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

package com.openexchange.contact.storage;

import java.sql.Connection;
import java.util.Date;
import com.openexchange.exception.OXException;
import com.openexchange.groupware.contact.helpers.ContactField;
import com.openexchange.groupware.container.Contact;
import com.openexchange.session.Session;


/**
 * {@link ContactUserStorage}
 *
 * @author <a href="mailto:jan.bauerdick@open-xchange.com">Jan Bauerdick</a>
 * @since v7.8
 */
public interface ContactUserStorage extends ContactStorage {

    /**
     * Creates a contact for a guest user
     *
     * @param contextId The context id
     * @param contact The contact
     * @param con Database connection
     * @return Id of created contact
     * @throws OXException On error
     */
    int createGuestContact(int contextId, Contact contact, Connection con) throws OXException;

    /**
     * Deletes a contact for a guest user
     *
     * @param contextId The context id
     * @param userId The internal user id
     * @param lastRead Time when the contact was last read from storage
     * @param con Database connection
     * @throws OXException On error
     */
    void deleteGuestContact(int contextId, int userId, Date lastRead, Connection con) throws OXException;

    /**
     * Updates a contact for a guest user without any checks
     *
     * @param contextId The context id
     * @param contactId The contact id
     * @param contact The updated contact
     * @param con Database connection
     * @throws OXException On error
     */
    void updateGuestContact(int contextId, int contactId, Contact contact, Connection con) throws OXException;

    /**
     * Updates a contact for a guest user
     *
     * @param session
     * @param contactId The contact id
     * @param contact The updated contact
     * @param lastRead Time when the contact was last read from storage
     * @throws OXException On error
     */
    void updateGuestContact(Session session, int contactId, Contact contact, Date lastRead) throws OXException;

    /**
     * Gets the guest's contact
     * @param contextId The context id
     * @param guestId The guest id
     * @param contactFields Fields to fill in the contact
     * @return The contact
     * @throws OXException On error
     */
    Contact getGuestContact(int contextId, int guestId, ContactField[] contactFields) throws OXException;

}
