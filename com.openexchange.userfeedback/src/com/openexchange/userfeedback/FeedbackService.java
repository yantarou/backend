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

package com.openexchange.userfeedback;

import java.util.Map;
import com.openexchange.exception.OXException;
import com.openexchange.session.Session;
import com.openexchange.userfeedback.filter.FeedbackFilter;

/**
 * {@link FeedbackService}
 *
 * @author <a href="mailto:kevin.ruthmann@open-xchange.com">Kevin Ruthmann</a>
 * @since v7.8.4
 */
public interface FeedbackService {

    /**
     * Stores feedback data and metadata for a given user
     *
     * @param session The session of the user
     * @param feedback The feedback data
     * @param params Additional parameters that have to be considered while persisting. Has to include parameter 'type'.
     * @throws OXException if feedback couldn't be stored
     */
    void store(Session session, Object feedback, Map<String, String> params) throws OXException;

    /**
     * Exports feedback data within an {@link ExportResultConverter}. The export data becomes generated/formatted based on the {@link ExportType} defined for {@link ExportResultConverter#get(ExportType)}
     * 
     * @param ctxGroup The context group to export for
     * @param filter The filter (containing the type of report) to limit the results
     * @return {@link ExportResultConverter} wrapping the data
     * @throws OXException if an export isn't possible due to errors
     */
    ExportResultConverter export(String ctxGroup, FeedbackFilter filter) throws OXException;

    /**
     * Exports feedback data within an {@link ExportResultConverter}. The export data becomes generated/formatted based on the {@link ExportType} defined for {@link ExportResultConverter#get(ExportType)}
     * 
     * @param ctxGroup The context group to export for
     * @param filter The filter (containing the type of report) to limit the results
     * @param configuration The configuration that provides information about the export preparation
     * @return {@link ExportResultConverter} wrapping the data
     * @throws OXException if an export isn't possible due to errors
     */
    ExportResultConverter export(String ctxGroup, FeedbackFilter filter, Map<String, String> configuration) throws OXException;

    /**
     * Delete feedback data
     * 
     * @param ctxGroup The context group
     * @param filter Feedback filter to determine which feedback should be deleted
     * @throws OXException If feedback could not be deleted
     */
    void delete(String ctxGroup, FeedbackFilter filter) throws OXException;
}
