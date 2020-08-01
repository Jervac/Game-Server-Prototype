package Visionire;

import java.time.LocalDateTime;

public class Utils {

    /** Prints the given message preceded with the current time **/
    public static void printWithTime(String message) {
        LocalDateTime date = LocalDateTime.now();
        System.out.println("[" + date.toString() + "] " + message);
    }
}
