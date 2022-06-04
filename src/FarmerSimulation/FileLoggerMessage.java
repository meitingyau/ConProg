package FarmerSimulation;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLoggerMessage {
    // use the classname for the logger, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    // private String severeMsg;
    // private String infoMsg;

    // public void setSevereMsg(String severeMsg){
    //     this.severeMsg = severeMsg;
    // }

    // public void setInfoMsg(String infoMsg){
    //     this.infoMsg = infoMsg;
    // }

    static public void logInfoMessage(String infoMsg) {
        LOGGER.info(infoMsg);
    }

    public void logSevereMessage(String severeMsg){
        LOGGER.severe(severeMsg);
    }

}
