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

package com.openexchange.continuation;

import java.util.UUID;
import com.openexchange.exception.OXException;

/**
 * {@link ContinuationException} - Signals an error for Continuation module.
 * <p>
 * Extends {@link OXException} by {@link #getUuid()} method.
 *
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a>
 * @since 7.6.0
 */
public final class ContinuationException extends OXException {

    private static final long serialVersionUID = -454163929764879301L;

    private final UUID uuid;

    /**
     * Initializes a new {@link ContinuationException}.
     *
     * @param uuid The UUID of associated continuation or <code>null</code>
     */
    public ContinuationException(final UUID uuid) {
        super();
        this.uuid = uuid;
    }

    /**
     * Initializes a new {@link ContinuationException}.
     *
     * @param uuid The UUID of associated continuation or <code>null</code>
     * @param cause The optional cause
     */
    public ContinuationException(final UUID uuid, Throwable cause) {
        super(cause);
        this.uuid = uuid;
    }

    /**
     * Initializes a new {@link ContinuationException}.
     *
     * @param uuid The UUID of associated continuation or <code>null</code>
     * @param code The error code number
     * @param displayMessage The display message
     * @param displayArgs The display message arguments
     */
    public ContinuationException(final UUID uuid, int code, String displayMessage, Object... displayArgs) {
        super(code, displayMessage, displayArgs);
        this.uuid = uuid;
    }

    /**
     * Initializes a new {@link ContinuationException}.
     *
     * @param uuid The UUID of associated continuation or <code>null</code>
     * @param code The error code number
     * @param displayMessage The display message
     * @param cause The optional cause
     * @param displayArgs The display message arguments
     */
    public ContinuationException(final UUID uuid, int code, String displayMessage, Throwable cause, Object... displayArgs) {
        super(code, displayMessage, cause, displayArgs);
        this.uuid = uuid;
    }

    /**
     * Gets the UUID
     *
     * @return The UUID or <code>null</code>
     */
    public UUID getUuid() {
        return uuid;
    }

}
