// NotificationManager.java - Código a refactorizar
public class NotificationManager {
    // TODO: Aplicar patrón Strategy para los tipos de notificación
    // TODO: Añadir sistema de logs
    
    public void send(String type, String message, String recipient) {
        if (type.equals("email")) {
            new EmailService().send(message, recipient);
        } else if (type.equals("sms")) {
            new SMSService().send(message, recipient);
        } else if (type.equals("push")) {
            new PushService().send(message, recipient);
        }
    }
    
    // TODO: Añadir método para enviar a múltiples destinatarios
    // TODO: Añadir sistema de reintentos
    // TODO: Añadir validación de parámetros
}
