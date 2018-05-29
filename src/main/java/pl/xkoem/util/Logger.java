package pl.xkoem.util;

import java.util.logging.Level;

public class Logger {
    public static void logCheckingInfo(long checkingTime, int checked) {
        String message = "Checking time: " + String.valueOf(checkingTime) + " Checked items: " + checked;

        java.util.logging.Logger.getLogger("Checked").log(Level.INFO, message);
    }
}
