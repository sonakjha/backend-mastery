# Methods, Access Modifiers & Packages

---

## What is a Method?

A method is a reusable block of code that performs a specific task.

Example

```java
public void greet() {
    System.out.println("Hello");
}
```

---

## Method Syntax

```java
accessModifier returnType methodName(parameters) {

}
```

Example

```java
public int add(int a, int b) {
    return a + b;
}
```

---

## Method Components

- Access Modifier
- Return Type
- Method Name
- Parameters
- Method Body
- Return Statement

---

# Access Modifiers

| Modifier | Same Class | Same Package | Subclass | Other Package |
|----------|------------|--------------|-----------|---------------|
| private | ✅ | ❌ | ❌ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| protected | ✅ | ✅ | ✅ | ❌* |
| public | ✅ | ✅ | ✅ | ✅ |

---

## Package

A package is a namespace that organizes related classes.

Example

```
billing
```

```
user
```

```
payment
```

---

## Method Overloading

Same method name

Different parameter list

Compile-time polymorphism

Example

```java
describe(String name)

describe(int age)

describe(String name, int age)
```

---

## Stack Frame

Every method call creates a new stack frame.

A stack frame stores:

- Parameters
- Local Variables
- Return Address

When the method finishes, the frame is automatically removed.

---

## Best Practices

- Prefer private visibility.
- Expose only necessary public methods.
- Keep methods short.
- One method should perform one task.
- Group related classes into packages.

---

## Common Errors

- Infinite recursion → StackOverflowError
- Two overloads differing only by return type → Compile Error
- Making everything public → Poor encapsulation

---

## Cheat Sheet

```
private
↓

default

↓

protected

↓

public
```

Method Overloading

```
Same Name
Different Parameters
Compile Time
```

Method Overriding

```
Same Signature
Subclass
Runtime
```