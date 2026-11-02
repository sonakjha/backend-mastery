public class Circle extends Shape  {

    double radius;
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public void describe() {
        System.out.println("This is a circle with color: " + color + " and radius: " + radius);
    }

    public static void main(String[] args) {
        Circle circle = new Circle("red", 5.0);
        circle.describe();
    }
}
