# Java Abstraction & Interfaces

## 1. What is Abstraction?

Abstraction is an OOP concept that hides internal implementation details and exposes only essential functionality to the user.

**Real-world example:**

When you use an ATM:
- You enter your PIN.
- You select withdrawal.
- You receive money.

You don't need to know the internal banking logic, database queries, or transaction processing.

Java provides abstraction through:
1. Abstract classes
2. Interfaces

---

## 2. Abstract Class

An abstract class is a class declared with the `abstract` keyword.

It cannot be instantiated directly and can contain both abstract and concrete methods.

### Syntax

```java
abstract class Animal {

    // Abstract method
    abstract void makeSound();

    // Concrete method
    void sleep() {
        System.out.println("Animal is sleeping");
    }
}
```

### Important characteristics

- Cannot create objects directly.
- Can have constructors.
- Can have instance variables.
- Can have abstract methods.
- Can have concrete methods.
- Can have static and final methods.
- Supports inheritance using `extends`.
- A class can extend only one class.

### Can an abstract class have a constructor?

**Yes.**

Although we cannot instantiate an abstract class directly, its constructor executes whenever a subclass object is created.

```java
abstract class Animal {

    String name;

    Animal(String name) {
        this.name = name;
        System.out.println("Parent constructor");
    }
}

class Dog extends Animal {

    Dog(String name) {
        super(name);
        System.out.println("Child constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Tommy");
    }
}
```

Output:

```text
Parent constructor
Child constructor
```

**Why do we need constructors in abstract classes?**

- Initialize common properties.
- Avoid duplicate initialization code.
- Ensure parent-class state is initialized before child-class behavior.

---

## 3. Abstract Method vs Concrete Method

| Abstract Method | Concrete Method |
|---|---|
| Has no method body | Has a method body |
| Must be implemented by a concrete subclass | Can be inherited directly |
| Declared using `abstract` | No `abstract` keyword |
| Defines what a subclass must do | Defines how common functionality works |

Example:

```java
abstract class Animal {

    abstract void makeSound();

    void sleep() {
        System.out.println("Sleeping...");
    }
}
```

---

## 4. Abstract Class Example: Animal

```java
abstract class Animal {

    abstract void makeSound();

    void sleep() {
        System.out.println("Animal is sleeping");
    }
}

class Cat extends Animal {

    @Override
    void makeSound() {
        System.out.println("Meow");
    }
}

class Dog extends Animal {

    @Override
    void makeSound() {
        System.out.println("Woof");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal cat = new Cat();
        Animal dog = new Dog();

        cat.makeSound();
        cat.sleep();

        dog.makeSound();
        dog.sleep();
    }
}
```

**Concepts covered:**
- Abstraction
- Inheritance
- Method overriding
- Runtime polymorphism

---

# 5. Interface

An interface is a contract that specifies what functionality a class must provide.

It is declared using the `interface` keyword.

A class implements an interface using `implements`.

### Syntax

```java
interface Vehicle {

    void start();

    void stop();
}

class Bike implements Vehicle {

    @Override
    public void start() {
        System.out.println("Bike started");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopped");
    }
}
```

### Important characteristics

- Cannot be instantiated directly.
- Cannot have constructors.
- Cannot have instance variables.
- Fields are implicitly `public static final`.
- Abstract methods are implicitly `public abstract`.
- Supports multiple interface implementation.
- A class uses `implements` to implement interfaces.

---

## 6. Interface Features by Java Version

### Before Java 8

Interfaces primarily contained:
- Abstract methods.
- Constants (`public static final`).

### Java 8 onwards

Interfaces can contain:
- Abstract methods.
- Default methods.
- Static methods.
- Constants.

### Java 9 onwards

Interfaces can also contain private methods and private static methods to reuse internal implementation.

Example:

```java
interface Vehicle {

    int SPEED = 100;

    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }

    static void info() {
        System.out.println("Vehicle interface");
    }

    private void helper() {
        System.out.println("Private helper");
    }
}
```

