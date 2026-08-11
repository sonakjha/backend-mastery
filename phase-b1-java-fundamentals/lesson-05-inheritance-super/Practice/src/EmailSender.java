public class EmailSender extends NotificationSender {

    @Override
    public void send(String message) {
        System.out.println(
            "Sending Email: " + message
        );
    }
}