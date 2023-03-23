package tuition.project3;

import static org.junit.Assert.*;

    /**
     * Test class for date.
     * @author: Viral Patel, Rohan Patel
     */
    public class StudyAbroadTest {
        @org.junit.Test

        public void tuitionDue() {
            boolean isStudyAbroad = true;
            int credits = 85;
            int creditsEnrolled = 12;
            Date dob = new Date("02/23/2002");
            Profile profile = new Profile("Patel", "Viral", dob);
            Major major = Major.EE;

            //Test case1: study abroad student
            International international1 = new International(isStudyAbroad, profile, major, credits);
            boolean check1 = international1.tuitionDue(creditsEnrolled) == 5918;
            assertTrue(check1);

            //Test case2: non study abroad student
            isStudyAbroad = false;
            International international2 = new International(isStudyAbroad, profile, major, credits);
            boolean check2 = international2.tuitionDue(creditsEnrolled) == 35655;
            assertTrue(check2);

        }
    }
