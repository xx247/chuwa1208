package LibrarySystem;

import java.util.ArrayList;
import java.util.List;

//- Create a `Library` class with:
//  - `private List<Book> books`
//  - Initialize books list in constructor (demonstrate Composition)
//  - `void addBook(Book book)`
//  - `void displayLibrary()` - display all books
public class Library {
    private List<Book> books;

    public Library(){
        books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void displayLibrary(){
        for(Book b:books){
            System.out.println("title: "+b.getTitle()+"isbn: "+b.getIsbn());
        }
    }
}
