package tuition.project3;

import java.util.Calendar;

/**
 * This is a class that allows for the creation of a date object.
 * @author Viral Patel, Rohan Patel
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructor to create today's date object.
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        this.day = today.DAY_OF_MONTH;
        this.month = today.MONTH;
        this.year = today.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    /**
     * Constructor to create a date object.
     * @param date: String in form mm/dd/yyyy
     */
    public Date(String date) {
        String[] monthDayYear = date.split("/");
        this.month = Integer.parseInt(monthDayYear[0]);
        this.day = Integer.parseInt(monthDayYear[1]);
        this.year = Integer.parseInt(monthDayYear[2]);
    } //take “mm/dd/yyyy” and create a Date object

    /**
     * Checks if a date is a valid calendar date, that it actually exists.
     * @return boolean: true if the date is valid, false otherwise.
     */
    public boolean isValid() {

        if (this.month == Constants.JANUARY || this.month == Constants.MARCH || this.month == Constants.MAY ||
                this.month == Constants.JULY || this.month == Constants.AUGUST || this.month == Constants.OCTOBER ||
                this.month == Constants.DECEMBER) {
            if (this.day >= Constants.MINDAYINMONTH && this.day <= Constants.MAXDAYINMMONTH)
                return true;
        }

        if (this.month == Constants.APRIL || this.month == Constants.JUNE || this.month == Constants.SEPTEMBER ||
                this.month == Constants.NOVEMBER) {
            if (this.day >= 1 && this.day <= Constants.MAXDAYINVMONTH)
                return true;
        }

        boolean leapYear = false;
        if (this.year % Constants.QUADRENNIAL == 0) {
            if (this.year % Constants.CENTENNIAL == 0) {
                if (this.year % Constants.QUATERCENTENNIAL == 0)
                    leapYear = true;
            }
            else
                leapYear = true;
        }

        if (this.month == Constants.FEBRUARY) {
            if (leapYear) {
                if (this.day >= Constants.MINDAYINMONTH && this.day <= Constants.MAXDAYINFEBLEAPYEAR)
                    return true;
            }
            else {
                if (this.day >= Constants.MINDAYINMONTH && this.day <= Constants.MAXDAYINFEB)
                    return true;
            }
        }

        return false;
    } //check if a date is a valid calendar date

    /**
     * Gets the year of the date object.
     * @return int: year.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets the day of the date object.
     * @return int: day.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Gets the month of the date object.
     * @return int: month.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Overrides the toString method, formats the Date object.
     * @return String: formatted Date.
     */
    @Override
    public String toString() {
        return "" + month + "/" + day + "/" + year;
    }

    /**
     * Overrides the equals method, checks if two dates are equal.
     * @param obj: takes in an object, if it is a date it will compare two dates.
     * @return boolean: true if dates are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return this.day == date.day && this.month == date.month && this.year == date.year;
        }

        return false;
    }

    /**
     * Overrides the compareTo method, checks which date has precedence over another.
     * @param date
     * @return int: 1 if greater, 0 if equal, -1 if less.
     */
    @Override
    public int compareTo(Date date){
        if(date.year<this.year) {
            return 1;
        } else if(date.year>this.year){
            return -1;
        } else{
            if(date.month<this.month){
                return 1;
            }else if(date.month>this.month){
                return -1;
            }else{
                if(date.day<this.day){
                    return 1;
                }else if(date.day>this.day){
                    return -1;
                }
            }
        }
        return 0;
    }

}
