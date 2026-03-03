// EmailService.java - encapsula la lógica de envío de emails
public class EmailService implements NotificationService {
    private static final Logger logger = new Logger(EmailService.class);
    @Override
    public void send(String message, String recipient) {
        try {
            logger.debug("Iniciando envío de email a: " + recipient);
            // Lógica específica de email
            System.out.println("Enviando email a " + recipient + ": " + message);
            logger.info("Email enviado exitosamente a: " + recipient);
            // TODO: implementar detalles reales de envío de email
        } catch (Exception e) {
            logger.error("Error al enviar email a " + recipient + ": " + e.getMessage());
        }
    }
}