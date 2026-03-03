// SMSService.java - encapsula la lógica de envío de SMS
public class SMSService implements NotificationService {
    private static final Logger logger = new Logger(SMSService.class);
    @Override
    public void send(String message, String recipient) {
        try {
            logger.debug("Iniciando envío de SMS a: " + recipient);
            // Lógica específica de SMS
            System.out.println("Enviando SMS a " + recipient + ": " + message);
            logger.info("SMS enviado exitosamente a: " + recipient);
            // TODO: implementar detalles reales de envío de SMS
        } catch (Exception e) {
            logger.error("Error al enviar SMS a " + recipient + ": " + e.getMessage());
        }
    }
}