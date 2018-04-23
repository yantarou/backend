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
 *     Copyright (C) 2017-2020 OX Software GmbH
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

package com.openexchange.mailfilter.properties;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.openexchange.exception.OXException;
import com.openexchange.mailfilter.exceptions.MailFilterExceptionCode;

/**
 * {@link CredentialSource}
 *
 * @author <a href="mailto:ioannis.chouklis@open-xchange.com">Ioannis Chouklis</a>
 */
public enum CredentialSource {
    /**
     * Full login (incl. context part) name and password are used from the current session
     */
    SESSION_FULL_LOGIN("session-full-login"),
    /**
     * lLgin name and password are used from the current session
     */
    SESSION("session"),
    /**
     * The login name is taken from the field imapLogin of the current
     * user the password is taken from the current session
     */
    IMAP_LOGIN("imapLogin"),
    /**
     * Use the primary mail address of the user and the password from the session
     */
    MAIL("mail");

    private static final Map<String, CredentialSource> MAP;
    static {
        ImmutableMap.Builder<String, CredentialSource> b = ImmutableMap.builder();
        for (CredentialSource credentialSource : CredentialSource.values()) {
            b.put(credentialSource.name, credentialSource);
        }
        MAP = b.build();
    }

    public final String name;

    /**
     * Initialises a new {@link CredentialSource}.
     * 
     * @param name
     */
    private CredentialSource(final String name) {
        this.name = name;
    }

    /**
     * The name of the {@link CredentialSource}
     * 
     * @param name The name of the {@link CredentialSource} as string
     * @return The {@link CredentialSource}
     * @throws OXException if an invalid {@link CredentialSource} is requested
     */
    public static CredentialSource credentialSourceFor(String name) throws OXException {
        CredentialSource credentialSource = MAP.get(name);
        if (credentialSource == null) {
            throw MailFilterExceptionCode.NO_VALID_CREDSRC.create();
        }
        return credentialSource;
    }
}
