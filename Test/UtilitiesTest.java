import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilitiesTest {
    @Test
    void readDate() {
        String date1 = "12-12-2019"; // correct value
        String date2 = "string";     // invalid value
        String date3 = "12-10-2018"; // old value
        String date4 = "2019-1-12"; // not correct format
        assertEquals("2019-12-12",Utilities.readDate(date1).toString());
        assertThrows(NullPointerException.class, () -> Utilities.readDate(date2).toString());
        assertThrows(NullPointerException.class, () -> Utilities.readDate(date3).toString());
        assertThrows(NullPointerException.class, () -> Utilities.readDate(date4).toString());
    }
    @Test
    void readInput(){
         String input1= ""; //  correct input
         String input2= "some thing . ."; // correct
         String input3 = "01228 ring"; // correct
         assertEquals("",Utilities.readInput(input1));
         assertEquals("some thing . .",Utilities.readInput(input2));
         assertEquals("01228 ring",Utilities.readInput(input3));

    }
}
