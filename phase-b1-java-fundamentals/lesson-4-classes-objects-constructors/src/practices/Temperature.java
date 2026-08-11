//package practices;

public class Temperature {

    private double celsius;
    public Temperature(double celsius) {
        
        if(celsius < -273.15) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (-273.15°C)");
        }
        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return (celsius * 9/5) + 32;
    }

    public static void main(String[] args){
        Temperature temp1 = new Temperature(25);
        System.out.println("Temperature in Celsius: " + temp1.getCelsius());
        System.out.println("Temperature in Fahrenheit: " + temp1.getFahrenheit());
    }
}
