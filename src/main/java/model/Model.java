package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Model {
    private final ArrayList<Person> people = new ArrayList<>();
    private final ArrayList<Fact> facts = new ArrayList<>();
    private final DefaultListModel<String> errors = new DefaultListModel<>();

    public Person addPerson(String firstname, String lastName, Calendar dateOfBirth, String postCode, String addressDetails) {
        try {
            final Person person = Person.create(firstname, lastName, postCode, dateOfBirth);
            final Fact fact = Fact.create(List.of(person), Type.ADDRESS, dateOfBirth, postCode, addressDetails);
            if (fact.isValid()) {
                people.add(person);
                facts.add(fact);
                return person;
            }
        } catch (IllegalArgumentException exception) {
            errors.addElement(exception.getMessage());
        }
        return null;
    }

    public ArrayList<Person> getPersons() {
        return people;
    }

    public ArrayList<Fact> getFacts() {
        return facts;
    }

    public DefaultListModel<String> getErrors() {
        return errors;
    }
}
