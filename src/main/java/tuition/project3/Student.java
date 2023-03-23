package tuition.project3;

/**
 * This is an abstract class that allows for the creation of a student object.
 * @author Viral Patel, Rohan Patel
 */

public abstract class Student implements Comparable<Student> {
    private Profile profile;
    private Major major;
    private int creditCompleted;

    /**
     * Constructor for the Student object.
     * @param profile object: consists of Name and DOB.
     * @param major enum object: 5 different options.
     * @param credits int: credits completed by the student.
     */
    public Student(Profile profile, Major major, int credits) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = credits;
    }

    /**
     * Updates the major of a student.
     * Major is searched for using the searchMajor method.
     * @param majorName string, used to search for a major in the enum class.
     * @return boolean: returns true if the major is successfully updated, otherwise returns false.
     */
    public boolean updateMajor(String majorName) {
        Major major = Major.searchMajor(majorName);
        if (major != null) {
            this.major = major;
            return true;
        }
        return false;
    }

    /**
     * Set the credits completed of a student object.
     * @param creditCompleted integer used to update the credits completed of the student object.
     */
    public void setCredit(int creditCompleted) {
        this.creditCompleted = creditCompleted;
    }

    /**
     * Gets the profile of the student object.
     * @return Profile: returns the profile object.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Gets the major of the student object.
     * @return Major: returns the major object.
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Gets the standing of the student based on credits completed.
     * The options are Freshman, Sophomore, Junior, or Senior.
     * @return String: returns the standing
     */
    public String getStanding() {
        String standing = "";
        if (this.creditCompleted < 30)
            standing = "Freshman";
        else if (this.creditCompleted < 60)
            standing = "Sophomore";
        else if (this.creditCompleted < 90)
            standing = "Junior";
        else
            standing = "Senior";

        return standing;
    }

    /**
     * Gets the credit completed of the student object.
     * @return integer: returns the credit completed.
     */
    public int getCredit() {
        return this.creditCompleted;
    }

    /**
     * Overrides the toString method in order to format the student object in the desired manner.
     * @return String: returns the formatted String.
     */
    @Override
    public String toString() {
        String standing = getStanding();

        return(this.profile.toString() + this.major + " credits completed: " +
                this.creditCompleted + " (" + standing + ")");
    }

    /**
     * Overrides the equals method in order to determine if two students are equal.
     * Two students are equal if their profiles are the same.
     * @param obj: takes in an object and checks if it is a Student.
     *           If it is, checks if two students are equal.
     * @return boolean: true if the students are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Student) {
            Student student = (Student) obj;
            return this.equals(student.profile);
        }

        return false;
    }

    /**
     * Overrides the compareTo method in order to determine which student has precedence over another.
     * Precedence is determined by standing (in alphabetical order).
     * @param student: takes in a student object and compares it to another student object.
     * @return integer: > 0 is precedence, and = 0 means they have equal precedence.
     */
    @Override
    public int compareTo(Student student) {
        int standingComparison = this.getStanding().compareTo(student.getStanding());
        return standingComparison;
    }

    /**
     * Gets the type of student, ex: Resident
     * @return String: the type of student
     */
    public String getType() {
        return "";
    }

    /**
     * Checks if a student is study abroad.
     * @return boolean: True if study abroad, false otherwise.
     */
    public boolean getStudyAbroadStatus() {
        return false;
    }

    /**
     * Gets the state code of a student if they are a TriState student.
     * @return String: state code.
     */
    public String getState() {
        return "no state";
    }

    /**
     * Checks if a student has a valid amount of credits enrolled.
     * Uses polymorphism because the credit constraints are different in some cases (ex: International student).
     * @param creditEnrolled: integer, the credits enrolled.
     * @return boolean: returns true if the credit enrolled is within the allowed constraints, otherwise return false.
     */
    public boolean isValid(int creditEnrolled) {
        return creditEnrolled >= Constants.MIN_CREDITS && creditEnrolled <= Constants.MAX_CREDITS;
    } //polymorphism

    /**
     * Calculates the tuition due based on the credits enrolled of a student.
     * Uses polymorphism, each subclass uses a different calculation algorithm.
     * @param creditsEnrolled: integer, credits enrolled.
     * @return double: The cost of tuition.
     */
    public abstract double tuitionDue(int creditsEnrolled); //polymorphism

    /**
     * Checks if a student is a resident.
     * Uses polymorphism, each subclass returns true or false (Resident class returns true, others return false).
     * @return boolean: true if the student is a resident, false otherwise.
     */
    public abstract boolean isResident(); //polymorphism

}

