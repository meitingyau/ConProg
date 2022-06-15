package FarmerSimulation;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class MyCustomFormatter extends Formatter {
    @Override
    public String format(LogRecord message) {
        StringBuffer sb = new StringBuffer();
        sb.append(message.getMessage());
        return sb.toString();
    }
}

public class FileLogger {

    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    static private MyCustomFormatter formatterTxt2;

    //logger setup for sequential logging
    static public void setupSequential() throws IOException {

        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        fileTxt = new FileHandler("Sequential_Logging.log");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    //logger setup for concurrent logging
    static public void setupConcurrent() throws IOException {

        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        fileTxt = new FileHandler("Concurrent_Logging.log");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    //logger setup for generating data text file from database
    static public void setupFileInDB() throws IOException {

        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        fileTxt = new FileHandler("Database_Data.txt");

        // create a TXT formatter
        formatterTxt2 = new MyCustomFormatter();
        fileTxt.setFormatter(formatterTxt2);
        logger.addHandler(fileTxt);
    }
}
