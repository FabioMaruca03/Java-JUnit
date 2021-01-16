package model;

import java.util.Calendar;
import java.util.List;

/**
 * @author ET
 * @version 31/10/2020 19:43
 */
public class Fact {
    private final Calendar dateCreated;
    private final Type type;
    private final Calendar date;
    private final String location;
    private final String description;
    private final List<Person> people;

    public Fact(List<Person> people, Type type, Calendar date, String location, String description) {
        dateCreated = Calendar.getInstance();
        this.people = people;
        this.type = type;
        this.date = date;
        this.location = location;
        this.description = description;
    }


    public Calendar getDateCreated() {
        return dateCreated;
    }

    public List<Person> getPeople() {
        return people;
    }

    public Type getType() {
        return type;
    }

    public Calendar getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public boolean isValid() {
        if (type.isDateRequired() && date == null)
            return false;
        if (type.isDescriptionRequired() && description == null)
            return false;
        return !type.isLocationRequired() || location != null;
    }

    public static Fact create(List<Person> people, Type type, Calendar date, String location, String description) {
        Fact fact = new Fact(people, type,date, location, description);
        people.forEach(person -> person.addFact(fact));
        return fact;
    }
}
