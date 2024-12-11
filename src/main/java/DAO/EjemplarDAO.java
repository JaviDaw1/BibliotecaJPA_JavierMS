package DAO;

import DTO.Ejemplar;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EjemplarDAO {
    private EntityManager em;

    public EjemplarDAO(EntityManager em) {
        this.em = em;
    }

    public void crear(Ejemplar ejemplar){
        em.getTransaction().begin();
        em.persist(ejemplar);
        em.getTransaction().commit();
    }

    public void actualizar(Ejemplar ejemplarActu){
        em.getTransaction().begin();
        em.merge(ejemplarActu);
        em.getTransaction().commit();
    }

    public List<Ejemplar> listaEjemplar(){
        return em.createQuery("SELECT e FROM Ejemplar e", Ejemplar.class).getResultList();
    }

    public Ejemplar buscar(int id){
        return em.find(Ejemplar.class, id);
    }

    public void eliminar(int id) {
        em.getTransaction().begin();
        Ejemplar p = buscar(id);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
    }

    public List<Ejemplar> buscarPorNombre(String nombre){
        return em.createQuery("SELECT e FROM Ejemplar e", Ejemplar.class).getResultList();
    }
}
