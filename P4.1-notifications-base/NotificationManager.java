// NotificationManager.java - Código a refactorizar
import java.util.Map;
import java.util.HashMap;

public class NotificationManager {
    // Strategy registry: map notification type -> service
    private final Map<String, NotificationService> strategies = new HashMap<>();

    // Register default services so existing code keeps working
    public NotificationManager() {
        register("email", new EmailService());
        register("sms", new SMSService());
        register("push", new PushService());
    }

    // Allow callers to register or override strategies
    public void register(String type, NotificationService service) {
        if (type == null || service == null) return;
        strategies.put(type, service);
    }

    public void send(String type, String message, String recipient) {
        NotificationService svc = strategies.get(type);
        if (svc == null) {
            System.out.println("Tipo de notificación desconocido: " + type);
            return;
        }
        svc.send(message, recipient);
    }

    // TODO: Añadir método para enviar a múltiples destinatarios
    // TODO: Añadir sistema de reintentos
    // TODO: Añadir validación de parámetros
}
