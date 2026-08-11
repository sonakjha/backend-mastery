Classes, Objects & Constructors

1. Class

A class is a blueprint or template used to create objects. Itdefines fields, methods, and constructors.

public class Car {
    String brand;
    String model;
    int year;
}

2. Object

An object is an actual instance of a class.

Car car = new Car();

One class can create many independent objects:

Car car1 = new Car();
Car car2 = new Car();

3. Fields

Fields represent the state/data of an object.

public class Book {
    String title;
    String author;
    int pages;
}

4. Methods

Methods represent the behavior of an object.

public void displayInfo() {
    System.out.println(title);
}

5. Constructor

A constructor initializes an object.

public Book(String title, String author, int pages) {
    this.title = title;
    this.author = author;
    this.pages = pages;
}

Constructor rules: - Same name as the class - No return type - Runs whennew creates an object - Can accept parameters - Can be overloaded -Can call another constructor with this(...)

6. Default Constructor

If no constructor is written, Java provides a no-argument constructor.

public class Car {
    String brand;
}

Car car = new Car();

If you write a constructor yourself, Java does not automatically providethe no-argument constructor.

7. Parameterized Constructor

public Car(String brand, String model) {
    this.brand = brand;
    this.model = model;
}

Usage:

Car car = new Car("Toyota", "Camry");

8. this Keyword

this refers to the current object.

public Book(String title) {
    this.title = title;
}

Here title is the constructor parameter and this.title is theobject's field.

9. Constructor Overloading

A class can have multiple constructors with different parameter lists.

public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
}

public Rectangle(double side) {
    this.width = side;
    this.height = side;
}

10. Constructor Chaining with this(...)

One constructor can call another constructor in the same class.

public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
}

public Rectangle(double side) {
    this(side, side);
}

this(...) must be the first statement in the constructor.

11. Object Creation

Car car = new Car("Toyota", "Camry", 2024);

Conceptually:

new
 ↓
Create object
 ↓
Run constructor
 ↓
Initialize fields
 ↓
Return reference

The object is allocated on the heap. A local reference variable isassociated with the current stack frame.

STACK                         HEAP

car ───────────────────────► Car Object
                              ├── brand = "Toyota"
                              ├── model = "Camry"
                              └── year = 2024

12. Constructor Validation

Constructors are a good place to validate object state.

public Product(String name, double price) {

    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Product name cannot be blank");
    }

    if (price < 0) {
        throw new IllegalArgumentException("Product price cannot be negative");
    }

    this.name = name;
    this.price = price;
}

This prevents invalid objects from being created.

13. Encapsulation

Encapsulation means controlling access to an object's internal state.

Prefer:

private double balance;

instead of:

public double balance;

Then expose controlled behavior:

public void deposit(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }

    balance += amount;
}

14. IllegalArgumentException

Use IllegalArgumentException when a constructor or method receives aninappropriate argument.

if (age < 0 || age > 150) {
    throw new IllegalArgumentException(
        "Age must be between 0 and 150"
    );
}

15. Constructor vs Method

Constructor                     Method

Initializes object              Performs behaviorSame name as class              Can have any valid nameNo return type                  Has a return type or voidCalled during object creation   Called explicitlyCan be overloaded               Can be overloaded

16. Common Mistakes

Constructor with a return type

Wrong:

public void Book(String title) {
}

Correct:

public Book(String title) {
}

Forgetting this

Wrong:

public Book(String title) {
    title = title;
}

Correct:

public Book(String title) {
    this.title = title;
}

Incorrect this(...)

this(...) must be the first statement.

Wrong:

public Rectangle(double side) {
    System.out.println("Creating square");
    this(side, side);
}

Correct:

public Rectangle(double side) {
    this(side, side);
}

17. Cheat Sheet

class
    ↓
Blueprint

object
    ↓
Instance of a class

new X()
    ↓
Create object → call constructor → return reference

constructor
    ↓
Initialize object

this
    ↓
Current object

this(...)
    ↓
Call another constructor in the same class

private
    ↓
Hide internal state/implementation

constructor validation
    ↓
Prevent invalid objects

18. Key Principle

A well-designed class should protect its own state.

Instead of:

public int age;

prefer:

private int age;

and validate it during construction.

This supports the principle:

Make illegal states unrepresentable.

19. Lesson Summary

Class
  ↓
Blueprint

Object
  ↓
Real instance

Constructor
  ↓
Initializes object

this
  ↓
Current object

this(...)
  ↓
Constructor chaining

private
  ↓
Encapsulation

Validation
  ↓
Protect object state

Core idea: Classes define structure and behavior, objects holdactual state, and constructors initialize and validate that state soobjects start life in a valid condition.