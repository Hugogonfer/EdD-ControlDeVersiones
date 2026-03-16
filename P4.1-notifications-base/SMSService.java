public class SMSService implements NotificationService {
    @Override
    public boolean sendNotification(String recipient, String message) {
        // Validación básica
        if (recipient == null || recipient.isEmpty() || message == null || message.isEmpty()) {
            System.err.println("[SMSService] Error: destinatario o mensaje vacío.");
            return false;
        }
        // Simulación de envío de SMS
        System.out.println("[SMSService] Enviando SMS a " + recipient + ": " + message);
        return true;
    }
}
