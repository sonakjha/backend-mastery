
import java.util.*;

public class UpperBoundDemo {

    // Accepts Number or any subclass of Number
    public static double sum(List<? extends Number> list) {

        double total = 0;

        for (Number num : list) {
            total += num.doubleValue();
        }

        return total;
    }

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(10, 20, 30);

        List<Double> decimals = Arrays.asList(10.5, 20.5, 30.5);

        System.out.println("Integer sum: " + sum(integers));

        System.out.println("Double sum: " + sum(decimals));
    }
}
