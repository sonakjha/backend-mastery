public class NotificationDemo {
    public static void main(String[] args) {
        NotificationSender email = new EmailSender("email-service-01");
        NotificationSender sms = new SmsSender("sms-service-01");

        email.send("sonak@example.com", "Welcome!");
        sms.send("+917529968149", "Your OTP is 4321");
    }
}

abstract class NotificationSender {
    protected String senderId;

    NotificationSender(String senderId) {
        this.senderId = senderId;
    }

    final void send(String recipient, String message) {
        validate(recipient, message);
        deliver(recipient, message);
        logDelivery(recipient);
    }

    private void validate(String recipient, String message) {
        if(recipient == null || recipient.isEmpty() || message == null || message.isEmpty()) {
            throw new IllegalArgumentException ("Recipient cannot be null or empty");
        }
    }

    protected abstract void deliver(String recipient, String message);

    private void logDelivery(String recipient) {
        System.out.println("[" + senderId + "] Delivered to " + recipient);
    }
}

class EmailSender extends NotificationSender {
    EmailSender(String senderId) {
        super(senderId);
    }

    @Override
    protected void deliver(String recipient, String message) {
        System.out.println("Sending EMAIL to " + recipient + ": " + message);
    }
}

class SmsSender extends NotificationSender {
    SmsSender(String senderId) {
        super(senderId);
    }

    @Override
    protected void deliver(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}