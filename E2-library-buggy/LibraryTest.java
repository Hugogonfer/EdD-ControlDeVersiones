import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LibraryTest {
    private Library library;
    
    @BeforeEach
    public void setUp() {
        library = new Library();
    }
    
    /**
     * Test 1: Agregar múltiples libros con ISBN diferentes
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
     * Test 2: Verificar que se pueden agregar múltiples libros legítimos
     * junto con intentos de duplicados intercalados (integración BUG 4)
     * /* */
    @Test
    public void testMixedLegitimateAndDuplicateBooks() {
        Book book1 = new Book("Programación Java", "Bruce Eckel", "978-8436215961");
        Book book2 = new Book("Python Avanzado", "David Beazley", "978-0134685526");
        Book book3 = new Book("Go Concurrency", "Katherine Cox-Buday", "978-1491927281");
        
        // Agregar libro legítimo
        library.addBook(book1);
        
        // Intentar agregar duplicado del mismo ISBN
        Book duplicate1 = new Book("Programación Java 2e", "Bruce Eckel", "978-8436215961");
        assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(duplicate1);
        }, "Debe lanzar excepción al intentar agregar duplicado de book1");
        
        // Agregar libro legítimo diferente
        library.addBook(book2);
        
        // Intentar agregar otro duplicado del primer ISBN
        Book duplicate2 = new Book("Java Advanced", "Bruce Eckel", "978-8436215961");
        assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(duplicate2);
        }, "Debe lanzar excepción al intentar agregar otro duplicado de book1");
        
        // Agregar tercer libro legítimo
        library.addBook(book3);
        
        // Intentar agregar duplicado del segundo ISBN
        Book duplicate3 = new Book("Python 3.9+", "David Beazley", "978-0134685526");
        assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(duplicate3);
        }, "Debe lanzar excepción al intentar agregar duplicado de book2");
        
        // Solo debe haber 3 libros legítimos, los duplicados fueron bloqueados
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
    
    /**
     * Test 7: Búsqueda case-insensitive - minúsculas
     */
    @Test
    public void testFindBookByTitleCaseInsensitiveLowercase() {
        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");
        library.addBook(book);
        
        Book foundBook = library.findBookByTitle("clean code");
        assertNotNull(foundBook, 
            "La búsqueda debe ser case-insensitive y encontrar 'clean code'");
        assertEquals(book, foundBook, "Debe retornar el libro correcto");
    }
    
    /**
     * Test 8: Búsqueda case-insensitive - MAYÚSCULAS
     */
    @Test
    public void testFindBookByTitleCaseInsensitiveUppercase() {
        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");
        library.addBook(book);
        
        Book foundBook = library.findBookByTitle("CLEAN CODE");
        assertNotNull(foundBook, 
            "La búsqueda debe ser case-insensitive y encontrar 'CLEAN CODE'");
        assertEquals(book, foundBook, "Debe retornar el libro correcto");
    }
    
    /**
     * Test 9: Búsqueda case-insensitive - MiXtO
     */
    @Test
    public void testFindBookByTitleCaseInsensitiveMixed() {
        Book book = new Book("The Pragmatic Programmer", "David Thomas", "978-0201616224");
        library.addBook(book);
        
        Book foundBook = library.findBookByTitle("ThE PrAgMaTiC pRoGrAmMeR");
        assertNotNull(foundBook, 
            "La búsqueda debe ser case-insensitive y encontrar variaciones de Case");
        assertEquals(book, foundBook, "Debe retornar el libro correcto");
    }
    
    /**
     * Test 10: Búsqueda case-sensitive exacta (funciona correctamente)
     */
    @Test
    public void testFindBookByTitleExactCase() {
        Book book = new Book("Refactoring", "Martin Fowler", "978-0201485677");
        library.addBook(book);
        
        Book foundBook = library.findBookByTitle("Refactoring");
        assertNotNull(foundBook, 
            "Debe encontrar el libro con búsqueda exacta");
        assertEquals("Refactoring", foundBook.getTitle(), 
            "Debe retornar el libro correcto");
    }
    
    /**
     * Test 11: Múltiples libros con búsqueda case-insensitive
     */
    @Test
    public void testFindBookByTitleMultipleBooksVariantCase() {
        Book book1 = new Book("Java Programming", "Oracle", "978-0134454122");
        Book book2 = new Book("Python Basics", "Guido van Rossum", "978-0134685526");
        library.addBook(book1);
        library.addBook(book2);
        
        // Búsqueda con mayúsculas diferentes
        Book foundJava = library.findBookByTitle("java programming");
        Book foundPython = library.findBookByTitle("PYTHON BASICS");
        
        assertNotNull(foundJava, "Debe encontrar 'java programming' (case-insensitive)");
        assertNotNull(foundPython, "Debe encontrar 'PYTHON BASICS' (case-insensitive)");
        assertEquals(book1, foundJava, "Debe retornar el primer libro");
        assertEquals(book2, foundPython, "Debe retornar el segundo libro");
    }
    
    // ==================== TESTS DE VALIDACIÓN DE BUGS ====================
    
    /**
     * BUG 1: Validar que existen todos los getters y setters
     */
    @Test
    public void testBug1GettersAndSetters() {
        Book book = new Book("Test Title", "Test Author", "978-1234567890");
        
        // Validar getters
        assertEquals("Test Title", book.getTitle(), "getTitle() debe retornar el título");
        assertEquals("Test Author", book.getAuthor(), "getAuthor() debe retornar el autor");
        assertEquals("978-1234567890", book.getIsbn(), "getIsbn() debe retornar el ISBN");
        assertTrue(book.isAvailable(), "isAvailable() debe retornar true inicialmente");
        
        // Validar setters
        book.setTitle("New Title");
        assertEquals("New Title", book.getTitle(), "setTitle() debe actualizar el título");
        
        book.setAuthor("New Author");
        assertEquals("New Author", book.getAuthor(), "setAuthor() debe actualizar el autor");
        
        book.setIsbn("978-0987654321");
        assertEquals("978-0987654321", book.getIsbn(), "setIsbn() debe actualizar el ISBN");
        
        book.setAvailable(false);
        assertFalse(book.isAvailable(), "setAvailable() debe actualizar la disponibilidad");
    }
    
    /**
     * BUG 2: Validar que borrow() no permite prestar un libro ya prestado
     */
    @Test
    public void testBug2BorrowValidation() {
        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");
        
        // Primer préstamo debe funcionar
        book.borrow();
        assertFalse(book.isAvailable(), "El libro debe estar prestado después de borrow()");
        
        // Segundo préstamo debe lanzar excepción
        assertThrows(IllegalStateException.class, () -> {
            book.borrow();
        }, "borrow() debe lanzar excepción al intentar prestar un libro ya prestado");
    }
    
    /**
     * BUG 3: Validar que returnBook() no permite devolver un libro ya disponible
     */
    @Test
    public void testBug3ReturnBookValidation() {
        Book book = new Book("Design Patterns", "Gamma et al.", "978-0201633610");
        
        // Intentar devolver un libro que nunca fue prestado debe lanzar excepción
        assertThrows(IllegalStateException.class, () -> {
            book.returnBook();
        }, "returnBook() debe lanzar excepción al intentar devolver un libro disponible");
        
        // Prestar y devolver debe funcionar
        book.borrow();
        book.returnBook();
        assertTrue(book.isAvailable(), "El libro debe estar disponible después de returnBook()");
        
        // Intentar devolver de nuevo debe lanzar excepción
        assertThrows(IllegalStateException.class, () -> {
            book.returnBook();
        }, "returnBook() debe lanzar excepción al intentar devolver nuevamente");
    }
    
    /**
     * BUG 4: Validar que addBook() no permite libros duplicados (mismo ISBN)
     */
    @Test
    public void testBug4NoDuplicateISBN() {
        Library testLibrary = new Library();
        Book book1 = new Book("Clean Code", "Robert Martin", "978-0132350884");
        Book book2 = new Book("Clean Code Plus", "Robert Martin", "978-0132350884");
        
        testLibrary.addBook(book1);
        
        // Intentar agregar libro con mismo ISBN debe lanzar excepción
        assertThrows(IllegalArgumentException.class, () -> {
            testLibrary.addBook(book2);
        }, "addBook() debe lanzar excepción al intentar agregar libro con ISBN duplicado");
        
        assertEquals(1, testLibrary.getBooks().size(), "La biblioteca debe contener solo 1 libro");
    }
    
    /**
     * BUG 6 y 7: Validar findAvailableBooks() funciona sin ConcurrentModification
     * y verifica disponibilidad real
     */
    @Test
    public void testBug6And7FindAvailableBooks() {
        Library testLibrary = new Library();
        Book book1 = new Book("Available Book", "Author 1", "978-1111111111");
        Book book2 = new Book("Borrowed Book", "Author 2", "978-2222222222");
        Book book3 = new Book("Another Available", "Author 3", "978-3333333333");
        
        testLibrary.addBook(book1);
        testLibrary.addBook(book2);
        testLibrary.addBook(book3);
        
        // Prestar el libro 2
        book2.borrow();
        
        // findAvailableBooks() debe retornar solo 2 libros, no 3
        List<Book> availableBooks = testLibrary.findAvailableBooks();
        assertEquals(2, availableBooks.size(), 
            "findAvailableBooks() debe retornar solo los libros disponibles (no todos)");
        assertTrue(availableBooks.contains(book1), "Debe contener el primer libro (disponible)");
        assertFalse(availableBooks.contains(book2), "No debe contener el libro prestado");
        assertTrue(availableBooks.contains(book3), "Debe contener el tercer libro (disponible)");
    }
    
    /**
     * BUG 8: Validar que existe método removeBookByISBN() y funciona correctamente
     */
    @Test
    public void testBug8RemoveBookByISBN() {
        Library testLibrary = new Library();
        Book book1 = new Book("Book 1", "Author 1", "978-1111111111");
        Book book2 = new Book("Book 2", "Author 2", "978-2222222222");
        Book book3 = new Book("Book 3", "Author 3", "978-3333333333");
        
        testLibrary.addBook(book1);
        testLibrary.addBook(book2);
        testLibrary.addBook(book3);
        
        assertEquals(3, testLibrary.getBooks().size(), "Debe haber 3 libros inicialmente");
        
        // Remover libro existente
        boolean result1 = testLibrary.removeBookByISBN("978-2222222222");
        assertTrue(result1, "removeBookByISBN() debe retornar true al remover un libro existente");
        assertEquals(2, testLibrary.getBooks().size(), "Debe haber 2 libros después de remover");
        
        // Remover libro NO existente
        boolean result2 = testLibrary.removeBookByISBN("978-9999999999");
        assertFalse(result2, "removeBookByISBN() debe retornar false al no encontrar el libro");
        assertEquals(2, testLibrary.getBooks().size(), "Debe seguir habiendo 2 libros");
        
        // Verificar que los libros correctos siguen presentes
        assertTrue(testLibrary.getBooks().contains(book1), "Debe contener el primer libro");
        assertTrue(testLibrary.getBooks().contains(book3), "Debe contener el tercer libro");
        assertFalse(testLibrary.getBooks().contains(book2), "No debe contener el segundo libro");
    }
}
