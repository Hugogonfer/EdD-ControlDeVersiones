// NotificationManager.java - Código a refactorizar
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class NotificationManager {
    private static final Logger logger = new Logger(NotificationManager.class);
    // Strategy registry: map notification type -> service
    private final Map<String, NotificationService> strategies = new HashMap<>();

    // Register default services so existing code keeps working
    public NotificationManager() {
        register("email", new EmailService());
        register("sms", new SMSService());
        register("push", new PushService());
        logger.info("NotificationManager inicializado con 3 servicios de notificación");
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
        logger.debug("Enviando notificación - Tipo: " + type + ", Destinatario: " + recipient);
        NotificationService svc = strategies.get(type);
        if (svc == null) {
            logger.error("Tipo de notificación desconocido: " + type);
            return;
        }
        svc.send(message, recipient);
        logger.info("Notificación enviada exitosamente - Tipo: " + type);
    }

    // Enviar a múltiples destinatarios
    public void sendToMultiple(String type, String message, List<String> recipients) {
        if (recipients == null || recipients.isEmpty()) {
            logger.warning("Intento de enviar a lista vacía o nula");
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

    // TODO: Añadir sistema de reintentos
    // TODO: Añadir validación de parámetros
}
