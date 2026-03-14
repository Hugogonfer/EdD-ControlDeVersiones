// LibraryApp.java - Aplicación principal
public class LibraryApp {
    public static void main(String[] args) {
        // Creamos una nueva librería
        Library library = new Library();
        
        // Agregar nuestros libros favoritos
        library.addBook(new Book("Clean Code", "Robert Martin", "978-0132350884"));
        library.addBook(new Book("Clean Code", "Robert Martin", "978-0132350884")); // Duplicado
        library.addBook(new Book("Design Patterns", "Gamma et al.", "978-0201633610"));
        
        // BUG: Intentar buscar con diferente capitalización
        Book found = library.findBookByTitle("clean code");
        System.out.println("Encontrado: " + (found != null ? found.getTitle() : "null"));
        
        // Comprobamos el préstamo
        Book book = library.findBookByTitle("Clean Code");
        if (book != null) {
            book.borrow();
            book.borrow(); // BUG: Prestar dos veces
            book.returnBook();
            book.returnBook(); // BUG: Devolver dos veces
        }
        
        // Listar disponibles
        System.out.println("Libros disponibles: " + library.findAvailableBooks().size());
    }
}
