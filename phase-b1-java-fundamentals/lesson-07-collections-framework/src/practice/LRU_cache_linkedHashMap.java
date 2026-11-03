import java.util.*;

public class LRU_cache_linkedHashMap {
    
    public static void main(String[] args) {

        int maxSize = 3;

        LinkedHashMap<Integer, String> cache =
            new LinkedHashMap<>(16, 0.75f, true) {

                @Override
                protected boolean removeEldestEntry(
                    Map.Entry<Integer, String> eldest
                ) {
                    return size() > maxSize;
                }
            };

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        // Access key 1
        cache.get(1);

        // Add new entry; least recently used is removed
        cache.put(4, "D");

        System.out.println(cache);
    }
}
