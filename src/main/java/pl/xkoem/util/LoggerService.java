package pl.xkoem.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerService {
    private static final Logger logger = LoggerFactory.getLogger("PriceEngine");

    public void logError(Class loggingClass, String message) {
        logger.error(getDateTime() + " [" + loggingClass.getSimpleName() + "] " + message);
    }

    public void logInfo(Class loggingClass, String message) {

        logger.info(getDateTime() + " [" + loggingClass.getSimpleName() + "] " + message);
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
