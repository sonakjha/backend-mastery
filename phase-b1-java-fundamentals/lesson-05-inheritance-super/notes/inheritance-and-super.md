# Lesson 5 — Inheritance and the `super` Keyword

## 1. What Is Inheritance & Why It Exists

Inheritance lets one class (subclass/child) acquire the fields and methods of
another class (superclass/parent), while also adding or changing behavior.

**Problem it solves:** shared logic across related classes (e.g. `Car`, `Truck`,
`Motorcycle` all sharing `brand`, `speed`, `startEngine()`) would otherwise be
duplicated. Inheritance defines shared logic once in a parent class; children
inherit it automatically and add only what's unique to themselves.

This is the "is-a" relationship — a `Truck` **is a** `Vehicle`. This differs from
composition ("has-a", e.g. a `Car` **has an** `Engine`) — covered later in Phase B7.

---

## 2. Internal Working

```java
class Vehicle {
    String brand;
    void startEngine() { System.out.println("Engine starting..."); }
}

class Car extends Vehicle {
    int doors;
}
```

- Every `Car` object on the heap contains all fields from `Vehicle` (`brand`)
  plus its own fields (`doors`)
- `Car` automatically has access to `startEngine()` without redefining it
- JVM builds an internal method lookup table (vtable) per class — this is how
  it knows, at runtime, exactly which version of a method to call (critical
  for method overriding)

### Constructor chaining with `super()`

Every subclass constructor **implicitly calls `super()`** (parent's no-arg
constructor) as its first action, unless you explicitly call `super(args)`.
This guarantees the parent portion of the object is fully initialized before
the child portion runs.

```java
class Vehicle {
    String brand;
    Vehicle(String brand) {
        this.brand = brand;
        System.out.println("Vehicle constructor: " + brand);
    }
}

class Car extends Vehicle {
    int doors;
    Car(String brand, int doors) {
        super(brand);   // MUST be first line
        this.doors = doors;
        System.out.println("Car constructor: " + doors + " doors");
    }
}
```

Execution order for `new Car("Toyota", 4)`:
1. `Car` constructor starts
2. `super(brand)` called first → `Vehicle` constructor runs completely
   → prints "Vehicle constructor: Toyota"
3. Rest of `Car` constructor runs → prints "Car constructor: 4 doors"

Same rule as `this()` from Lesson 4 (must be first line, same reason: guarantee
a single clean initialization path) — just chaining to the parent class instead
of another constructor in the same class.

---

## 3. Overriding vs Overloading — Full Picture

| | Overloading (Lesson 3) | Overriding (this lesson) |
|---|---|---|
| Location | Same class | Parent + child classes |
| Method name | Same | Same |
| Parameters | Different | Same (identical signature) |
| Resolved | Compile time | Runtime |
| Question answered | "Which version fits these args?" | "Which class's version, based on actual object type?" |

```java
class Vehicle {
    void makeSound() { System.out.println("Generic vehicle sound"); }
}

class Car extends Vehicle {
    @Override
    void makeSound() { System.out.println("Vroom vroom"); }
}

public class Test {
    public static void main(String[] args) {
        Vehicle v = new Car();   // reference type: Vehicle, actual object: Car
        v.makeSound();           // "Vroom vroom" - runtime decides via actual object!
    }
}
```

This is **runtime polymorphism**: even though `v` is *declared* as `Vehicle`,
the JVM looks at the actual object on the heap (`Car`) at runtime and calls
that class's version of `makeSound()`.

---

## 4. Examples Recap

- **Animal / Dog** — basic inheritance, inherited `eat()`, own `bark()`
- **Employee / Manager** — overriding `calculateSalary()`, using
  `super.calculateSalary()` to reuse and extend parent logic
- **PaymentMethod / CreditCardPayment / PayPalPayment** — polymorphic list
  pattern (forward-looking preview using `List`/`ArrayList`, not required for
  the assignment — Collections Framework comes later in Phase B1)

### The `this.` vs `super.` trap (Q3 from interview questions)

