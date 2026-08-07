public class CastingDemo {

    public static void main(String[] args) {

        System.out.println("========== Widening ==========");

        int number = 100;

        long longValue = number;
        float floatValue = longValue;
        double doubleValue = floatValue;

        System.out.println("int    : " + number);
        System.out.println("long   : " + longValue);
        System.out.println("float  : " + floatValue);
        System.out.println("double : " + doubleValue);


        System.out.println("\n========== Narrowing ==========");

        double price = 199.99;

        int amount = (int) price;

        System.out.println("double : " + price);
        System.out.println("int    : " + amount);


        System.out.println("\n========== Overflow ==========");

        int value = 130;

        byte b = (byte) value;

        System.out.println("Original int : " + value);
        System.out.println("After Casting: " + b);


        System.out.println("\n========== Integer Overflow ==========");

        int max = Integer.MAX_VALUE;

        System.out.println("Before Overflow : " + max);

        max++;

        System.out.println("After Overflow  : " + max);
    }
}