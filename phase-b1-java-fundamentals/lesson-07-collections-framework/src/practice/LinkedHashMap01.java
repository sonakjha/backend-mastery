import java.util.*;

public class LinkedHashMap01 {
    
    public static void main(String[] args) {
        //LinkedHashMap<Integer, String> map = new LinkedHashMap();
        TreeMap<Integer, String> map = new TreeMap();

        map.put(3, "Three");
        map.put(1, "One");
        map.put(2, "Two");

        System.out.println(map);
    }
}
