interface Walkable {
    default void move() {
        System.out.println("Walking...");
    }
}

interface Swimmable {
    default void move(){
        System.out.println("Swimming...");
    }
}

class Amphibian implements Walkable, Swimmable {
    @Override
    public void move() {
        Walkable.super.move();
        Swimmable.super.move();

        System.out.println("Amphibian can both walk and swim!");
    }

}

public class AdvancedInterfaceConflict {
    public static void main(String[] args) {

        Amphibian amphibian = new Amphibian();
        amphibian.move();

    }
    
}
