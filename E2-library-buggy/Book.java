// Book.java
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    
    public Book(String title, String author, String isbn, Boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }
    
    // BUG 1: No hay getters/setters para todos los campos
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return isbn;}
    public Boolean getAvailable() { return available;}
    // Faltan getIsbn() y isAvailable
    
    public void borrow() {
        // BUG 2: No valida si ya está prestado
        available = false;
    }
    
    public void returnBook() {
        // BUG 3: No valida si ya estaba disponible
        available = true;
    }
}
