package tuition.project3;

/**
 * This is a class that allows for the creation of a profile object.
 * @author Viral Patel, Rohan Patel
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    /**
     * The constructor for the profile class, creates a profile given a last name, first name, and date of birth.
     * @param lastName String: last name of the student.
     * @param firstName String: first name of the student.
     * @param dateOfBirth Date: DOB of the student.
     */
    public Profile(String lastName, String firstName, Date dateOfBirth) {
        this.lname = lastName;
        this.fname = firstName;
        this.dob = dateOfBirth;
    }

    /**
     * Overrides the toString method in order to format the profile object.
     * @return String: desired formatting of the information that the profile object holds.
     */
    @Override
    public String toString() {
        return(this.fname + " " + this.lname + " " + this.dob);
    }

    /**
     * Overrides the equals method, checks if two profiles are equal
     * Profiles are equal if the last name, first name, and DOB are the same.
     * @param obj: takes in an object, if it is a Profile then compares the two.
     * @return boolean: returns true if the profiles are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            boolean lnameequals = this.lname.equalsIgnoreCase(profile.lname);
            boolean fnameequals = this.fname.equalsIgnoreCase(profile.fname);

            return lnameequals && fnameequals && this.dob.equals(profile.dob);
        }

        return false;
    }

    /**
     * Overrides the compareTo method in order to determine which profile has precedence alphabetically.
     * If the names are same, the dates of birth are compared.
     * @param profile: takes in a profile object, in order to compare with another profile object.
     * @return int: > 0 if has precedence, 0 if equal, < 0 if less.
     */
    @Override
    public int compareTo(Profile profile) {

        int lastNameComparison = this.lname.compareTo(profile.lname);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        int firstNameComparison = this.fname.compareTo(profile.fname);
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        return this.dob.compareTo(profile.dob);

    }
}
