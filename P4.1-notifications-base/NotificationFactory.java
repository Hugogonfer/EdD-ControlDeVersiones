// NotificationFactory.java - Fábrica de servicios de notificación
import java.util.Map;
import java.util.HashMap;

public class NotificationFactory {
    private static final Logger logger = new Logger(NotificationFactory.class);
    private static final Map<String, NotificationService> registry = new HashMap<>();
    private static NotificationFactory instance;

    // Singleton pattern - una única instancia de la fábrica
    private NotificationFactory() {
        logger.info("NotificationFactory instanciada");
    }

    public static synchronized NotificationFactory getInstance() {
        if (instance == null) {
            instance = new NotificationFactory();
            // Registrar servicios por defecto
            instance.registerDefault();
        }
        return instance;
    }

    // Registrar servicios de notificación por defecto
    private void registerDefault() {
        register("email", new EmailService());
        register("sms", new SMSService());
        register("push", new PushService());
        logger.info("Servicios por defecto registrados: email, sms, push");
    }

    // Registrar un nuevo servicio o sobrescribir uno existente
    public void register(String type, NotificationService service) {
        if (!ParameterValidator.validateType(type)) {
            logger.error("Tipo de servicio inválido - No se puede registrar");
            return;
        }
        
        if (service == null) {
            logger.error("Servicio nulo para tipo: " + type + " - No se puede registrar");
            return;
        }

        registry.put(type, service);
        logger.info("Servicio registrado: " + type + " -> " + service.getClass().getSimpleName());
    }

    // Obtener un servicio por tipo
    public NotificationService getService(String type) {
        if (!ParameterValidator.validateType(type)) {
            logger.error("Tipo de servicio inválido");
            return null;
        }

        NotificationService service = registry.get(type);
        if (service == null) {
            logger.warning("Servicio no encontrado para tipo: " + type);
            return null;
        }

        logger.debug("Servicio obtenido: " + type);
        return service;
    }

    // Verificar si un tipo de servicio está registrado
    public boolean isServiceRegistered(String type) {
        if (!ParameterValidator.validateType(type)) {
            return false;
        }
        boolean exists = registry.containsKey(type);
        logger.debug("Verificación de servicio: " + type + " - " + (exists ? "Registrado" : "No registrado"));
        return exists;
    }

    // Obtener cantidad de servicios registrados
    public int getServiceCount() {
        return registry.size();
    }

    // Obtener lista de tipos de servicios disponibles
    public String[] getAvailableTypes() {
        return registry.keySet().toArray(new String[0]);
    }

    // Resetear la fábrica (eliminar todos los servicios)
    public void reset() {
        registry.clear();
        logger.warning("NotificationFactory ha sido reseteada - Todos los servicios fueron eliminados");
    }

    // Imprimir información de servicios registrados
    public void printServiceInfo() {
        logger.info("=== Información de Servicios Registrados ===");
        logger.info("Total de servicios: " + registry.size());
        for (String type : registry.keySet()) {
            logger.info("  - " + type + ": " + registry.get(type).getClass().getSimpleName());
        }
    }
}
