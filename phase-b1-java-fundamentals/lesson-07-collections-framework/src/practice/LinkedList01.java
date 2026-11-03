import java.util.*;

public class LinkedList01 {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();

        names.add("Sonak");
        names.add("Rohit");
        names.add("Amit");

        names.addFirst("Firsst");
        System.out.println(names);

        names.addLast("Last");
        System.out.println(names);

        names.removeFirst();
        System.out.println(names);
        names.removeLast();
        System.out.println(names);

        System.out.println(names.get(1));
    }
}
