public class NotificationDemo {

    public static void main(String[] args) {

        NotificationSender email =
                new EmailSender();

        NotificationSender sms =
                new SmsSender();

        email.send("Your order has been shipped.");

        sms.send("Your OTP is 123456.");
    }
}