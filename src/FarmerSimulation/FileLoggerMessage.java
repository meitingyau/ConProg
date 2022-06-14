package FarmerSimulation;

import java.util.logging.Logger;

public class FileLoggerMessage {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    static public void logInfoMessage(String infoMsg) {
        LOGGER.info(infoMsg);
    }

}
