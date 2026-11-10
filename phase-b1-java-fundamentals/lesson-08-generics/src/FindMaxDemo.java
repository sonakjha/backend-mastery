
import java.util.*;

public class FindMaxDemo {

    // T must implement Comparable<T>
    public static <T extends Comparable<T>> T findMax(List<T> list) {

        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        T max = list.get(0);

        for (T item : list) {

            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 50, 20, 90, 30);

        List<String> names = Arrays.asList("Java", "Python", "C++");

        System.out.println("Maximum number: " + findMax(numbers));

        System.out.println("Maximum string: " + findMax(names));
    }
}
