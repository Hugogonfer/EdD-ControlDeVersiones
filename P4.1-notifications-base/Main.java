// Main.java - Programa principal
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();
        
        // Habilitar persistencia de logs en archivo
        NotificationManager.setFileLoggingEnabled(true);
        
        // Configurar reintentos
        manager.setMaxRetries(2);
        manager.setRetryDelayMs(500); // 500 ms entre reintentos
        
        // Ejemplos de uso individual
        manager.send("email", "Bienvenido al sistema", "usuario@email.com");
        manager.send("sms", "Tu código es 1234", "+34123456789");
        manager.send("push", "Tienes un nuevo mensaje", "user_device_001");
        
        System.out.println("\n--- Ejemplos de validación de parámetros ---");
        // Estos intentos fallarán por validación
        manager.send("email", "", "usuario@email.com");  // Mensaje vacío
        manager.send("email", "Mensaje válido", "");     // Destinatario vacío
        manager.send("", "Mensaje válido", "usuario@email.com");  // Tipo vacío
        
        System.out.println("\n--- Envío con reintentos (método individual) ---");
        boolean success = manager.sendWithRetry("email", "Email importante con reintentos", "vip@email.com");
        System.out.println("Resultado del envío con reintentos: " + (success ? "Exitoso" : "Fallido"));
        
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
        
        System.out.println("\n--- Envío masivo con reintentos ---");
        List<String> criticalRecipients = Arrays.asList(
            "critical1@email.com",
            "critical2@email.com",
            "critical3@email.com"
        );
        manager.sendToMultipleWithRetry("email", "ALERTA CRÍTICA - Requiere confirmación", criticalRecipients);
        
        System.out.println("\n--- Logs guardados en: logs/notification_system.log ---");
    }
}
