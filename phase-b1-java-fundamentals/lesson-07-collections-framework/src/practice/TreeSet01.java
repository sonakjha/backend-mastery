import java.util.*;

//TreeSet stores unique elements in sorted order.
public class TreeSet01 {
    public static void main(String[] args) {
    TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(50);
        numbers.add(10);
        numbers.add(30);
        numbers.add(20);
        numbers.add(10);

        System.out.println(numbers);

        System.out.println(numbers.first());
        System.out.println(numbers.last());
    }
}
