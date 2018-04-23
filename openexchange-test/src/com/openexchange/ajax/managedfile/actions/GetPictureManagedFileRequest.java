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

package com.openexchange.ajax.managedfile.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import com.openexchange.ajax.framework.AbstractAJAXParser;

/**
 * {@link GetPictureManagedFileRequest}
 *
 * @author <a href="mailto:ioannis.chouklis@open-xchange.com">Ioannis Chouklis</a>
 */
public class GetPictureManagedFileRequest extends AbstractManagedFileRequest<GetPictureManagedFileResponse> {

    private String uid;

    /**
     * Initializes a new {@link GetPictureManagedFileRequest}.
     * 
     * @param failOnError
     */
    public GetPictureManagedFileRequest(boolean failOnError) {
        super(failOnError);
    }

    /**
     * Initializes a new {@link GetPictureManagedFileRequest}.
     */
    public GetPictureManagedFileRequest(final String uid) {
        this(true);
        this.uid = uid;
    }

    @Override
    public String getServletPath() {
        return "/ajax/image/mfile/picture";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.openexchange.ajax.framework.AJAXRequest#getMethod()
     */
    @Override
    public com.openexchange.ajax.framework.AJAXRequest.Method getMethod() {
        return Method.GET;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.openexchange.ajax.framework.AJAXRequest#getParameters()
     */
    @Override
    public com.openexchange.ajax.framework.AJAXRequest.Parameter[] getParameters() throws IOException, JSONException {
        List<Parameter> list = new ArrayList<Parameter>(1);
        list.add(new URLParameter("uid", uid));
        return list.toArray(new Parameter[list.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.openexchange.ajax.framework.AJAXRequest#getParser()
     */
    @Override
    public AbstractAJAXParser<? extends GetPictureManagedFileResponse> getParser() {
        return new GetPictureManagedFileParser(failOnError);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.openexchange.ajax.framework.AJAXRequest#getBody()
     */
    @Override
    public Object getBody() throws IOException, JSONException {
        return null;
    }

}
