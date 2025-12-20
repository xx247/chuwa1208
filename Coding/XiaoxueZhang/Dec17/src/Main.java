//P1. Create a complete exception handling system for an order processing application.
//- In `Main`:
//        - Call `processOrder` with different scenarios
//  - Catch exceptions and print error code and message
//- Think: Why use an interface instead of directly using the enum in the exception class?

//P2. Resource Management with try-with-resources
//Implement a file processing system demonstrating proper resource management.
//- In `Main`:
//  - Use try-with-resources to create both LogWriter and DataProcessor
//  - Demonstrate that close() methods are called automatically
//  - Test the execution order by creating multiple resources
//- Compare: Write the same functionality using traditional try-finally approach and explain why try-with-resources is better

//P3. Enum-Based State Machine
//Design a state machine for tracking package delivery status using Enum.
//- In `Main`:
//        - Create a package with PENDING status
//  - Test valid transitions: PENDING → PICKED_UP → IN_TRANSIT → DELIVERED
//  - Test invalid transition: DELIVERED → IN_TRANSIT (should throw exception)
//  - Demonstrate `fromCode()` method
//- Think: How does Enum help prevent invalid states compared to using int constants?

//P4. Collection Operations and Transformations
//Implement a student management system demonstrating various collection operations.

//- In `Main`:
//  - Add at least 10 students with various scores
//  - Test all methods and display results
//  - Demonstrate that duplicate student IDs are handled correctly
//- Think: Why do we maintain both a List and a Map? What are the trade-offs?

//P5 Bank Account Withdrawal with Exception Chaining
//Implement a simple bank withdrawal system demonstrating exception handling and re-throwing.
//- In `Main`:
//  - Create a BankAccount with balance 1000
//  - Test successful withdrawal (500)
//  - Test failed withdrawal (1500) and catch the exception
//  - Observe that finally block executes in both cases
//- Think: Why do we re-throw the exception in ATM instead of just catching it? What's the benefit of exception chaining?
import BankAccWithdrawal.ATM;
import BankAccWithdrawal.BankAccount;
import BankAccWithdrawal.InsufficientBalanceException;
import OrderProcessingApp.OrderException;
import OrderProcessingApp.OrderService;
import PaperResource.DataProcessor;

import PackageTracking.Package;
import StudentManagement.Student;
import StudentManagement.StudentManager;

import java.util.List;
import java.util.Map;

import static PackageTracking.DeliveryStatus.*;


public class Main {
    public static void main(String[] args) {
//P1
        OrderService s1 = new OrderService();
        try {
            s1.processOrder("12423432", 23);
            s1.processOrder("2134", 60);
            s1.processOrder(null, 60);
            s1.processOrder("", 60);
            s1.processOrder("CANCELLED", 60);
        } catch (OrderException e) {
            System.out.println("Error catched:" + e.getErrorCode());
            System.out.println(e.getMessage());
        }
        //s1.processOrder("CANCELLED",60);

//P2
        try (DataProcessor dataProcessor1 = new DataProcessor("Some data1");
             DataProcessor dataProcessor2 = new DataProcessor("Some data2");
             DataProcessor dataProcessor3 = new DataProcessor("Some data3");
        ) {
            dataProcessor1.processData();
            dataProcessor2.processData();
            dataProcessor3.processData();
        }
//        traditional try-finally approach
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream("data.txt");
//            // use resource
//        } finally {
//            if (fis != null) {
//                fis.close();
//            }
//        }
//P3
        Package p = new Package("1", PENDING);
        p.updateStatus(PICKED_UP);
        p.updateStatus(IN_TRANSIT);
        p.updateStatus(DELIVERED);
        try {
            p.updateStatus(IN_TRANSIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(PENDING.getCode());

//P4
        StudentManager manager = new StudentManager();

        // Add 10+ students
        manager.addStudent(new Student("1", "Alice", 95));
        manager.addStudent(new Student("2", "Bob", 82));
        manager.addStudent(new Student("3", "Charlie", 76));
        manager.addStudent(new Student("4", "David", 88));
        manager.addStudent(new Student("5", "Eve", 59));
        manager.addStudent(new Student("6", "Frank", 60));
        manager.addStudent(new Student("7", "Grace", 73));
        manager.addStudent(new Student("8", "Hank", 100));
        manager.addStudent(new Student("9", "Ivy", 45));
        manager.addStudent(new Student("10", "Jack", 79));

        // Duplicate ID test (same id = same student)
        manager.addStudent(new Student("1", "Alice_New", 99));

        System.out.println("\n===== Top 3 Students =====");
        System.out.println(manager.getTopStudents(3));

        System.out.println("\n===== Unique Names =====");
        System.out.println(manager.getUniqueNames());

        System.out.println("\n===== Grouped by Score =====");
        Map<Integer, List<Student>> groups = manager.groupByScore();
        System.out.println("0-59: " + groups.get(0));
        System.out.println("60-79: " + groups.get(60));
        System.out.println("80-100: " + groups.get(80));

        System.out.println("\n===== Removing Student with ID 3 =====");
        manager.removeStudentById("3");

        System.out.println(manager.getTopStudents(20));

        System.out.println("\n===== Removing Student with ID 1 (Duplicate ID Handling) =====");
        manager.removeStudentById("1");

        System.out.println(manager.getTopStudents(20));
//P5
        BankAccount account = new BankAccount("ACC1001", 1000);
        ATM atm = new ATM();

        // Successful withdrawal
        try {
            System.out.println("\n--- Test 1: Successful Withdrawal ---");
            atm.processWithdrawal(account, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught in Main: " + e.getMessage());
        }

        // Failed withdrawal
        try {
            System.out.println("\n--- Test 2: Failed Withdrawal ---");
            atm.processWithdrawal(account, 1500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught in Main: " + e.getMessage());
        }
    }
}
