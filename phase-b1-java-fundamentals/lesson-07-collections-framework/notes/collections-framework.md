# Java Collections Framework --- Complete Notes

> **Goal:** Understand the Java Collections Framework (JCF), choose the
> right collection, use its common methods, understand ordering and
> duplicates, and prepare for Java interviews and backend development.
>
> Examples use Java 8+ syntax and are compatible with Java 17 unless
> otherwise noted.

------------------------------------------------------------------------

## 1. What is the Java Collections Framework?

The Java Collections Framework (JCF) is a set of **interfaces,
implementation classes, and utility algorithms** used to store and
manipulate groups of objects.

Without collections, you might use arrays:

``` java
int[] numbers = new int[3];
numbers[0] = 10;
numbers[1] = 20;
numbers[2] = 30;
```

Arrays have a fixed size. Collections such as `ArrayList` can grow and
shrink dynamically.

``` java
List<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);
```

### Main benefits

-   Dynamic storage: many collections can grow or shrink.
-   Ready-made data structures: lists, sets, queues, and maps.
-   Reusable operations: searching, sorting, iteration, and more.
-   Type safety through generics, such as `List<String>`.
-   Different implementations offer different performance and ordering
    guarantees.

### Three parts of the framework

1.  **Interfaces** describe what operations a data structure supports:
    `List`, `Set`, `Queue`, `Map`.
2.  **Implementation classes** provide the actual data structure:
    `ArrayList`, `HashSet`, `HashMap`, etc.
3.  **Utility algorithms** are provided mainly by the `Collections`
    class: sorting, reversing, shuffling, and more.

------------------------------------------------------------------------

## 2. Collections hierarchy

A simplified view of the hierarchy:

``` text
Iterable
   |
Collection
   |
   +-- List
   |     +-- ArrayList
   |     +-- LinkedList
   |     +-- Vector (legacy)
   |            +-- Stack (legacy)
   |
   +-- Set
   |     +-- HashSet
   |     |     +-- LinkedHashSet
   |     +-- SortedSet
   |           +-- NavigableSet
   |                 +-- TreeSet
   |
   +-- Queue
         +-- PriorityQueue
         +-- Deque
               +-- ArrayDeque
               +-- LinkedList

Map (separate from Collection)
   +-- HashMap
   |     +-- LinkedHashMap
   +-- SortedMap
         +-- NavigableMap
               +-- TreeMap
```

**Important:** `Map` is part of the Collections Framework, but it does
**not** extend the `Collection` interface. A `Map` stores key-value
pairs; a `Collection` stores individual elements.

`LinkedList` implements both `List` and `Deque`, so it can be used as a
list or as a queue/deque.

------------------------------------------------------------------------

## 3. Quick collection cheat sheet

``` text
List   → ordered, duplicates allowed, indexed
  ArrayList  → dynamic array, O(1) get, O(n) middle insert
  LinkedList → doubly-linked list, O(1) end insert, O(n) get

Set    → no duplicates
  HashSet       → O(1) average, no guaranteed order
  LinkedHashSet → O(1) average, insertion order
  TreeSet       → O(log n), sorted order

Map    → key-value pairs, keys unique (NOT a Collection subtype)
  HashMap       → O(1) average, no guaranteed order
  LinkedHashMap → O(1) average, insertion/access order
  TreeMap       → O(log n), sorted by key

Rule: override equals() AND hashCode() together, always,
when defining logical equality for objects used in hash-based collections.

Fail-fast: structurally modifying a collection directly while iterating
may cause ConcurrentModificationException.

Safe removal during iteration: use Iterator.remove().
```

Complexities are typical/average-case expectations, not guarantees for
every possible input or implementation state. See the detailed sections
below.

------------------------------------------------------------------------

# PART A --- LIST

## 4. What is List?

`List` is an interface that represents an ordered collection.

Main properties:

-   Maintains an order.
-   Allows duplicate elements.
-   Allows access by index.
-   Usually allows `null`, depending on implementation.
-   Indexes start at `0`.

Example:

``` java
List<String> names = new ArrayList<>();

names.add("Amit");
names.add("Priya");
names.add("Amit");

System.out.println(names);
System.out.println(names.get(1));
```

Possible output:

``` text
[Amit, Priya, Amit]
Priya
```

### Common List methods

  Method                 Purpose
  ---------------------- ----------------------------------
  `add(value)`           Add an element at the end
  `add(index, value)`    Insert at an index
  `get(index)`           Read an element
  `set(index, value)`    Replace an element
  `remove(index)`        Remove by index
  `remove(object)`       Remove the first matching object
  `contains(value)`      Check whether a value exists
  `indexOf(value)`       Find first matching index
  `lastIndexOf(value)`   Find last matching index
  `size()`               Number of elements
  `isEmpty()`            Check whether list is empty
  `clear()`              Remove all elements
  `subList(from, to)`    View a range; `to` is exclusive

