package tuition.project3;

/**
 * This is a class that extends the student class, which allows for the creation of an nonresident student object.
 * @author Viral Patel, Rohan Patel
 */
public class NonResident extends Student {
    public NonResident(Profile profile, Major major, int credits) {
        super(profile, major, credits);
    }

    /**
     * Calculates the tuition due for a nonresident student.
     * @param creditsEnrolled: integer, credits enrolled.
     * @return double: returns the calculated tuition for a nonresident student with a number of credits enrolled.
     */
    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;

        if (creditsEnrolled > Constants.MAX_FULLTIME_CREDITS)
            tuition =  Constants.NONRESIDENT_TUITION + Constants.UNIVERSITY_FEE +
                    Constants.NONRESIDENT_PER_CREDIT * (creditsEnrolled - Constants.MAX_FULLTIME_CREDITS);
        else if (creditsEnrolled >= Constants.MIN_FULLTIME_CREDITS)
            tuition =  Constants.NONRESIDENT_TUITION + Constants.UNIVERSITY_FEE;
        else
            tuition =  Constants.NONRESIDENT_PER_CREDIT * creditsEnrolled + Constants.UNIVERSITY_FEE_PARTTIME;

        return tuition;
    }

    /**
     * Checks if the student is a resident
     * @return boolean: always false because nonresidents cannot be residents.
     */
    public boolean isResident() {
        return false;
    }

    /**
     * Gets the type of student.
     * @return String: returns Non-Resident.
     */
    public String getType() {
        return "Non-Resident";
    }

    /**
     * Overrides the toString method in order to format the non-resident student object.
     * @return String: desired formatting of the non-resident student object.
     */
    @Override
    public String toString() {
        String standing = getStanding();

        return(this.getProfile().toString() + this.getMajor() + " credits completed: " +
                this.getCredit() + " (" + standing + ")" + " (non-resident)");
    }
}
