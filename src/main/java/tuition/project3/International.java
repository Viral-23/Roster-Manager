package tuition.project3;

/**
 * This is a class that extends the student class, which allows for the creation of an international student object.
 * @author Viral Patel, Rohan Patel
 */
public class International extends Student {
    private boolean isStudyAbroad;

    /**
     * The constructor for an international student, which is a subclass of student.
     * @param isStudyAbroad boolean: true if the student is studyAbroad, false otherwise.
     * @param profile Profile: profile of the student.
     * @param major Major: major of the student.
     * @param credits int: credits of the student.
     */
    public International(boolean isStudyAbroad, Profile profile, Major major, int credits) {
        super(profile, major, credits);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Calculates the tuition due for an international student.
     * @param creditsEnrolled: integer, credits enrolled.
     * @return double: returns the tuition due.
     */
    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;

        if (this.isStudyAbroad)
            tuition = Constants.UNIVERSITY_FEE + Constants.HEALTH_INSURANCE_FEE;
        else
            tuition = Constants.INTERNATIONAL_TUITION + Constants.UNIVERSITY_FEE + Constants.HEALTH_INSURANCE_FEE;

        if (creditsEnrolled > Constants.MAX_FULLTIME_CREDITS)
            tuition += (creditsEnrolled - Constants.MAX_FULLTIME_CREDITS) * Constants.NONRESIDENT_PER_CREDIT;

        return tuition;

    }

    /**
     * Checks if an international student is a resident
     * @return boolean: always false, because international student cannot be resident.
     */
    public boolean isResident() {
        return false;
    }

    /**
     * Gets the study abroad status
     * @return boolean: true if study abroad, false otherwise.
     */
    public boolean getStudyAbroadStatus() {
        return this.isStudyAbroad;
    }

    /**
     * Overrides the toString method to format the international student object.
     * @return String: desired formatting of the international student object.
     */
    @Override
    public String toString() {
        String standing = getStanding();
        String studyAbroad = "";
        if (isStudyAbroad)
            studyAbroad = ":study abroad";
        return(this.getProfile().toString() + this.getMajor() + " credits completed: " +
                this.getCredit() + " (" + standing + ")" + " (non-resident) (international" + studyAbroad + ")");
    }

    /**
     * Checks if the credits enrolled is valid of an international student.
     * @param creditEnrolled: integer, the credits enrolled.
     * @return boolean: true if within constraints, false otherwise.
     */
    @Override
    public boolean isValid(int creditEnrolled) {
        if (isStudyAbroad)
            return creditEnrolled >= Constants.MIN_STUDYABROAD_CREDITS
                    && creditEnrolled <= Constants.MAX_STUDYABROAD_CREDITS;

        return creditEnrolled >= Constants.MIN_NONSTUDYABROAD_CREDITS
                && creditEnrolled <= Constants.MAX_CREDITS;
    } //polymorphism

    /**
     * Gets the type of student (international).
     * @return String: returns type of student object, International.
     */
    public String getType() {
        return "International";
    }

}
