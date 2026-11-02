public class InterfaceDemo {
    public static void main (String[] args) {
        Duck duck = new Duck();
        duck.fly();
        duck.swim();
        duck.land();
    }
}

interface Flyable {
    void fly();

    default void land() {
        System.out.println("Landing...");
    }
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck is flyiny... ");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming...");
    }
}
