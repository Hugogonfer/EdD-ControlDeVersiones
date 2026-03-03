// Main.java - Programa principal
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();
        
        // Habilitar persistencia de logs en archivo
        NotificationManager.setFileLoggingEnabled(true);
        
        // Obtener acceso a la fábrica para operaciones avanzadas
        NotificationFactory factory = manager.getFactory();
        
        System.out.println("=== INFORMACIÓN DE SERVICIOS ===");
        factory.printServiceInfo();
        System.out.println("Servicios disponibles: " + Arrays.toString(factory.getAvailableTypes()));
        
        // Registrar un servicio personalizado
        System.out.println("\n=== REGISTRO DE SERVICIO PERSONALIZADO ===");
        class CustomNotificationService implements NotificationService {
            @Override
            public void send(String message, String recipient) {
                System.out.println("Enviando notificación personalizada a " + recipient + ": " + message);
            }
        }
        manager.register("custom", new CustomNotificationService());
        System.out.println("Nuevos servicios disponibles: " + Arrays.toString(factory.getAvailableTypes()));
        
        // Configurar reintentos
        manager.setMaxRetries(2);
        manager.setRetryDelayMs(500); // 500 ms entre reintentos
        
        // Ejemplos de uso individual
        System.out.println("\n=== ENVÍOS INDIVIDUALES ===");
        manager.send("email", "Bienvenido al sistema", "usuario@email.com");
        manager.send("sms", "Tu código es 1234", "+34123456789");
        manager.send("push", "Tienes un nuevo mensaje", "user_device_001");
        manager.send("custom", "Mensaje mediante servicio personalizado", "custom_recipient");
        
        System.out.println("\n=== EJEMPLOS DE VALIDACIÓN ===");
        // Estos intentos fallarán por validación
        manager.send("email", "", "usuario@email.com");  // Mensaje vacío
        manager.send("email", "Mensaje válido", "");     // Destinatario vacío
        manager.send("", "Mensaje válido", "usuario@email.com");  // Tipo vacío
        
        System.out.println("\n=== ENVÍO CON REINTENTOS ===");
        boolean success = manager.sendWithRetry("email", "Email importante con reintentos", "vip@email.com");
        System.out.println("Resultado: " + (success ? "Exitoso" : "Fallido"));
        
        // Ejemplos de envío a múltiples destinatarios
        System.out.println("\n=== ENVÍO MASIVO DE EMAILS ===");
        List<String> emailRecipients = Arrays.asList(
            "user1@email.com",
            "user2@email.com",
            "user3@email.com"
        );
        manager.sendToMultiple("email", "Notificación importante para todos", emailRecipients);
        
        System.out.println("\n=== ENVÍO MASIVO DE SMS ===");
        List<String> smsRecipients = Arrays.asList(
            "+34111111111",
            "+34222222222",
            "+34333333333"
        );
        manager.sendToMultiple("sms", "Código de verificación: 5678", smsRecipients);
        
        System.out.println("\n=== ENVÍO MASIVO DE PUSH ===");
        List<String> pushRecipients = Arrays.asList(
            "device_001",
            "device_002",
            "device_003",
            "device_004"
        );
        manager.sendToMultiple("push", "Actualización disponible", pushRecipients);
        
        System.out.println("\n=== ENVÍO MASIVO CON REINTENTOS ===");
        List<String> criticalRecipients = Arrays.asList(
            "critical1@email.com",
            "critical2@email.com",
            "critical3@email.com"
        );
        manager.sendToMultipleWithRetry("email", "ALERTA CRÍTICA - Requiere confirmación", criticalRecipients);
        
        System.out.println("\n=== INFORMACIÓN FINAL ===");
        System.out.println("Total de servicios registrados: " + factory.getServiceCount());
        factory.printServiceInfo();
        System.out.println("\nLogs guardados en: logs/notification_system.log");
    }
}
