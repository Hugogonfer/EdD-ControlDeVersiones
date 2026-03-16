// NotificationManager.java - Código a refactorizar
public class NotificationManager {
    // TODO: Separar en clases diferentes: EmailService, SMSService, PushService
    // TODO: Aplicar patrón Strategy para los tipos de notificación
    // TODO: Añadir sistema de logs
    
    public void send(String type, String message, String recipient) {
        if (type == null || type.isEmpty()) {
            LogService.error("Tipo de notificación vacío.");
            return;
        }
        if (recipient == null || recipient.isEmpty()) {
            LogService.error("Destinatario vacío.");
            return;
        }
        if (message == null || message.isEmpty()) {
            LogService.error("Mensaje vacío.");
            return;
        }
        LogService.info("Intentando enviar notificación tipo '" + type + "' a '" + recipient + "'");
        NotificationService service;
        switch (type.toLowerCase()) {
            case "email":
                service = new EmailService();
                break;
            case "sms":
                service = new SMSService();
                break;
            case "push":
                service = new PushService();
                break;
            default:
                LogService.error("Tipo de notificación desconocido: " + type);
                return;
        }
        boolean result = service.sendNotification(recipient, message);
        if (result) {
            LogService.info("Notificación enviada correctamente a '" + recipient + "' por " + type);
        } else {
            LogService.error("Error al enviar notificación " + type + " a " + recipient);
        }
    }
    
    // TODO: Añadir método para enviar a múltiples destinatarios
    // TODO: Añadir sistema de reintentos
    // TODO: Añadir validación de parámetros
}
