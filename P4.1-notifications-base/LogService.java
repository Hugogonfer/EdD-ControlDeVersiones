import java.util.logging.Logger;

public class LogService {
    private static final Logger logger = Logger.getLogger(LogService.class.getName());

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void error(String msg) {
        logger.severe(msg);
    }
}
