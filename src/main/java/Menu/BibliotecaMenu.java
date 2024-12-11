package Menu;

import DTO.Usuario;
import Service.EjemplarService;
import Service.LibroService;
import Service.PrestamoService;
import Service.UsuarioService;

import java.time.LocalDate;
import java.util.Scanner;

public class BibliotecaMenu {
    private final EjemplarService ejemplarService;
    private final LibroService libroService;
    private final PrestamoService prestamoService;
    private final UsuarioService usuarioService;

    public BibliotecaMenu(EjemplarService ejemplarService, LibroService libroService, PrestamoService prestamoService, UsuarioService usuarioService) {
        this.ejemplarService = ejemplarService;
        this.libroService = libroService;
        this.prestamoService = prestamoService;
        this.usuarioService = usuarioService;
    }

    public void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Inicio de Sesión =====");
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = usuarioService.getListaUsuarios().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (usuario != null) {
            if (usuario.getPenalizacionHasta() != null && usuario.getPenalizacionHasta().isAfter(LocalDate.now())) {
                System.out.println("Usted tiene una penalización activa hasta: " + usuario.getPenalizacionHasta());
                return;
            }

            System.out.println("Bienvenido, " + usuario.getNombre());
            if (usuario.getTipo().equalsIgnoreCase("administrador")) {
                UsuarioAdminMenu adminMenu = new UsuarioAdminMenu(ejemplarService, libroService, prestamoService, usuarioService);
                adminMenu.mostrarMenu();
            } else if (usuario.getTipo().equalsIgnoreCase("normal")) {
                UsuarioNormalMenu normalMenu = new UsuarioNormalMenu(prestamoService);
                normalMenu.mostrarMenu(usuario);
            }
        } else {
            System.out.println("Credenciales incorrectas. Intente de nuevo.");
        }
    }
}
