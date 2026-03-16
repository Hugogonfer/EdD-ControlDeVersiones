public class NotificationFactory {
    public static NotificationService getService(String type) {
        switch (type.toLowerCase()) {
            case "email": return new EmailService();
            case "sms": return new SMSService();
            case "push": return new PushService();
            default: throw new IllegalArgumentException("Tipo de notificación no reconocido: " + type);
        }
    }
}

