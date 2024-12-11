package Service;

import DAO.EjemplarDAO;
import DTO.Ejemplar;

import java.util.ArrayList;
import java.util.List;

public class EjemplarService {
    EjemplarDAO ejemplarDAO;
    List<Ejemplar> listaEjemplares= new ArrayList<>();

    public EjemplarService(EjemplarDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaEjemplares= ejemplarDAO.listaEjemplar();
    }

    public void insertarEjemplar(Ejemplar ejemplar){
        ejemplarDAO.crear(ejemplar);
        sincronizar();
    }

    public void actualizarEjemplar(Ejemplar ejemplarActu){
        ejemplarDAO.actualizar(ejemplarActu);
        sincronizar();
    }

    public Ejemplar buscarId(int id){
        return ejemplarDAO.buscar(id);
    }

    public void eliminar(Ejemplar ejemplar) {
        ejemplarDAO.eliminar(ejemplar.getId());
        sincronizar();
    }

    public List<Ejemplar> getListaEjemplares() {
        return ejemplarDAO.listaEjemplar();
    }
}
