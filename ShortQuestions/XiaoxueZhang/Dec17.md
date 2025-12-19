Q1. What are the three major categories of exceptions in Java's exception hierarchy? For each category, explain: (1) Whether it must be handled at compile-time, (2) Common examples, (3) Best practices for handling.
Checked exceptions:
    must be handled at compile time 
    IOException, FileNotFoundException
    try catch/throws
unchecked exceptions:
    Not force handling
    NullPointerException, ArrayIndexOutOfBoundsException
    if (obj == null) {
    throw new IllegalArgumentException("Object cannot be null");
    }
errors:
    should not be caught or handled in application code
    OutOfMemoryError, StackOverflowError

Q2. Explain the execution order of try-catch-finally blocks. If both the catch block and finally block contain return statements, which value will be returned? Why is it strongly discouraged to use return statements in finally blocks?
exception occurs in try block, then move to catch block, finally block executes always
The return value in finally will be returned
It overrides return values

Q3. What is the "catch scope should be from small to large" rule? Why must specific exception types (like OrderNotFoundException) be caught before general ones (like Exception)? What happens if you violate this rule?
Catch more specific exceptions first, and more general exceptions later.
The first catch already catches all exceptions. The second catch is unreachable code.
If a specific exception is placed after a general one, it becomes unreachable, causing a compile-time error. This rule ensures correct exception handling and prevents meaningful exceptions from being swallowed.

Q4. Compare throw and throws: (1) Where is each used in code? (2) What follows each keyword? (3) Provide one practical example demonstrating both keywords working together in a DAO-Service-Controller architecture.
throw:
    used inside a method body
    exception object follows throw
throws:
    used in the method signature
    one or more exception class names follow throws

//Dao layer
public class OrderDao {
    public String findOrderById(String orderId) throws OrderNotFoundException {
        if (orderId == null) {
            throw new OrderNotFoundException("Order ID not found in database");
        }
        return "Order-" + orderId;
    }
}
//Service layer
public class OrderService {

    private OrderDao orderDao = new OrderDao();

    public String getOrder(String orderId) throws OrderNotFoundException {
        return orderDao.findOrderById(orderId);
    }
}
//Controller layer
public class OrderController {

    private OrderService service = new OrderService();

    public void processRequest(String orderId) {
        try {
            String order = service.getOrder(orderId);
            System.out.println("Order found: " + order);
        } catch (OrderNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

Q5. What is try-with-resources syntax (introduced in Java 7)? What interface must a class implement to be used with try-with-resources? Explain the execution order when multiple resources are declared.
Try-with-resources is a Java feature (introduced in Java 7) that automatically closes resources when they are no longer needed.
must implement AutoCloseable
When multiple resources are declared, they are created in order and closed in reverse order, ensuring safe and predictable cleanup.

Q6. When creating custom exceptions, how do you decide between extending Exception vs extending RuntimeException? Provide criteria for each choice and one example scenario for each.
You should extend Exception(checked) when the error is recoverable and the caller is expected to handle it, such as I/O or business rule failures. You should extend RuntimeException(unchecked) when the error represents a programming bug or invalid state where recovery is not expected. Checked exceptions enforce handling at compile time, while unchecked exceptions support fail-fast behavior and cleaner APIs.

Q7. Explain the two important features of Enum: "Every element is in values" and "Every element is a constructor". How would you implement an Enum with a private constructor that accepts parameters?
Every enum constant is automatically stored in a static array returned by the values() method.
Each enum constant is actually a singleton object created by calling the enum’s constructor.
Enums can have private constructors with parameters to associate data and behavior with each constant, while still preventing external instantiation.

Q8. Describe the popular Enum template pattern (Interface + Enum + Exception). What are its four components? How does using an interface type (IErrorCode) allow the exception class to accept multiple different enum types?
The Enum template pattern consists of four components: an error code interface defining a contract, multiple enum implementations representing domain-specific error codes, a custom exception class that accepts the interface type, and usage layers that throw the exception. By typing the exception parameter as the interface, the exception can accept multiple different enum types polymorphically, making the design flexible, extensible, and aligned with SOLID principles.

Q9. Compare the three major Collection interfaces: List, Set, and Queue. For each, explain: (1) Ordering characteristics, (2) Duplicate element handling, (3) Most commonly used implementation class, (4) One typical use case.
List:
    Maintains insertion order
    Duplicates allowed
    ArrayList
    list of items in a shopping cart, UI display lists
Set:
    No guaranteed order
    No duplicates allowed
    HashSet
    unique user IDs, tags, permissions
Queue:
    FIFO
    Duplicates allowed
    LinkedList
    task scheduling, message processing, producer–consumer pattern

Q10. Explain the difference between HashMap and Hashtable. Why is Hashtable considered obsolete? What are the modern alternatives for thread-safe Map implementations?
HashMap is not thread-safe but offers better performance and allows null keys and values. Hashtable is thread-safe through synchronized methods but suffers from poor performance due to coarse-grained locking and outdated design, making it obsolete. Modern Java applications use ConcurrentHashMap for efficient thread-safe access or Collections.synchronizedMap for simpler synchronization needs.