If `Manager.calculateSalary()` called `this.calculateSalary()` instead of
`super.calculateSalary()`:
- `this` refers to the actual object (a `Manager`)
- Method calls resolve by *actual object type* at runtime, not by "the
  enclosing class's code"
- So `this.calculateSalary()` resolves to `Manager`'s own overridden version →
  calls itself → infinite recursion → `StackOverflowError`
- `super.calculateSalary()` bypasses runtime lookup and goes straight to the
  parent's implementation — this is the correct way to reuse parent logic

---

## 5. Best Practices
- Always use `@Override` — lets the compiler catch typos (a misspelled method
  name becomes an accidental overload, and `@Override` flags it as an error)
- Use `super(args)` explicitly when the parent has no no-arg constructor, or
  to be clear about initialization order
- Favor inheritance only for genuine "is-a" relationships — not just for code
  reuse (that's composition's job)
- Keep inheritance hierarchies shallow (2-3 levels max)

## 6. Anti-Patterns
- Using inheritance purely for code reuse without a real "is-a" relationship
  (classic example: `Stack extends Vector` in old Java)
- Forgetting `@Override`, accidentally creating an overload via a typo'd
  signature
- Deep inheritance chains (`A extends B extends C extends D...`)
- Overriding a method and ignoring parent behavior when `super.method()`
  should have been called to preserve shared logic

---

## 7. Edge Cases
- If a parent has **no** no-arg constructor, every subclass **must**
  explicitly call `super(args)` — otherwise compile error
- Fields are **not polymorphic** — a subclass field with the same name as the
  parent's **hides** it rather than overrides it; which one is accessed
  depends on the *reference type*, not the actual object type
- `static` methods **cannot** be overridden — only hidden; resolved at
  compile time based on reference type
- `private` methods are not inherited at all, so they cannot be overridden by
  a subclass

## 8. Performance & Memory
- Runtime method dispatch has a small lookup overhead vs a direct call, but
  modern JVMs optimize this heavily (inline caching) — negligible in practice
- A subclass object's heap footprint includes storage for its own fields
  **and** every inherited field up its hierarchy — not a shared reference to
  a parent template

## 9. Real Company Examples
- Java's own `Stack extends Vector` — a cautionary tale about misusing
  inheritance for reuse rather than a genuine "is-a" relationship
- Spring Framework's `DataAccessException` hierarchy — textbook proper
  inheritance, each subclass a genuine specific kind of failure

---

## 10. Cheat Sheet

```
extends        → establishes "is-a" inheritance relationship
super(args)    → calls parent constructor, MUST be first line
super.method() → calls parent's version of an overridden method
@Override      → compiler-checked annotation, catches signature mistakes

Overloading: same class, different params, compile-time
Overriding:  parent/child, same signature, runtime (polymorphism)

Fields  → NOT polymorphic (resolved by reference type)
Methods → polymorphic (resolved by actual object type at runtime)
static methods  → CANNOT be overridden, only hidden
private methods → CANNOT be overridden (not inherited)
```

## 11. Summary
Inheritance lets a subclass acquire and extend a parent's fields and methods,
modeling "is-a" relationships. Constructors chain via `super(args)` (must be
first statement), guaranteeing the parent initializes before the child.
Overriding is resolved at runtime based on the object's actual type — the
mechanism behind polymorphism. Overloading is resolved at compile time based
on argument types/count. Fields are never polymorphic; methods always are
(except `static` and `private` ones).

## 12. Interview Q&A Summary

1. **`this()` vs `super()`** — `this()` calls another constructor in the same
   class; `super()` calls the parent's constructor. Both must be the first
   statement, so they can't appear together in one constructor.
2. **No constructor in subclass** — Java provides a default no-arg
   constructor, which implicitly calls `super()`.
3. **`this.calculateSalary()` instead of `super.calculateSalary()`** —
   infinite recursion → `StackOverflowError` (see section 4 above for full
   explanation).
4. **Overriding a `private` parent method** — not possible; `private` methods
   aren't inherited at all.