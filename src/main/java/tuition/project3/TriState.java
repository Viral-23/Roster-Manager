package tuition.project3;

import tuition.project3.Constants;
import tuition.project3.Major;
import tuition.project3.NonResident;
import tuition.project3.Profile;

/**
 * This is a class that extends the student class, which allows for the creation of an TriState student object.
 * @author Viral Patel, Rohan Patel
 */
public class TriState extends NonResident {
    private String state;

    /**
     * The constructor for the TriState student object, data consists of a state code, profile, major, and credits.
     * @param state String: state code of the student.
     * @param profile Profile: profile of the student.
     * @param major Major: major of the student.
     * @param credits int: credits completed of the student.
     */
    public TriState(String state, Profile profile, Major major, int credits) {
        super(profile, major, credits);
        this.state = state;
    }

    /**
     * Calculates the tuition due of a TriState student.
     * A reduction in tuition is available given a specific state code.
     * @param creditsEnrolled: integer, credits enrolled.
     * @return double: the tuition due of the student.
     */
    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;
        double stateReduction = 0;

        if (creditsEnrolled > Constants.MAX_FULLTIME_CREDITS)
            tuition =  Constants.NONRESIDENT_TUITION + Constants.UNIVERSITY_FEE +
                    Constants.NONRESIDENT_PER_CREDIT * (creditsEnrolled - Constants.MAX_FULLTIME_CREDITS);
        else if (creditsEnrolled >= Constants.MIN_FULLTIME_CREDITS)
            tuition =  Constants.NONRESIDENT_TUITION + Constants.UNIVERSITY_FEE;
        else
            tuition =  Constants.NONRESIDENT_PER_CREDIT * creditsEnrolled + Constants.UNIVERSITY_FEE_PARTTIME;

        if (creditsEnrolled >= Constants.MIN_FULLTIME_CREDITS) {
            if (this.state.equalsIgnoreCase("NY"))
                stateReduction = 4000;
            else if (this.state.equalsIgnoreCase("CT"))
                stateReduction = 5000;
        }


        return tuition - stateReduction;
    }

    /**
     * Gets the state code of a Tristate student.
     * @return String: state code.
     */
    public String getState() {
        return this.state;
    }

    /**
     * Overrides the toString in order to display the information within the TriState student.
     * @return String: returns the desired formatting of the tristate student.
     */
    @Override
    public String toString() {
        String standing = getStanding();

        return(this.getProfile().toString() + this.getMajor() + " credits completed: " +
                this.getCredit() + " (" + standing + ")" + " (non-resident) (tri-state:" + this.state + ")");
    }

    /**
     * Gets the type of student.
     * @return String: returns Tri-State.
     */
    public String getType() {
        return "Tri-State";
    }
}