//package practices;

public class User {
    private String username;
    private String email;
    private int age;

    public User(String username, String email, int age) {

        if(username==null || username.isBlank()){
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException(
                "Email must contain '@'"
            );
        }

        if (age < 0 || age > 150) {
            throw new IllegalArgumentException(
                "Age must be between 0 and 150"
            );
        }

        this.username = username;
        this.email = email;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Username: " + username);
        System.out.println("Email   : " + email);
        System.out.println("Age     : " + age);
    }

    public static void main(String[] args) {
        User user1 = new User(
                    "sonak",
                    "sonak@example.com",
                    25
                );
    }
}
