package Service;

import DAO.UsuarioDAO;
import DTO.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    UsuarioDAO usuarioDAO;
    List<Usuario> listaUsuario= new ArrayList<>();

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaUsuario= usuarioDAO.listaUsuario();
    }

    public void insrtarUsuario(Usuario prestamo){
        usuarioDAO.crear(prestamo);
        sincronizar();
    }

    public Usuario buscarId(int id){
        return usuarioDAO.buscar(id);
    }

    public void actualizarUsuario(Usuario prestamoActu){
        usuarioDAO.actualizar(prestamoActu);
        sincronizar();
    }

    public void eliminar(Usuario prestamo) {
        usuarioDAO.eliminar(prestamo.getId());
        sincronizar();
    }

    public List<Usuario> getListaUsuarios() {
        return usuarioDAO.listaUsuario();
    }
}
