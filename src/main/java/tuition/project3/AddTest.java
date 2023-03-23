package tuition.project3;

import static org.junit.Assert.*;

/**
 * Test class for date.
 * @author: Viral Patel, Rohan Patel
 */
    public class AddTest {
        @org.junit.Test

        public void add() {

            boolean isStudyAbroad = true;
            int credits = 85;
            int creditsEnrolled = 12;
            Date dob = new Date("02/23/2002");
            Profile profile = new Profile("Patel", "Viral", dob);
            Major major = Major.EE;
            String state = "NY";

            Roster roster1 = new Roster();
            //Test case1: add international student 1
            International international = new International(isStudyAbroad, profile, major, credits);
            boolean check1 = roster1.add(international);
            assertTrue(check1);

            //Test case2: add duplicate international student
            boolean check2 = roster1.add(international);
            assertFalse(check2);

            Roster roster2 = new Roster();
            //Test case3: add TriState student 1
            TriState triState = new TriState(state, profile, major, credits);
            boolean check3 = roster2.add(triState);
            assertTrue(check3);

            //Test case4: add duplicate TriState student
            boolean check4 = roster2.add(triState);
            assertFalse(check4);
        }
    }
