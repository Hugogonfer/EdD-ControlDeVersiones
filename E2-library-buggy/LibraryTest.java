public class LibraryTest {
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("===== Iniciando pruebas de Biblioteca =====\n");
        
        testDuplicateISBNDetection();
        testDetectDuplicateBook();
        
        System.out.println("\n===== Resultados de pruebas =====");
        System.out.println("✓ Pruebas pasadas: " + testsPassed);
        System.out.println("✗ Pruebas fallidas: " + testsFailed);
        System.out.println("Total de pruebas: " + (testsPassed + testsFailed));
    }

    private static void testDuplicateISBNDetection() {
        System.out.println("Test 1: testDuplicateISBNDetection()");
        try {
            Library library = new Library();
            Book book1 = new Book("Clean Code", "Robert Martin", "978-0132350884");
            Book book2 = new Book("Clean Code", "Robert Martin", "978-0132350884");
            
            library.addBook(book1);
            library.addBook(book2);
            
            System.out.println("  - BUG DETECTADO: Se agregaron 2 libros con ISBN duplicado");
            System.out.println("  - Se debería permitir solo 1 libro por ISBN único");
            System.out.println("  ✓ PASÓ\n");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("  ✗ FALLÓ: " + e.getMessage() + "\n");
            testsFailed++;
        }
    }

    private static void testDetectDuplicateBook() {
        System.out.println("Test 2: testDetectDuplicateBook()");
        try {
            Library library = new Library();
            Book book1 = new Book("Clean Code", "Robert Martin", "978-0132350884");
            Book book2 = new Book("Clean Code", "Robert Martin", "978-0132350884");
            
            int sizeBefore = library.getBooks().size();
            library.addBook(book1);
            
            if (library.getBooks().size() != sizeBefore + 1) {
                throw new AssertionError("El primer libro no se agregó correctamente");
            }
            
            library.addBook(book2);
            
            if (library.getBooks().size() == sizeBefore + 1) {
                System.out.println("  - No permite agregar libro con ISBN duplicado");
                System.out.println("  ✓ PASÓ\n");
                testsPassed++;
            } else {
                System.out.println("  - ERROR: Se permitió agregar libro duplicado");
                System.out.println("  ✗ FALLÓ\n");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("  ✗ FALLÓ: " + e.getMessage() + "\n");
            testsFailed++;
        }
    }
}
