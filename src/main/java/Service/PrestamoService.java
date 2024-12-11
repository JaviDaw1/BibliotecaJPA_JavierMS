package Service;

import DAO.PrestamoDAO;
import DTO.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class PrestamoService {
    PrestamoDAO prestamoDAO;
    List<Prestamo> listaPrestamos= new ArrayList<>();

    public PrestamoService(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaPrestamos= prestamoDAO.listaPrestamo();
    }

    public void insrtarPrestamo(Prestamo prestamo){
        prestamoDAO.crear(prestamo);
        sincronizar();
    }

    public void actualizarPrestamo(Prestamo prestamoActu){
        prestamoDAO.actualizar(prestamoActu);
        sincronizar();
    }

    public Prestamo buscarId(int id){
        return prestamoDAO.buscar(id);
    }

    public void eliminar(Prestamo prestamo) {
        prestamoDAO.eliminar(prestamo.getId());
        sincronizar();
    }

    public List<Prestamo> getListaPrestamos() {
        return prestamoDAO.listaPrestamo();
    }
}
