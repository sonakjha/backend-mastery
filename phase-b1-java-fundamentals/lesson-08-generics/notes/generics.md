# Java Generics -- Complete Notes (Java 17)

## 1. What are Generics?

Generics let classes, interfaces, and methods work with different
reference types while preserving compile-time type safety.

Without generics:

``` java
List list = new ArrayList();
list.add("Java");
list.add(100);
String name = (String) list.get(0);
```

This permits mixed values and requires casts. A wrong cast can cause
`ClassCastException`.

With generics:

``` java
List<String> names = new ArrayList<>();
names.add("Java");
// names.add(100); // Compile-time error
String name = names.get(0);
```

Benefits: type safety, fewer casts, reusable code, clearer APIs.

## 2. Generic classes

A generic class declares type parameters after its name.

``` java
class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

public class BoxDemo {
    public static void main(String[] args) {
        Box<String> text = new Box<>("Hello");
        Box<Integer> number = new Box<>(100);

        System.out.println(text.getValue());
        System.out.println(number.getValue());
    }
}
```

`T` is a placeholder for a type. In `Box<String>`, T is String; in
`Box<Integer>`, T is Integer.

Common naming conventions: - `T`: Type - `E`: Element - `K`: Key - `V`:
Value - `N`: Number - `R`: Result

These are conventions, not keywords.

### Pair\<K, V\> -- two type parameters

``` java
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair { key = " + key + ", value = " + value + " }";
    }
}

public class PairDemo {
    public static void main(String[] args) {
        Pair<String, Integer> p1 = new Pair<>("Age", 27);
        Pair<Integer, Integer> p2 = new Pair<>(101, 5000);

        System.out.println(p1);
        System.out.println(p2);
    }
}
```

`Pair<String, Integer>` means K is String and V is Integer.

## 3. Generic methods

A generic method declares its own type parameter before its return type.

``` java
public static <T> T identity(T value) {
    return value;
}
```

Example:

``` java
public class GenericMethodDemo {
    public static <T> void printValue(T value) {
        System.out.println(value);
    }

    public static void main(String[] args) {
        printValue("Java");
        printValue(100);
        printValue(10.5);
    }
}
```

### Generic swap method

``` java
public class SwapDemo {
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] numbers = {10, 20, 30};
        swap(numbers, 0, 2);
        System.out.println(java.util.Arrays.toString(numbers));

        String[] names = {"Java", "Python", "C++"};
        swap(names, 0, 2);
        System.out.println(java.util.Arrays.toString(names));
    }
}
```

`<T>` declares the method's type parameter; `T[]` means the method
accepts an array of that type.

## 4. Bounded type parameters

Bounds restrict which types can be used.

### Upper bound: `T extends Number`

``` java
class NumberBox<T extends Number> {
    private T number;

    public NumberBox(T number) {
        this.number = number;
    }

    public double asDouble() {
        return number.doubleValue();
    }
}
```

Allowed examples: `Integer`, `Double`, `Float`, `Long`, `Short`, `Byte`,
and `Number`. `String` is not allowed because it does not extend Number.

### Bounded generic method: findMax

``` java
import java.util.*;

public class FindMaxDemo {
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        T max = list.get(0);

        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 50, 20, 90, 30);
        List<String> names = Arrays.asList("Java", "Python", "C++");

        System.out.println(findMax(numbers)); // 90
        System.out.println(findMax(names));   // Python
    }
}
```

`Comparable<T>` provides `compareTo()`: - Positive: this value is
greater. - Negative: this value is smaller. - Zero: equal in ordering.

Strings are compared lexicographically (based on character values).

### Multiple bounds: `<T extends A & B>`

A type parameter may have one class bound and multiple interface bounds.

``` java
interface Printable {
    void print();
}

class Document {
}

class Report extends Document implements Printable {
    public void print() {
        System.out.println("Printing report");
    }
}

class Printer<T extends Document & Printable> {
    public void printItem(T item) {
        item.print();
    }
}
```

If a class is one of the bounds, it must appear first. Use `&` between
bounds.

## 5. Wildcards (`?`)

`?` means an unknown type.

``` java
List<?> items;
```

It can refer to `List<String>`, `List<Integer>`, `List<Double>`, etc.

``` java
public static void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}
```

You can read values as `Object`. You cannot add an arbitrary non-null
value because the actual element type is unknown; `null` is allowed. You
can still remove elements or clear the list.

### `List<?>` vs `List<Object>`

-   `List<Object>` is specifically a list whose element type is Object;
    you can add any object.
-   `List<?>` is a list of some particular but unknown type; you cannot
    safely add an arbitrary object.

## 6. Upper bounded wildcard: `? extends T`

`List<? extends Number>` means a list of some unknown type that is
Number or a subclass of Number. It can accept `List<Number>`,
`List<Integer>`, `List<Double>`, etc.

