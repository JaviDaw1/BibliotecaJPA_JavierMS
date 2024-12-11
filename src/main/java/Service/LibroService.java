package Service;

import DAO.LibroDAO;
import DTO.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroService {
    LibroDAO libroDAO;
    List<Libro> listaLibros= new ArrayList<>();

    public LibroService(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaLibros= libroDAO.listaLibro();
    }

    public void insertarLibro(Libro productos){
        libroDAO.crear(productos);
        sincronizar();
    }

    public void actualizarLibro(Libro productosActu){
        libroDAO.actualizar(productosActu);
        sincronizar();
    }

    public Libro buscarIsbn(String isbn){
        return libroDAO.buscar(isbn);
    }

    public Libro buscarId(int id){
        return libroDAO.buscarId(id);
    }

    public void eliminar(Libro libro) {
        libroDAO.eliminar(libro.getIsbn());
        sincronizar();
    }

    public List<Libro> getListaLibros() {
        return libroDAO.listaLibro();
    }
}
