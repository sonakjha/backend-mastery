public class Shape {
    String color;

    public Shape(String color) {
        this.color = color;
    }

    public void describe() {
        System.out.println("This is a shape with color: " + color);
    }
}