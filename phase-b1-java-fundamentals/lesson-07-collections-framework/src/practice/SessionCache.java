import java.util.*;

public class SessionCache {
    // LinkedHashMap with access-order = true gives you LRU (Least Recently Used) behavior for free
    private final Map<String, String> cache;

    public SessionCache(int maxSize) {
        this.cache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > maxSize;   // auto-evict the least recently used entry
            }
        };
    }

    public void put(String sessionId, String userData) {
        cache.put(sessionId, userData);
    }

    public String get(String sessionId) {
        return cache.get(sessionId);
    }

    public static void main(String[] args) {
        SessionCache sessionCache = new SessionCache(3);
        sessionCache.put("s1", "UserA");
        sessionCache.put("s2", "UserB");
        sessionCache.put("s3", "UserC");
        sessionCache.get("s1");          // accessing s1 marks it "recently used"
        sessionCache.put("s4", "UserD"); // triggers eviction of LEAST recently used (s2, not s1)

        System.out.println(sessionCache.cache.keySet()); // [s3, s1, s4]
    }
}