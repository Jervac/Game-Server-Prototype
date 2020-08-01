package Visionire.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Log {
    ERROR, INFO, DEBUG, SELF;

    public static void out(Log type, String message) {
        out(type, message, "com.visionire.downtown");
    }

    public static void out(Log type, String message, Object sender) {
        out(type, message, sender.getClass().getName());
    }

    public static void out(Log type, String message, String _class) {
        switch (type) {
            case DEBUG:
                log(_class).debug(message);
                break;

            case ERROR:
                log(_class).error(message);
                break;

            case INFO:
                log(_class).info(message);
                break;

            case SELF:
                //log(_class).severe(message);
                break;
        }

    }

    public static Logger log(String _class) {
        return LoggerFactory.getLogger(_class);
    }
}
