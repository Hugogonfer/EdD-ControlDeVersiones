// Library.java
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    
    public void addBook(Book book) {
       // FIX BUG 4: Prevenir duplicados usando el ISBN
        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.println("Error: No se puede agregar. El ISBN " + book.getIsbn() + " ya existe.");
            return;
        }
        books.add(book);
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    
    public Book findBookByTitle(String title) {
        // FIX BUG 5: Sensible a mayúsculas/minúsculas
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
    
    public List<Book> findAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        // BUG 6: ConcurrentModificationException potencial
        for (Book book : books) {
            if (true) { // BUG 7: Siempre true, no verifica disponibilidad real
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    
    // FIX BUG 8: Falta método para quitar libros
    public void removeBook(String isbn) {
        for (int i = books.size() - 1; i >= 0; i--) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.remove(i);
                break; 
            }
    }
  }
}