### Example

``` java
import java.util.*;

public class ListDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Amit");
        names.add("Priya");
        names.add("Rahul");

        System.out.println(names);        // [Amit, Priya, Rahul]
        System.out.println(names.get(0)); // Amit

        names.set(1, "Neha");
        System.out.println(names);        // [Amit, Neha, Rahul]

        names.remove("Amit");
        System.out.println(names);        // [Neha, Rahul]
    }
}
```

## 5. ArrayList

`ArrayList` is a resizable-array implementation of `List`.

### How it works

-   Stores elements in an internal array.
-   Provides fast index-based access.
-   When its internal array fills up, it creates a larger array and
    copies elements.
-   It is not synchronized by default.

``` java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.get(1)); // 20
```

### Complexity

  Operation                        Typical time
  ---------------------------- ----------------
  `get(index)`                             O(1)
  `set(index, value)`                      O(1)
  Add at end                     O(1) amortized
  Insert at beginning/middle               O(n)
  Remove at beginning/middle               O(n)
  Search with `contains()`                 O(n)

**Why is middle insertion O(n)?**

Elements after the insertion point must be shifted to make room.

Example:

``` text
Before: [10, 20, 30, 40]
Insert 99 at index 1
After:  [10, 99, 20, 30, 40]
```

The elements `20`, `30`, and `40` move one position to the right.

### When to use ArrayList

Use it when:

-   You need frequent reads by index.
-   You mostly add elements at the end.
-   You need a general-purpose list.

## 6. LinkedList

`LinkedList` is a doubly linked list implementation. Each node stores an
element and links to the previous and next nodes.

Conceptually:

``` text
null <- [10] <-> [20] <-> [30] -> null
          prev     next
```

### How it works

-   Each node points to its previous and next node.
-   It does not need to shift all following elements when a node is
    unlinked.
-   It must traverse nodes to reach an arbitrary index.
-   It implements both `List` and `Deque`.

### Complexity

  Operation                                       Typical time
  ---------------------------------- -------------------------
  `get(index)`                                            O(n)
  `set(index, value)`                      O(n) to locate node
  Add/remove at first or last end                         O(1)
  Insert/remove at arbitrary index     O(n) to locate position
  Search with `contains()`                                O(n)

**Important:** `list.add(index, value)` on a LinkedList is not
automatically O(1). Finding the index takes O(n). Once the node is
located, linking/unlinking is O(1).

### Example

``` java
import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();

        names.add("Amit");
        names.add("Priya");
        names.addFirst("Rahul");
        names.addLast("Neha");

        System.out.println(names); // [Rahul, Amit, Priya, Neha]

        names.removeFirst();
        names.removeLast();

        System.out.println(names); // [Amit, Priya]
    }
}
```

### ArrayList vs LinkedList

  -----------------------------------------------------------------------
  Feature                 ArrayList               LinkedList
  ----------------------- ----------------------- -----------------------
  Internal structure      Resizable array         Doubly linked nodes

  Index access            O(1)                    O(n)

  Add at end              O(1) amortized          O(1)

  Add/remove at ends      End add is amortized    O(1)
                          O(1); front remove O(n) 

  Middle                  O(n) due to shifting    O(n) to locate, O(1) to
  insertion/removal                               relink

  Memory overhead         Usually lower           Higher due to node
                                                  links

  Common use              General-purpose list    Deque operations and
                          and frequent reads      frequent end changes
  -----------------------------------------------------------------------

**Practical default:** Prefer `ArrayList` unless you have a specific
reason to use `LinkedList`.

## 7. Vector and Stack (legacy classes)

### Vector

-   Resizable array like ArrayList.
-   Its individual methods are synchronized.
-   Often slower than ArrayList for single-threaded use.
-   Legacy class; modern concurrent designs often use other approaches.

### Stack

-   Legacy LIFO (Last In, First Out) stack class.
-   Extends `Vector`.
-   Main methods: `push()`, `pop()`, `peek()`, `empty()`.
-   Prefer `ArrayDeque` for a stack in most new code.

``` java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

System.out.println(stack.peek()); // 20
System.out.println(stack.pop());  // 20
System.out.println(stack.pop());  // 10
```

------------------------------------------------------------------------

# PART B --- SET

## 8. What is Set?

`Set` is an interface that stores unique elements.

Main properties:

-   Does not allow duplicates according to its equality/ordering rules.
-   Does not provide index-based access.
-   Ordering depends on the implementation.
-   Null support depends on the implementation.

Example:

``` java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);

System.out.println(numbers.size()); // 2
```

The second `10` is not added.

## 9. HashSet

`HashSet` stores unique elements using a hash table. Internally, it uses
a `HashMap` to store elements as keys with a shared dummy value.

Properties:

-   Average O(1) add, remove, and contains.
-   No guaranteed iteration order.
-   Allows one `null` element.
-   Not synchronized.

