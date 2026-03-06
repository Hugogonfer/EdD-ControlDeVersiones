import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    
    @BeforeEach
    public void setUp() {
        library = new Library();
    }
    
    /**
     * Test 1: Verificar que se puede agregar un único libro
     */
    @Test
    public void testAddSingleBook() {
        Book book = new Book("El Quijote", "Miguel de Cervantes", "978-8491050254");
        
        int initialSize = library.getBooks().size();
        library.addBook(book);
        
        assertEquals(initialSize + 1, library.getBooks().size(), 
            "La biblioteca debe contener un libro más después de agregarlo");
        assertTrue(library.getBooks().contains(book), 
            "El libro debe estar en la biblioteca");
    }
    
    /**
     * Test 2: Agregar múltiples libros con ISBN diferentes
     */
    @Test
    public void testAddMultipleDifferentBooks() {
        Book book1 = new Book("Clean Code", "Robert Martin", "978-0132350884");
        Book book2 = new Book("Design Patterns", "Gamma et al.", "978-0201633610");
        Book book3 = new Book("Refactoring", "Martin Fowler", "978-0201485677");
        
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        
        assertEquals(3, library.getBooks().size(), 
            "La biblioteca debe contener exactamente 3 libros");
        assertTrue(library.getBooks().contains(book1) && 
                  library.getBooks().contains(book2) && 
                  library.getBooks().contains(book3),
            "Todos los libros deben estar presentes en la biblioteca");
    }
    
    /**
     * Test 3: Detectar intento de agregar libro duplicado por ISBN
     * Este es el test principal que verifica el BUG de duplicados
     */
    @Test
    public void testAddDuplicateBookBySameISBN() {
        String isbnDuplicado = "978-0132350884";
        
        Book originalBook = new Book("Clean Code", "Robert Martin", isbnDuplicado);
        Book duplicateBook = new Book("Clean Code", "Robert Martin", isbnDuplicado);
        Book anotherVariantBook = new Book("Clean Code 2nd Edition", "Robert Martin", isbnDuplicado);
        
        library.addBook(originalBook);
        int sizeAfterFirst = library.getBooks().size();
        
        library.addBook(duplicateBook);
        int sizeAfterSecond = library.getBooks().size();
        
        library.addBook(anotherVariantBook);
        int sizeAfterThird = library.getBooks().size();
        
        // Se espera que solo haya 1 libro con ese ISBN
        assertEquals(1, sizeAfterFirst, 
            "Debe haber 1 libro después del primer intento");
        assertEquals(1, sizeAfterSecond, 
            "No debe permitir agregar libro duplicado con mismo ISBN");
        assertEquals(1, sizeAfterThird, 
            "No debe permitir agregar variante de libro con mismo ISBN");
    }
    
    /**
     * Test 4: Validar comportamiento con múltiples intentos de duplicado
     */
    @Test
    public void testMultipleAttemptsToAddDuplicates() {
        Book primaryBook = new Book("Algoritmos", "Cormen", "978-8425219997");
        library.addBook(primaryBook);
        
        int sizeAfterPrimary = library.getBooks().size();
        
        // Intentar agregar 5 libros duplicados
        for (int i = 0; i < 5; i++) {
            Book duplicate = new Book("Algoritmos - Copy " + i, "Cormen", "978-8425219997");
            library.addBook(duplicate);
        }
        
        int finalSize = library.getBooks().size();
        
        assertEquals(sizeAfterPrimary, finalSize, 
            "Los intentos de agregar duplicados deben ser bloqueados");
        assertEquals(1, finalSize, 
            "Debe haber solo 1 libro con ese ISBN después de 5 intentos");
    }
    
    /**
     * Test 5: Verificar que se pueden agregar múltiples libros legítimos
     * junto con intentos de duplicados intercalados
     */
    @Test
    public void testMixedLegitimateAndDuplicateBooks() {
        Book book1 = new Book("Programación Java", "Bruce Eckel", "978-8436215961");
        Book book2 = new Book("Python Avanzado", "David Beazley", "978-0134685526");
        Book book3 = new Book("Go Concurrency", "Katherine Cox-Buday", "978-1491927281");
        
        // Agregar libro legítimo
        library.addBook(book1);
        
        // Intentar agregar duplicado del mismo ISBN
        Book duplicate1 = new Book("Programación Java 2e", "Bruce Eckel", "978-8436215961");
        library.addBook(duplicate1);
        
        // Agregar libro legítimo diferente
        library.addBook(book2);
        
        // Intentar agregar otro duplicado del primer ISBN
        Book duplicate2 = new Book("Java Advanced", "Bruce Eckel", "978-8436215961");
        library.addBook(duplicate2);
        
        // Agregar tercer libro legítimo
        library.addBook(book3);
        
        // Intentar agregar duplicado del segundo ISBN
        Book duplicate3 = new Book("Python 3.9+", "David Beazley", "978-0134685526");
        library.addBook(duplicate3);
        
        // Solo debe haber 3 libros legítimos, los duplicados deben estar bloqueados
        assertEquals(3, library.getBooks().size(), 
            "Solo deben estar los 3 libros legítimos, los 3 intentos de duplicados deben bloquearse");
        assertTrue(library.getBooks().contains(book1), "Debe contener el primer libro");
        assertTrue(library.getBooks().contains(book2), "Debe contener el segundo libro");
        assertTrue(library.getBooks().contains(book3), "Debe contener el tercer libro");
    }
    
    /**
     * Test 6: Validar ISBN distinto permite agregar otro libro similar
     */
    @Test
    public void testSimilarBooksWithDifferentISBN() {
        Book book1 = new Book("Design Patterns", "Gamma et al.", "978-0201633610");
        Book book2 = new Book("Design Patterns", "Gamma et al.", "978-0201633611"); // ISBN diferente
        
        library.addBook(book1);
        library.addBook(book2);
        
        assertEquals(2, library.getBooks().size(), 
            "Dos libros con mismo título pero ISBN diferente deben ser agregados");
        assertTrue(library.getBooks().contains(book1) && library.getBooks().contains(book2),
            "Ambos libros deben estar presentes");
    }
}
