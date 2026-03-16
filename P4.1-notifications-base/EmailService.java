public class EmailService implements NotificationService {
    @Override
    public boolean sendNotification(String recipient, String message) {
        // Validación básica
        if (recipient == null || recipient.isEmpty() || message == null || message.isEmpty()) {
            System.err.println("[EmailService] Error: destinatario o mensaje vacío.");
            return false;
        }
        // Simulación de envío de email
        System.out.println("[EmailService] Enviando email a " + recipient + ": " + message);
        return true;
    }
}
