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

package com.openexchange.find;

import java.util.List;
import com.openexchange.exception.OXException;
import com.openexchange.find.facet.FacetInfo;
import com.openexchange.find.spi.ModuleSearchDriver;
import com.openexchange.osgi.annotation.SingletonService;
import com.openexchange.tools.session.ServerSession;

/**
 * The {@link SearchService} is the entry point to utilize the Find API.
 *
 * @author <a href="mailto:steffen.templin@open-xchange.com">Steffen Templin</a>
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a> JavaDoc
 * @since 7.6.0
 */
@SingletonService
public interface SearchService {

    /**
     * Performs an auto-complete request for a given module.
     *
     * @param autocompleteRequest The auto-complete search request to execute
     * @param module The module in which to perform the auto-complete search
     * @param session The associated session
     * @return An {@link AutocompleteResult}. Never <code>null</code>.
     */
    AutocompleteResult autocomplete(AutocompleteRequest autocompleteRequest, Module module, ServerSession session) throws OXException;

    /**
     * Performs a search request for a given module.
     *
     * @param searchRequest The search request to execute
     * @param module The module in which to perform the search
     * @param session The associated session
     * @return A {@link SearchResult}. Never <code>null</code>.
     */
    SearchResult search(SearchRequest searchRequest, Module module, ServerSession session) throws OXException;

    /**
     * Gets the appropriate driver for given module.
     *
     * @param facetInfos The basic facet information
     * @param module The module
     * @param session The associated session
     * @return The driver
     * @throws OXException If no suitable driver exists
     */
    ModuleSearchDriver getDriver(List<FacetInfo> facetInfos, Module module, ServerSession session) throws OXException;

}