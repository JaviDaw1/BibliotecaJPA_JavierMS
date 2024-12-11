package DAO;

import DTO.Libro;
import jakarta.persistence.EntityManager;

import javax.naming.LinkRef;
import java.util.List;

public class LibroDAO {
    private EntityManager em;

    public LibroDAO(EntityManager em) {
        this.em = em;
    }

    public void crear(Libro libro){
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public void actualizar(Libro libroActu){
        em.getTransaction().begin();
        em.merge(libroActu);
        em.getTransaction().commit();
    }

    public List<Libro> listaLibro(){
        return em.createQuery("SELECT p FROM Libro p", Libro.class).getResultList();
    }

    public Libro buscar(String isbn){
        return em.find(Libro.class, isbn);
    }

    public Libro buscarId(int id){
        return em.find(Libro.class, id);
    }

    public void eliminar(String isbn){
        em.getTransaction().begin();
        Libro p= buscar(isbn);
        if(p != null){
            em.remove(p);
        }
        em.getTransaction().commit();
    }
}
