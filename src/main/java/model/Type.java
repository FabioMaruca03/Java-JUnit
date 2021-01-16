package model;

/**
 * @author ET
 * @version 02/11/2020 12:25
 */
public enum Type {
    FIRSTNAME(1, 1, false, false, true),
    LASTNAME(1, 1, false, false, true),
    CHILD(2, 3, true, false, true),
    COUPLE(2, 2, true, false, false),
    ADDRESS(1, 0, false, true, true);

    private final int minPeople;
    private final int maxPeople;
    private final boolean dateRequired;
    private final boolean locationRequired;
    private final boolean descriptionRequired;

    Type(int minPeople, int maxPeople,
         boolean dateRequired, boolean locationRequired, boolean descriptionRequired) {
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.dateRequired = dateRequired;
        this.locationRequired = locationRequired;
        this.descriptionRequired = descriptionRequired;
    }

    public int getMinPeople() {
        return minPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public boolean isDateRequired() {
        return dateRequired;
    }

    public boolean isLocationRequired() {
        return locationRequired;
    }

    public boolean isDescriptionRequired() {
        return descriptionRequired;
    }
}
