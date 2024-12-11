import DAO.EjemplarDAO;
import DAO.LibroDAO;
import DAO.PrestamoDAO;
import DAO.UsuarioDAO;
import Menu.BibliotecaMenu;
import Service.EjemplarService;
import Service.LibroService;
import Service.PrestamoService;
import Service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager em= emf.createEntityManager();

        EjemplarDAO ejemplarDAO = new EjemplarDAO(em);
        LibroDAO libroDAO= new LibroDAO(em);
        PrestamoDAO prestamoDAO= new PrestamoDAO(em);
        UsuarioDAO usuarioDAO= new UsuarioDAO(em);

        EjemplarService ejemplarService= new EjemplarService(ejemplarDAO);
        LibroService libroService= new LibroService(libroDAO);
        PrestamoService prestamoService= new PrestamoService(prestamoDAO);
        UsuarioService usuarioService= new UsuarioService(usuarioDAO);

        BibliotecaMenu menu = new BibliotecaMenu(ejemplarService, libroService, prestamoService, usuarioService);
        menu.iniciarSesion();

        em.close();
        emf.close();
    }
}
