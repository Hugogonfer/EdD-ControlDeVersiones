public class SMSService implements NotificationService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("[LOG] Preparando envío para: " + recipient);
        System.out.println("Enviando SMS a " + recipient + ": " + message);
        System.out.println("[LOG] Envío finalizado.");
    }
}