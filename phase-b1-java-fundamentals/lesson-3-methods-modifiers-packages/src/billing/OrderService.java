public class OrderService {

    public double calculateOrderTotal(double price, int quantity) {
        validateInput(price, quantity);
        double subtotal = price * quantity;
        return applyTax(subtotal);
    }

    public void printReceipt(double price, int quantity) {
        double total = calculateOrderTotal(price, quantity);
        System.out.println("Total: " + total);
    }

    private void validateInput(double price, int quantity) {
        if (price < 0 || quantity <= 0) {
            throw new IllegalArgumentException("Invalid order input");
        }
    }

    private double applyTax(double subtotal){
        double taxRate = 0.08;
        return subtotal * (1 + taxRate);
    }
    public static void main(String[] args) {
        OrderService service = new OrderService();
        service.printReceipt(50.0,3);
    }
}