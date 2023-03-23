package tuition.project3;

/**
 * This is a class that extends the student class, which allows for the creation of an resident student object.
 * @author Viral Patel, Rohan Patel
 */
public class Resident extends Student {
    private int scholarship;

    /**
     * Constructor for resident object, contains scholarship amount, profile, major, and credits completed.
     * @param scholarship int: amount of scholarship a student has.
     * @param profile Profile: profile of a student.
     * @param major Major: major of a student.
     * @param credits int: credits completed of a student.
     */
    public Resident(int scholarship, Profile profile, Major major, int credits) {
        super(profile, major, credits);
        this.scholarship = scholarship;
    }

    /**
     * Calculates the tuition due of a resident student.
     * @param creditsEnrolled: integer, credits enrolled.
     * @return double: the tuition due.
     */
    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;

        if (creditsEnrolled > Constants.MAX_FULLTIME_CREDITS)
            tuition =  Constants.RESIDENT_TUITION + Constants.UNIVERSITY_FEE +
                    Constants.RESIDENT_PER_CREDIT * (creditsEnrolled - Constants.MAX_FULLTIME_CREDITS);
        else if (creditsEnrolled >= Constants.MIN_FULLTIME_CREDITS)
            tuition =  Constants.RESIDENT_TUITION + Constants.UNIVERSITY_FEE;
        else
            tuition =  Constants.RESIDENT_PER_CREDIT * creditsEnrolled + Constants.UNIVERSITY_FEE_PARTTIME;

        if (creditsEnrolled < Constants.MIN_FULLTIME_CREDITS)
            return tuition;

        return tuition - scholarship;
    }

    /**
     * Checks if a resident is a resident.
     * @return boolean: always true.
     */
    public boolean isResident() {
        return true;
    }

    /**
     * Gets the type of student object
     * @return String: returns Resident
     */
    public String getType() {
        return "Resident";
    }

    /**
     * Updates the scholarship amount of a student if it is within the constraints.
     * @param scholarship int: scholarship amount.
     * @return boolean: true if the scholarship is updated, false otherwise.
     */
    public boolean updateScholarship(int scholarship) {
        if (scholarship <= Constants.MAX_SCHOLARSHIP && scholarship > 0) {
            this.scholarship = scholarship;
            return true;
        }

        return false;
    }

    /**
     * Overrides the toString method in order to format the data contained within the resident object.
     * @return String: the desired formatting of the resident object.
     */
    @Override
    public String toString() {
        String standing = getStanding();

        return(this.getProfile().toString() + this.getMajor() + " credits completed: " +
                this.getCredit() + " (" + standing + ")" + " (resident)");
    }
}
