public class Student extends Person  {
    String studentId;

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    @Override
    public String getDetails() {
        String personDetails = super.getDetails();
        return personDetails + ", Student ID: " + studentId;
    }

    public static void main(String[] args) {
        Student student = new Student("Alice", 20, "S12345");
        System.out.println(student.getDetails());
    }

}
