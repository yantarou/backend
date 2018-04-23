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

package com.openexchange.mailaccount.internal;

import com.openexchange.database.AbstractCreateTableImpl;

/**
 * {@link CreateMailAccountTables}
 *
 * @author <a href="mailto:marcus.klein@open-xchange.com">Marcus Klein</a>
 */
public final class CreateMailAccountTables extends AbstractCreateTableImpl {

    public CreateMailAccountTables() {
        super();
    }

    @Override
    public String[] getCreateStatements() {
        return createStatements;
    }

    @Override
    public String[] requiredTables() {
        return requiredTables;
    }

    @Override
    public String[] tablesToCreate() {
        return createdTables;
    }

    public static final String getCreateMailAccount() {  // --> com.openexchange.mailaccount.internal.CreateMailAccountTables
        return "CREATE TABLE user_mail_account ("
            + "id INT4 UNSIGNED NOT NULL,"
            + "cid INT4 UNSIGNED NOT NULL,"
            + "user INT4 UNSIGNED NOT NULL,"
            + "name VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "url VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "login VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "password VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,"
            + "primary_addr VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "personal VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,"
            + "replyTo VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,"
            + "default_flag TINYINT UNSIGNED NOT NULL DEFAULT 0,"
            + "spam_handler VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "trash VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "sent VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "drafts VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "spam VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "confirmed_spam VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "confirmed_ham VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "archive VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '',"
            + "unified_inbox TINYINT UNSIGNED DEFAULT 0,"
            + "trash_fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "sent_fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "drafts_fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "spam_fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "confirmed_spam_fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "confirmed_ham_fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci,"
            + "archive_fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '',"
            + "starttls TINYINT UNSIGNED NOT NULL DEFAULT 0,"
            + "oauth INT(10) UNSIGNED DEFAULT NULL,"
            + "disabled TINYINT UNSIGNED NOT NULL DEFAULT 0,"
            + "failed_auth_count INT4 UNSIGNED NOT NULL DEFAULT 0,"
            + "failed_auth_date BIGINT(64) NOT NULL DEFAULT 0,"
            + "PRIMARY KEY (cid, id, user),"
            + "INDEX (cid, user)"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
    }

    public static final String getCreateTransportAccount() {  // --> com.openexchange.mailaccount.internal.CreateMailAccountTables
        return "CREATE TABLE user_transport_account ("
            + "id INT4 UNSIGNED NOT NULL,"
            + "cid INT4 UNSIGNED NOT NULL,"
            + "user INT4 UNSIGNED NOT NULL,"
            + "name VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "url VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "login VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "password VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,"
            + "send_addr VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "personal VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,"
            + "replyTo VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,"
            + "default_flag TINYINT UNSIGNED NOT NULL DEFAULT 0,"
            + "unified_inbox TINYINT UNSIGNED DEFAULT 0,"
            + "starttls TINYINT UNSIGNED NOT NULL DEFAULT 0,"
            + "oauth INT(10) UNSIGNED DEFAULT NULL,"
            + "disabled TINYINT UNSIGNED NOT NULL DEFAULT 0,"
            + "failed_auth_count INT4 UNSIGNED NOT NULL DEFAULT 0,"
            + "failed_auth_date BIGINT(64) NOT NULL DEFAULT 0,"
            + "PRIMARY KEY (cid, id, user),"
            + "INDEX (cid, user)"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
    }

    public static final String getCreateMailAccountProperties() {  // --> com.openexchange.mailaccount.internal.CreateMailAccountTables
        return "CREATE TABLE user_mail_account_properties ("
            + "id INT4 UNSIGNED NOT NULL,"
            + "cid INT4 UNSIGNED NOT NULL,"
            + "user INT4 UNSIGNED NOT NULL,"
            + "name VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "value VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "PRIMARY KEY (cid, id, user, name)"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
    }

    public static final String getCreateTransportAccountProperties() {  // --> com.openexchange.mailaccount.internal.CreateMailAccountTables
        return "CREATE TABLE user_transport_account_properties ("
            + "id INT4 UNSIGNED NOT NULL,"
            + "cid INT4 UNSIGNED NOT NULL,"
            + "user INT4 UNSIGNED NOT NULL,"
            + "name VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "value VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
            + "PRIMARY KEY (cid, id, user, name)"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
    }

    private static final String getCreateSequence() {
        return "CREATE TABLE sequence_mail_service (" +
            "cid INT4 unsigned NOT NULL," +
            "id INT4 unsigned NOT NULL," +
            "PRIMARY KEY (cid)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
    }

    private static final String[] requiredTables = { "user" };

    private static final String[] createdTables = { "user_mail_account", "user_mail_account_properties", "user_transport_account", "user_transport_account_properties", "sequence_mail_service", "pop3_storage_ids", "pop3_storage_deleted" };

    private static final String[] createStatements = {

        getCreateMailAccount(),

        getCreateMailAccountProperties(),

        getCreateTransportAccount(),

        getCreateTransportAccountProperties(),

        getCreateSequence(),

        "CREATE TABLE pop3_storage_ids ("
        + "cid INT4 UNSIGNED NOT NULL,"
        + "user INT4 UNSIGNED NOT NULL,"
        + "id INT4 UNSIGNED NOT NULL,"
        + "uidl VARCHAR(128) CHARACTER SET latin1 NOT NULL,"
        + "fullname VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
        + "uid VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,"
        + "PRIMARY KEY (cid, user, id, uidl)"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci",

        "CREATE TABLE pop3_storage_deleted ("
        + "cid INT4 UNSIGNED NOT NULL,"
        + "user INT4 UNSIGNED NOT NULL,"
        + "id INT4 UNSIGNED NOT NULL,"
        + "uidl VARCHAR(128) CHARACTER SET latin1 NOT NULL,"
        + "PRIMARY KEY (cid, user, id, uidl)"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci"
    };
}
