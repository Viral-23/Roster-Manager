package tuition.project3;

/**
 * This is a class that allows for the creation of a Roster object, which is an array of Students.
 * @author Viral Patel, Rohan Patel
 */
public class Roster {
    private Student[] roster;
    private int size;

    /**
     * Finds the index of a student in the roster array.
     * @param student Student: The student object being searched for.
     * @return int: index of the student.
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++)
            if (roster[i].getProfile().equals(student.getProfile()))
                return i;
        return Constants.NOT_FOUND;
    } //search the given student in roster

    /**
     * Increases the size of the roster array by 4.
     */
    private void grow() {

        Student[] temp = new Student[size + 4];
        for (int i = 0; i < size; i++)
            temp[i] = roster[i];

        roster = temp;
    } //increase the array capacity by 4

    /**
     * Adds a new student to the roster.
     * If the roster does not have enough space, automatically increases size of the roster.
     * @param student Student: the student object being added to the roster.
     * @return boolean: true if adding was successful, false otherwise.
     */
    public boolean add(Student student) {

        if (contains(student)) {
            return false;
        }

        if (roster == null)
            grow();

        if (roster.length == size)
            grow();

        roster[size] = student;
        size++;
        return true;
    } //add student to end of array

    /**
     * Removes a student from the roster.
     * @param student Student: the student object being removed from the roster.
     * @return boolean: true if the student was removed, false otherwise.
     */
    public boolean remove(Student student) {

        if (!contains(student)) {
            return false;
        }

        int index = find(student);

        for (int i = index; i < size - 1; i++) {
            roster[i] = roster[i + 1];
        }
        roster[size - 1] = null; // remove reference to last student
        size--;
        return true;
    }//maintain the order after remove

    /**
     * Checks if the student is in the roster.
     * @param student Student: the student being searched for.
     * @return boolean: true if the student is in the roster, false otherwise.
     */
    public boolean contains(Student student) {

        if (find(student) == -1)
            return false;
        return true;
    } //if the student is in roster

