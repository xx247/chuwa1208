import LibrarySystem.Book;
import LibrarySystem.Library;
import PaymentSystem.CreditCardPayment;
import PaymentSystem.PayPalPayment;


//- In `Main`:
//  - Call `getInstance()` multiple times and verify it's the same instance (use `==`)
//  - Set 3 configuration items
//  - Display all configurations


public class Main {
    public static void main(String[] args) {
        //p1
        PayPalPayment paypal = new PayPalPayment();
        paypal.processPayment(100.0);
        CreditCardPayment credit = new CreditCardPayment();
        credit.processPayment(250.5);
        paypal.printReceipt();
        credit.printReceipt();

        //p2
        Library library = new Library();

        Book b1 = new Book("The Hobbit", "ISBN001");
        Book b2 = new Book("1984", "ISBN002");
        Book b3 = new Book("Harry Potter", "ISBN003");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.displayLibrary();

        //p3
        ConfigManager c1 = ConfigManager.getInstance();
        ConfigManager c2 = ConfigManager.getInstance();
        ConfigManager c3 = ConfigManager.getInstance();
        System.out.println("same instance?"+(c1==c2));

        c1.setConfig("appName", "LibrarySystem");
        c1.setConfig("version", "1.2.5");
        c1.setConfig("mode", "production");

        c1.displayAllConfigs();
//- Add comments explaining:
//  - Why must the constructor be private?
        //So that user can't new instances, only class itself can
//  - Why must the `getInstance()` method be static?
        //Because we need to access to the instance without create an object
//  - How does this implementation ensure thread safety?
        //The inner class loads only when getInstance() is first called. Because class initialization
        //is synchronized internally by the JVM, no two threads can create the instance simultaneously.
//- Advanced: How would you design test code to verify Singleton's thread safety in a multi-threaded environment?
    }
}