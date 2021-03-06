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

package com.openexchange.calendar.json.actions;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import org.json.JSONException;
import com.openexchange.ajax.AJAXServlet;
import com.openexchange.ajax.requesthandler.AJAXRequestResult;
import com.openexchange.api2.AppointmentSQLInterface;
import com.openexchange.calendar.json.AppointmentAJAXRequest;
import com.openexchange.calendar.json.AppointmentActionFactory;
import com.openexchange.exception.OXException;
import com.openexchange.groupware.calendar.AppointmentSqlFactoryService;
import com.openexchange.groupware.calendar.CalendarCollectionService;
import com.openexchange.groupware.calendar.CalendarDataObject;
import com.openexchange.groupware.calendar.OXCalendarExceptionCodes;
import com.openexchange.oauth.provider.resourceserver.annotations.OAuthAction;
import com.openexchange.server.ServiceExceptionCode;
import com.openexchange.server.ServiceLookup;
import com.openexchange.tools.session.ServerSession;

/**
 * {@link ChangeExceptionsAction}
 *
 * @author <a href="mailto:martin.herfurth@open-xchange.com">Martin Herfurth</a>
 */
@OAuthAction(AppointmentActionFactory.OAUTH_READ_SCOPE)
public class ChangeExceptionsAction extends AppointmentAction {

    /**
     * Initializes a new {@link ChangeExceptionsAction}.
     *
     * @param services
     */
    public ChangeExceptionsAction(final ServiceLookup services) {
        super(services);
    }

    @Override
    protected AJAXRequestResult perform(final AppointmentAJAXRequest req) throws OXException, JSONException {
        final int id = req.checkInt(AJAXServlet.PARAMETER_ID);
        final int inFolder = req.checkInt(AJAXServlet.PARAMETER_FOLDERID);
        final int[] columns = req.checkIntArray(AJAXServlet.PARAMETER_COLUMNS);

        final ServerSession session = req.getSession();

        final AppointmentSqlFactoryService sqlFactoryService = getService();
        if (null == sqlFactoryService) {
            throw ServiceExceptionCode.serviceUnavailable(AppointmentSqlFactoryService.class);
        }
        final AppointmentSQLInterface appointmentSql = sqlFactoryService.createAppointmentSql(session);

        Date timestamp = null;
        try {
            final CalendarDataObject master = appointmentSql.getObjectById(id, inFolder); // Check for read rights.
            timestamp = master.getLastModified();
        } catch (final SQLException e) {
            throw OXCalendarExceptionCodes.CALENDAR_SQL_ERROR.create(e, new Object[0]);
        }

        final CalendarCollectionService collection = getService(CalendarCollectionService.class);
        final CalendarDataObject[] appointments = collection.getChangeExceptionsByRecurrence(id, _appointmentFields, session);

        return new AJAXRequestResult(Arrays.asList(appointments), timestamp, "appointment");
    }

}