Remember:
- Default methods have implementations and can be inherited.
- Static interface methods are called using the interface name.
- Private methods are internal helpers and cannot be accessed by implementing classes.
- Interface fields are constants, not instance variables.

---

# 7. Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|---|---|---|
| Keyword | abstract class | interface |
| Object creation | Not directly allowed | Not directly allowed |
| Constructor | Yes | No |
| Instance variables | Yes | No |
| Constants | Yes | Yes |
| Abstract methods | Yes | Yes |
| Concrete methods | Yes | Default/static/private methods |
| Inheritance | Single class inheritance | Multiple interface implementation |
| Access modifiers | Different access modifiers | Public methods by default, private helpers allowed |
| Main purpose | Shared state and behavior | Contract or capability |

### When should you use an abstract class?

Use an abstract class when:
- Classes share common properties.
- Classes need common initialization.
- Classes share common implementation.
- You want to provide a common base class.

### When should you use an interface?

Use an interface when:
- Different classes need the same capability.
- Classes are unrelated.
- You want multiple interface implementation.
- You want loose coupling and flexible design.

---

# 8. Interface with Unrelated Classes

An interface allows unrelated classes to implement the same contract without sharing a parent class.

Example: Payable interface.

```java
interface Payable {

    double calculatePay();
}

class Freelancer implements Payable {

    double hourlyRate = 800;
    int hoursWorked = 100;

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

class Employee implements Payable {

    double salary = 50000;
    double bonus = 5000;

    @Override
    public double calculatePay() {
        return salary + bonus;
    }
}

public class Main {

    public static void main(String[] args) {

        Payable freelancer = new Freelancer();
        Payable employee = new Employee();

        System.out.println(freelancer.calculatePay());
        System.out.println(employee.calculatePay());
    }
}
```

**Important:** Freelancer and Employee do not inherit from each other. Both independently implement Payable.

This is interface-based polymorphism.

---

# 9. Multiple Inheritance in Java

Java does not allow a class to extend multiple classes.

Invalid example:

```java
class A {
    void show() {
        System.out.println("A");
    }
}

class B {
    void show() {
        System.out.println("B");
    }
}

// Compilation error
class C extends A, B {
}
```

### Why doesn't Java support multiple class inheritance?

Because of the Diamond Problem.

## 10. Diamond Problem

Imagine two parent classes contain a method with the same name.

A child class inherits both methods.

Now Java cannot automatically determine which parent implementation should be executed.

Conceptually:

```text
       A
      / \
     B   C
      \ /
       D
```

If B and C inherit or override the same method from A, ambiguity can arise when D inherits from both.

Java avoids this ambiguity by not allowing multiple class inheritance.

**Important:** Java does allow multiple interface implementation because it provides explicit conflict-resolution rules for default methods.

---

# 11. Default Method Conflict in Interfaces

Java 8 introduced default methods.

But what happens when two interfaces contain default methods with the same signature?

Java requires the implementing class to resolve the conflict.

Example:

```java
interface Walkable {

    default void move() {
        System.out.println("Walking");
    }
}

interface Swimmable {

    default void move() {
        System.out.println("Swimming");
    }
}

class Amphibian implements Walkable, Swimmable {

    @Override
    public void move() {

        Walkable.super.move();

        Swimmable.super.move();

    }
}

public class Main {

    public static void main(String[] args) {

        Amphibian obj = new Amphibian();

        obj.move();
    }
}
```

Output:

```text
Walking
Swimming
```

### How to resolve the conflict?

Use:

```java
InterfaceName.super.methodName();
```

Example:

```java
Walkable.super.move();
```

This explicitly invokes the default implementation from Walkable.

**Interview point:** If two unrelated interfaces provide the same default method, the implementing class must override it to resolve the ambiguity.

---

# 12. Multiple Interface Implementation

A Java class can implement multiple interfaces.

Syntax:

```java
class X extends OneAbstractClass implements InterfaceA, InterfaceB, InterfaceC {

}
```

Remember the order:

1. `extends` comes first.
2. `implements` comes second.

