import listControl.Utilities;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {
    @Test
    void readDate() {
        String date1 = "2019-12-12"; // correct value
        String date2 = "string";     // invalid value
        String date3 = "2018-10-18"; // old value
        String date4 = "2019-1-12"; // not correct format
        assertEquals("2019-12-12", Objects.requireNonNull(Utilities.readDate(date1)).toString());
        assertThrows(NullPointerException.class, () -> Objects.requireNonNull(Utilities.readDate(date2)));
        assertThrows(NullPointerException.class, () -> Objects.requireNonNull(Utilities.readDate(date3)));
        assertThrows(NullPointerException.class, () -> Objects.requireNonNull(Utilities.readDate(date4)));
    }

    @Test
    void readInput() {
        String input1 = ""; //  correct input
        String input2 = "some thing . ."; // correct
        String input3 = "01228 ring"; // correct
        assertEquals("", Utilities.readInput(input1));
        assertEquals("some thing . .", Utilities.readInput(input2));
        assertEquals("01228 ring", Utilities.readInput(input3));

    }
}