``` java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add("Priya");
names.add("Amit");

System.out.println(names); // Order is not guaranteed
```

### Complexity

  Operation                                           Average time
  -------------- -------------------------------------------------
  `add()`                                                     O(1)
  `remove()`                                                  O(1)
  `contains()`                                                O(1)
  Iteration        O(size + backing table capacity), approximately

Worst-case behavior can be slower due to collisions; modern Java
HashMap-based implementations can treeify heavily populated buckets
under certain conditions.

## 10. LinkedHashSet

`LinkedHashSet` extends HashSet behavior by maintaining a linked order
of entries.

Properties:

-   Unique elements.
-   Preserves insertion order.
-   Average O(1) add, remove, and contains.
-   Allows one `null`.

``` java
Set<String> names = new LinkedHashSet<>();

names.add("Amit");
names.add("Priya");
names.add("Rahul");
names.add("Amit");

System.out.println(names); // [Amit, Priya, Rahul]
```

The duplicate `Amit` is ignored, and the original insertion order is
retained.

Use it when you need uniqueness and insertion order.

## 11. TreeSet

`TreeSet` stores unique elements in sorted order. It is backed by a
balanced tree (a Red-Black tree in standard Java implementations).

Properties:

-   Sorted according to natural ordering or a supplied `Comparator`.
-   Add, remove, and contains are O(log n).
-   Does not allow duplicates according to its comparison result.
-   With natural ordering, `null` is not supported.

``` java
Set<Integer> numbers = new TreeSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(10);

System.out.println(numbers); // [10, 30, 40]
```

### Reverse order

``` java
Set<Integer> numbers = new TreeSet<>(Comparator.reverseOrder());

numbers.add(10);
numbers.add(40);
numbers.add(30);

System.out.println(numbers); // [40, 30, 10]
```

### HashSet vs LinkedHashSet vs TreeSet

  -------------------------------------------------------------------------
  Feature             HashSet           LinkedHashSet     TreeSet
  ------------------- ----------------- ----------------- -----------------
  Duplicates          Not allowed       Not allowed       Not allowed by
                                                          comparison

  Order               No guarantee      Insertion order   Sorted order

  Typical             Average O(1)      Average O(1)      O(log n)
  add/search/remove                                       

  Null                One allowed       One allowed       Natural ordering
                                                          does not support
                                                          null

  Underlying          HashMap           Hash table +      Red-Black tree
  structure                             linked order      
  -------------------------------------------------------------------------

## 12. equals() and hashCode() --- critical for HashSet

When you store custom objects in `HashSet`, understand how Java
identifies duplicates.

Consider:

``` java
class Employee {
    String id;
    String name;

    Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Now:

``` java
Set<Employee> employees = new HashSet<>();

employees.add(new Employee("E1", "Alice"));
employees.add(new Employee("E1", "Alice Updated"));

System.out.println(employees.size());
```

Without overriding `equals()` and `hashCode()`, the size is normally
`2`, because the default equality behavior compares object identity.

If employee ID defines logical identity, override both methods
consistently:

``` java
import java.util.Objects;

class Employee {
    private final String id;
    private final String name;

    Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;

        Employee other = (Employee) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" + id + ", " + name + "}";
    }
}
```

Now two Employee objects with the same ID are considered equal by this
definition, so the HashSet stores only one of them.

### The contract

1.  If `a.equals(b)` is true, `a.hashCode()` must equal `b.hashCode()`.
2.  Equal hash codes do not guarantee that two objects are equal.
3.  Unequal objects may have the same hash code (a hash collision).
4.  Fields used by `equals()` and `hashCode()` should not be changed
    while the object is stored in a hash-based collection.

**Rule:** Override `equals()` and `hashCode()` together whenever you
define logical equality for custom objects used in hash-based
collections such as `HashSet` and `HashMap`.

------------------------------------------------------------------------

# PART C --- MAP

## 13. What is Map?

A `Map` stores data in key-value pairs.

Example:

``` text
E1 → Alice
E2 → Bob
E3 → Priya
```

Properties:

-   Keys are unique.
-   Values can be duplicated.
-   A key maps to at most one value at a time.
-   `Map` is not a subtype of `Collection`.
-   Different Map implementations have different ordering and null
    rules.

``` java
Map<Integer, String> employees = new HashMap<>();

employees.put(101, "Amit");
employees.put(102, "Priya");
employees.put(103, "Rahul");

