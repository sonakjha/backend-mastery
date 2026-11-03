import java.util.List;
import java.util.ArrayList;

public class ListDemo {
    
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        System.out.println(fruits.get(1));
        fruits.remove("Banana");
        System.out.println(fruits);
        System.out.println(fruits.contains("Apple"));
        System.out.println(fruits.size());

    }
}
