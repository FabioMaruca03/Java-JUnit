package model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static model.Type.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ET
 * @version 27/10/2020 16:08
 */
public class ShouldHavePersonData {
    /*
     Given that I have no data
     When I create a contact for John Smith
     Then the contact should have 2 facts
     And the contact's firstname should be John
     And the contact's lastname should be Smith
     And the contact creation date should be today
     And firstname fact should have a location of empty string
     And lastname fact should have a location of empty string
     And firstname fact date should be contact creation date
     And lastname fact date should be contact creation date
     And firstname fact should have 1 person in the people list
     And firstname fact should have contact John Smith in people list
     And firstname fact creation date should be today
     */
    @Test
    public void shouldCreateAContactRecord() {
        Person contact = Person.create("John", "Smith");
        final Calendar date = Calendar.getInstance();
        assertEquals(2, contact.getFactCount());
        final Fact firstNameFact = contact.getFact(FIRSTNAME).get();
        assertEquals("John", firstNameFact.getDescription());
        final Fact lastNameFact = contact.getFact(LASTNAME).get();
        assertEquals("Smith", lastNameFact.getDescription());
        final Calendar contactDateCreated = contact.getDateCreated();
        assertEquals(date.get(Calendar.YEAR), contactDateCreated.get(Calendar.YEAR));
        assertEquals(date.get(Calendar.DAY_OF_YEAR), contactDateCreated.get(Calendar.DAY_OF_YEAR));
        assertEquals("", firstNameFact.getLocation());
        assertEquals("", lastNameFact.getLocation());
        Calendar firstNameDate = firstNameFact.getDate();
        assertEquals(contactDateCreated.get(Calendar.YEAR), firstNameDate.get(Calendar.YEAR));
        assertEquals(contactDateCreated.get(Calendar.DAY_OF_YEAR), firstNameDate.get(Calendar.DAY_OF_YEAR));
        Calendar lastNameDate = lastNameFact.getDate();
        assertEquals(contactDateCreated.get(Calendar.YEAR), lastNameDate.get(Calendar.YEAR));
        assertEquals(contactDateCreated.get(Calendar.DAY_OF_YEAR), lastNameDate.get(Calendar.DAY_OF_YEAR));
        assertEquals(1, firstNameFact.getPeople().size());
        assertSame(contact, firstNameFact.getPeople().get(0));
        final Calendar factCreationDate = firstNameFact.getDateCreated();
        assertEquals(date.get(Calendar.YEAR), factCreationDate.get(Calendar.YEAR));
        assertEquals(date.get(Calendar.DAY_OF_YEAR), factCreationDate.get(Calendar.DAY_OF_YEAR));
    }

    /*
     Given that I have no data
     When I create a contact for Mary Brown in Birmingham on 2 January 2020
     Then the contact should have 2 facts
     And the contact's firstname should be Mary
     And the contact's lastname should be Brown
     And the contact creation date should be today
     And firstname fact should have a location of Birmingham
     And lastname fact should have a location of Birmingham
     And firstname fact date should be 2 January 2020
     And lastname fact date should be 2 January 2020
     And lastname fact should have 1 person in the people list
     And lastname fact should have contact John Smith in people list
     */
    @Test
    public void shouldCreateContactAndFact() {
        Calendar factDate = Calendar.getInstance();
        factDate.set(2020, Calendar.JANUARY, 2);
        Person contact = Person.create("Mary", "Brown", "Birmingham", factDate);
        final Calendar date = Calendar.getInstance();
        assertEquals(2, contact.getFactCount());
        final Fact firstNameFact = contact.getFact(FIRSTNAME).get();
        assertEquals("Mary", firstNameFact.getDescription());
        final Fact lastNameFact = contact.getFact(LASTNAME).get();
        assertEquals("Brown", lastNameFact.getDescription());
        assertEquals(date.get(Calendar.YEAR), contact.getDateCreated().get(Calendar.YEAR));
        assertEquals(date.get(Calendar.DAY_OF_YEAR), contact.getDateCreated().get(Calendar.DAY_OF_YEAR));
        assertEquals("Birmingham", firstNameFact.getLocation());
        assertEquals("Birmingham", lastNameFact.getLocation());
        assertEquals(factDate, firstNameFact.getDate());
        assertEquals(factDate, lastNameFact.getDate());
        assertEquals(1, lastNameFact.getPeople().size());
        assertSame(contact, lastNameFact.getPeople().get(0));
    }