System.out.println(employees.get(102)); // Priya
```

### Common Map methods

  -----------------------------------------------------------------------
  Method                              Purpose
  ----------------------------------- -----------------------------------
  `put(key, value)`                   Insert or replace a mapping

  `get(key)`                          Get value for a key

  `getOrDefault(key, defaultValue)`   Get value or fallback

  `remove(key)`                       Remove mapping

  `containsKey(key)`                  Check key

  `containsValue(value)`              Check value

  `putIfAbsent(key, value)`           Insert only if key is absent/mapped
                                      to null

  `replace(key, value)`               Replace an existing mapping

  `keySet()`                          View of keys

  `values()`                          View of values

  `entrySet()`                        View of key-value entries

  `size()`                            Number of mappings

  `isEmpty()`                         Check whether empty

  `clear()`                           Remove all mappings
  -----------------------------------------------------------------------

## 14. HashMap

`HashMap` stores key-value pairs using a hash table.

Properties:

-   Average O(1) `put()`, `get()`, and `remove()`.
-   No guaranteed iteration order.
-   Allows one `null` key and multiple `null` values.
-   Not synchronized.
-   Keys should have stable `equals()` and `hashCode()` behavior while
    stored.

``` java
Map<String, Integer> scores = new HashMap<>();

scores.put("Amit", 90);
scores.put("Priya", 95);
scores.put("Rahul", 80);

System.out.println(scores.get("Priya")); // 95
```

### What happens with a duplicate key?

``` java
scores.put("Amit", 90);
scores.put("Amit", 99);

System.out.println(scores.get("Amit")); // 99
```

The old value is replaced. A Map cannot contain two separate mappings
for the same key.

### How HashMap works internally (conceptual)

1.  It calls the key's `hashCode()`.
2.  It uses the hash to select a bucket.
3.  It checks keys in that bucket using `equals()` to find a matching
    key.
4.  If the key exists, its value is replaced.
5.  Otherwise, a new mapping is stored.

A collision occurs when different keys map to the same bucket. Java
handles collisions within the bucket. In modern Java implementations,
sufficiently large collision-heavy buckets may be converted to tree bins
when capacity conditions are met.

### Common implementation details

For the standard Java HashMap implementation:

-   Default initial capacity is 16 when the table is first allocated.
-   Default load factor is 0.75.
-   Resizing occurs when the threshold is exceeded.
-   Treeification normally requires a bucket to reach 8 entries and the
    table capacity to be at least 64; exact implementation behavior is
    version-dependent.

These are implementation details, not general Map interface guarantees.

## 15. LinkedHashMap

`LinkedHashMap` is a HashMap-style map with predictable iteration order.

Two modes:

1.  Insertion-order mode: maintains the order in which entries were
    inserted.
2.  Access-order mode: maintains entries from least recently accessed to
    most recently accessed.

### Insertion-order example

``` java
Map<Integer, String> map = new LinkedHashMap<>();

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");

System.out.println(map.keySet()); // [1, 2, 3]
```

### Access-order example

``` java
LinkedHashMap<Integer, String> map =
    new LinkedHashMap<>(16, 0.75f, true);

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");

map.get(1);

System.out.println(map.keySet()); // [2, 3, 1]
```

Accessing key `1` moves it to the most recently accessed position.

### LRU Cache using LinkedHashMap

LRU means **Least Recently Used**. When a cache is full, remove the
entry that has not been accessed for the longest time.

``` java
import java.util.*;

public class SessionCache {
    private final Map<String, String> cache;

    public SessionCache(int maxSize) {
        this.cache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(
                Map.Entry<String, String> eldest
            ) {
                return size() > maxSize;
            }
        };
    }

    public void put(String sessionId, String userData) {
        cache.put(sessionId, userData);
    }

    public String get(String sessionId) {
        return cache.get(sessionId);
    }

    public static void main(String[] args) {
        SessionCache sessionCache = new SessionCache(3);

        sessionCache.put("s1", "UserA");
        sessionCache.put("s2", "UserB");
        sessionCache.put("s3", "UserC");

        sessionCache.get("s1");          // s1 becomes most recently used
        sessionCache.put("s4", "UserD"); // s2 is evicted

        System.out.println(sessionCache.cache.keySet());
        // [s3, s1, s4]
    }
}
```

How it works:

-   `true` enables access-order.
-   `get("s1")` moves `s1` to the most recently used position.
-   Inserting `s4` makes the size 4.
-   `removeEldestEntry()` returns true because `size() > maxSize`.
-   The eldest entry (`s2`) is removed.

This simple example is not thread-safe. A production cache used by
multiple threads needs appropriate concurrency control and may be better
served by a dedicated caching library.

## 16. TreeMap

`TreeMap` stores key-value pairs sorted by key.

It is based on a Red-Black tree in standard Java implementations.

Properties:

-   Keys are sorted by natural ordering or a supplied Comparator.
-   `put()`, `get()`, and `remove()` are O(log n).
-   Natural ordering does not support null keys.
-   Values may be null.
-   Keys that compare as equal are treated as the same key.

``` java
Map<Integer, String> map = new TreeMap<>();

map.put(30, "Rahul");
map.put(10, "Amit");
map.put(20, "Priya");

