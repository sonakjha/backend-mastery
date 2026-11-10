public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value){
        this.value = value;
    }

    public static void main(String[] args) {
        Box<String> nameBox = new Box<>("Sonak");
        String name = nameBox.get();
        System.out.println(name);

        Box<Integer> ageBox = new Box<>(25);
        int age = ageBox.get();
        System.out.println(age);
    }
}