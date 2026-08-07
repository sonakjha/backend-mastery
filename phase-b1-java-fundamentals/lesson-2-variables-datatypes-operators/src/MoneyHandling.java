import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyHandling {

    public static BigDecimal calculateTotal(BigDecimal price,
                                            int quantity,
                                            BigDecimal taxRate) {

        BigDecimal subtotal = price.multiply(BigDecimal.valueOf(quantity));

        BigDecimal tax = subtotal.multiply(taxRate);

        return subtotal.add(tax)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {

        BigDecimal price = new BigDecimal("299.99");

        int quantity = 3;

        BigDecimal taxRate = new BigDecimal("0.18");

        BigDecimal total =
                calculateTotal(price, quantity, taxRate);

        System.out.println("Price      : " + price);
        System.out.println("Quantity   : " + quantity);
        System.out.println("Tax Rate   : 18%");
        System.out.println("Total Cost : " + total);

        /*
         * Why BigDecimal?
         *
         * float and double cannot precisely represent decimal values.
         * Financial calculations require exact precision.
         * BigDecimal avoids rounding errors and is the standard choice
         * for banking, e-commerce, and payment systems.
         */
    }
}