System.out.println(map);
```

Output:

``` text
{10=Amit, 20=Priya, 30=Rahul}
```

### Reverse key order

``` java
Map<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());

map.put(30, "Rahul");
map.put(10, "Amit");
map.put(20, "Priya");

System.out.println(map);
```

Output:

``` text
{30=Rahul, 20=Priya, 10=Amit}
```

### HashMap vs LinkedHashMap vs TreeMap

  -----------------------------------------------------------------------
  Feature           HashMap           LinkedHashMap     TreeMap
  ----------------- ----------------- ----------------- -----------------
  Ordering          No guarantee      Insertion or      Sorted by key
                                      access order      

  Typical           Average O(1)      Average O(1)      O(log n)
  get/put/remove                                        

  Null key          One allowed       One allowed       Not with natural
                                                        ordering

  Duplicate keys    Not allowed       Not allowed       Not allowed by
                                                        comparison

  Structure         Hash table        Hash table +      Red-Black tree
                                      linked order      
  -----------------------------------------------------------------------

## 17. Hashtable and ConcurrentHashMap

### Hashtable

-   Legacy Map implementation.
-   Individual methods are synchronized.
-   Does not allow null keys or null values.
-   Usually not the first choice for new code.

### ConcurrentHashMap

-   Designed for concurrent access by multiple threads.
-   Supports concurrent retrievals and updates with internal concurrency
    control.
-   Does not allow null keys or null values.
-   Useful when multiple threads share a Map.

``` java
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ConcurrentMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> counts = new ConcurrentHashMap<>();

        counts.put("Java", 1);
        counts.put("Spring", 2);

        counts.merge("Java", 1, Integer::sum);

        System.out.println(counts); // Java count becomes 2
    }
}
```

**Note:** A concurrent collection helps with thread safety, but compound
application logic may still need atomic methods such as `putIfAbsent`,
`compute`, or `merge`.

------------------------------------------------------------------------

# PART D --- QUEUE AND DEQUE

## 18. What is Queue?

A Queue is generally used for processing elements in FIFO order:

**FIFO = First In, First Out.**

Think of a line at a ticket counter. The person who joins first is
generally served first.

Main methods:

  Method        Inserts/removes/reads   If operation fails
  ------------- ----------------------- ---------------------------
  `add(e)`      Insert                  Throws exception
  `offer(e)`    Insert                  Returns false
  `remove()`    Remove head             Throws exception if empty
  `poll()`      Remove head             Returns null if empty
  `element()`   Read head               Throws exception if empty
  `peek()`      Read head               Returns null if empty

### Queue example

``` java
import java.util.*;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.offer("A");
        queue.offer("B");
        queue.offer("C");

        System.out.println(queue);       // [A, B, C]
        System.out.println(queue.peek()); // A

        System.out.println(queue.poll()); // A
        System.out.println(queue);        // [B, C]
    }
}
```

## 19. PriorityQueue

A `PriorityQueue` processes elements according to priority rather than
insertion order.

By default, the smallest element has the highest priority.

``` java
Queue<Integer> queue = new PriorityQueue<>();

queue.offer(40);
queue.offer(10);
queue.offer(30);
queue.offer(20);

System.out.println(queue.peek()); // 10

while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

Output:

``` text
10
20
30
40
```

**Important:** Iterating over a PriorityQueue does not guarantee sorted
order. Repeatedly calling `poll()` retrieves elements in priority order.

### Max heap using Comparator

``` java
Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

queue.offer(10);
queue.offer(40);
queue.offer(20);

while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

Output:

``` text
40
20
10
```

Typical complexities:

  Operation            Time
  -------------- ----------
  `peek()`             O(1)
  `offer()`        O(log n)
  `poll()`         O(log n)
  `contains()`         O(n)

## 20. Deque (Double-Ended Queue)

A Deque allows insertion and removal at both ends.

It can work as:

-   A queue (FIFO).
-   A stack (LIFO).

`ArrayDeque` is often a good choice for stack and queue operations.

``` java
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(10);
deque.addLast(20);
deque.addFirst(5);

System.out.println(deque); // [5, 10, 20]

deque.removeFirst();
deque.removeLast();

System.out.println(deque); // [10]
```

### Stack with ArrayDeque

``` java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek()); // 30
System.out.println(stack.pop());  // 30
```

### Queue with ArrayDeque

``` java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(10);
queue.offerLast(20);

System.out.println(queue.pollFirst()); // 10
```

`ArrayDeque` does not allow null elements.

------------------------------------------------------------------------

# PART E --- ITERATION

## 21. What is Iterator?

An Iterator is used to traverse a collection one element at a time.

Main methods:

-   `hasNext()` --- checks whether another element exists.
-   `next()` --- returns the next element.
-   `remove()` --- removes the last element returned by the iterator,
    when supported.

``` java
List<String> names = new ArrayList<>();

