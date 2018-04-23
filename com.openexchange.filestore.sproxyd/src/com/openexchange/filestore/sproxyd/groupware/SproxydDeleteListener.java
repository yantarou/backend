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

package com.openexchange.filestore.sproxyd.groupware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import com.openexchange.database.Databases;
import com.openexchange.exception.OXException;
import com.openexchange.groupware.delete.DeleteEvent;
import com.openexchange.groupware.delete.DeleteFailedExceptionCodes;
import com.openexchange.groupware.delete.DeleteListener;
import com.openexchange.java.util.UUIDs;

/**
 * {@link SproxydDeleteListener}
 *
 * @author <a href="mailto:thorben.betten@open-xchange.com">Thorben Betten</a>
 */
public class SproxydDeleteListener implements DeleteListener {

    @Override
    public void deletePerformed(DeleteEvent event, Connection readCon, Connection writeCon) throws OXException {
        if (event.getType() == DeleteEvent.TYPE_USER) {
            deleteUserEntriesFromDB(event, writeCon);
        } else if (event.getType() == DeleteEvent.TYPE_CONTEXT) {
            deleteContextEntriesFromDB(event, writeCon);
        } else {
            return;
        }
    }

    private void deleteContextEntriesFromDB(DeleteEvent event, Connection writeCon) throws OXException {
        int contextId = event.getContext().getContextId();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            int pos = 1;
            stmt = writeCon.prepareStatement("SELECT scality_id FROM scality_filestore WHERE cid = ?");
            stmt.setInt(pos, contextId);
            rs = stmt.executeQuery();
            List<UUID> uuids = new LinkedList<UUID>();
            while (rs.next()) {
                uuids.add(UUIDs.toUUID(rs.getBytes(1)));
            }
            // TODO: Delete queired files

            Databases.closeSQLStuff(rs, stmt);
            rs = null;
            pos = 1;
            stmt = writeCon.prepareStatement("DELETE FROM scality_filestore WHERE cid = ?");
            stmt.setInt(pos, contextId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw DeleteFailedExceptionCodes.SQL_ERROR.create(e, e.getMessage());
        } catch (Exception e) {
            throw DeleteFailedExceptionCodes.ERROR.create(e, e.getMessage());
        } finally {
            Databases.closeSQLStuff(rs, stmt);
        }
    }

    private void deleteUserEntriesFromDB(DeleteEvent event, Connection writeCon) throws OXException {
        int contextId = event.getContext().getContextId();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            int pos = 1;
            stmt = writeCon.prepareStatement("SELECT scality_id FROM scality_filestore WHERE cid = ? AND user = ?");
            stmt.setInt(pos++, contextId);
            stmt.setInt(pos, event.getId());
            rs = stmt.executeQuery();
            List<UUID> uuids = new LinkedList<UUID>();
            while (rs.next()) {
                uuids.add(UUIDs.toUUID(rs.getBytes(1)));
            }
            // TODO: Delete queired files

            Databases.closeSQLStuff(rs, stmt);
            rs = null;
            pos = 1;
            stmt = writeCon.prepareStatement("DELETE FROM scality_filestore WHERE cid = ? AND user = ?");
            stmt.setInt(pos++, contextId);
            stmt.setInt(pos, event.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw DeleteFailedExceptionCodes.SQL_ERROR.create(e, e.getMessage());
        } catch (Exception e) {
            throw DeleteFailedExceptionCodes.ERROR.create(e, e.getMessage());
        } finally {
            Databases.closeSQLStuff(rs, stmt);
        }
    }

}
