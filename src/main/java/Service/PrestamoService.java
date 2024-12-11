package Service;

import DAO.PrestamoDAO;
import DTO.Ejemplar;
import DTO.Prestamo;
import DTO.Usuario;
import java.time.LocalDate;
import java.util.List;

public class PrestamoService {
    PrestamoDAO prestamoDAO;
    List<Prestamo> listaPrestamos;

    public PrestamoService(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaPrestamos = prestamoDAO.listaPrestamo();
    }

    public void insertarPrestamo(Prestamo prestamo) {
/*        Usuario usuario = prestamo.getUsuario();
        Ejemplar ejemplar = prestamo.getEjemplar();
        validarPrestamo(usuario, ejemplar);
        ejemplar.setEstado("Prestado");*/
        prestamo.setFechaDevolucion(prestamo.getFechaInicio().plusDays(15));
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

/*    private void validarPrestamo(Usuario usuario, Ejemplar ejemplar) {
        if (!"Disponible".equalsIgnoreCase(ejemplar.getEstado())) {
            throw new IllegalArgumentException("El ejemplar no está disponible.");
        }

        long prestamosActivos = listaPrestamos.stream()
                .filter(p -> p.getUsuario().getId().equals(usuario.getId()) && p.getFechaDevolucion() == null)
                .count();

        if (prestamosActivos >= 3) {
            throw new IllegalArgumentException("El usuario ya tiene 3 préstamos activos.");
        }

        if (usuario.getPenalizacionHasta() != null && usuario.getPenalizacionHasta().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("El usuario tiene una penalización activa hasta: " + usuario.getPenalizacionHasta());
        }
    }

    public void registrarDevolucion(int prestamoId) {
        Prestamo prestamo = buscarId(prestamoId);
        if (prestamo == null) {
            throw new IllegalArgumentException("Préstamo no encontrado.");
        }

        Ejemplar ejemplar = prestamo.getEjemplar();
        ejemplar.setEstado("Disponible");

        if (LocalDate.now().isAfter(prestamo.getFechaDevolucion())) {
            long diasRetraso = LocalDate.now().toEpochDay() - prestamo.getFechaDevolucion().toEpochDay();
            Usuario usuario = prestamo.getUsuario();
            long diasPenalizacion = diasRetraso * 15;
            usuario.setPenalizacionHasta(LocalDate.now().plusDays(diasPenalizacion));
        }

        prestamo.setFechaDevolucion(LocalDate.now());
        actualizarPrestamo(prestamo);
    }*/
}
