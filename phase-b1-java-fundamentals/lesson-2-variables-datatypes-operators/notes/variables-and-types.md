# Variables, Data Types, and Type Casting in Java

## Variables

A variable is a named memory location used to store data.

Example

```java
int age = 25;
```

---

# Primitive Data Types

| Type | Size | Default | Example |
|------|------|----------|---------|
| byte | 1 byte | 0 | 100 |
| short | 2 bytes | 0 | 20000 |
| int | 4 bytes | 0 | 100000 |
| long | 8 bytes | 0L | 9999999999L |
| float | 4 bytes | 0.0f | 10.5f |
| double | 8 bytes | 0.0 | 99.99 |
| char | 2 bytes | '\u0000' | 'A' |
| boolean | JVM dependent | false | true |

---

# Reference Types

Reference variables store the address of objects.

Example

```java
String name = "Sonak";
```

---

# Widening Type Casting

Smaller datatype → Larger datatype

Safe

Automatic

Example

```java
int a = 10;
long b = a;
```

---

# Narrowing Type Casting

Larger datatype → Smaller datatype

Requires explicit casting

May lose data

Example

```java
double value = 10.99;
int number = (int) value;
```

Output

```
10
```

---

# Integer Cache

Java caches Integer objects from

```
-128 to 127
```

Example

```java
Integer a = 127;
Integer b = 127;

System.out.println(a == b); // true

Integer x = 200;
Integer y = 200;

System.out.println(x == y); // false
```

Always use `.equals()` to compare wrapper values.

---

# Integer Overflow

```java
int value = Integer.MAX_VALUE;

value++;

System.out.println(value);
```

Output

```
-2147483648
```

---

# BigDecimal

Never use `float` or `double` for money.

Always use

```java
BigDecimal
```

---

# Memory

Primitive Local Variables

```
Stack
```

Objects

```
Heap
```

References

```
Stack → Heap
```

---

# Best Practices

- Use primitive types unless an object is required.
- Prefer `.equals()` over `==` for wrapper objects.
- Avoid unnecessary autoboxing.
- Use `BigDecimal` for currency.
- Be careful with narrowing conversions.

# Cheat Sheet

Primitives: byte, short, int, long, float, double, char, boolean
Stored on:  Stack (value itself)
Reference types: stored on Stack (pointer) → Heap (actual object)

Widening (safe, implicit):   int → long → float → double
Narrowing (risky, explicit): double → int  (data loss possible)

Money → always BigDecimal, never float/double
Integer cache: -128 to 127 → == works; outside range → use .equals()