package tuition.project3;

import static org.junit.Assert.*;

    /**
     * Test class for date.
     * @author: Viral Patel, Rohan Patel
     */
    public class DateTest {
        @org.junit.Test

        public void isValid() {
            //Test case1: Regular date
            Date date = new Date("2/23/2002");
            assertTrue(date.isValid());

            //Test case2: 29 days in leap year
            Date date2 = new Date("2/29/2000");
            assertTrue(date2.isValid());

            //Test case3: Non-positive day
            Date date3 = new Date("2/-23/2002");
            assertFalse(date3.isValid());

            //Test case4: Day does not exist
            Date date4 = new Date("2/32/2002");
            assertFalse(date4.isValid());

            //Test case5: Non-positive month
            Date date5 = new Date("-13/23/2002");
            assertFalse(date5.isValid());

            //Test case6: Month is greater than 12
            Date date6 = new Date("23/23/2002");
            assertFalse(date6.isValid());

            //Test case7: Zero day
            Date date7 = new Date("2/00/2002");
            assertFalse(date7.isValid());
        }
    }

