# Práctica Integrada: Biblioteca con Bugs

## Instrucciones Especiales
Esta práctica combina **depuración** y **control de versiones**. Para cada bug:

1. **IDENTIFÍCALO** con el debugger de VS Code
2. **DOCUMÉNTALO** en Issues de GitHub
3. **CREA UN TEST** que lo reproduzca
4. **CORRÍGELO** en un commit separado
5. **VERIFICA** que tu test ahora pasa

## Bugs Conocidos (¡No mires si quieres el desafío!)
<!-- Esto está oculto en detalles -->
<details>
<summary>Lista de bugs (SPOILER)</summary>

1. ✅ Libros duplicados permitidos - **CORREGIDO**
2. ✅ Búsqueda sensible a mayúsculas/minúsculas - **CORREGIDO**
3. ✅ Puedes prestar un libro ya prestado - **CORREGIDO**
4. ✅ Puedes devolver un libro ya disponible - **CORREGIDO**
5. Listado de disponibles muestra todos - **PENDIENTE**
6. ✅ Falta método para quitar libros - **CORREGIDO (removeBook aggregado)**
7. Faltan getters importantes - **PENDIENTE**
</details>

## Bugs Corregidos y Nuevas Funcionalidades

### 1. Validación de Libros Duplicados
- **Problema**: Se permitía agregar múltiples libros con el mismo ISBN
- **Solución**: El método `addBook()` ahora valida que no exista un libro con el mismo ISBN
- **Método**: `isDuplicateISBN(String isbn)` privado para validación

### 2. Búsqueda Case-Insensitive
- **Problema**: La búsqueda por título era sensible a mayúsculas/minúsculas
- **Solución**: Se cambió `equals()` a `equalsIgnoreCase()` en `findBookByTitle()`
- **Ejemplo**: Ahora "clean code", "CLEAN CODE" y "Clean Code" retornan el mismo libro

### 3. Validación en borrow()
- **Problema**: Se podía prestar un libro que ya estaba prestado
- **Solución**: Valida que el libro esté disponible antes de prestarlo
- **Excepción**: Lanza `IllegalStateException` si lo intentas

### 4. Validación en returnBook()
- **Problema**: Se podía devolver un libro que ya estaba disponible
- **Solución**: Valida que el libro esté prestado antes de devolverlo
- **Excepción**: Lanza `IllegalStateException` si lo intentas

### 5. Método removeBook() (Nueva Funcionalidad)
- **Descripción**: Permite eliminar un libro de la biblioteca por su ISBN
- **Firma**: `public boolean removeBook(String isbn)`
- **Retorna**: `true` si se eliminó, `false` si no existe

## Métodos Disponibles en Library

```java
// Gestión de libros
void addBook(Book book)                    // Agrega libro (evita duplicados)
boolean removeBook(String isbn)            // Elimina libro por ISBN
List<Book> getBooks()                      // Retorna todos los libros

// Búsqueda
Book findBookByTitle(String title)         // Búsqueda case-insensitive
List<Book> findAvailableBooks()            // Lista libros disponibles

// Utilidad privada
private boolean isDuplicateISBN(String isbn)  // Verifica duplicados
```

## Métodos Disponibles en Book

```java
// Getters
String getTitle()
String getAuthor()
String getISBN()
Boolean getAvailable()

// Operaciones
void borrow()                              // Marca libro como prestado (valida estado)
void returnBook()                          // Devuelve libro a disponible (valida estado)
```

## Evidencias Requeridas
Para cada bug, incluir en el PR:
- [ ] Screenshot del breakpoint
- [ ] Screenshot de variables en el momento del error
- [ ] Enlace al issue correspondiente
- [ ] Código del test que lo reproduce

## Estructura del repositorio
```bash
/E2-library-buggy/
├── README.md
├── .gitignore      # Archivos a ignorar (localizado en un directorio superior) 
├── Main.java       # Punto de entrada
├── Book.java
├── Library.java
├── E2-Full_Stack_Developer-Debug+Git.pdf
└── /documentacion/    # Para las capturas de pantalla, entre otras las del debugger
```