``` java
import java.util.*;

public class UpperBoundDemo {
    public static double sum(List<? extends Number> list) {
        double total = 0;

        for (Number number : list) {
            total += number.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(10, 20, 30);
        List<Double> doubles = Arrays.asList(10.5, 20.5, 30.5);

        System.out.println(sum(ints));    // 60.0
        System.out.println(sum(doubles)); // 61.5
    }
}
```

Reading is safe as Number:

``` java
Number n = list.get(0);
```

Adding an arbitrary value is prohibited:

``` java
List<Double> decimals = new ArrayList<>();
List<? extends Number> list = decimals;

// list.add(100); // Compile-time error
```

If this were allowed, an Integer could be inserted into the original
`List<Double>`. `null` is the exception.

## 7. Lower bounded wildcard: `? super T`

`List<? super Integer>` means a list of Integer or a superclass of
Integer. It can accept `List<Integer>`, `List<Number>`, or
`List<Object>`.

``` java
import java.util.*;

public class LowerBoundDemo {
    public static void fillWithIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        List<Number> nums = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        fillWithIntegers(ints);
        fillWithIntegers(nums);
        fillWithIntegers(objects);

        System.out.println(ints);
        System.out.println(nums);
        System.out.println(objects);
    }
}
```

Adding Integer is safe because Integer is also a Number and an Object.
When reading, the safe general type is Object:

``` java
Object value = list.get(0);
```

## 8. PECS -- Producer Extends, Consumer Super

PECS is a rule of thumb for choosing wildcard bounds.

**Producer Extends:** If the collection supplies values for your code to
read, use `extends`.

``` java
public static double sum(List<? extends Number> values) {
    double total = 0;
    for (Number n : values) total += n.doubleValue();
    return total;
}
```

**Consumer Super:** If your code puts values into the collection, use
`super`.

``` java
public static void addNumbers(List<? super Integer> values) {
    values.add(10);
    values.add(20);
}
```

Remember: - Producer → `extends` - Consumer → `super` - If you need both
flexible reading and writing, an exact type parameter may be more
suitable.

## 9. Invariance

Java generic types are invariant. Even though Integer extends Number,
`List<Integer>` is not a subtype of `List<Number>`.

``` java
List<Integer> integers = new ArrayList<>();

// List<Number> numbers = integers; // Compile-time error
```

If this assignment were allowed, someone could add a Double through the
`List<Number>` reference into the original Integer list.

Use a wildcard when appropriate:

``` java
List<? extends Number> numbers = integers;
```

This allows reading as Number, but not arbitrary additions.

## 10. Type erasure

Java generics are checked by the compiler and implemented largely
through type erasure: - The compiler checks generic type correctness. -
Generic type arguments are generally erased from ordinary runtime object
types. - The compiler inserts casts where required. - `List<String>` and
`List<Integer>` are both runtime instances of `ArrayList`.

Example:

``` java
List<String> names = new ArrayList<>();
List<Integer> numbers = new ArrayList<>();

System.out.println(names.getClass() == numbers.getClass()); // true
```

Type erasure does not mean generics are pointless at runtime; generic
type rules are still checked at compile time.

## 11. Restrictions of generics

### Cannot instantiate T directly

``` java
class Box<T> {
    // T value = new T(); // Compile-time error
}
```

The actual type is not known in a way that lets Java instantiate it
directly. Pass an instance or a factory/supplier instead.

### Cannot create a generic array of T directly

``` java
class Box<T> {
    // T[] values = new T[10]; // Compile-time error
}
```

Arrays are reified at runtime, while generic type arguments are erased.

### Cannot use parameterized types with instanceof

``` java
// obj instanceof List<String> // Compile-time error
if (obj instanceof List<?>) {
    System.out.println("It is a List");
}
```

### Cannot use primitive type arguments

``` java
// List<int> numbers = new ArrayList<>(); // Compile-time error
List<Integer> numbers = new ArrayList<>();
List<Double> decimals = new ArrayList<>();
```

Use wrapper classes such as Integer, Double, and Boolean.

### Static members cannot use the enclosing class's T

``` java
class Box<T> {
    // static T value; // Compile-time error
}
```

A static member belongs to the class, not to a particular `Box<T>`
instance. A static generic method can declare its own type parameter:

``` java
class Utility {
    public static <T> void print(T value) {
        System.out.println(value);
    }
}
```

### Cannot create arrays of parameterized types

``` java
// List<String>[] lists = new List<String>[10]; // Compile-time error
```

## 12. Raw types -- avoid them

A raw type omits the generic type argument.

Avoid:

``` java
List list = new ArrayList();
list.add("Java");
list.add(100);
```

Problems: compile-time type safety is lost, casts may be needed, and
incorrect casts can cause `ClassCastException`.

Prefer:

