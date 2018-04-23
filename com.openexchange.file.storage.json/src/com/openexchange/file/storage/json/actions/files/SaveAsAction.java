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

package com.openexchange.file.storage.json.actions.files;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.openexchange.ajax.requesthandler.AJAXRequestResult;
import com.openexchange.exception.OXException;
import com.openexchange.file.storage.AbstractFileFieldHandler;
import com.openexchange.file.storage.File;
import com.openexchange.file.storage.File.Field;
import com.openexchange.file.storage.FileStorageFileAccess;
import com.openexchange.file.storage.composition.IDBasedFileAccess;
import com.openexchange.file.storage.meta.FileFieldSet;
import com.openexchange.groupware.attach.AttachmentBase;
import com.openexchange.groupware.attach.AttachmentField;
import com.openexchange.groupware.attach.AttachmentMetadata;
import com.openexchange.groupware.attach.util.GetSwitch;
import com.openexchange.groupware.ldap.User;
import com.openexchange.groupware.userconfiguration.UserConfiguration;
import com.openexchange.java.Streams;
import com.openexchange.tools.session.ServerSession;

/**
 * {@link SaveAsAction}
 *
 * @author <a href="mailto:francisco.laguna@open-xchange.com">Francisco Laguna</a>
 */
public class SaveAsAction extends AbstractWriteAction {

    @Override
    public AJAXRequestResult handle(InfostoreRequest request) throws OXException {
        request.require(Param.FOLDER_ID, Param.ATTACHED_ID, Param.MODULE, Param.ATTACHMENT).requireFileMetadata();

        int folderId = Integer.parseInt(request.getFolderId());
        int attachedId = request.getAttachedId();
        int moduleId = request.getModule();
        int attachment = request.getAttachment();

        final File file = request.getFile();
        final List<Field> sentColumns = request.getSentColumns();

        AttachmentBase attachments = request.getAttachmentBase();
        IDBasedFileAccess fileAccess = request.getFileAccess();

        final ServerSession session = request.getSession();

        final User user = session.getUser();
        final UserConfiguration userConfiguration = session.getUserConfiguration();

        final AttachmentMetadata att = attachments.getAttachment(
            session, folderId,
            attachedId,
            moduleId,
            attachment,
            session.getContext(),
            user,
            userConfiguration);

        final FileFieldSet fileSet = new FileFieldSet();
        final GetSwitch attGet = new GetSwitch(att);

        File.Field.forAllFields(new AbstractFileFieldHandler() {

            @Override
            public Object handle(final Field field, final Object... args) {

                if (sentColumns.contains(field)) {
                    return null; // SKIP
                }

                // Otherwise copy from attachment

                final AttachmentField matchingAttachmentField = getMatchingAttachmentField(field);
                if (matchingAttachmentField == null) {
                    return null; // Not a field to copy
                }

                final Object value = matchingAttachmentField.doSwitch(attGet);
                field.doSwitch(fileSet, file, value);

                return null;
            }

        });

        file.setId(FileStorageFileAccess.NEW);
        InputStream fileData = attachments.getAttachedFile(session, folderId, attachedId, moduleId, attachment, session.getContext(), user, userConfiguration);
        try {
            /*
             * save attachment in storage, ignoring potential warnings
             */
            List<Field> modifiedColumns = null != sentColumns ? new ArrayList<Field>(sentColumns) : new ArrayList<Field>();
            modifiedColumns.add(Field.FILENAME);
            modifiedColumns.add(Field.FILE_SIZE);
            modifiedColumns.add(Field.FILE_MIMETYPE);
            modifiedColumns.add(Field.TITLE);
            modifiedColumns.add(Field.DESCRIPTION);
            String newID = fileAccess.saveDocument(file, fileData, FileStorageFileAccess.UNDEFINED_SEQUENCE_NUMBER, modifiedColumns, false, true, false);
            AJAXRequestResult result = new AJAXRequestResult(newID, new Date(file.getSequenceNumber()));
            List<OXException> warnings = fileAccess.getAndFlushWarnings();
            if (null != warnings && 0 < warnings.size()) {
                result.addWarnings(warnings);
            }
            return result;
        } finally {
            Streams.close(fileData);
        }
    }

    protected AttachmentField getMatchingAttachmentField(final File.Field fileField) {
        switch(fileField) {
        case FILENAME : return AttachmentField.FILENAME_LITERAL;
        case FILE_SIZE : return AttachmentField.FILE_SIZE_LITERAL;
        case FILE_MIMETYPE : return AttachmentField.FILE_MIMETYPE_LITERAL;
        case TITLE : return AttachmentField.FILENAME_LITERAL;
        case DESCRIPTION : return AttachmentField.COMMENT_LITERAL;
        default: return null;
        }
    }

}
