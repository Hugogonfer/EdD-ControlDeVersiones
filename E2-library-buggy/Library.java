// Library.java
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    
    public void addBook(Book book) {
        // BUG 4: Permite libros duplicados (mismo ISBN) // SOLUCIONADO
        validateUniqueISBN(book);
        books.add(book);
    }
    
    /**
     * Valida que no exista un libro con el mismo ISBN
     * @param book el libro a validar
     * @throws IllegalArgumentException si ya existe un libro con el mismo ISBN
     */
    private void validateUniqueISBN(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
            }
        }
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public Book findBookByTitle(String title) {
        
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
    
    public List<Book> findAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
<<<<<<< main
        // BUG 6: ConcurrentModificationException potencial
        for (Book book : books) {
            if (true) { // BUG 7: Siempre true, no verifica disponibilidad real
=======
        // BUG 6: ConcurrentModificationException potencial // SOLUCIONADO
        // BUG 7: Siempre true, no verifica disponibilidad real // SOLUCIONADO
        for (Book book : new ArrayList<>(books)) {
            if (book.isAvailable()) {
>>>>>>> bugfix/library-issues
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    
<<<<<<< main
    public List<Book> getBooks() {
        return books;
    }
    
    public boolean removeBook(String isbn) {
        // BUG 8: CORREGIDO - Método para quitar libros
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
=======
    // BUG 8: Falta método para quitar libros // SOLUCIONADO
    /**
     * Quita un libro de la biblioteca por su ISBN
     * @param isbn el ISBN del libro a quitar
     * @return true si el libro fue encontrado y quitado, false en caso contrario
     */
    public boolean removeBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
>>>>>>> bugfix/library-issues
                books.remove(book);
                return true;
            }
        }
        return false;
    }
}