names.add("Amit");
names.add("Priya");
names.add("Rahul");

Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();
    System.out.println(name);
}
```

## 22. ConcurrentModificationException

A common mistake is structurally modifying a collection directly while
using an iterator to traverse it.

Incorrect example:

``` java
List<Integer> numbers = new ArrayList<>(
    Arrays.asList(10, 20, 30, 40)
);

for (Integer number : numbers) {
    if (number == 20) {
        numbers.remove(number); // Unsafe during iteration
    }
}
```

This can throw `ConcurrentModificationException`.

Why?

The iterator expects the collection's structure not to be modified
independently while it is traversing it.

### Safe removal: Iterator.remove()

``` java
List<Integer> numbers = new ArrayList<>(
    Arrays.asList(10, 20, 30, 40)
);

Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {
    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}

System.out.println(numbers); // [10, 30, 40]
```

The iterator performs the removal and updates its own traversal state.

### Alternative: removeIf()

For simple filtering, use:

``` java
numbers.removeIf(number -> number == 20);
```

### What does fail-fast mean?

Many standard collection iterators are fail-fast: they may throw
`ConcurrentModificationException` when they detect structural
modification outside the iterator.

-   It is a bug-detection mechanism, not a thread-safety guarantee.
-   It is best-effort; do not rely on the exception always occurring.
-   It can happen even in a single thread.
-   `Iterator.remove()` is the standard safe removal operation during
    iteration.

## 23. ListIterator

`ListIterator` is available for lists and supports traversal in both
directions.

Additional methods include:

-   `hasPrevious()`
-   `previous()`
-   `add(value)`
-   `set(value)`

``` java
List<String> names = new ArrayList<>(
    Arrays.asList("Amit", "Priya", "Rahul")
);

ListIterator<String> iterator = names.listIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

while (iterator.hasPrevious()) {
    System.out.println(iterator.previous());
}
```

------------------------------------------------------------------------

# PART F --- SORTING AND COMPARISON

## 24. Comparable

`Comparable` defines a class's natural ordering.

It has one main method:

``` java
int compareTo(T other);
```

Example:

``` java
class Student implements Comparable<Student> {
    int marks;

    Student(int marks) {
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks, other.marks);
    }
}
```

Now students can be sorted by marks using their natural ordering.

``` java
List<Student> students = new ArrayList<>();

students.add(new Student(70));
students.add(new Student(90));
students.add(new Student(80));

Collections.sort(students);
```

## 25. Comparator

`Comparator` defines an external/custom ordering.

``` java
Comparator<Student> descendingMarks =
    (a, b) -> Integer.compare(b.marks, a.marks);

students.sort(descendingMarks);
```

Modern style:

``` java
students.sort(Comparator.comparingInt(s -> s.marks));
```

Reverse order:

``` java
students.sort(
    Comparator.comparingInt((Student s) -> s.marks).reversed()
);
```

### Comparable vs Comparator

  Comparable                  Comparator
  --------------------------- ----------------------------
  Natural ordering            Custom/external ordering
  Implemented by the class    Defined separately
  `compareTo()`               `compare()`
  Usually one natural order   Can define multiple orders

------------------------------------------------------------------------

# PART G --- COLLECTIONS UTILITY CLASS

## 26. Collections vs Collection

These names are similar but different:

-   `Collection` is an interface.
-   `Collections` is a utility class containing static helper methods.

Examples:

``` java
List<Integer> numbers = new ArrayList<>(
    Arrays.asList(5, 2, 8, 1, 3)
);

Collections.sort(numbers);
System.out.println(numbers); // [1, 2, 3, 5, 8]

Collections.reverse(numbers);
System.out.println(numbers); // [8, 5, 3, 2, 1]

Collections.shuffle(numbers);
System.out.println(numbers); // Randomized order

