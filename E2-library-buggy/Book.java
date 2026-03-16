// Book.java
public class Book {
    private String title;
    private String author;https://github.com/Hugogonfer/EdD-ControlDeVersiones/pull/23/conflict?name=E2-library-buggy%252FLibrary.java&ancestor_oid=8e9e3272b00d3bd0c785379e14111781da025d70&base_oid=b68f355e411f0262640b7627414984bb9161a48d&head_oid=5a8308242962c1f48f68c9060d102e6d01cc6942
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
        validateNotBorrowed();
        available = false;
    }
    
    public void returnBook() {
        // BUG 3: No valida si ya estaba disponible // SOLUCIONADO
        validateBorrowed();
        available = true;
    }
    
    /**
     * Valida que el libro no esté prestado
     * @throws IllegalStateException si el libro ya está prestado
     */
    private void validateNotBorrowed() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed");
        }
    }
    
    /**
     * Valida que el libro esté prestado
     * @throws IllegalStateException si el libro ya está disponible
     */
    private void validateBorrowed() {
        if (available) {
            throw new IllegalStateException("Book is already available");
        }
    }
}
