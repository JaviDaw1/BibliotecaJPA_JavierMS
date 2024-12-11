package Menu;

import DTO.Ejemplar;
import DTO.Libro;
import DTO.Prestamo;
import DTO.Usuario;
import Service.EjemplarService;
import Service.LibroService;
import Service.PrestamoService;
import Service.UsuarioService;

import java.sql.Date;
import java.util.Scanner;

public class UsuarioAdminMenu {
    private final EjemplarService ejemplarService;
    private final LibroService libroService;
    private final PrestamoService prestamoService;
    private final UsuarioService usuarioService;

    public UsuarioAdminMenu(EjemplarService ejemplarService, LibroService libroService, PrestamoService prestamoService, UsuarioService usuarioService) {
        this.ejemplarService = ejemplarService;
        this.libroService = libroService;
        this.prestamoService = prestamoService;
        this.usuarioService = usuarioService;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===== Menú Principal =====");
            System.out.println("1. Gestión de Ejemplares");
            System.out.println("2. Gestión de Libros");
            System.out.println("3. Gestión de Préstamos");
            System.out.println("4. Gestión de Usuarios");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> menuEjemplares(scanner);
                case 2 -> menuLibros(scanner);
                case 3 -> menuPrestamos(scanner);
                case 4 -> menuUsuarios(scanner);
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // Menu Ejemplares
    private void menuEjemplares(Scanner scanner) {
        int opcion;
        do {
            System.out.println("===== Gestión de Ejemplares =====");
            System.out.println("1. Listar Ejemplares");
            System.out.println("2. Crear Ejemplar");
            System.out.println("3. Actualizar Ejemplar");
            System.out.println("4. Eliminar Ejemplar");
            System.out.println("5. Revisar Stock");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();


            switch (opcion) {
                case 1 -> listarEjemplares();
                case 2 -> crearEjemplar(scanner);
                case 3 -> actualizarEjemplar(scanner);
                case 4 -> eliminarEjemplar(scanner);
                case 5 -> obtenerStock(scanner);
                case 6 -> System.out.println("Regresando al Menú Principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void listarEjemplares() {
        System.out.println("Lista de Ejemplares:");
        for (Ejemplar ejemplar : ejemplarService.getListaEjemplares()) {
            System.out.println(ejemplar);
        }
    }

    private void crearEjemplar(Scanner scanner) {
        System.out.println("Crear Nuevo Ejemplar:");
        System.out.print("Ingrese ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese Estado: ");
        String estado = scanner.nextLine();

        Ejemplar nuevoEjemplar = new Ejemplar();
        nuevoEjemplar.setLibroId(isbn, libroService);
        nuevoEjemplar.setEstado(estado);

        ejemplarService.insertarEjemplar(nuevoEjemplar);
        System.out.println("Ejemplar creado exitosamente.");
    }

    private void actualizarEjemplar(Scanner scanner) {
        System.out.print("Ingrese el ID del Ejemplar a actualizar: ");
        int id = scanner.nextInt();
        Ejemplar ejemplar = ejemplarService.getListaEjemplares().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (ejemplar != null) {
            scanner.nextLine();
            System.out.print("Ingrese nuevo Estado: ");
            String estado = scanner.nextLine();

            ejemplar.setEstado(estado);
            ejemplarService.actualizarEjemplar(ejemplar);
            System.out.println("Ejemplar actualizado exitosamente.");
        } else {
            System.out.println("Ejemplar no encontrado.");
        }
    }

    private void eliminarEjemplar(Scanner scanner) {
        System.out.print("Ingrese el ID del Ejemplar a eliminar: ");
        int id = scanner.nextInt();
        Ejemplar ejemplar = ejemplarService.getListaEjemplares().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (ejemplar != null) {
            ejemplarService.eliminar(ejemplar);
            System.out.println("Ejemplar eliminado exitosamente.");
        } else {
            System.out.println("Ejemplar no encontrado.");
        }
    }

    private void obtenerStock(Scanner scanner) {
        System.out.print("Ingrese el ISBN del Libro que quieres revisar el Stock: ");
        String isbn = scanner.nextLine();

        long stock = ejemplarService.getListaEjemplares().stream()
                .filter(e -> e.getLibro().getIsbn().equals(isbn) && e.getEstado().equalsIgnoreCase("Disponible"))
                .count();

        if (stock > 0) {
            System.out.println("Número de ejemplares disponibles para el libro con ISBN '" + isbn + "': " + stock);
        } else {
            System.out.println("No hay ejemplares disponibles para el libro con ISBN: " + isbn);
        }
    }


    // Menu Libros
    private void menuLibros(Scanner scanner) {
        int opcion;
        do {
            System.out.println("===== Gestión de Libros =====");
            System.out.println("1. Listar Libros");
            System.out.println("2. Crear Libro");
            System.out.println("3. Actualizar Libro");
            System.out.println("4. Eliminar Libro");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> listarLibros();
                case 2 -> crearLibro(scanner);
                case 3 -> actualizarLibro(scanner);
                case 4 -> eliminarLibro(scanner);
                case 5 -> System.out.println("Regresando al Menú Principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void listarLibros() {
        System.out.println("Lista de Libros:");
        for (Libro libro : libroService.getListaLibros()) {
            System.out.println(libro);
        }
    }

    private void crearLibro(Scanner scanner) {
        System.out.println("Crear Nuevo Libro:");
        scanner.nextLine();
        System.out.print("Ingrese ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese Autor: ");
        String autor = scanner.nextLine();

        Libro nuevoLibro = new Libro();
        nuevoLibro.setIsbn(isbn);
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAutor(autor);

        libroService.insertarLibro(nuevoLibro);
        System.out.println("Libro creado exitosamente.");
    }

    private void actualizarLibro(Scanner scanner) {
        System.out.print("Ingrese el ISBN del Libro a actualizar: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();
        Libro libro = libroService.buscarIsbn(isbn);

        if (libro != null) {
            System.out.print("Ingrese nuevo Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Ingrese nuevo Autor: ");
            String autor = scanner.nextLine();

            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libroService.actualizarLibro(libro);
            System.out.println("Libro actualizado exitosamente.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void eliminarLibro(Scanner scanner) {
        System.out.print("Ingrese el ISBN del Libro a eliminar: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();
        Libro libro = libroService.buscarIsbn(isbn);

        if (libro != null) {
            libroService.eliminar(libro);
            System.out.println("Libro eliminado exitosamente.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }


    // Menu Prestamos
    private void menuPrestamos(Scanner scanner) {
        int opcion;
        do {
            System.out.println("===== Gestión de Préstamos =====");
            System.out.println("1. Listar Préstamos");
            System.out.println("2. Crear Préstamo");
            System.out.println("3. Actualizar Préstamo");
            System.out.println("4. Eliminar Préstamo");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> listarPrestamos();
                case 2 -> crearPrestamo(scanner);
                case 3 -> actualizarPrestamo(scanner);
                case 4 -> eliminarPrestamo(scanner);
                case 5 -> System.out.println("Regresando al Menú Principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void listarPrestamos() {
        System.out.println("Lista de Préstamos:");
        for (Prestamo prestamo : prestamoService.getListaPrestamos()) {
            System.out.println(prestamo);
        }
    }

    private void crearPrestamo(Scanner scanner) {
        System.out.println("Crear Nuevo Préstamo:");
        System.out.print("Ingrese ID del Usuario: ");
        int usuarioId = scanner.nextInt();
        System.out.print("Ingrese ID del Ejemplar: ");
        int ejemplarId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese Fecha de Inicio (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese Fecha de Devolución (YYYY-MM-DD): ");
        String fechaDevolucion = scanner.nextLine();

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setUsuarioId(usuarioId, usuarioService);
        nuevoPrestamo.setEjemplarId(ejemplarId, ejemplarService);
        nuevoPrestamo.setFechaInicio(Date.valueOf(fechaInicio).toLocalDate());
        nuevoPrestamo.setFechaDevolucion(Date.valueOf(fechaDevolucion).toLocalDate());

        prestamoService.insrtarPrestamo(nuevoPrestamo);
        System.out.println("Préstamo creado exitosamente.");
    }

    private void actualizarPrestamo(Scanner scanner) {
        System.out.print("Ingrese el ID del Préstamo a actualizar: ");
        int id = scanner.nextInt();
        Prestamo prestamo = prestamoService.buscarId(id);

        if (prestamo != null) {
            scanner.nextLine();
            System.out.print("Ingrese nueva Fecha de Devolución (YYYY-MM-DD): ");
            String fechaDevolucion = scanner.nextLine();

            prestamo.setFechaDevolucion(Date.valueOf(fechaDevolucion).toLocalDate());
            prestamoService.actualizarPrestamo(prestamo);
            System.out.println("Préstamo actualizado exitosamente.");
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    private void eliminarPrestamo(Scanner scanner) {
        System.out.print("Ingrese el ID del Préstamo a eliminar: ");
        int id = scanner.nextInt();
        Prestamo prestamo = prestamoService.buscarId(id);

        if (prestamo != null) {
            prestamoService.eliminar(prestamo);
            System.out.println("Préstamo eliminado exitosamente.");
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }


    // Menu Usuarios
    private void menuUsuarios(Scanner scanner) {
        int opcion;
        do {
            System.out.println("===== Gestión de Usuarios =====");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Crear Usuario");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> listarUsuarios();
                case 2 -> crearUsuario(scanner);
                case 3 -> actualizarUsuario(scanner);
                case 4 -> eliminarUsuario(scanner);
                case 5 -> System.out.println("Regresando al Menú Principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void listarUsuarios() {
        System.out.println("Lista de Usuarios:");
        for (Usuario usuario : usuarioService.getListaUsuarios()) {
            System.out.println(usuario);
        }
    }

    private void crearUsuario(Scanner scanner) {
        System.out.println("Crear Nuevo Usuario:");
        scanner.nextLine();
        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Ingrese Tipo de Usuario: ");
        String tipo = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setDni(dni);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setTipo(tipo);

        usuarioService.insrtarUsuario(nuevoUsuario);
        System.out.println("Usuario creado exitosamente.");
    }

    private void actualizarUsuario(Scanner scanner) {
        System.out.print("Ingrese el ID del Usuario a actualizar: ");
        int id = scanner.nextInt();
        Usuario usuario = usuarioService.buscarId(id);

        if (usuario != null) {
            scanner.nextLine();
            System.out.print("Ingrese nuevo Email: ");
            String email = scanner.nextLine();
            System.out.print("Ingrese nueva Contraseña: ");
            String password = scanner.nextLine();

            usuario.setEmail(email);
            usuario.setPassword(password);
            usuarioService.actualizarUsuario(usuario);
            System.out.println("Usuario actualizado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void eliminarUsuario(Scanner scanner) {
        System.out.print("Ingrese el ID del Usuario a eliminar: ");
        int id = scanner.nextInt();
        Usuario usuario = usuarioService.buscarId(id);

        if (usuario != null) {
            usuarioService.eliminar(usuario);
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
