public class BiginerAnimal {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.makeSound();
        dog.sleep();
        System.out.println("=====================");
        cat.makeSound();
        cat.sleep();
    }
}

abstract class Animal {
    abstract void makeSound();

    void sleep() {
        System.out.println("Animal is sleeping...");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks: Woof!");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meows: Meow!");
    }
}
