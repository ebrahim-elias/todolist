package listControl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public abstract class Utilities {

    /**
     * convert the string date to type localDate with the format yyyy-mm-dd
     *
     * @param date the input from user
     * @return the input as localDate
     */
    public static LocalDate readDate(String date) {
        LocalDate localDate;
        try {
            // parse the string to localDate format with validating the input and then the old date if its entered
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();
            if (localDate.compareTo(today) >= 0) {
                return localDate;
            } else {
                System.out.println("The date is old pleas try again: ");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Value (yyyy-MM-dd) Please Try again: ");
            return null;
        }
        return null;
    }

    /**
     * validate the input if it is empty or not
     *
     * @param input the user input
     * @return the user input
     */
    public static String readInput(String input) {
        if (!input.equals("")) {
            return input;
        } else {
            System.out.println(">> must have value\n" +
                    ">> Please enter value:\n" +
                    ">>");
        }
        return "";
    }
}
