// NotificationManager.java - Código a refactorizar
public class NotificationManager {
    // TODO: Aplicar patrón Strategy para los tipos de notificación
    // TODO: Añadir sistema de logs
    
    public void send(String type, String message, String recipient) {
        if (type.equals("email")) {
            NotificationService svc = new EmailService();
            svc.send(message, recipient);
        } else if (type.equals("sms")) {
            NotificationService svc = new SMSService();
            svc.send(message, recipient);
        } else if (type.equals("push")) {
            NotificationService svc = new PushService();
            svc.send(message, recipient);
        }
    }
    
    // TODO: Añadir método para enviar a múltiples destinatarios
    // TODO: Añadir sistema de reintentos
    // TODO: Añadir validación de parámetros
}
