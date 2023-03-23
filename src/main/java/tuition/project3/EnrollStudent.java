package tuition.project3;

/**
 * This is a class that allows for the creation of a EnrollStudent object.
 * @author Viral Patel, Rohan Patel
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * EnrollStudent constructor, has a profile and credits enrolled.
     * @param profile: Last name, first name, DOB.
     * @param creditsEnrolled: Integer, credits the student is enrolled.
     */
    public EnrollStudent(Profile profile, int creditsEnrolled) {
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Gets the profile of the enrolled student.
     * @return Profile: profile of the student.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Gets the credits enrolled of the enrolled student.
     * @return int: credits enrolled.
     */
    public int getCreditsEnrolled() {
        return this.creditsEnrolled;
    }

    /**
     * Updates the credits enrolled to a new desired value.
     * @param creditsEnrolled: the desired update value.
     */
    public void updateCreditsEnrolled(int creditsEnrolled) {
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Overrides the equals method, checks if two enrolled students are equal.
     * Two students are equal if their profiles are the same.
     * @param obj: takes in an object, if it is an EnrollStudent the comparison is made
     * @return boolean: true if the students are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof EnrollStudent) {
            EnrollStudent student = (EnrollStudent) obj;
            return this.getProfile().equals(student.profile);
        }

        return false;
    }

    /**
     * Overrides the toString method in order to format the EnrollStudent object.
     * @return String: the desired format in which an enrolled student is printed.
     */
    @Override
    public String toString() {
        return(this.profile.toString() + " credits enrolled: " + this.creditsEnrolled);
    }
}
