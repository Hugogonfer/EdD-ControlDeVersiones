// Book.java
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }
    
    // BUG 1: No hay getters/setters para todos los campos // SOLUCIONADO
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }
    
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setAvailable(boolean available) { this.available = available; }
    
    public void borrow() {
        // BUG 2: No valida si ya está prestado // SOLUCIONADO
        if (!available) {
            throw new IllegalStateException("Book is already borrowed");
        }
        available = false;
    }
    
    public void returnBook() {
        // BUG 3: No valida si ya estaba disponible // SOLUCIONADO
        if (available) {
            throw new IllegalStateException("Book is already available");
        }
        available = true;
    }
}
