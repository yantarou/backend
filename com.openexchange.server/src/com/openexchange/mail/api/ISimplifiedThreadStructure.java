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

package com.openexchange.mail.api;

import java.util.List;
import com.openexchange.exception.OXException;
import com.openexchange.mail.IndexRange;
import com.openexchange.mail.MailField;
import com.openexchange.mail.MailSortField;
import com.openexchange.mail.OrderDirection;
import com.openexchange.mail.dataobjects.MailMessage;
import com.openexchange.mail.search.SearchTerm;


/**
 * {@link ISimplifiedThreadStructure}
 *
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a>
 */
public interface ISimplifiedThreadStructure {

    /**
     * An <b>optional</b> convenience method that gets the messages located in given folder sorted by message thread reference. By default
     * <code>null</code> is returned assuming that mailing system does not support message thread reference, but may be overridden if it
     * does.
     * <p>
     * If underlying mailing system is IMAP, this method requires the IMAPv4 SORT extension or in detail the IMAP <code>CAPABILITY</code>
     * command should contain "SORT THREAD=ORDEREDSUBJECT THREAD=REFERENCES".
     *
     * @param folder The folder full name
     * @param includeSent <code>true</code> to include sent mails in thread; otherwise <code>false</code>
     * @param cache Whether caller allows to serve this call with possibly cached content
     * @param indexRange The optional index range
     * @param max The max. number of messages to consider
     * @param sortField The sort field applied to thread root elements
     * @param order Whether ascending or descending sort order
     * @param fields The fields to pre-fill in returned instances of {@link MailMessage}
     * @param headerNames The header names to pre-fill in returned instances of {@link MailMessage}
     * @return The thread-sorted messages or <code>null</code> if SORT is not supported by mail server
     * @throws OXException If messages cannot be returned
     */
    public List<List<MailMessage>> getThreadSortedMessages(String folder, boolean includeSent, boolean cache, IndexRange indexRange, long max, MailSortField sortField, OrderDirection order, MailField[] fields, SearchTerm<?> searchTerm) throws OXException;

}
