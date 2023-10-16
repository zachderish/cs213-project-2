import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void withinDayRange() {
        Date input1 = new Date("12/03/2023");
        assertTrue(input1.isValid());

    }
    @Test
    void belowDayRange(){
        Date input1 = new Date("11/00/2005");
        assertFalse(input1.isValid());
    }

    @Test
    void AboveDayRange(){
        Date input1 = new Date("7/32/2003");
        assertFalse(input1.isValid());
    }
    @Test
    void withinMonthRange(){
        Date input1 = new Date("5/4/2017");
        assertTrue(input1.isValid());
    }
    @Test
    void BelowMonthRange(){
        Date input1 = new Date("00/2/2016");
        assertFalse(input1.isValid());
    }
    @Test
    void AboveMonthRange(){
        Date input1 = new Date("13/16/2000");
        assertFalse(input1.isValid());
    }

    @Test
    void checkDaysOverLeapYear(){
        Date input1 = new Date("2/29/2019");
        assertFalse(input1.isValid());
    }

    @Test
    void checkProperLeapYearDays(){
        Date input1 = new Date("2/29/2020");
        assertTrue(input1.isValid());
    }


}