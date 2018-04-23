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

package com.openexchange.ajax.contact.action;

import java.util.TimeZone;
import java.util.UUID;
import com.openexchange.ajax.AJAXServlet;
import com.openexchange.ajax.FinalContactConstants;
import com.openexchange.ajax.container.Response;
import com.openexchange.ajax.framework.AbstractAJAXParser;
import com.openexchange.ajax.framework.Params;
import com.openexchange.groupware.container.Contact;

/**
 * @author <a href="mailto:tobias.prinz@open-xchange.com">Tobias Prinz</a>
 */
public class GetAssociatedContactsRequest extends AbstractContactRequest<GetAssociatedContactsResponse> {

    protected UUID uuid;
    protected Contact contact;
    protected TimeZone tz;

    public GetAssociatedContactsRequest(UUID uid, TimeZone tz) {
        super();
        this.uuid = uid;
        this.tz = tz;
    }

    public GetAssociatedContactsRequest(Contact c, TimeZone tz) {
        super();
        this.contact = c;
        this.tz = tz;
    }

    @Override
    public Object getBody() {
        return null;
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public Parameter[] getParameters() {
        if (uuid != null) {
            return new Params(AJAXServlet.PARAMETER_ACTION, FinalContactConstants.ACTION_GET_ASSOCIATED.getName(), FinalContactConstants.PARAMETER_UUID.getName(), String.valueOf(uuid)).toArray();
        }

        Params params = new Params(AJAXServlet.PARAMETER_ACTION, FinalContactConstants.ACTION_GET_ASSOCIATED.getName());
        if (contact.containsUserField20()) {
            params.add(FinalContactConstants.PARAMETER_UUID.getName(), contact.getUserField20());
        } else {
            params.add(AJAXServlet.PARAMETER_FOLDERID, String.valueOf(contact.getParentFolderID()), AJAXServlet.PARAMETER_ID, String.valueOf(contact.getObjectID()));
        }

        return params.toArray();
    }

    @Override
    public AbstractAJAXParser<GetAssociatedContactsResponse> getParser() {
        return new AbstractAJAXParser<GetAssociatedContactsResponse>(true) {

            @Override
            public GetAssociatedContactsResponse createResponse(final Response response) {
                return new GetAssociatedContactsResponse(response, tz);
            }
        };
    }
}
