// NotificationManager.java - Código a refactorizar
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class NotificationManager {
    private static final Logger logger = new Logger(NotificationManager.class);
    // Strategy registry: map notification type -> service
    private final Map<String, NotificationService> strategies = new HashMap<>();
    // Configuración de reintentos
    private int maxRetries = 3;
    private long retryDelayMs = 1000; // 1 segundo

    // Register default services so existing code keeps working
    public NotificationManager() {
        register("email", new EmailService());
        register("sms", new SMSService());
        register("push", new PushService());
        logger.info("NotificationManager inicializado con 3 servicios de notificación");
    }

    // Configurar número máximo de reintentos
    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        logger.debug("Máximo de reintentos configurado a: " + maxRetries);
    }

    // Configurar delay entre reintentos (en milisegundos)
    public void setRetryDelayMs(long delayMs) {
        if (!ParameterValidator.validateRetryDelay(delayMs)) {
            logger.warning("Configuración de delay rechazada, manteniendo valor anterior: " + this.retryDelayMs + "ms");
            return;
        }
        this.retryDelayMs = delayMs;
        logger.debug("Delay entre reintentos configurado a: " + delayMs + "ms");
    }

    // Allow callers to register or override strategies
    public void register(String type, NotificationService service) {
        if (type == null || service == null) {
            logger.warning("Intento de registrar servicio con tipo o servicio nulo");
            return;
        }
        strategies.put(type, service);
        logger.info("Servicio registrado: " + type + " -> " + service.getClass().getSimpleName());
    }

    public void send(String type, String message, String recipient) {
        // Validar parámetros
        if (!ParameterValidator.validateSendParameters(type, message, recipient)) {
            logger.error("Parámetros inválidos para send() - No se realizará el envío");
            return;
        }
        
        logger.debug("Enviando notificación - Tipo: " + type + ", Destinatario: " + recipient);
        NotificationService svc = strategies.get(type);
        if (svc == null) {
            logger.error("Tipo de notificación desconocido: " + type);
            return;
        }
        svc.send(message, recipient);
        logger.info("Notificación enviada exitosamente - Tipo: " + type);
    }

    // Enviar con reintentos automáticos
    public boolean sendWithRetry(String type, String message, String recipient) {
        return sendWithRetry(type, message, recipient, maxRetries);
    }

    // Enviar con reintentos automáticos con número personalizado de intentos
    public boolean sendWithRetry(String type, String message, String recipient, int retries) {
        // Validar parámetros
        if (!ParameterValidator.validateSendParameters(type, message, recipient)) {
            logger.error("Parámetros inválidos para sendWithRetry() - No se realizará el envío");
            return false;
        }
        if (!ParameterValidator.validateRetries(retries)) {
            logger.error("Número de reintentos inválido: " + retries);
            return false;
        }
        
        int attempt = 0;
        while (attempt <= retries) {
            try {
                logger.debug("Intento " + (attempt + 1) + "/" + (retries + 1) + " - Tipo: " + type + ", Destinatario: " + recipient);
                NotificationService svc = strategies.get(type);
                if (svc == null) {
                    logger.error("Tipo de notificación desconocido: " + type);
                    return false;
                }
                svc.send(message, recipient);
                logger.info("Notificación enviada exitosamente - Tipo: " + type + ", Intento: " + (attempt + 1));
                return true;
            } catch (Exception e) {
                attempt++;
                if (attempt <= retries) {
                    logger.warning("Error en intento " + attempt + ", reintentando en " + retryDelayMs + "ms - " + e.getMessage());
                    try {
                        Thread.sleep(retryDelayMs);
                    } catch (InterruptedException ie) {
                        logger.error("Interrupción durante el sleep de reintento: " + ie.getMessage());
                        Thread.currentThread().interrupt();
                        return false;
                    }
                } else {
                    logger.error("Falló tras " + (attempt) + " intentos - Tipo: " + type + ", Destinatario: " + recipient + " - " + e.getMessage());
                }
            }
        }
        return false;
    }

    // Enviar a múltiples destinatarios
    public void sendToMultiple(String type, String message, List<String> recipients) {
        // Validar parámetros
        if (!ParameterValidator.validateSendToMultipleParameters(type, message, recipients)) {
            logger.error("Parámetros inválidos para sendToMultiple() - No se realizará el envío");
            return;
        }
        
        if (!strategies.containsKey(type)) {
            logger.error("Tipo de notificación desconocido: " + type);
            return;
        }
        
        logger.info("Iniciando envío masivo - Tipo: " + type + ", Destinatarios: " + recipients.size());
        int successCount = 0;
        
        for (String recipient : recipients) {
            try {
                send(type, message, recipient);
                successCount++;
            } catch (Exception e) {
                logger.error("Error al enviar a " + recipient + ": " + e.getMessage());
            }
        }
        
        logger.info("Envío masivo completado - Tipo: " + type + ", Exitosos: " + successCount + "/" + recipients.size());
    }

    // Enviar a múltiples destinatarios con reintentos
    public void sendToMultipleWithRetry(String type, String message, List<String> recipients) {
        // Validar parámetros
        if (!ParameterValidator.validateSendToMultipleParameters(type, message, recipients)) {
            logger.error("Parámetros inválidos para sendToMultipleWithRetry() - No se realizará el envío");
            return;
        }
        
        if (!strategies.containsKey(type)) {
            logger.error("Tipo de notificación desconocido: " + type);
            return;
        }
        
        logger.info("Iniciando envío masivo con reintentos - Tipo: " + type + ", Destinatarios: " + recipients.size() + ", Máx. intentos: " + (maxRetries + 1));
        int successCount = 0;
        int failureCount = 0;
        
        for (String recipient : recipients) {
            if (sendWithRetry(type, message, recipient)) {
                successCount++;
            } else {
                failureCount++;
            }
        }
        
        logger.info("Envío masivo con reintentos completado - Tipo: " + type + ", Exitosos: " + successCount + ", Fallos: " + failureCount + " de " + recipients.size());
    }

    // Habilitar/deshabilitar persistencia de logs en archivo
    public static void setFileLoggingEnabled(boolean enabled) {
        Logger.setFileLoggingEnabled(enabled);
    }
}
