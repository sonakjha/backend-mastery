abstract class PaymentProcessor {
    public final void processPayment(double amount) {
        validatePayment(amount);
        executePayment(amount);
        System.out.println("Payment processed successfully.");
    }

    protected void validatePayment(double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Invalid payment amount: " + amount);
        }
        System.out.println("Payment amount validated: $" + amount);
    }

    protected abstract void executePayment(double amount);
}

class CreditCardPaymentProcessor extends PaymentProcessor {
    @Override
    protected void executePayment(double amount) {
        System.out.println(
                "Processing credit card payment of $" + amount
        );
    }
}

class UpiProcessor extends PaymentProcessor  {
    
    @Override
    protected void executePayment(double amount) {
        System.out.println(
                "Processing UPI payment of $" + amount
        );
    }
}

public class ProductionPaymentDemo {
    public static void main(String[] args) {
        
        PaymentProcessor creditCardProcessor = new CreditCardPaymentProcessor();
        creditCardProcessor.processPayment(100.0);

        System.out.println("=====================");

        PaymentProcessor upiProcessor = new UpiProcessor();
        upiProcessor.processPayment(200.0);
    }
    
}