    /**
     * Provides the student roster sorted by profile in the form of a string.
     * @return String: returns the string containing the entire student roster.
     */
    public String print() {

        if (size == 0) {
            return "Student roster is empty!";
        }
        String ret = "* Student roster sorted by last name, first name, DOB **";

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int profileComparison = roster[j].getProfile().compareTo(roster[j + 1].getProfile());

                if (profileComparison > 0) {
                    Student temp = roster[j];
                    roster[j] = roster[j + 1];
                    roster[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            String temp ="\n" + roster[i].toString();
            ret += temp;
        }

        ret += "\n* end of roster **";

        return ret;
    } //print roster sorted by profiles

    /**
     * Provides the student roster sorted by school major in the form of a string.
     * @return String: returns the string containing the entire student roster.
     */
    public String printBySchoolMajor() {

        if (size == 0) {
            return "Student roster is empty!";
        }

        String ret = "* Student roster sorted by school, major **";

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (roster[j].getMajor().compare(roster[j + 1].getMajor()) > 0) {
                    Student temp = roster[j];
                    roster[j] = roster[j + 1];
                    roster[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            String temp = "\n" + roster[i].toString();
            ret += temp;
        }

        ret += "\n* end of roster **";

        return ret;

    } //print roster sorted by school major

    /**
     * Provides the student roster sorted by standing in the form of a string.
     * @return String: returns the string containing the entire student roster.
     */
    public String printByStanding() {

        if (size == 0) {
            return "Student roster is empty!";
        }

        String ret = "* Student roster sorted by standing **";

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (roster[j].compareTo(roster[j + 1]) > 0) {
                    Student temp = roster[j];
                    roster[j] = roster[j + 1];
                    roster[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            String temp = "\n" + roster[i].toString();
            ret += temp;
        }
        ret += "\n* end of roster **";

        return ret;

    } //print roster sorted by standing

    /**
     * Changes the major of a student if it is valid.
     * @param input String: the console input, contains information about the student and the desired change in major.
     * @return String: returns the String containing the information on what happened when executing the command.
     */
    public String changeMajor(String[] input) {

        Date dob = new Date(input[3]);
        Profile profile = new Profile(input[2], input[1], dob);
        Student student = new NonResident(profile, null, Constants.NOT_FOUND);

        if (!contains(student)) {
            return student.getProfile().toString() + " is not in the roster.";
        }


        int index = find(student);
        if (roster[index].getMajor().getMajorName().equals(input[4])) {
            return (student.getProfile().toString() + " Major is already " + input[4]);
        }

        roster[index].updateMajor(input[4]);
        return (student.getProfile().toString() + " major changed to " + input[4]);
    }

    /**
     * Prints the roster filtered by school name.
     * @param schoolName String: name of school.
     * @return String: returns the list of students filtered by a certain school specified by the input parameter
     * schoolName in a String.
     */
    public String filterBySchool(String schoolName) {

        if (size == 0) {
            return "Student roster is empty!";
        }

        String ret = "* Student roster sorted by school **";

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int profileComparison = roster[j].getProfile().compareTo(roster[j + 1].getProfile());

                if (profileComparison > 0) {
                    Student temp = roster[j];
                    roster[j] = roster[j + 1];
                    roster[j + 1] = temp;
                }
            }
        }

        int newSize = 0;
        Student[] temp = new Student[size];
        for (int i = 0; i < size; i++) {
            if (roster[i].getMajor().getSchoolName().equalsIgnoreCase(schoolName)) {
                temp[newSize] = roster[i];
                newSize++;
            }
        }

        for (int i = 0; i < newSize; i++) {
            String temporary = "\n" + temp[i].getProfile().toString() + temp[i].getMajor().toString() +
                    " credits completed: " + temp[i].getCredit() + " (" + roster[i].getStanding() + ")";

            ret += temporary;
        }

        ret += "\n* end of roster **";

        return ret;
        }

    /**
     * Awards scholarship to a student
     * Checks if there is a valid number of arguments and if the enrolled student is in the roster..
     * @param input String: console input which has the information of the student and desired scholarship award.
     * @param enrollStudents Enrollment: the Enrollment array that contains the enrolled students.
     * @return String: the status message when attempting to award a scholarship to a student.
     */
    public String awardScholarship(String[] input, Enrollment enrollStudents) {
        if (input.length == 5) {
            try {
                Date dob = new Date(input[3]);
                Profile profile = new Profile(input[2], input[1], dob);
                Student student = new Resident(Constants.NOT_FOUND, profile, null, Constants.NOT_FOUND);

                if (!contains(student)) {
                    return (profile.toString() + " is not in the roster.");
                }
            } catch (RuntimeException e) {
                return "Invalid input";
            }

        }
        if (input.length < 5) {
            return ("Missing data in line command.");
        }

        if (input.length > 5)
            return ("Invalid input");

        Date dob = new Date(input[3]);
        Profile profile = new Profile(input[2], input[1], dob);
        Student student = new Resident(Constants.NOT_FOUND, profile, null, Constants.NOT_FOUND);
        int scholarshipAmount;
        try {
            scholarshipAmount = Integer.valueOf(input[4]);
        } catch (NumberFormatException e) {
            return ("Amount is not an integer.");
        }
        if (contains(student)) {
            if (roster[find(student)].isResident()) {
                EnrollStudent enrollStudent = new EnrollStudent(profile, Constants.NOT_FOUND);
                if (enrollStudents.getEnrollStudent(enrollStudent) != null) {
                    EnrollStudent checkPartTime = enrollStudents.getEnrollStudent(enrollStudent);
                    if (checkPartTime.getCreditsEnrolled() < Constants.MIN_FULLTIME_CREDITS) {
                        return (profile.toString() +
                                " part time student is not eligible for the scholarship.");
                    }
                }

                Resident resident = new Resident(0, roster[find(student)].getProfile(),
                        roster[find(student)].getMajor(), roster[find(student)].getCredit());
                if(resident.updateScholarship(scholarshipAmount)) {
                    resident.updateScholarship(scholarshipAmount);
                    roster[find(student)] = resident;
                    return (profile.toString() + ": scholarship amount updated");
                }
                else {
                    return (scholarshipAmount + ": invalid amount");
                }
            }
            else
                return (student.getProfile().toString() +
                        " (" + roster[find(student)].getType() + ") is not eligible for the scholarship.");
        }

        return "invalid input";
    }

    /**
     * Gets the student object from the roster.
     * @param student Student: the student object being searched for, usually contains just the profile.
     * @return Student: returns the actual student object within the roster, otherwise returns null if not found.
     */
    public Student getStudent(Student student) {
        if (contains(student))
            return roster[find(student)];

        return null;
    }

    /**
     * Updates the credits of a student.
     * @param student Student: student whose credits are being updated.
     * @param creditsCompleted int: the credits being added.
     */
    public void updateCredits(Student student, int creditsCompleted) {
        if (contains(student))
            roster[find(student)].setCredit(roster[find(student)].getCredit() + creditsCompleted);
    }

    /**
     * Stores the students eligible for graduation (>= 120 credits) in a string.
     * @return String: returns the list of students eligible for graduation stored in a String.
     */
    public String PrintEligibleForGraduation() {
        String ret = "";
        for (int i = 0; i < size; i++) {
            if (roster[i].getCredit() >= Constants.ELIGIBLE_FOR_GRADUATION) {
                ret += ( "\n" + roster[i].toString());
            }
        }
        if (ret.equals(""))
            return ("No students eligible for graduation.");

        return ("** list of students eligible for graduation **\n" + ret);
    }

}
