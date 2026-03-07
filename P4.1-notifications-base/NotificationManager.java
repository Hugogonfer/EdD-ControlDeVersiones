// NotificationManager.java - Código a refactorizar
public class NotificationManager {
    // TODO: Separar en clases diferentes: EmailService, SMSService, PushService
    // TODO: Aplicar patrón Strategy para los tipos de notificación
    // TODO: Añadir sistema de logs
    
    public void send(String type, String message, String recipient) {
        if (type.equals("email")) {
            // Código para enviar email
            System.out.println("Enviando email a " + recipient + ": " + message);
            // Lógica compleja de email aquí...
        } else if (type.equals("sms")) {
            // Código para enviar SMS
            System.out.println("Enviando SMS a " + recipient + ": " + message);
            // Lógica compleja de SMS aquí...
        } else if (type.equals("push")) {
            // Código para notificación push
            System.out.println("Enviando push a " + recipient + ": " + message);
            // Lógica compleja de push aquí...
            
        }
    }
    
    // TODO: Añadir método para enviar a múltiples destinatarios
    // TODO: Añadir sistema de reintentos
    // TODO: Añadir validación de parámetros
}
