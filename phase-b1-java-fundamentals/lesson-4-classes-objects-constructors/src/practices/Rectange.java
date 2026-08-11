//package practices;

public class Rectange {

    double weidth;
    double height;
    public Rectange(double side){
        this(side, side);
    }

    public Rectange(double weidth, double height) {
        this.weidth = weidth;
        this.height = height;
    }

    

    public double getArea() {
        return weidth * height;
    }

    public static void main(String[] args){
        
        Rectange rectange1 = new Rectange(5, 10);
        System.out.println("Area of rectangle: " + rectange1.getArea());

        Rectange square = new Rectange(4);
        System.out.println("Area of square: " + square.getArea());

    }
}
