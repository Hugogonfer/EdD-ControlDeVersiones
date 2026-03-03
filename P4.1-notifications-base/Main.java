// Main.java - Programa principal
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();
        
        // Ejemplos de uso individual
        manager.send("email", "Bienvenido al sistema", "usuario@email.com");
        manager.send("sms", "Tu código es 1234", "+34123456789");
        manager.send("push", "Tienes un nuevo mensaje", "user_device_001");
        
        // Ejemplos de envío a múltiples destinatarios
        System.out.println("\n--- Envío masivo de emails ---");
        List<String> emailRecipients = Arrays.asList(
            "user1@email.com",
            "user2@email.com",
            "user3@email.com"
        );
        manager.sendToMultiple("email", "Notificación importante para todos", emailRecipients);
        
        System.out.println("\n--- Envío masivo de SMS ---");
        List<String> smsRecipients = Arrays.asList(
            "+34111111111",
            "+34222222222",
            "+34333333333"
        );
        manager.sendToMultiple("sms", "Código de verificación: 5678", smsRecipients);
        
        System.out.println("\n--- Envío masivo de push ---");
        List<String> pushRecipients = Arrays.asList(
            "device_001",
            "device_002",
            "device_003",
            "device_004"
        );
        manager.sendToMultiple("push", "Actualización disponible", pushRecipients);
    }
}