Example:

```java
interface Printable {
    void print();
}

interface Scannable {
    void scan();
}

abstract class Machine {
    void powerOn() {
        System.out.println("Machine powered on");
    }
}

class Printer extends Machine implements Printable, Scannable {

    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning...");
    }
}
```

---

# 13. Template Method Design Pattern

The Template Method pattern defines the skeleton of an algorithm in a parent class.

Subclasses implement specific steps without changing the overall workflow.

It is commonly implemented using:
- Abstract classes.
- Concrete methods.
- Abstract methods.
- Final template methods.

### Example: Notification System

```java
abstract class NotificationSender {

    protected String senderId;

    NotificationSender(String senderId) {
        this.senderId = senderId;
    }

    // Template method
    final void send(String recipient, String message) {

        validate(recipient, message);

        deliver(recipient, message);

        logDelivery(recipient);
    }

    private void validate(String recipient, String message) {

        if (recipient == null || message == null) {
            throw new IllegalArgumentException(
                "Recipient and message required"
            );
        }
    }

    // Subclasses provide their own implementation
    protected abstract void deliver(
        String recipient,
        String message
    );

    private void logDelivery(String recipient) {

        System.out.println(
            "[" + senderId + "] Delivered to " + recipient
        );
    }
}

class EmailSender extends NotificationSender {

    EmailSender(String senderId) {
        super(senderId);
    }

    @Override
    protected void deliver(String recipient, String message) {

        System.out.println(
            "Sending EMAIL to " + recipient + ": " + message
        );
    }
}

class SmsSender extends NotificationSender {

    SmsSender(String senderId) {
        super(senderId);
    }

    @Override
    protected void deliver(String recipient, String message) {

        System.out.println(
            "Sending SMS to " + recipient + ": " + message
        );
    }
}

public class Main {

    public static void main(String[] args) {

        NotificationSender email =
            new EmailSender("email-service-01");

        NotificationSender sms =
            new SmsSender("sms-service-01");

        email.send("user@example.com", "Welcome!");

        sms.send("+911234567890", "Your OTP is 4321");
    }
}
```

### Why is send() final?

```java
final void send(String recipient, String message)
```

The `final` keyword prevents subclasses from overriding the send method.

Without final:
- A subclass could skip validation.
- A subclass could skip logging.
- A subclass could change the processing sequence.

With final:
- The parent controls the workflow.
- Subclasses customize only the delivery mechanism.

**Remember:**

Parent class = Defines the algorithm.

Child class = Implements the varying step.

This is the Template Method pattern.

---

# 14. Production-Level Example: Payment Processor

```java
abstract class PaymentProcessor {

    // Fixed payment workflow
    public final void processPayment(double amount) {

        validateAmount(amount);

        executePayment(amount);

        System.out.println("Payment completed");
    }

    // Shared validation
    protected void validateAmount(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Invalid payment amount"
            );
        }

        System.out.println("Amount validated: " + amount);
    }

    // Payment-specific implementation
    protected abstract void executePayment(double amount);
}

class CreditCardProcessor extends PaymentProcessor {

    @Override
    protected void executePayment(double amount) {

        System.out.println(
            "Processing Credit Card payment: " + amount
        );
    }
}

class UpiProcessor extends PaymentProcessor {

    @Override
    protected void executePayment(double amount) {

        System.out.println(
            "Processing UPI payment: " + amount
        );
    }
}

public class Main {

    public static void main(String[] args) {

        PaymentProcessor creditCard =
            new CreditCardProcessor();

        PaymentProcessor upi =
            new UpiProcessor();

        creditCard.processPayment(2500);

        System.out.println();

        upi.processPayment(1000);
    }
}
```

**Production takeaway:** A parent class can enforce a shared workflow while allowing subclasses to implement provider-specific operations.

For real financial applications, use BigDecimal for monetary values and include transaction handling, idempotency, and secure provider integration.

---

# 15. Important Interview Questions

### Beginner

**Q1. What is abstraction?**