``` java
List<String> names = new ArrayList<>();
names.add("Java");
// names.add(100); // Compile-time error
```

Raw types mainly exist for compatibility with older pre-generics code.

## 13. Production example -- Result`<T>`{=html}

A Result wrapper can represent either successful data or a failure
message.

**Result.java**

``` java
public class Result<T> {
    private final boolean success;
    private final T data;
    private final String error;

    private Result(boolean success, T data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return success ? "Success: " + data : "Failure: " + error;
    }
}
```

**ParseAgeDemo.java**

``` java
public class ParseAgeDemo {
    public static Result<Integer> parseAge(String input) {
        try {
            int age = Integer.parseInt(input);

            if (age < 0) {
                return Result.failure("Age cannot be negative");
            }

            return Result.success(age);
        } catch (NumberFormatException e) {
            return Result.failure("Please enter a valid numeric age");
        }
    }

    public static void main(String[] args) {
        System.out.println(parseAge("27"));
        System.out.println(parseAge("-5"));
        System.out.println(parseAge("abc"));
    }
}
```

Output:

``` text
Success: 27
Failure: Age cannot be negative
Failure: Please enter a valid numeric age
```

`Integer.parseInt()` throws `NumberFormatException` for non-numeric
input. This is a learning example; production APIs should also consider
whether `getData()` should be callable on a failure result and whether a
richer error model is needed.

## 14. Generics cheat sheet

``` text
class Box<T>                    → generic class
<T> T method(...)               → generic method (<T> before return type)
<T extends Number>              → upper bound (T is Number or subclass)
<T extends A & B>               → multiple bounds
List<?>                         → unknown type (read-only-ish)
List<? extends T>               → PRODUCER: read as T, can't add
List<? super T>                 → CONSUMER: write T, reads give Object

PECS: Producer Extends, Consumer Super

Type erasure: generics exist at COMPILE time only
Can't: new T(), new T[], instanceof List<String>, List<int>, static T
Invariance: List<Integer> is NOT a List<Number>
Never use raw types
```

### Cheat-sheet clarifications

-   `List<?>` is not immutable or completely read-only: you can remove
    elements and add `null`, but cannot add an arbitrary non-null value.
-   `List<? extends T>` supports safe reads as T; arbitrary additions
    are prohibited.
-   `List<? super T>` supports adding T values; reads are safely typed
    as Object.
-   `static T` is invalid when T is the enclosing class's type
    parameter. A static generic method can declare its own `<T>`.
-   `new T[]` is not allowed directly. Generic arrays need special
    handling and are best avoided in ordinary application code.
-   Type erasure is a runtime implementation detail; generic type checks
    are still enforced by the compiler.

## 15. Common interview questions

1.  What are generics and what problems do they solve?
2.  What is the difference between a generic class and a generic method?
3.  What is a bounded type parameter?
4.  Difference between `T extends Number` and `? extends Number`?
5.  Difference between `List<?>`, `List<Object>`, and `List<String>`?
6.  Explain PECS with an example.
7.  Why can't you add an Integer to `List<? extends Number>`?
8.  Why can you add an Integer to `List<? super Integer>`?
9.  What is type erasure?
10. Why can't you write `new T()` or `new T[]`?
11. Why is `List<Integer>` not a subtype of `List<Number>`?
12. What is a raw type and why should it be avoided?
13. Can a static generic method exist?

## 16. Coding practice

**Beginner** 1. Create `Box<T>` with `setValue()` and `getValue()`. 2.
Create `Pair<K,V>` with constructor, getters, and `toString()`. 3.
Create `Pair<String,Integer>` and `Pair<Integer,Integer>`.

**Intermediate** 1. Write `printArray(T[] array)`. 2. Write
`swap(T[] array, int i, int j)`. 3. Write
`<T extends Comparable<T>> T findMax(List<T> list)`. 4. Test with
Integer and String lists.

**Advanced** 1. Write `double sum(List<? extends Number> list)`. 2.
Write `void fillWithIntegers(List<? super Integer> list)`. 3. Try adding
an Integer to `List<? extends Number>` and inspect the compile error. 4.
Explain why `List<Integer>` cannot be assigned to `List<Number>`.

**Production-level** 1. Build `Result<T>` with `success()` and
`failure()`. 2. Add `isSuccess()`, `getData()`, and `getError()`. 3.
Implement `Result<Integer> parseAge(String input)`. 4. Return failure
for non-numeric and negative input using try/catch.

## 17. Recommended learning order

1.  Why generics are needed.
2.  Generic classes (`Box<T>`, `Pair<K,V>`).
3.  Generic methods (`printArray`, `swap`).
4.  Bounded types (`T extends Number`).
5.  Wildcards (`?`, `? extends`, `? super`).
6.  PECS and invariance.
7.  Type erasure and restrictions.
8.  `Result<T>` example.
9.  Interview questions and coding exercises.
