1. What is the difference between JDK, JRE, and JVM?
JVM (java virtual machine) is the engine that runs java bytecode.
JRE (java runtime environment) provides JVM and libraries needed to run java aaplications
JDK (java development kit) includes JRE along with development tools like compiler

2. What are the main differences between primitive types and
reference types?
primitive typed can be compared using ==
Primitive types store actual values and live on the stack with fixed size and default values.
Reference types store memory addresses of objects on the heap, default to null, and their size varies. 
Passing primitives to methods copies the value, while passing reference types copies the reference so the object can be changed.

3. Is Java pass-by-value or pass-by-reference? Explain.
Java is pass-by-value.
For primitives, the actual value is copied.
For objects, the value copied is the reference to the object, not the object itself.
This means a method can modify the object’s contents, but it cannot change which object the caller’s reference points to.

4. Why are Strings immutable in Java?
Strings are immutable in Java to ensure security, thread safety, and efficient memory use through string pooling

5. What is the String Constant Pool and how does it work?
The String Constant Pool (SCP) is a special memory area inside the Java heap that stores unique Strings.
Instead of creating a new String every time, Java reuses existing immutable String objects in this pool to save memory and improve performance.
When a String is created, JVM checks the pool first—if it exists, the reference is reused; if not, a new one is added

6. What is the purpose of the final keyword in Java?
Make something immutable
Variable: define contants
Method: prevent override
Class: prevent inheritance

7. What does the static keyword mean for variables or methods?
The static keyword means the member belongs to the class itself, not to any instance (object) of the class.
Static variables are shared across all instances.
Static methods can be called without creating objects but cannot access instance variables directly.

8. What is a static block and when does it run?
A static block is a section of code inside a class that runs once when the class is loaded into memory. It is used for initializing static variables or performing one-time setup.

9. Can a static method access non-static variables? Why or why not?
No.
Because static methods belong to the class, not to any object. Non-static variables belong to individual objects.

10. Describe the JVM loading order: static block, static variables, and constructor.
JVM loads static variables and executes static blocks first, and this happens once when the class is loaded.
When an object is created, instance variables and instance initialization blocks run first, then the constructor executes.

11. What is the difference between a static variable and a constant defined as public static final?
A static variable is a class-level variable whose value can change and is shared across all instances.
A public static final variable is a constant — it is also shared, but its value cannot change.

12. Explain why immutable objects (like String) are thread-safe by design. Provide an example scenario.
Because the object cannot change, no thread can modify it, and therefore, no two threads can conflict over updates.

13. What problem does making a class final solve in terms of
immutability and inheritance security?
Making a class final prevents it from being subclassed. This is important for immutability because a subclass could override methods or add mutable fields that break the immutability guarantees. It also prevents inheritance-based security risks by ensuring that core behavior cannot be changed or abused by malicious subclasses. Classes like String and Integer are final for exactly these reasons.