package Menu;

import DTO.Prestamo;
import DTO.Usuario;
import Service.PrestamoService;

import java.util.Scanner;

public class UsuarioNormalMenu {
    private final PrestamoService prestamoService;

    public UsuarioNormalMenu(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    public void mostrarMenu(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("===== Menú de Usuario =====");
            System.out.println("1. Ver mis Préstamos");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> listarPrestamosUsuario(usuario);
                case 2 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 2);
    }

    private void listarPrestamosUsuario(Usuario usuario) {
        System.out.println("Tus Préstamos:");
        prestamoService.getListaPrestamos().stream()
                .filter(p -> p.getUsuario().getId().equals(usuario.getId()))
                .forEach(System.out::println);
    }
}
