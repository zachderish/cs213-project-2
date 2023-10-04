import java.util.Calendar;
//REUSED CODE FROM DATE CLASS WILL MAKE CHANGES LATER TO FIT

/**
 * Define the abstract data type Date and its methods
 * @author Kenrick Eagar, Zachary Derish
 */
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int JANUARY = 0, FEBRUARY = 1, MARCH = 2;
    public static final int APRIL = 3, MAY = 4, JUNE = 5, JULY = 6;
    public static final int AUGUST = 7, SEPTEMBER = 8, OCTOBER = 9, NOVEMBER = 10, DECEMBER = 11;


    private final Calendar RIGHT_NOW = Calendar.getInstance();
    private final int CURRENT_YEAR = RIGHT_NOW.get(Calendar.YEAR);
    private final int CURRENT_MONTH = RIGHT_NOW.get(Calendar.MONTH) + 1;
    private final int CURRENT_DAY = RIGHT_NOW.get(Calendar.DAY_OF_MONTH);

    private final int SIX_MONTHS = 6;
    private final int ONE_DAY = 1;

    /**
     * Date constructor
     *
     * @param year  the year associated with the Date
     * @param month the month associated with the Date
     * @param day   the day associated with the Date
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month - 1;
        this.day = day;
    }

    /**
     * returns true if it's a leap year false otherwise
     *
     * @param year the year to be checked for leap year
     * @return true if leap year, false otherwise
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0 || year % QUATERCENTENNIAL == 0) {
            return true;
        }
        return false;
    }

    /**
     * Return number of days in a month based on month itself and leap year status
     *
     * @param month month of the year represented as int
     * @param year  year represented as int
     * @return int of number of days contained in month
     */
    public int numberOfDays(int month, int year) { //returns the number of days given the month and year
        if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) { //if its any of those months they have 30 days
            return 30;
        } else if (month != FEBRUARY) { // all the rest of the months besides February have 31 days
            return 31;
        } else {
            if (isLeapYear(year)) { // depending on if February is a leap year it will either have 28 or 29 days
                return 29;
            }
        }
        return 28;

    }

    /**
     * Check the Date object to ensure year month and day are all valid inputs
     *
     * @return true if date is within six months and not before current day, false otherwise
     */
    public boolean isValid() {
        int inputMonth = this.month;
        int inputDay = this.day;
        int inputYear = this.year;

        boolean validDay = inputDay <= numberOfDays(inputMonth, inputYear) && inputDay >= 1;
        boolean validMonth = inputMonth <= 11 && inputMonth >= 0;
        boolean validDay_Month = validDay && validMonth;

        return validDay_Month;

    }

    /**
     * Check the Date object to ensure it is being scheduled within 6 month timeframe
     *
     * @return true if date is within six months and not before current day, false otherwise
     */
    public String within6Months() {
        // create calendar object for the date to be scheduled
        Calendar scheduleDate = Calendar.getInstance();
        scheduleDate.set(this.year, this.month, this.day);

        // create Calendar object for the first day that cannot be scheduled
        Calendar scheduleLimit = Calendar.getInstance();
        scheduleLimit.add(Calendar.MONTH, SIX_MONTHS);
        scheduleLimit.add(Calendar.DAY_OF_MONTH, ONE_DAY);

        if (!scheduleDate.before(scheduleLimit)) {
            return "Event date must be within 6 months!";
        }
        if (!scheduleDate.after(RIGHT_NOW)) {
            return "Event date must be a future date!";
        }
        return "VALID";
    }

    /**
     * compare two date objects based on year, month and day
     *
     * @param date the second Date object in the comparison
     * @return 1 if date1 > date2, -1 if date1 < date2, and 0 otherwise
     */
    @Override
    public int compareTo(Date date) {
        // compare year
        if (this.year > date.year)
            return 1;
        if (this.year < date.year)
            return -1;
        // compare month
        if (this.month > date.month)
            return 1;
        if (this.month < date.month)
            return -1;
        // compare day
        if (this.day > date.day)
            return 1;
        if (this.day < date.day)
            return -1;
        return 0;
    }

    /**
     * Getter method for Date object
     *
     * @return String format of this Date object
     */
    public String getDate() {
        String finalDate = (this.month + 1) + "/" + this.day + "/" + this.year;
        return finalDate;
    }
}
