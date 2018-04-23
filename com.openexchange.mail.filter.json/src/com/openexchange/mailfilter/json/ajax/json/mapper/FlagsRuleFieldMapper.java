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
 *    trademarks of the OX Software GmbH. group of companies.
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
 *     Copyright (C) 2016-2020 OX Software GmbH.
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

package com.openexchange.mailfilter.json.ajax.json.mapper;

import java.util.ArrayList;
import org.apache.jsieve.SieveException;
import org.json.JSONArray;
import org.json.JSONException;
import com.openexchange.exception.OXException;
import com.openexchange.jsieve.commands.Rule;
import com.openexchange.jsieve.commands.RuleComment;
import com.openexchange.mailfilter.json.ajax.json.fields.RuleField;
import com.openexchange.tools.session.ServerSession;

/**
 * {@link FlagsRuleFieldMapper}
 *
 * @author <a href="mailto:ioannis.chouklis@open-xchange.com">Ioannis Chouklis</a>
 */
public class FlagsRuleFieldMapper implements RuleFieldMapper {

    /**
     * Initialises a new {@link FlagsRuleFieldMapper}.
     */
    public FlagsRuleFieldMapper() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#getAttributeName()
     */
    @Override
    public RuleField getAttributeName() {
        return RuleField.flags;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#isNull(com.openexchange.jsieve.commands.Rule)
     */
    @Override
    public boolean isNull(Rule rule) {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#getAttribute(com.openexchange.jsieve.commands.Rule)
     */
    @Override
    public Object getAttribute(Rule rule) throws JSONException, OXException {
        JSONArray array = new JSONArray();
        RuleComment ruleComment = rule.getRuleComment();
        if ((ruleComment != null) && (ruleComment.getFlags() != null)) {
            for (String flag : ruleComment.getFlags()) {
                array.put(flag);
            }
        }
        return array;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#setAttribute(com.openexchange.jsieve.commands.Rule, java.lang.Object)
     */
    @Override
    public void setAttribute(Rule rule, Object attribute, ServerSession session) throws JSONException, SieveException, OXException {
        JSONArray array = (JSONArray) attribute;
        ArrayList<String> list = new ArrayList<String>(array.length());
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        RuleComment ruleComment = rule.getRuleComment();
        if (null != ruleComment) {
            ruleComment.setFlags(list);
        } else {
            rule.setRuleComments(new RuleComment(list));
        }
    }
}