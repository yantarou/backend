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

package com.openexchange.conversion;

import static com.openexchange.conversion.DataExceptionMessages.TRUNCATED_MSG;
import static com.openexchange.conversion.DataExceptionMessages.TYPE_NOT_SUPPORTED_MSG;
import com.openexchange.exception.Category;
import com.openexchange.exception.DisplayableOXExceptionCode;
import com.openexchange.exception.OXException;
import com.openexchange.exception.OXExceptionFactory;
import com.openexchange.exception.OXExceptionStrings;

/**
 * Enumeration about all {@link OXException}s.
 *
 * @author <a href="mailto:marcus@open-xchange.org">Marcus Klein</a>
 */
public enum DataExceptionCodes implements DisplayableOXExceptionCode {

    /**
     * The given type of %1$s is not supported
     */
    TYPE_NOT_SUPPORTED("The given type of %1$s is not supported", TYPE_NOT_SUPPORTED_MSG, Category.CATEGORY_ERROR, 1),
    /**
     * Missing argument %1$s
     */
    MISSING_ARGUMENT("Missing argument %1$s", OXExceptionStrings.MESSAGE, Category.CATEGORY_ERROR, 2),
    /**
     * Invalid value for argument %1$s: ``%2$s''
     */
    INVALID_ARGUMENT("Invalid value for argument %1$s: \"%2$s\"", OXExceptionStrings.MESSAGE, Category.CATEGORY_ERROR, 3),
    /**
     * Unknown data source identifier: %1$s
     */
    UNKNOWN_DATA_SOURCE("Unknown data source identifier: %1$s", OXExceptionStrings.MESSAGE, Category.CATEGORY_ERROR, 4),
    /**
     * Unknown data handler identifier: %1$s
     */
    UNKNOWN_DATA_HANDLER("Unknown data handler identifier: %1$s", OXExceptionStrings.MESSAGE, Category.CATEGORY_ERROR, 5),
    /**
     * No matching type could be found for data source %1$s and data handler %2$s
     */
    NO_MATCHING_TYPE("No matching type could be found for data source %1$s and data handler %2$s", OXExceptionStrings.MESSAGE, Category.CATEGORY_ERROR, 6),
    /**
     * An error occurred: %1$s
     */
    ERROR("An error occurred: %1$s", OXExceptionStrings.MESSAGE, Category.CATEGORY_ERROR, 7),
    /**
     * The following field(s) are too long: %1$s
     */
    TRUNCATED("The following field(s) are too long: %1$s", TRUNCATED_MSG, Category.CATEGORY_TRUNCATED, 8),
    /**
     * Unable to change data. (%1$s)
     */
    UNABLE_TO_CHANGE_DATA("Unable to change data. (%1$s)", OXExceptionStrings.MESSAGE, Category.CATEGORY_USER_INPUT, 9),
    /**
     * An I/O error occurred: %1$s
     */
    IO_ERROR("An I/O error occurred: %1$s", OXExceptionStrings.MESSAGE, Category.CATEGORY_ERROR, 10),

    ;

    private final Category category;

    private final int number;

    private final String message;

    private final String displayMessage;

    private DataExceptionCodes(final String message, final String displayMessage, final Category category, final int detailNumber) {
        this.message = message;
        this.number = detailNumber;
        this.category = category;
        this.displayMessage = displayMessage;
    }

    @Override
    public String getPrefix() {
        return "CNV";
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(final OXException e) {
        return OXExceptionFactory.getInstance().equals(this, e);
    }

    /**
     * Creates a new {@link OXException} instance pre-filled with this code's attributes.
     *
     * @return The newly created {@link OXException} instance
     */
    public OXException create() {
        return OXExceptionFactory.getInstance().create(this, new Object[0]);
    }

    /**
     * Creates a new {@link OXException} instance pre-filled with this code's attributes.
     *
     * @param args The message arguments in case of printf-style message
     * @return The newly created {@link OXException} instance
     */
    public OXException create(final Object... args) {
        return OXExceptionFactory.getInstance().create(this, (Throwable) null, args);
    }

    /**
     * Creates a new {@link OXException} instance pre-filled with this code's attributes.
     *
     * @param cause The optional initial cause
     * @param args The message arguments in case of printf-style message
     * @return The newly created {@link OXException} instance
     */
    public OXException create(final Throwable cause, final Object... args) {
        return OXExceptionFactory.getInstance().create(this, cause, args);
    }

    /* (non-Javadoc)
     * @see com.openexchange.exception.DisplayableOXExceptionCode#getDisplayMessage()
     */
    @Override
    public String getDisplayMessage() {
        return displayMessage;
    }
}
