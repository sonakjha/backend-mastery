public class OverloadDemo {

    public void describe(String name) {
        System.out.println("Name : " + name);
    }

    public void describe(int age) {
        System.out.println("Age : " + age);
    }

    public void describe(String name, int age) {
        System.out.println("Name : " + name + ", Age : " + age);
    }

    public static void main(String[] args) {

        OverloadDemo demo = new OverloadDemo();

        demo.describe("Sonak");
        demo.describe(25);
        demo.describe("Sonak", 25);
    }
}