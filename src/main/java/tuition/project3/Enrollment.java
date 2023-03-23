package tuition.project3;

/**
 * This is a class that allows for the creation of a Enrollment object, which is an array of EnrollStudents.
 * @author Viral Patel, Rohan Patel
 */

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    /**
     * Adds a student to the enrollment, updating the credits enrolled if the student is already enrolled.
     * @param enrollStudent: the enrollStudent object being added to the EnrollmentStudent array.
     */
    public void add(EnrollStudent enrollStudent) {

        if (enrollStudents == null)
            grow();

        if (size == enrollStudents.length)
            grow();

        if (contains(enrollStudent)) {
            enrollStudents[find(enrollStudent)].updateCreditsEnrolled(enrollStudent.getCreditsEnrolled());
        }
        else {
            enrollStudents[size] = enrollStudent;
            size++;
        }

    } //add to the end of array

    /**
     * Removes a student from the enrollment, maintaining the order of the remaining students. Stores the status
     * message of the action in a String.
     * @param enrollStudent: the enrollStudent objecting being removed from the EnrollmentStudent array.
     * @return String: returns the String containing the status message.
     */
    //move the last one in the array to replace the deleting index position
    public String remove(EnrollStudent enrollStudent){
        if (!contains(enrollStudent)) {
            return (enrollStudent.getProfile().toString() + " is not enrolled.");
        }
        else {
            enrollStudents[find(enrollStudent)] = enrollStudents[size-1];
            size--;
            return (enrollStudent.getProfile().toString() + " dropped.");
        }

    }

    /**
     * Checks if the enrollment contains the specified student.
     * @param enrollStudent: the student to check for
     * @return boolean: true if the student is enrolled; false otherwise
     */
    public boolean contains(EnrollStudent enrollStudent){
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].equals(enrollStudent))
                return true;
        }
        return false;

    }

    /**
     * Searches the enrollment for the specified student.
     * @param enrollStudent: the student to search for
     * @return int: the index of the student in the enrollment, or Constants.NOT_FOUND (-1) if the student is not found
     */
    private int find(EnrollStudent enrollStudent) {
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].equals(enrollStudent))
                return i;
        }

        return Constants.NOT_FOUND;
    }

    /**
     * Stores the EnrollmentStudent array containing the enrolled students in a string.
     * @return String: returns the String containing all of the enrolled students.
     */
    public String print() {
        if (size == 0) {
            return "Enrollment is empty!";
        }

        String ret =  "** Enrollment **";
        for (int i = 0; i < size; i++) {
            String temp = "\n" + enrollStudents[i];
            ret += temp;
        }

        ret += "\n* end of enrollment *";

        return ret;
    } //print the array as is without sorting

    /**
     * Increases the size of the EnrollmentStudent array by 1
     */
    private void grow() {
        EnrollStudent[] temp = new EnrollStudent[size + 1];
        for (int i = 0; i < size; i++)
            temp[i] = enrollStudents[i];

        enrollStudents = temp;

    }

    /**
     * Gets the EnrollStudent from the enrollstudents array.
     * @param enrollStudent: the student to search for.
     * @return EnrollStudent: the EnrollStudent object if found, else null.
     */
    public EnrollStudent getEnrollStudent(EnrollStudent enrollStudent) {
        if (contains(enrollStudent))
            return enrollStudents[find(enrollStudent)];
        return null;
    }

    /**
     * Stores the tuition due of the Student roster within a string.
     * @param roster: the Student roster.
     * @return String: returns the String containing the tuition due for each student enrolled.
     */
    public String printTuition(Roster roster) {
        if (size == 0) {
            return "Student Enrollment Roster is empty!";
        }

        String ret = "** Tuition due **";

        for (int i = 0; i < size; i++) {
            Student temp = new NonResident(enrollStudents[i].getProfile(), null, Constants.NOT_FOUND);
            Student student = roster.getStudent(temp);
            if (roster.getStudent(student) != null) {
                String studyAbroad = "";
                String state = "";
                if (!student.getState().equals("no state"))
                    state = " " + student.getState();
                if (student.getType().equals("International")) {
                    studyAbroad = " student";
                    if (student.getStudyAbroadStatus())
                        studyAbroad += "study abroad";
                }

                String tempString = student.getProfile().toString() + " (" + student.getType() + studyAbroad + state +
                        ") enrolled " + enrollStudents[i].getCreditsEnrolled() + " credits: Tuition due: $" +
                        String.format("%.2f", roster.getStudent(student).tuitionDue(enrollStudents[i].getCreditsEnrolled()));
                ret += tempString;
            }
        }

        ret += "\n* end of tuition due *";

        return ret;

    }

    /**
     * Updates the student roster with the credits from EnrollStudents in the Enrollment array.
     * @param roster: the student roster being updated
     */
    public void semesterEnd(Roster roster) {
        for (int i = 0; i < size; i++) {
            Student student = new NonResident(enrollStudents[i].getProfile(), null, Constants.NOT_FOUND);
            if (roster.getStudent(student) != null)
                roster.updateCredits(roster.getStudent(student), enrollStudents[i].getCreditsEnrolled());
        }
    }

}
