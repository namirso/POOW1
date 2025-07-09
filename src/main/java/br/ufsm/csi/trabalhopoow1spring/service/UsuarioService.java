package br.ufsm.csi.trabalhopoow1spring.service;

import br.ufsm.csi.trabalhopoow1spring.dao.PermissaoDAO;
import br.ufsm.csi.trabalhopoow1spring.dao.UsuarioDAO;
import br.ufsm.csi.trabalhopoow1spring.model.Permissao;
import br.ufsm.csi.trabalhopoow1spring.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class  UsuarioService {

    @Autowired
    private static UsuarioDAO dao = new UsuarioDAO();

    public String excluir(int id) {
        if (dao.temReviewsAssociadas(id)) {
            return "Não é possível excluir - usuário possui reviews cadastradas!";
        }

        return dao.excluir(id);
    }

    public ArrayList<Usuario> listar() {
        return dao.buscarUsuariosComPermissoes();
    }

    public Usuario buscar(int usuarioId) {
        return dao.buscar(usuarioId);
    }

    public Usuario buscarEmail(String email) {
        return dao.buscarEmail(email);
    }

    public String alterar(Usuario usuario) {
        return dao.alterar(usuario);
    }

    public String inserir(Usuario usuario) {
        if(dao.buscarEmail(usuario.getEmail()) != null){
            return "email-existente";
        }
        return dao.inserir(usuario);
    }

}
