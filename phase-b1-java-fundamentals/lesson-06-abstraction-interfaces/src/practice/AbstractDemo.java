abstract class Shape {
    String color;

    Shape(String color){
        this.color = color;
    }

    abstract double calculateArea();

    void displayInfo() {
        System.out.println("Color: " + color + ", Area: " + calculateArea());
    }
}

class Circle extends Shape {
    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class AbstractDemo {
    public static void main(String[] args) {
        Circle circle = new Circle("Red", 5.0);
        circle.displayInfo();
    }
}