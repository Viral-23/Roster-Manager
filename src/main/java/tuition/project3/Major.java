package tuition.project3;

/**
 * This is an enum class that is a list of possible majors a Student could have.
 * @author Viral Patel, Rohan Patel
 */
public enum Major {
    CS("01:198", "CS", "SAS"),
    MATH("01:640", "MATH", "SAS"),
    EE("14:332", "EE", "SOE"),
    ITI("04:547", "ITI", "SC&I"),
    BAIT("33:136", "BAIT", "RBS");

    private final String majorCode;
    private final String majorName;
    private final String schoolName;

    /**
     * Constructor for major, consists of major code, major name, and school name.
     * @param majorCode String: major code of the student.
     * @param majorName String: major name of the student.
     * @param schoolName String: school name of the student.
     */
    Major(String majorCode, String majorName, String schoolName) {
        this.majorCode = majorCode;
        this.majorName = majorName;
        this.schoolName = schoolName;
    }

    /**
     * Gets the major code of a student.
     * @return String: returns major code.
     */
    public String getMajorCode() {
        return majorCode;
    }

    /**
     * Gets the major name of a student.
     * @return String: returns major name.
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * Gets the school name of a student.
     * @return String: returns school name.
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Overrides the toString method, in order to format the information a major object consists of.
     * @return String: returns the desired formatting of the major object.
     */
    @Override
    public String toString() {
       return(" (" + this.majorCode + " " + this.majorName + " " + this.schoolName + ")");
    }

    /**
     * Searches for a major based on the name of a major.
     * @param majorName: name of the major.
     * @return Major: returns major based on the major name.
     */
    public static Major searchMajor(String majorName) {
        switch(majorName.toUpperCase()) {
            case "CS":
                return Major.CS;
            case "MATH":
                return Major.MATH;
            case "EE":
                return Major.EE;
            case "ITI":
                return Major.ITI;
            case "BAIT":
                return Major.BAIT;
            default:
                return null;
        }
    }

    /**
     * Checks if the school name is valid.
     * @param schoolName: name of the school.
     * @return boolean: returns true if it is within the list of majors, false otherwise.
     */
    public static boolean searchSchool(String schoolName) {
        schoolName = schoolName.toUpperCase();
        if (schoolName.equals("SAS") || schoolName.equals("SOE") || schoolName.equals("SC&I")
        || schoolName.equals("RBS"))
            return true;
        return false;
    }

    /**
     * Determines the precedence of majors, useful for sorting.
     * CS would be printed first while BAIT would be printed last.
     * @param major: the major of the student.
     * @return int: returns the number corresponding to the precedence of the major.
     */
    private int precedence(Major major) {
        switch(major) {
            case CS:
                return 1;
            case MATH:
                return 2;
            case EE:
                return 3;
            case ITI:
                return 4;
            case BAIT:
                return 5;
            default:
                return -1;
        }
    }

    /**
     * compares two majors based on the precedence.
     * @param major: major of the student.
     * @return int: returns 1 if greater, 0 if equal, -1 if less.
     */
    public int compare(Major major) {

        int schoolComparison = this.schoolName.compareTo(major.schoolName);
        if (schoolComparison != 0)
            return schoolComparison;

        int value1 = precedence(this);
        int value2 = precedence(major);

        if (value1 == value2)
            return 0;
        else if (value1 > value2)
            return 1;
        else
            return -1;
    }
}