Hiding implementation details and exposing essential functionality.

**Q2. Can we create an object of an abstract class?**

No. But we can create a reference of an abstract class pointing to a concrete subclass object.

**Q3. Can an abstract class have a constructor?**

Yes. It executes when a subclass object is created.

**Q4. Can an abstract class have concrete methods?**

Yes.

**Q5. Can an abstract class have zero abstract methods?**

Yes. It can be declared abstract even if it contains only concrete methods.

### Intermediate

**Q6. Can an interface have a constructor?**

No.

**Q7. Can an interface have variables?**

Yes, but they are implicitly public static final constants.

**Q8. Can an interface extend another interface?**

Yes. It can also extend multiple interfaces.

**Q9. Can a class implement multiple interfaces?**

Yes.

**Q10. Can an abstract class implement an interface without implementing all its methods?**

Yes. It can leave them abstract for concrete subclasses to implement.

### Advanced

**Q11. What is the Diamond Problem?**

Ambiguity caused by inheriting the same method through multiple parent paths.

**Q12. How do you resolve conflicting default methods?**

Override the method and explicitly select an implementation using InterfaceName.super.methodName().

**Q13. Can we override a final method?**

No.

**Q14. What is the Template Method pattern?**

A design pattern where a parent class defines the algorithm's structure and subclasses implement selected steps.

**Q15. Why use an interface instead of an abstract class?**

When unrelated classes need to follow the same contract or capability without sharing a common class hierarchy.

---

# 16. Quick Revision Cheat Sheet

```text
ABSTRACT CLASS
→ "is-a" relationship + shared STATE and behavior
→ Single class inheritance only

INTERFACE
→ "can-do" contract
→ Multiple interface implementation allowed

Abstract class CAN have:
→ Constructors
→ Instance fields
→ Concrete methods
→ Abstract methods
→ Static and final methods

Interface CAN have:
→ Constants (public static final)
→ Abstract methods
→ Default methods (Java 8+)
→ Static methods (Java 8+)
→ Private methods (Java 9+)

Interface CANNOT have:
→ Constructors
→ Instance fields

INHERITANCE SYNTAX:

class X extends OneAbstractClass
        implements InterfaceA, InterfaceB {
}

DIAMOND PROBLEM
→ Why Java doesn't allow multiple class inheritance
→ Avoids ambiguity when multiple parents provide implementations

DEFAULT METHOD CONFLICT
→ Two interfaces have the same default method
→ Child class must override and resolve the conflict

TEMPLATE METHOD PATTERN
→ Abstract class defines the algorithm skeleton
→ Final method protects the workflow
→ Subclasses implement the varying step
```

---

# 17. Practice Exercises

1. Create an abstract class `Shape` with an abstract method `calculateArea()`. Implement `Circle` and `Rectangle`.
2. Create an interface `Playable` with `play()`. Implement it in `Guitar` and `Piano`.
3. Create two interfaces with the same default method and resolve the conflict.
4. Create an abstract class `DatabaseConnection` with a final `connect()` method and an abstract `executeQuery()` method. Implement MySQL and PostgreSQL versions.
5. Create a `PaymentProcessor` with Credit Card, UPI, and Net Banking implementations.
6. Create an interface `Logger` and implement it in `FileLogger`, `ConsoleLogger`, and `DatabaseLogger`.
7. Create a class that extends one abstract class and implements three interfaces.

**Recommended practice order:** Exercises 1 → 2 → 3 → 4 → 5 → 6 → 7.

---

## Final Interview Revision

| Concept | Remember |
|---|---|
| Abstraction | Hide implementation details |
| Abstract class | Shared state and behavior |
| Interface | Contract/capability |
| Constructor | Initializes parent state |
| `extends` | Inherit a class or interface |
| `implements` | Implement an interface |
| `default` | Interface method with implementation |
| `InterfaceName.super` | Resolve default method conflicts |
| `final` | Prevent overriding |
| Template Method | Fixed algorithm, customizable steps |
| Diamond Problem | Ambiguous inherited implementations |
