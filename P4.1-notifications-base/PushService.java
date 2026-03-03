// PushService.java - encapsula la lógica de notificaciones push
public class PushService implements NotificationService {
    private static final Logger logger = new Logger(PushService.class);
    @Override
    public void send(String message, String recipient) {
        try {
            logger.debug("Iniciando envío de notificación push a: " + recipient);
            // Lógica específica de push
            System.out.println("Enviando push a " + recipient + ": " + message);
            logger.info("Push enviado exitosamente a: " + recipient);
            // TODO: implementar detalles reales de envío push
        } catch (Exception e) {
            logger.error("Error al enviar push a " + recipient + ": " + e.getMessage());
        }
    }
}