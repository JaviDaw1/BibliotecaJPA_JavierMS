package DAO;

import DTO.Usuario;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UsuarioDAO {
    private EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public void crear(Usuario usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void actualizar(Usuario usuarioActu){
        em.getTransaction().begin();
        em.merge(usuarioActu);
        em.getTransaction().commit();
    }

    public List<Usuario> listaUsuario(){
        return em.createQuery("SELECT p FROM Usuario p", Usuario.class).getResultList();
    }

    public Usuario buscar(int id){
        return em.find(Usuario.class, id);
    }

    public void eliminar(int id){
        em.getTransaction().begin();
        Usuario p= buscar(id);
        if(p != null){
            em.remove(p);
        }
        em.getTransaction().commit();
    }
}
