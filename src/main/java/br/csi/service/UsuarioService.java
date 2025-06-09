package br.csi.service;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;
import java.util.ArrayList;

public class  UsuarioService {

    private static UsuarioDAO dao = new UsuarioDAO();

    public String excluir(int id){

        if(dao.excluir(id)){
            return "Sucesso ao excluir usuario";
        }else{
            return "Erro ao excluir usuario";
        }

    }

    public ArrayList<Usuario> listar(){
        return dao.listar();
    }

    public Usuario buscar(int usuarioId) {
        return dao.buscar(usuarioId);
    }

    public Usuario buscar(String email) {
        return dao.buscar(email);
    }

    public String alterar(Usuario usuario) {
        return dao.alterar(usuario);
    }

    public String inserir(Usuario usuario) {
        return dao.inserir(usuario);
    }

}
