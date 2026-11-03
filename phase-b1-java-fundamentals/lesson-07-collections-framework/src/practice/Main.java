import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Sonak");
        names.add("Rahul");
        names.add("Ammit");

        System.out.println(names);
        System.out.println(names.get(0));

        names.remove("Rahul");
        System.out.println(names);
    }
    
}
