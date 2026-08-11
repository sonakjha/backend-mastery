public class Product {
    String name;
    double price;

    public Product(String name, double price){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if(price<0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        this.name = name;
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("Product Name: " + name);
        System.out.println("Product Price: $" + price);
    }

    public static void main(String[] args) {
        //Product product1= new Product(null, 0);
        Product product2= new Product("Laptop", 999.99);
        //product1.displayInfo();
        product2.displayInfo();

    }
}