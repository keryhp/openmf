package uk.ac.openmf.utils;


/**
 * @author harish
 */
public enum Frequency{
    DAILY (1),
    WEEKLY (7),
    FORTNIGHTLY (15),
    MONTHLY (30),
    THREEMONTHLY (90);

    private final int bit;

    /**
     * Creates an authority with a specific bit representation. It's important that this doesn't
     * change as it will be used in the database. The enum ordinal is less reliable as the enum may be
     * reordered or have new roles inserted which would change the ordinal values.
     *
     * @param bit the permission bit which will represent this authority in the datastore.
     */
    Frequency(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public String getFrequency() {
        return toString();
    }
}
