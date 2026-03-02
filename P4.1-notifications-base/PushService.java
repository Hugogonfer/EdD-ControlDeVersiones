// PushService.java - encapsula la lógica de notificaciones push
public class PushService implements NotificationService {
    @Override
    public void send(String message, String recipient) {
        // Lógica específica de push
        System.out.println("Enviando push a " + recipient + ": " + message);
        // TODO: implementar detalles reales de envío push
    }
}