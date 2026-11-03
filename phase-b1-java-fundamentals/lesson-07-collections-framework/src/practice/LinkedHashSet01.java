import java.util.*;
//LinkedHashSet maintains insertion order while preventing duplicates.
public class LinkedHashSet01 {

    public static void main(String[] args) {

        LinkedHashSet<String> cities = new LinkedHashSet<>();

        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Pune");
        cities.add("Delhi");

        System.out.println(cities);
    }
    
}
