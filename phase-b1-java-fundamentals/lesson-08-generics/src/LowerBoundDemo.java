
import java.util.*;

public class LowerBoundDemo {

    // Accepts Integer or any superclass of Integer
    public static void fillWithIntegers(List<? super Integer> list) {

        list.add(10);
        list.add(20);
        list.add(30);
    }

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();

        List<Number> numbers = new ArrayList<>();

        List<Object> objects = new ArrayList<>();

        fillWithIntegers(integers);

        fillWithIntegers(numbers);

        fillWithIntegers(objects);

        System.out.println("Integers: " + integers);

        System.out.println("Numbers: " + numbers);

        System.out.println("Objects: " + objects);
    }
}
