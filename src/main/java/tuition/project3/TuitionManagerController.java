package tuition.project3;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is a class that controls the functions that the GUI contains.
 * @author Viral Patel, Rohan Patel
 */

public class TuitionManagerController {

    @FXML
    private Label addConfirmationText, removeConfirmationText, filterConfirmationText, changeMajorConfirmationText,
            runMsg, printConfirmation, enrollmentPrintConfirmation, selectedFile, scholarshipConfirmationText,
            semesterEndConfirmation;
    @FXML
    private TextField addStudentField, removeStudentField, changeMajorField, awardScholarshipField;
    @FXML
    private ChoiceBox sortingMethod, filterMethod, majorMethod, enrollmentPrintMethod;
    @FXML
    private TextFlow printedRosterArea, printedEnrollmentRosterArea, semesterEndArea;
    @FXML
    private ScrollPane rosterScroll, enrollmentScroll, semesterScroll;

    private File file;

    private boolean checkSemesterEnd = true;

    private Roster roster = new Roster();
    private Enrollment enrollmentRoster = new Enrollment();

    /**
     * Initializes the options for drop down menus, and sets the content for scrollboxes to the proper text.
     */
    @FXML
    public void initialize() {
        runMsg.setText("Roster Manager running...");

        String sortingOptions[] = {"Profile", "School Major", "Standing" };
        sortingMethod.getItems().addAll(sortingOptions);

        String filterOptions[] = {"SAS", "SOE", "SC&I", "RBS"};
        filterMethod.getItems().addAll(filterOptions);

        String majorOptions[] = {"CS", "MATH", "EE", "ITI", "BAIT"};
        majorMethod.getItems().addAll(majorOptions);

        String enrollPrintOptions[] = {"Enrolled Students", "Tuition"};
        enrollmentPrintMethod.getItems().addAll(enrollPrintOptions);

        rosterScroll.setContent(printedRosterArea);
        enrollmentScroll.setContent(printedEnrollmentRosterArea);
        semesterScroll.setContent(semesterEndArea);
    }

    /**
     * Performs the add command to the roster given an input from the corresponding text field. Sets confirmation text
     * that displays the result of the action.
     */
    @FXML
    protected void onAddButtonClick() {
        String command = addStudentField.getText();
        addConfirmationText.setText(addHandler(roster, enrollmentRoster, command));
    }

    /**
     * Performs the remove command to the roster given an input from the corresponding text field. Sets confirmation
     * text that displays the result of the action.
     */
    @FXML
    protected void onRemoveButtonClick() {
        String command = removeStudentField.getText();
        removeConfirmationText.setText(removeHandler(roster, enrollmentRoster, command));
    }

    /**
     * Gets the sorting method from a drop down menu, which is then attempted to be used to print the roster to the GUI.
     * If no options is selected, then the confirmation text will display "no options selected".
     */
    @FXML
    protected void onPrintButtonClick() {

        String sortingType = (String) sortingMethod.getValue();
        printedRosterArea.getChildren().clear();

        try {
            if (sortingType.equals("Profile")) {
                printedRosterArea.getChildren().add(new Text(roster.print()));
            }
            else if (sortingType.equals("School Major")) {
                printedRosterArea.getChildren().add(new Text(roster.printBySchoolMajor()));
            }
            else if (sortingType.equals("Standing")) {
                printedRosterArea.getChildren().add(new Text(roster.printByStanding()));
            }

            printConfirmation.setText("Printing by " + sortingType);
        } catch (NullPointerException e) {
            printConfirmation.setText("No option selected.");
        }
    }

    /**
     * Gets the sorting method from a drop down menu, which is then attempted to be used to print the enrollment
     * roster to the GUI. If no options is selected, then the confirmation text will display "no options selected".
     */
    @FXML
    protected void onEnrollmentPrintButtonClick() {
        String sortingType = (String) enrollmentPrintMethod.getValue();
        printedEnrollmentRosterArea.getChildren().clear();

        try {
            if (sortingType.equals("Enrolled Students")) {
                printedEnrollmentRosterArea.getChildren().add(new Text(enrollmentRoster.print()));
            }
            else if (sortingType.equals("Tuition")) {
                printedEnrollmentRosterArea.getChildren().add(new Text(enrollmentRoster.printTuition(roster)));
            }

            enrollmentPrintConfirmation.setText("Printing by " + sortingType);
        } catch (NullPointerException e) {
            enrollmentPrintConfirmation.setText("No option selected.");
        }
    }

