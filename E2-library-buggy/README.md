Sistema de Gestión de Biblioteca (DAW)
Este proyecto es una aplicación Java diseñada para gestionar un catálogo de libros, permitiendo operaciones básicas como añadir, buscar, prestar, devolver y eliminar ejemplares, garantizando la integridad de los datos.

📋 Características Principales
A continuación se detallan las funcionalidades implementadas tras la fase de corrección sistematizada:

    1. Gestión de Libros
    Validación de ISBN Único: El sistema impide la inserción de libros duplicados basándose en su código ISBN.

    Eliminación de Ejemplares: Se ha añadido la capacidad de retirar libros del catálogo de forma segura mediante su identificador único.

    2. Sistema de Búsqueda Avanzada
    Búsqueda por Título (Case-Insensitive): El buscador ignora la diferencia entre mayúsculas y minúsculas para facilitar la experiencia del usuario.

    Búsqueda por ISBN: Método optimizado para encontrar ejemplares específicos rápidamente.

    3. Control de Estado de Préstamos
    Validación Lógica: Se han corregido errores de estado; ahora un libro no puede ser prestado si ya está fuera, ni devuelto si ya se encuentra en la biblioteca.

    Filtrado de Disponibilidad: Capacidad para listar únicamente los libros que están listos para ser prestados en ese momento.

🛠️ Estructura del Proyecto

    Book.java: Define la entidad Libro con sus atributos (título, autor, ISBN) y lógica de estado.

    Library.java: Contiene la lógica de negocio para la gestión de la colección.

    LibraryApp.java: Clase principal para demostración y ejecución manual.

    LibraryTest.java: Suite de pruebas unitarias para garantizar el correcto funcionamiento de las validaciones.
    
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
