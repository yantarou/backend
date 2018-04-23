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

import org.apache.jsieve.SieveException;
import org.json.JSONException;
import org.json.JSONObject;
import com.openexchange.exception.OXException;
import com.openexchange.jsieve.commands.IfCommand;
import com.openexchange.jsieve.commands.Rule;
import com.openexchange.jsieve.commands.TestCommand;
import com.openexchange.mailfilter.json.ajax.json.fields.GeneralField;
import com.openexchange.mailfilter.json.ajax.json.fields.RuleField;
import com.openexchange.mailfilter.json.ajax.json.mapper.parser.CommandParser;
import com.openexchange.mailfilter.json.ajax.json.mapper.parser.CommandParserRegistry;
import com.openexchange.mailfilter.json.ajax.json.mapper.parser.TestCommandParserRegistry;
import com.openexchange.mailfilter.json.osgi.Services;
import com.openexchange.tools.session.ServerSession;

/**
 * {@link TestCommandRuleFieldMapper}
 *
 * @author <a href="mailto:ioannis.chouklis@open-xchange.com">Ioannis Chouklis</a>
 */
public class TestCommandRuleFieldMapper implements RuleFieldMapper {

    /**
     * Initialises a new {@link TestCommandRuleFieldMapper}.
     */
    public TestCommandRuleFieldMapper() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#getAttributeName()
     */
    @Override
    public RuleField getAttributeName() {
        return RuleField.test;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#isNull(com.openexchange.jsieve.commands.Rule)
     */
    @Override
    public boolean isNull(Rule rule) {
        return rule.getTestCommand() == null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#getAttribute(com.openexchange.jsieve.commands.Rule)
     */
    @Override
    public Object getAttribute(Rule rule) throws JSONException, OXException {
        JSONObject object = new JSONObject();
        if (!isNull(rule)) {
            TestCommand testCommand = rule.getTestCommand();
            if(testCommand!=null){
                String commandName = testCommand.getCommand().getCommandName();
                CommandParserRegistry<TestCommand> parserRegistry = Services.getService(TestCommandParserRegistry.class);
                CommandParser<TestCommand> parser = parserRegistry.get(commandName);
                parser.parse(object, testCommand);
            }
        }
        return object;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.openexchange.mailfilter.json.ajax.json.RuleFieldMapper#setAttribute(com.openexchange.jsieve.commands.Rule, java.lang.Object)
     */
    @Override
    public void setAttribute(Rule rule, Object attribute, ServerSession session) throws JSONException, SieveException, OXException {
        JSONObject object = (JSONObject) attribute;
        String id = object.getString(GeneralField.id.name());

        TestCommand existingTestCommand = rule.getTestCommand();
        CommandParserRegistry<TestCommand> parserRegistry = Services.getService(TestCommandParserRegistry.class);
        CommandParser<TestCommand> parser = parserRegistry.get(id);
        if (parser == null) {
            //TODO: better exception handling
            throw new JSONException("Unknown test command while creating object: " + id);
        }
        TestCommand parsedTestCommand = parser.parse(object, session);
        if (existingTestCommand != null) {
            rule.getIfCommand().setTestcommand(parsedTestCommand);
            return;
        }

        if (rule.getCommands().isEmpty()) {
            rule.addCommand(new IfCommand(parsedTestCommand));
        }
    }
}
