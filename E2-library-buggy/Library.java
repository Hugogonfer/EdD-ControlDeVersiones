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
        // BUG 6: ConcurrentModificationException potencial // SOLUCIONADO
        // BUG 7: Siempre true, no verifica disponibilidad real // SOLUCIONADO
        for (Book book : new ArrayList<>(books)) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    
    // BUG 8: Falta método para quitar libros
}
