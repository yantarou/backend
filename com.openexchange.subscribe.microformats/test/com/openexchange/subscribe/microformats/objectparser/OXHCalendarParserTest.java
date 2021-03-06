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

package com.openexchange.subscribe.microformats.objectparser;

import static org.junit.Assert.assertEquals;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import com.openexchange.exception.OXException;
import com.openexchange.groupware.container.Appointment;


/**
 * @author <a href="mailto:tobias.prinz@open-xchange.com">Tobias Prinz</a>
 */
public class OXHCalendarParserTest {
    protected OXHCalendarParser parser;

    protected SimpleDateFormat sdf ;

    protected String fullAppointment =
        "<div id=\"hcalendar-My-hCalendar-event\" class=\"vevent\">" +
    		"<a href=\"http://microformats.org/code/hcalendar/creator\" class=\"url\">" +
    		    "<abbr title=\"2010-02-01T13:00+01:0000\" class=\"dtstart\">February 1th 1pm</abbr>, " +
    		    "<abbr title=\"2010-02-02T14:32+01:00\" class=\"dtend\"> 2:32pm 2010</abbr> : " +
    		    "<span class=\"summary\">My hCalendar event</span> at " +
    		    "<span class=\"location\">my place, not yours</span>" +
    		 "</a>" +
    		 "<div class=\"description\">" +
    		        "This hCalendar was generated by the official creator. It still sucks, because you cannot actually see when it ends." +
    		 "</div>" +
    		 "<div class=\"tags\">Tags: " +
    		     "<a href=\"http://eventful.com/events/tags/myTag\" rel=\"tag\">myTag</a>" +
    		     "<a href=\"http://eventful.com/events/tags/myOtherTag\" rel=\"tag\"> myOtherTag</a>" +
    		     "<a href=\"http://eventful.com/events/tags/mySpecialTag\" rel=\"tag\"> mySpecialTag</a>" +
    		 "</div> " +
    		 "<p>This " +
    		     "<a href=\"http://microformats.org/wiki/hcalendar\">hCalendar event</a> brought to you by the " +
    		     "<a href=\"http://microformats.org/code/hcalendar/creator\">hCalendar Creator</a>." +
    		 "</p>" +
    	"</div>";

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat();
        this.parser = new OXHCalendarParser();
    }

    @Test
    public void testDateParsing() {
        Calendar cal = Calendar.getInstance();
        for (String dateString : new String[] { "2005-06-20", "20.06.2005", "06/20/2005", "20.6.2005 0:0:0", "2005-06-20T00:00+00:0000" }) {
            cal.setTime(OXHCalendarParser.parseDate(dateString));
            assertEquals(dateString + ": Year should match", 2005, cal.get(Calendar.YEAR));
            assertEquals(dateString + ": Month should match", Calendar.JUNE, cal.get(Calendar.MONTH));
            assertEquals(dateString + ": Day should match", 20, cal.get(Calendar.DAY_OF_MONTH));
            assertEquals(dateString + ": Hours should match", 0, cal.get(Calendar.HOUR_OF_DAY));
            assertEquals(dateString + ": Minutes should match", 0, cal.get(Calendar.MINUTE));
            assertEquals(dateString + ": Seconds should match", 0, cal.get(Calendar.SECOND));
        }
    }

    @Test
    public void testShouldParseISO8601Date() {
        Calendar cal = Calendar.getInstance();
        String dateString = "2005-06-20T13:14+02:0600";
        cal.setTime(OXHCalendarParser.parseDate(dateString));
        assertEquals(dateString + ": Year should match", 2005, cal.get(Calendar.YEAR));
        assertEquals(dateString + ": Month should match", Calendar.JUNE, cal.get(Calendar.MONTH));
        assertEquals(dateString + ": Day should match", 20, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(dateString + ": Hours should match", 13 + 2, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(dateString + ": Minutes should match", 14, cal.get(Calendar.MINUTE));
        assertEquals(dateString + ": Seconds should match", 6, cal.get(Calendar.SECOND));
    }

    @Test
    public void testShouldParseWhenStartingWithVCalendar() {
        String html = "<div id=\"hcalendar-event-title\" class=\"vevent\">" + "<abbr title=\"2010-02-01\" class=\"dtstart\">February 1th</abbr>, " + "<abbr title=\"2010-02-02\" class=\"dtend\"> 2010</abbr> " + "<span class=\"summary\">event title</span>" + "<p>This <a href=\"http://microformats.org/wiki/hcalendar\">hCalendar event</a> brought to you by the <a href=\"http://microformats.org/code/hcalendar/creator\">hCalendar Creator</a>." + "</p>" + "</div>";
    }

    @Test
    public void testShouldParseWhenStartingWithVEvent() throws OXException, ParseException {
        String html = "<span class=\"vevent\">" + "<span class=\"summary\">The microformats.org site was launched</span>" + "on <span class=\"dtstart\">2005-06-20</span>" + "at the Supernova Conference " + "in <span class=\"location\">San Francisco, CA, USA</span>." + "</span>";
        Collection<Appointment> entries = parser.parse(new StringReader(html));
        assertEquals("Should find one entry", 1, entries.size());
        Appointment app = entries.iterator().next();

        assertEquals("Location should match", "San Francisco, CA, USA", app.getLocation());
        assertEquals("Summary should match", "The microformats.org site was launched", app.getNote());
        assertEquals("Date should match", OXHCalendarParser.parseDate("2005-06-20"), app.getStartDate());
    }

    @Test
    public void testShouldParseWhenHavingNestedVCalendarAndVEvent() throws OXException {
        String html = "<div class=\"vCalendar\">" + fullAppointment + fullAppointment.replaceFirst("hcalendar-My-hCalendar-event", "hcalendar-My-other-hCalendar-event") + "</div>";
        Collection<Appointment> entries = parser.parse(new StringReader(html));
        assertEquals("Should find two entries", 2, entries.size());
    }
}
