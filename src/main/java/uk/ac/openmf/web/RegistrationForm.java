package uk.ac.openmf.web;

import org.hibernate.validator.constraints.NotBlank;

import uk.ac.openmf.validation.Forename;
import uk.ac.openmf.validation.Surname;

/**
 * @author harish
 */
public class RegistrationForm {
    @Forename
    private String forename;
    @Surname
    private String surname;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
