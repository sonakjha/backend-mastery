public class VariableBasics {

    public static void main(String[] args) {

        // Primitive Data Types

        byte byteValue = 100;
        short shortValue = 30000;
        int intValue = 100000;
        long longValue = 9999999999L;

        float floatValue = 10.5f;
        double doubleValue = 99.999;

        char charValue = 'A';
        boolean booleanValue = true;

        // Printing Values

        System.out.println("===== Primitive Data Types =====");

        System.out.println("byte    : " + byteValue);
        System.out.println("short   : " + shortValue);
        System.out.println("int     : " + intValue);
        System.out.println("long    : " + longValue);
        System.out.println("float   : " + floatValue);
        System.out.println("double  : " + doubleValue);
        System.out.println("char    : " + charValue);
        System.out.println("boolean : " + booleanValue);

        // Reference Type

        String language = "Java";

        System.out.println("\n===== Reference Type =====");
        System.out.println(language);
    }
}