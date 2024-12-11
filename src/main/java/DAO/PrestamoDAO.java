package DAO;

import DTO.Prestamo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PrestamoDAO {
    private EntityManager em;

    public PrestamoDAO(EntityManager em) {
        this.em = em;
    }

    public void crear(Prestamo prestamo){
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
    }

    public void actualizar(Prestamo prestamoActu){
        em.getTransaction().begin();
        em.merge(prestamoActu);
        em.getTransaction().commit();
    }

    public List<Prestamo> listaPrestamo(){
        return em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
    }

    public Prestamo buscar(int id){
        return em.find(Prestamo.class, id);
    }

    public void eliminar(int id){
        em.getTransaction().begin();
        Prestamo p= buscar(id);
        if(p != null){
            em.remove(p);
        }
        em.getTransaction().commit();
    }
}