System.out.println(Collections.max(numbers));
System.out.println(Collections.min(numbers));
```

Other useful methods:

-   `Collections.frequency(collection, value)`
-   `Collections.swap(list, i, j)`
-   `Collections.binarySearch(list, key)` --- list must be sorted
    according to the same ordering.
-   `Collections.unmodifiableList(list)` --- returns an unmodifiable
    view, not an independent immutable copy.
-   `Collections.synchronizedList(list)` --- returns a synchronized
    wrapper; iteration still requires external synchronization on the
    returned list.

------------------------------------------------------------------------

# PART H --- COMPLEXITY SUMMARY

## 27. Quick time-complexity table

  ----------------------------------------------------------------------------------
  Collection           Get/search             Add          Remove Ordering
  --------------- --------------- --------------- --------------- ------------------
  ArrayList       Index get O(1),        End O(1)     Middle O(n) Insertion/index
                      search O(n)       amortized                 order

  LinkedList      Index get O(n),       Ends O(1)      Ends O(1), Insertion/index
                      search O(n)                       arbitrary order
                                                    position O(n) 
                                                        to locate 

  HashSet            Average O(1)    Average O(1)    Average O(1) No guarantee
                         contains                                 

  LinkedHashSet      Average O(1)    Average O(1)    Average O(1) Insertion order
                         contains                                 

  TreeSet                O(log n)        O(log n)        O(log n) Sorted
                         contains                                 

  HashMap            Average O(1)    Average O(1)    Average O(1) No guarantee
                              get             put                 

  LinkedHashMap      Average O(1)    Average O(1)    Average O(1) Insertion/access
                              get             put                 order

  TreeMap            O(log n) get    O(log n) put        O(log n) Sorted by key

  PriorityQueue      `contains()`        O(log n)  `poll()` O(log Priority at head
                   O(n), `peek()`                              n) 
                             O(1)                                 
  ----------------------------------------------------------------------------------

These are typical complexities. Actual performance depends on
implementation details, collisions, resizing, and the operation being
measured.

### Big-O intuition

-   O(1): approximately constant work as the collection grows.
-   O(log n): work grows slowly as the collection grows.
-   O(n): work grows proportionally to the number of elements.
-   O(n log n): common for efficient comparison sorting.

------------------------------------------------------------------------

# PART I --- INTERVIEW QUESTIONS AND ANSWERS

## 28. Basic questions

**Q1. What is the Java Collections Framework?**

A: A set of interfaces, implementations, and algorithms for storing and
manipulating groups of objects.

**Q2. Difference between Collection and Collections?**

A: `Collection` is an interface. `Collections` is a utility class with
static methods such as `sort()`, `reverse()`, and `shuffle()`.

**Q3. Difference between List and Set?**

A: List is ordered, indexed, and permits duplicates. Set prevents
duplicates and does not provide index-based access.

**Q4. Does Map extend Collection?**

A: No. Map is a separate interface in the Collections Framework.

**Q5. Can a List contain duplicates?**

A: Yes.

**Q6. Can a Set contain duplicates?**

A: No, according to that Set implementation's equality or comparison
rules.

**Q7. Which List is generally best for frequent index access?**

A: ArrayList.

**Q8. Which Set maintains insertion order?**

A: LinkedHashSet.

**Q9. Which Set maintains sorted order?**

A: TreeSet.

**Q10. Which Map maintains sorted keys?**

A: TreeMap.

## 29. Intermediate questions

**Q11. How does HashSet identify duplicates?**

A: It uses hash codes to locate a bucket and equality checks to identify
an equal existing element.

**Q12. Why override equals() and hashCode() together?**

A: Equal objects must have the same hash code. Hash-based collections
rely on both methods.

**Q13. What is a hash collision?**

A: Two different keys or elements map to the same hash bucket. The
collection handles the collision and uses equality checks to distinguish
keys.

**Q14. Why is ArrayList get() O(1)?**

A: It uses an array, so an element can be accessed directly by index.

**Q15. Why is LinkedList get() O(n)?**

A: It must traverse nodes to reach the requested index.

**Q16. What is the difference between HashMap and TreeMap?**

A: HashMap provides average O(1) basic operations with no ordering
guarantee. TreeMap keeps keys sorted and provides O(log n) basic
operations.

**Q17. What is LinkedHashMap?**

A: A HashMap-style implementation that maintains insertion order or
access order.

**Q18. What is PriorityQueue?**

A: A queue that exposes the highest-priority element at its head. By
default, the smallest element has priority.

**Q19. What is ConcurrentModificationException?**

A: An exception that fail-fast iterators may throw when they detect
structural modification outside the iterator during traversal.

**Q20. How can you safely remove an element while iterating?**

A: Use `Iterator.remove()` or, for simple filtering, `removeIf()`.

## 30. Advanced questions

**Q21. Is HashMap thread-safe?**

A: No. Use a suitable concurrent Map such as ConcurrentHashMap when
shared concurrent access is required.

**Q22. Does ConcurrentHashMap allow null?**

A: No, it does not allow null keys or values.

**Q23. Does HashMap allow null?**

A: It permits one null key and multiple null values.

**Q24. Is PriorityQueue iteration sorted?**

A: No. Only the head is guaranteed to be the next element according to
the priority ordering. Repeated `poll()` calls retrieve elements in
priority order.

**Q25. Why is ArrayDeque often preferred over Stack?**

A: ArrayDeque is a modern, efficient deque implementation and avoids the
legacy Stack/Vector inheritance design.

**Q26. What is LRU?**

A: Least Recently Used. When a bounded cache is full, it evicts the
entry accessed least recently.

**Q27. How can LinkedHashMap implement LRU?**

A: Enable access-order with the three-argument constructor and override
`removeEldestEntry()` to evict when the size exceeds the limit.

**Q28. What is the difference between fail-fast and thread-safe?**

A: Fail-fast iterators detect some unexpected structural modifications.
Thread safety means operations are coordinated safely across threads.
Fail-fast does not make a collection thread-safe.

------------------------------------------------------------------------

# PART J --- CODING PRACTICE

## 31. Beginner exercises

1.  Create an ArrayList of 5 integers and print all elements.
2.  Insert an element at index 2.
3.  Remove a particular value from an ArrayList.
4.  Find the largest and smallest numbers in a List.
5.  Remove duplicates from a List using HashSet.
6.  Store 5 employee names in a LinkedHashSet and verify insertion
    order.
7.  Store numbers in a TreeSet and print them in ascending order.
8.  Count the frequency of each word using HashMap.

## 32. Intermediate exercises

1.  Sort a list of employees by salary using Comparator.
2.  Sort students by marks using Comparable.
3.  Find the first non-repeating character in a String using
    LinkedHashMap.
4.  Find the common elements between two arrays using HashSet.
5.  Count each character's frequency using HashMap.
6.  Use PriorityQueue to find the K largest elements.
7.  Remove all even numbers from a List using Iterator or `removeIf()`.
8.  Group employees by department using `Map<String, List<Employee>>`.

## 33. Advanced exercises

1.  Implement an LRU Cache using LinkedHashMap.
2.  Implement a stack using ArrayDeque.
3.  Implement a queue using ArrayDeque.
4.  Find the top K frequent elements using HashMap and PriorityQueue.
5.  Merge two sorted lists.
6.  Build a word-frequency counter that returns words sorted by
    frequency.
7.  Use ConcurrentHashMap to maintain counts from multiple threads.
8.  Implement a custom class with correct `equals()` and `hashCode()`
    and store it in a HashSet and HashMap.

------------------------------------------------------------------------

# PART K --- WHICH COLLECTION SHOULD YOU CHOOSE?

## 34. Real-world selection guide

  -----------------------------------------------------------------------
  Requirement                         Choose
  ----------------------------------- -----------------------------------
  General-purpose list                ArrayList

  Frequent access by index            ArrayList

  Frequent insert/remove at both ends ArrayDeque or LinkedList, depending
                                      on need

  Unique elements, order unimportant  HashSet

  Unique elements, preserve insertion LinkedHashSet
  order                               

  Unique sorted elements              TreeSet

  Key-value lookup, order unimportant HashMap

  Key-value lookup, insertion/access  LinkedHashMap
  order                               

  Key-value lookup, sorted keys       TreeMap

  Priority-based processing           PriorityQueue

  Stack operations                    ArrayDeque

  FIFO queue                          ArrayDeque

  Shared Map with concurrent access   ConcurrentHashMap
  -----------------------------------------------------------------------

------------------------------------------------------------------------

# FINAL REVISION CHEAT SHEET

``` text
LIST
  Ordered, duplicates allowed, indexed.
  ArrayList  → dynamic array, O(1) index access,
               O(n) middle insert/remove.
  LinkedList → doubly linked list, O(1) end operations,
               O(n) index access.

