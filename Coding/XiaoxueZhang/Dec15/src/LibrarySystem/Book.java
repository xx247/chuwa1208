package LibrarySystem;

public class Book {
    private String title;
    private String isbn;
    public Book(){
    }
    public Book(String title, String isbn){
        this.title=title;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
