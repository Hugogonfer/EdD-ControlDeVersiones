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
    
    // BUG 1: No hay getters/setters para todos los campos
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    // FIX: Añadidos getters faltantes
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }
    
    public void borrow() { // FIX Bug 2 : Validar si esta availible
        if (available) {
            available = false;
        }
    }

    public void returnBook() { // FIX Bug 3 : Validar si no esta availible
        if (available) {
        if (!available) {
            available = true;
        }
    }
    }
}