    /**
     * Gets the sorting method from a drop down menu, which is then attempted to be used to print the roster to the GUI.
     * If no options is selected, then the confirmation text will display "no options selected".
     */
    @FXML
    protected void onFilterButtonClick() {
        String filterType = (String) filterMethod.getValue();
        printedRosterArea.getChildren().clear();

        if (filterType == null) {
            filterConfirmationText.setText("No option selected.");
            return;
        }
        printedRosterArea.getChildren().add(new Text(roster.filterBySchool(filterType)));
        filterConfirmationText.setText("Sorting by " + filterType);
    }

    /**
     * Allows the user to select a text file from their PC. The file is then saved to the variable file for later use.
     * @throws InterruptedException
     */
    @FXML
    protected void onChooseFileClickButton() throws InterruptedException {
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            selectedFile.setText(file.getName() + " selected");
        }
    }

    /**
     * Receives a major from a drop down menu and a student through a text field. Given the information, the student's
     * major is attempted to be changed. Confirmation text will display what occurred.
     */
    @FXML
    protected void onChangeMajorButtonClick() {
        String fieldText = changeMajorField.getText();
        String major = (String) majorMethod.getValue();

        if (fieldText.equals("") && major == null) {
            changeMajorConfirmationText.setText("Please select major and specify student");
            return;
        }

        if (fieldText.equals("")) {
            changeMajorConfirmationText.setText("Missing student input");
            return;
        }

        if (major == null) {
            changeMajorConfirmationText.setText("No major selected");
            return;
        }

        String command = "C " + fieldText + " " + major;
        String[] input = command.split("\\s+");
        try {
            changeMajorConfirmationText.setText(roster.changeMajor(input));
        } catch (RuntimeException e) {
            changeMajorConfirmationText.setText("Invalid input");
        }
    }

    /**
     * Gets a student and scholarship amount from the text field. The information is then used to attempt to award the
     * student with the specified scholarship amount.
     */
    @FXML
    protected void onAwardScholarshipButtonClick() {
        String command = "S " + awardScholarshipField.getText();
        String input[] = command.split("\\s+");
        scholarshipConfirmationText.setText(roster.awardScholarship(input, enrollmentRoster));
    }

    /**
     * Ends the semester, updating the credits in the roster for the students currently enrolled. If there are students
     * eligible for graduation, then they will be printed. This button can only be used once to perform the action,
     * otherwise it will display an error for the confirmation text.
     */
    @FXML
    protected void onSemesterEndButtonClick() {
        if (checkSemesterEnd) {
            checkSemesterEnd = false;

            enrollmentRoster.semesterEnd(roster);
            semesterEndArea.getChildren().add(new Text(roster.PrintEligibleForGraduation()));
            semesterEndConfirmation.setText("Semester end complete.");
            return;
        }
        semesterEndConfirmation.setText("Semester end already complete, cannot perform again.");
    }

    /**
     * Reads the data stored in the chosen file if there is one. Otherwise, displays an error message for the
     * confirmation text.
     */
    @FXML
    protected void onLoadDataButtonClick() {

        if (file != null) {
            if (readFromTextFile(roster, file)) {
                selectedFile.setText("Students successfully loaded into the roster.");
            }
        }
        else
            selectedFile.setText("No file selected.");

    }

    /**
     * Handles the add command, allowing students to be added to either the roster or enrollment roster.
     * @param roster: the roster in which the student is added.
     * @param enrollmentRoster: the enrollment roster in which the student is added.
     * @param command: the provided string in the text field, which is broken down and interpreted.
     * @return String: the confirmation text based on what occurred in the attempt to add the student.
     */
    private String addHandler(Roster roster, Enrollment enrollmentRoster, String command) {

        String[] input = command.split("\\s+");

        if (input[0].equals("AR") || input[0].equals("AN") || input[0].equals("AT") || input[0].equals("AI")) {
            if (isValidStudent(input).equals("accepted")) {
                if (roster.add(createStudent(input)))
                    return (createStudent(input).getProfile().toString() + " added to the roster.");
                else
                    return (createStudent(input).getProfile().toString() + " is already in the roster.");
            }
            else
                return isValidStudent(input);
        }
        else if (input[0].equals("E")) {
            if (isValidEnrollmentStudent(roster, input).equals("accepted")) {
                enrollmentRoster.add(createEnrollStudent(input));
                return (createEnrollStudent(input).getProfile().toString() +
                        " enrolled " + createEnrollStudent(input).getCreditsEnrolled() + " credits");
            }
            else
                return (isValidEnrollmentStudent(roster, input));
        }

        return "Invalid input";
    }

    /**
     * Handles the remove command, which allows students to be removed from the roster or enrollment roster.
     * @param roster: the roster in which the student is being removed from.
     * @param enrollmentRoster: the enrollment roster in which the student is being removed from.
     * @param command: the provided string in the text field, which is broken down and interpreted.
     * @return String: the confirmation text based on what occurred in the attempt to add the student.
     */
    private String removeHandler(Roster roster, Enrollment enrollmentRoster, String command) {

        String[] input = command.split("\\s+");
        try {
            if (input[0].equals("R")) {
                Student student = createStudent(input);
                if (roster.remove(student))
                    return (student.getProfile().toString() + " removed from roster.");
                else
                    return (student.getProfile().toString() + " is not in the roster.");
            }
            else if (input[0].equals("D")) {
                return (enrollmentRoster.remove(createEnrollStudent(input)));
            }

        } catch (IndexOutOfBoundsException e) {
            return "Invalid input";
        } catch (RuntimeException e) {
            return "Invalid input";
        }

        return "Invalid input";
    }
    /**
     * Checks if a student is valid
     * This includes:
     * proper amount of data arguments provided, valid DOB, valid major, and valid number of credits completed
     * @param input String[]: String array that contains the console input.
     * @return String: returns the message that explains what happened in the attempt to validate the student input.
     */
    private String isValidStudent(String[] input) {

        if (input[0].equals("AR")) {
            if (input.length < 6) {
                return("Missing data in line command.");
            }
        } else if (input[0].equals("AT")) {
            if (input.length < 6) {
                return("Missing data in line command.");
            }
            else if ((input.length == 6)) {
                return("Missing the state code.");
            }
            else if (input.length == 7) {
                if (!(input[6].equalsIgnoreCase("NJ") || input[6].equalsIgnoreCase("NY")
                        || input[6].equalsIgnoreCase("CT"))) {
                    return(input[6] + ": Invalid state code.");
                }
            }
        } else if (input[0].equals("AI")) {
            if (input.length < 6) {
                return("Missing data in line command.");
            }
        } else if (input[0].equals("AN")) {
            if (input.length < 6) {
                return("Missing data in line command.");
            }
        }

        Date dob = new Date(input[3]);
        Date today = new Date();
        if (!dob.isValid()) {
            return("DOB invalid: " + input[3] + " not a valid calendar date!");
        }

        if (today.getYear() - dob.getYear() < 16) {
            return("DOB invalid: " + input[3] + " younger than 16 years old.");
        } else if (today.getYear() - dob.getYear() == 16) {
            if (today.getMonth() < dob.getMonth()) {
                return("DOB invalid: " + input[3] + " younger than 16 years old.");
            } else if (today.getMonth() == dob.getMonth()) {
                if (today.getDay() < dob.getDay()) {
                    return("DOB invalid: " + input[3] + " younger than 16 years old.");
                }
            }
        }


        Major major = Major.searchMajor(input[4]);
        if (major == null) {
            return("Major code invalid: " + input[4]);
        }

        int creditComplete;
        try {
            creditComplete = Integer.parseInt(input[5]);
        } catch (NumberFormatException e) {
            return("Credits completed invalid: not an integer!");
        }

        if (creditComplete < 0) {
            return("Credits completed invalid: cannot be negative!");
        }

        return "accepted";
    }

    /**
     * Creates a student object with console input.
     * @param input String[]: String array that consists of the console input.
     * @return Student: returns student object created.
     */
    private Student createStudent(String[] input) {
        Date dob = new Date(input[3]);
        Profile profile = new Profile(input[2], input[1], dob);
        Major major = null;
        int creditComplete = Constants.NOT_FOUND;

        Student student = new NonResident(profile, major, creditComplete);

        if (input.length >= 6) {
            major = Major.searchMajor(input[4]);
            creditComplete = Integer.parseInt(input[5]);

            if (input[0].equals("AR") || input[0].equals("R")) {
                int scholarship = 0;
                Resident resident = new Resident(scholarship, profile, major, creditComplete);
                return resident;
            } else if (input[0].equals("AN") || input[0].equals("N")) {
                NonResident nonResident = new NonResident(profile, major, creditComplete);
                return nonResident;
            } else if (input[0].equals("AI") || input[0].equals("I")) {
                boolean isStudyAbroad = false;
                if (input.length == 7)
                    isStudyAbroad = Boolean.parseBoolean(input[6]);
                International internationalStudent =
                        new International(isStudyAbroad, profile, major, creditComplete);
                return internationalStudent;
            }
            if (input.length == 7) {
                if (input[0].equals("AT") || input[0].equals("T")) {
                    String state = input[6];
                    TriState triStateStudent = new TriState(state, profile, major, creditComplete);
                    return triStateStudent;
                }
            }
        }

        return student;
    }

    /**
     * Creates a EnrollStudent object with console input.
     * @param input String[]: String array that consists of the console input.
     * @return Student: returns EnrollStudent object created.
     */
    private EnrollStudent createEnrollStudent(String[] input) {
        Date dob = new Date(input[3]);
        Profile profile = new Profile(input[2], input[1], dob);
        int creditsEnrolled = Constants.NOT_FOUND;

        if (input.length == 5)
            creditsEnrolled = Integer.parseInt(input[4]);

        EnrollStudent student = new EnrollStudent(profile, creditsEnrolled);
        return student;
    }

    /**
     * Reads in data from a text file, and populates the roster with the information.
     * @param roster Roster: the roster being populated.
     * @param file File: file that the data is being read in from.
     * @return boolean: returns true if data is successfully read, otherwise returns false.
     */
    public boolean readFromTextFile(Roster roster, File file) {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] arguments = scanner.nextLine().split(",");
                roster.add(createStudent(arguments));
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }

    /**
     * Checks if EnrollmentStudent is valid
     * Valid if:
     * DOB exists, enough input arguments, student in roster, and credits enrolled is within constraints.
     * @param roster Roster: needed to check if the student is in the roster.
     * @param input String[]: String array that consists of the console input.
     * @return String: returns the message that explains what happened in the attempt to validate the
     * enrollment student input.
     */
    private String isValidEnrollmentStudent(Roster roster, String[] input) {
        Date dob = new Date(input[3]);
        Date today = new Date();
        if (!dob.isValid()) {
            return ("DOB invalid: " + input[3] + " not a valid calendar date!");
        }

        if (today.getYear() - dob.getYear() < 16) {
            return ("DOB invalid: " + input[3] + " younger than 16 years old.");
        } else if (today.getYear() - dob.getYear() == 16) {
            if (today.getMonth() < dob.getMonth()) {
                return ("DOB invalid: " + input[3] + " younger than 16 years old.");
            } else if (today.getMonth() == dob.getMonth()) {
                if (today.getDay() < dob.getDay()) {
                    return ("DOB invalid: " + input[3] + " younger than 16 years old.");
                }
            }
        }

        if (input.length < 5) {
            return ("Missing data in line command.");
        }

        int creditEnrolled;
        try {
            creditEnrolled = Integer.parseInt(input[4]);
        } catch (NumberFormatException e) {
            return ("Credits enrolled is not an integer.");
        }

        if (creditEnrolled < 0) {
            return ("Credits completed invalid: cannot be negative!");
        }

        Profile profile = new Profile(input[2], input[1], dob);
        Student temp = new NonResident(profile, null, Constants.NOT_FOUND);

        if (!roster.contains(temp)) {
            return ("Cannot Enroll: " + profile.toString() + " is not in the roster.");
        }

        Student student = roster.getStudent(temp);

        if (!student.isValid(creditEnrolled)) {
            if (student.isResident())
                return ("(Resident) " + creditEnrolled + ": invalid credit hours.");
            else {
                String studyAbroad = "";
                if (student.getStudyAbroadStatus())
                    studyAbroad = " studentstudy abroad";
                return ("(" + student.getType() +
                        studyAbroad + ") " + creditEnrolled + ": invalid credit hours.");
            }
        }

        return "accepted";

    }

}