// Logger.java - Sistema simple de logs con persistencia en archivo
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String LOG_FILE = "logs/notification_system.log";
    private static boolean fileLoggingEnabled = true;
    
    public enum LogLevel {
        INFO("INFO"),
        WARNING("WARNING"),
        ERROR("ERROR"),
        DEBUG("DEBUG");
        
        private final String label;
        
        LogLevel(String label) {
            this.label = label;
        }
        
        public String getLabel() {
            return label;
        }
    }
    
    private final String className;
    
    public Logger(Class<?> clazz) {
        this.className = clazz.getSimpleName();
    }
    
    public void info(String message) {
        log(LogLevel.INFO, message);
    }
    
    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }
    
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
    
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
    
    // Habilitar/deshabilitar logging en archivo
    public static void setFileLoggingEnabled(boolean enabled) {
        fileLoggingEnabled = enabled;
    }

    private void log(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] %s - %s: %s", 
            timestamp, 
            level.getLabel(), 
            className, 
            message);
        
        // Log en consola
        System.out.println(logMessage);
        
        // Log en archivo si está habilitado
        if (fileLoggingEnabled) {
            writeLogToFile(logMessage);
        }
    }

    private void writeLogToFile(String logMessage) {
        try {
            // Crear directorio de logs si no existe
            Files.createDirectories(Paths.get("logs"));
            
            // Escribir en el archivo de log
            Files.write(
                Paths.get(LOG_FILE),
                (logMessage + System.lineSeparator()).getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}
