public class PushService implements NotificationService {
    @Override
    public boolean sendNotification(String recipient, String message) {
        // Validación básica
        if (recipient == null || recipient.isEmpty() || message == null || message.isEmpty()) {
            System.err.println("[PushService] Error: destinatario o mensaje vacío.");
            return false;
        }
        // Simulación de envío de notificación push
        System.out.println("[PushService] Enviando notificación push a " + recipient + ": " + message);
        return true;
    }
}
