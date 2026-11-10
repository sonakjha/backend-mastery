import java.util.List;

public class GenericsDemo {
    
    static<T> T firstElement(List<T> list) {
        return list.get(0);
    }

    static<T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    static double sumAll(List<? extends Number> numbers) {
        double sum = 0;
        for(Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    static void addIntegers(List<? super Integer> target) {
        target.add(1);
        target.add(2);
    }

    public static void main(String[] args) {
        System.out.println(firstElement(List.of("A", "B")));
        System.out.println(max(10,20));
        System.out.println(max("apple", "banana"));
        System.out.println(sumAll(List.of(1, 2, 3.5)));
        System.out.println(sumAll(List.of(1.5, 2.5, 3.5)));
    }
}
