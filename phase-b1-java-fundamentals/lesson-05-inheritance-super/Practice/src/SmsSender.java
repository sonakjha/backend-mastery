public class SmsSender extends NotificationSender {

    @Override
    public void send(String message) {
        System.out.println(
            "Sending SMS: " + message
        );
    }
}