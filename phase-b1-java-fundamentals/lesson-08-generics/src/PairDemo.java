
class Pair<K, V> {

    private K key;
    private V value;

    // Constructor
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Get key
    public K getKey() {
        return key;
    }

    // Get value
    public V getValue() {
        return value;
    }

    // Convert object into String
    @Override
    public String toString() {
        return "Pair { key = " + key + ", value = " + value + " }";
    }
}

public class PairDemo {

    public static void main(String[] args) {

        // String key and Integer value
        Pair<String, Integer> pair1 =
                new Pair<>("Age", 27);

        // Integer key and Integer value
        Pair<Integer, Integer> pair2 =
                new Pair<>(101, 5000);

        System.out.println(pair1);
        System.out.println(pair2);

        System.out.println(pair1.getKey());
        System.out.println(pair1.getValue());
    }
}
