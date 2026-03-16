public interface NotificationService {
    /**
     * Envía una notificación al destinatario especificado.
     * @param recipient El destinatario de la notificación.
     * @param message El mensaje a enviar.
     * @return true si la notificación fue enviada correctamente, false en caso contrario.
     */
    boolean sendNotification(String recipient, String message);
}