SET
  Unique elements.
  HashSet       → average O(1), no guaranteed order.
  LinkedHashSet → average O(1), insertion order.
  TreeSet       → O(log n), sorted order.

MAP
  Key-value pairs, keys unique.
  Map is NOT a subtype of Collection.
  HashMap       → average O(1), no guaranteed order.
  LinkedHashMap → average O(1), insertion/access order.
  TreeMap       → O(log n), sorted keys.

QUEUE
  Usually FIFO.
  PriorityQueue → priority order; smallest first by default.
  ArrayDeque    → efficient queue and stack operations.

IMPORTANT RULES
  1. Override equals() AND hashCode() together for logical equality.
  2. HashSet and HashMap depend on hashCode() and equals().
  3. TreeSet and TreeMap use ordering/comparison to identify duplicates/keys.
  4. Fail-fast iterators may throw ConcurrentModificationException
     after unexpected structural modification.
  5. Use Iterator.remove() for safe removal during iteration.
  6. PriorityQueue iteration is not sorted; use poll() for priority order.
  7. Prefer ArrayList for general-purpose lists.
  8. Prefer ArrayDeque over legacy Stack for stack operations.
```

## Suggested study order

1.  List: ArrayList, LinkedList, methods and complexity.
2.  Set: HashSet, LinkedHashSet, TreeSet.
3.  Map: HashMap, LinkedHashMap, TreeMap.
4.  `equals()` and `hashCode()`.
5.  Queue, Deque and PriorityQueue.
6.  Iterator, ListIterator and ConcurrentModificationException.
7.  Comparable, Comparator and Collections utility methods.
8.  LRU Cache and coding exercises.

**Interview priority:** Give extra practice to ArrayList, HashMap,
HashSet, PriorityQueue, `equals()`/`hashCode()`, Comparator, and time
complexity. These concepts are useful in Java backend development and
data-structure interview problems.