    /*
     Given I have a contact Joseph Morrison in Chester
     And I have a contact Martha Morrison in Chester
     And Joseph Morrison is married to Martha Morrison
     And I have a contact Jacob Morrison in Chester
     When I add Jacob Morrison as a child of Joseph and Martha Morrison
     Then the contact Joseph Morrison should have 4 facts
     And the contact Martha Morrison should have 4 facts
     And the contact Jacob Morrison should have 3 facts
     */
    @Test
    public void shouldCreateAFamilyGroup() {
        final Calendar today = Calendar.getInstance();
        Person joseph = Person.create("Joseph", "Morrison", "Chester", today);
        Person martha = Person.create("Martha", "Morrison", "Chester", today);
        final Calendar marriageDate = Calendar.getInstance();
        marriageDate.set(2001, Calendar.MARCH, 15);
        Fact marriage = Fact.create(List.of(joseph, martha), COUPLE, marriageDate, "Liverpool",
                "Marriage");
        Person jacob = Person.create("Jacob", "Morrison", "Chester", today);
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(2005, Calendar.JUNE, 30);
        Fact child = Fact.create(List.of(jacob, joseph, martha), CHILD, birthdate, "Chester",
                "Biological");
        assertEquals(4, joseph.getFactCount());
        assertEquals(4, martha.getFactCount());
        assertEquals(3, jacob.getFactCount());
    }

    /* todo: ( a )
     Given that I have no data
     When I create a contact for Kennedy as their last name and a null location
     Then the contact should have 1 fact
     And no first name fact should be created
     And the contact's lastname should be Kennedy
     And lastname fact should have a location of ""
     */

    @Test
    public void shouldMatch1A() {
        Person kennedy = Person.create(null, "Kennedy");
        assertEquals(1, kennedy.getFactCount());
        assertTrue(kennedy.getFact(LASTNAME).isPresent());
        assertEquals("Kennedy", kennedy.getFact(LASTNAME).get().getDescription());
    }

    /* todo: ( c )
     Given that I have no data
     When I create a contact for Jacki as their first name
     Then the contact should have 1 fact
     And no last name fact should be created
     And the contact's firstname should be Jacki
     */

    @Test
    public void shouldMatch1C() {
        Person jacki = Person.create("Jacki", null);
        assertEquals(1, jacki.getFactCount());
        assertTrue(jacki.getFact(FIRSTNAME).isPresent());
        assertEquals("Jacki", jacki.getFact(FIRSTNAME).get().getDescription());
    }


    // todo: ( e )

    /*
     Given that I have no data
     When I create a contact without a name
     Then no contact should be created
     And I should get a message back saying "A first or last name is required"
     */

    /*
     Given that I have no data
     When I create a contact for Kennedy on 31 December 2030
     Then no contact should be created
     And I should get a message back saying "Fact date should be less than today's date"
     */

    @Test
    public void shouldMatch1E() {
        assertThrows(IllegalArgumentException.class, () -> Person.create(null, null), "A first or last name is required");
        assertThrows(IllegalArgumentException.class, () -> Person.create(null, "Kennedy", "", new GregorianCalendar(2030, Calendar.DECEMBER, 31)), "Fact date should be less than today’s date");
    }

    @Test
    public void shouldAddPerson() {
        final Model model = new Model();

        model.addPerson(null, "Kennedy", new GregorianCalendar(2030, Calendar.DECEMBER, 31), "", "");
        assertEquals(1, model.getErrors().size());
        assertEquals("Fact date should be less than today’s date", model.getErrors().elementAt(0));

        model.addPerson(null, null, new GregorianCalendar(2020, Calendar.DECEMBER, 31), "", "");
        assertEquals(2, model.getErrors().size());
        assertEquals("A first or last name is required", model.getErrors().elementAt(1));

        assertTrue(model.getFacts().isEmpty());
        assertTrue(model.getPersons().isEmpty());

        model.addPerson("null", null, new GregorianCalendar(2020, Calendar.DECEMBER, 31), "", "");
        assertFalse(model.getPersons().isEmpty());
        assertEquals(1, model.getFacts().size());
    }

}
