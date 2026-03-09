import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddDuplicateBook() {
        Library lib = new Library();
        Book b1 = new Book("Clean Code", "Robert Martin", "978-0132350884");
        Book b2 = new Book("Clean Code Duplicate", "Same ISBN", "978-0132350884");
        
        lib.addBook(b1);
        lib.addBook(b2); // Esto no deberia funcionar
        
        // Verification: Solo deberia haber un libro
        assertEquals(1, lib.findAvailableBooks().size(), "Library should not allow duplicate ISBNs");
    }
    
    @Test
    public void testFindBookByIsbn() {
        Library lib = new Library();
        String isbn = "123-456";
        lib.addBook(new Book("Test", "Author", isbn));
        
        assertNotNull(lib.findBookByIsbn(isbn));
        assertNull(lib.findBookByIsbn("non-existent"));
    }

    @Test
    public void testFindBookByTitleCaseInsensitive() {
        Library lib = new Library();
        lib.addBook(new Book("Padres", "Juan", "123"));
        assertNotNull(lib.findBookByTitle("padres"));
    }
}