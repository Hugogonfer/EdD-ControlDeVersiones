// NotificationManager.java - Código a refactorizar
public class NotificationManager{

    
    public void send(NotificationService service, String message, String recipient) {
        if (service == null || message == null || recipient == null || recipient.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros de notificación no pueden ser nulos o vacíos");
        }
        service.send(message, recipient);
    }
    
    // TODO: Añadir método para enviar a múltiples destinatarios
    // TODO: Añadir sistema de reintentos
    // TODO: Añadir validación de parámetros
}


