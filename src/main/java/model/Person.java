package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import static java.util.List.of;
import static model.Type.*;

/**
 * @author ET
 * @version 31/10/2020 16:57
 */
public class Person {
    private final ArrayList<Fact> factList = new ArrayList<>();
    private final Calendar dateCreated;

    private Person() {
        dateCreated = Calendar.getInstance();
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void addFact(Fact fact) {
        factList.add(fact);
    }

    public int getFactCount() {
        return factList.size();
    }

    public Optional<Fact> getFact(Type type) {
        return factList.stream().filter(fact -> fact.getType().equals(type)).findFirst();
    }

    public static Person create(String firstName, String lastName) {
        return create(firstName,  lastName, "", Calendar.getInstance());
    }

    public static Person create(String firstName, String lastName, String address, Calendar factDate) {
        final Person person = new Person();
        if (address == null) address = "";
        if (factDate.after(Calendar.getInstance())) throw new IllegalArgumentException("Fact date should be less than today’s date");
        if (firstName == null && lastName == null) throw new IllegalArgumentException("A first or last name is required");
        if (firstName != null) person.addFact(new Fact(of(person), FIRSTNAME, factDate, address, firstName));
        if (lastName != null) person.addFact(new Fact(of(person), LASTNAME, factDate, address, lastName));
        return person;
    }
}
