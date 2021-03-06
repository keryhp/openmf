package uk.ac.openmf.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author harish
 */
public enum AppRole implements GrantedAuthority {
    ADMIN (0),
    NEW_USER (1),
    USER (2),
    FIELD_OFFICER (3),
    SUPERVISOR (4),
    ACCOUNTANT (5),
    CLIENT (6),
    BRANCH_MANAGER (7),
    GENERAL_MANAGER (8),
    HIGH_LEVEL (9);

    private final int bit;

    /**
     * Creates an authority with a specific bit representation. It's important that this doesn't
     * change as it will be used in the database. The enum ordinal is less reliable as the enum may be
     * reordered or have new roles inserted which would change the ordinal values.
     *
     * @param bit the permission bit which will represent this authority in the datastore.
     */
    AppRole(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public String getAuthority() {
        return toString();
    }
}
