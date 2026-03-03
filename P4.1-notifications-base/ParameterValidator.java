// ParameterValidator.java - Validación centralizada de parámetros
import java.util.List;

public class ParameterValidator {
    private static final Logger logger = new Logger(ParameterValidator.class);

    // Validar mensaje
    public static boolean validateMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            logger.error("Mensaje vacío o nulo - Validación fallida");
            return false;
        }
        if (message.length() > 1000) {
            logger.warning("Mensaje muy largo (" + message.length() + " caracteres) - Máximo esperado: 1000");
            return false;
        }
        return true;
    }

    // Validar recipient/destinatario
    public static boolean validateRecipient(String recipient) {
        if (recipient == null || recipient.trim().isEmpty()) {
            logger.error("Destinatario vacío o nulo - Validación fallida");
            return false;
        }
        if (recipient.length() < 3) {
            logger.error("Destinatario demasiado corto - Se requiere mínimo 3 caracteres");
            return false;
        }
        return true;
    }

    // Validar tipo de notificación
    public static boolean validateType(String type) {
        if (type == null || type.trim().isEmpty()) {
            logger.error("Tipo de notificación vacío o nulo - Validación fallida");
            return false;
        }
        return true;
    }

    // Validar lista de destinatarios
    public static boolean validateRecipients(List<String> recipients) {
        if (recipients == null) {
            logger.error("Lista de destinatarios nula - Validación fallida");
            return false;
        }
        if (recipients.isEmpty()) {
            logger.error("Lista de destinatarios vacía - Se requiere al menos 1 destinatario");
            return false;
        }
        if (recipients.size() > 1000) {
            logger.warning("Demasiados destinatarios (" + recipients.size() + ") - Máximo esperado: 1000");
            return false;
        }

        // Validar cada destinatario
        for (int i = 0; i < recipients.size(); i++) {
            String recipient = recipients.get(i);
            if (!validateRecipient(recipient)) {
                logger.error("Destinatario inválido en posición " + i + ": " + recipient);
                return false;
            }
        }
        return true;
    }

    // Validar número de reintentos
    public static boolean validateRetries(int retries) {
        if (retries < 0) {
            logger.error("Número de reintentos no puede ser negativo: " + retries);
            return false;
        }
        if (retries > 10) {
            logger.warning("Número de reintentos muy alto (" + retries + ") - Se recomienda máximo 10");
            return false;
        }
        return true;
    }

    // Validar delay de reintentos (milisegundos)
    public static boolean validateRetryDelay(long delayMs) {
        if (delayMs < 0) {
            logger.error("Delay de reintentos no puede ser negativo: " + delayMs + "ms");
            return false;
        }
        if (delayMs > 60000) {
            logger.warning("Delay entre reintentos muy alto (" + delayMs + "ms) - Se recomienda máximo 60000ms");
            return false;
        }
        return true;
    }

    // Validación completa para send()
    public static boolean validateSendParameters(String type, String message, String recipient) {
        return validateType(type) && validateMessage(message) && validateRecipient(recipient);
    }

    // Validación completa para sendToMultiple()
    public static boolean validateSendToMultipleParameters(String type, String message, List<String> recipients) {
        return validateType(type) && validateMessage(message) && validateRecipients(recipients);
    }
}
