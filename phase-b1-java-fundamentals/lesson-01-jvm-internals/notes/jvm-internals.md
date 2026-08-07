# JVM Internals

## Introduction

Java achieves **Write Once, Run Anywhere (WORA)** by compiling source
code into **bytecode**, which is executed by the **Java Virtual Machine
(JVM)**.

------------------------------------------------------------------------

## JDK vs JRE vs JVM

  Component   Purpose
  ----------- -----------------------------------------------
  **JDK**     Development toolkit (compiler, debugger, JRE)
  **JRE**     Runtime environment (JVM + libraries)
  **JVM**     Executes Java bytecode

Relationship:

``` text
JDK ⊃ JRE ⊃ JVM
```

------------------------------------------------------------------------

## Java Execution Flow

``` text
YourProgram.java
      │
      ▼
 javac (Compiler)
      │
      ▼
YourProgram.class (Bytecode)
      │
      ▼
Class Loader
      │
      ▼
JVM Runtime
 ├── Runtime Data Areas
 └── Execution Engine
      ├── Interpreter
      └── JIT Compiler
      │
      ▼
Native Machine Code
      │
      ▼
Operating System / CPU
```

------------------------------------------------------------------------

## JVM Components

### Class Loader

-   Loads `.class` files into memory.
-   Performs loading, linking, and initialization.

### Runtime Data Areas

-   Heap
-   Java Stack
-   Method Area / Metaspace
-   Program Counter Register
-   Native Method Stack

### Execution Engine

-   **Interpreter** executes bytecode instruction by instruction.
-   **JIT Compiler** compiles frequently executed ("hot") code into
    optimized native machine code.

------------------------------------------------------------------------

## Why Java is Platform Independent

The `.class` bytecode is platform independent. The JVM is platform
dependent because it generates machine code for the underlying operating
system and CPU.

------------------------------------------------------------------------

## Common Errors

-   `UnsupportedClassVersionError` -- Compiled with a newer Java version
    than the runtime supports.
-   `ClassFormatError` -- Invalid or corrupted class file.

------------------------------------------------------------------------

## Best Practices

-   Keep the same Java version in development, CI/CD, and production.
-   Use LTS releases for production.
-   Ship `.jar` files, not `.java` source files.

------------------------------------------------------------------------

## Cheat Sheet

``` text
javac  -> .java -> .class
java   -> JVM -> execute bytecode
JDK    -> JRE + Development Tools
JRE    -> JVM + Core Libraries
JVM    -> Class Loader + Runtime Memory + Execution Engine
JIT    -> Optimizes hot code to native machine code
```
