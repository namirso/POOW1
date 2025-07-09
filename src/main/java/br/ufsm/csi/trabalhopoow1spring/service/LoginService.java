package br.ufsm.csi.trabalhopoow1spring.service;

import br.ufsm.csi.trabalhopoow1spring.dao.UsuarioDAO;
import br.ufsm.csi.trabalhopoow1spring.model.Usuario;

public class LoginService {

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = new UsuarioDAO().buscarEmail(email);

        if (usuario == null) {
            return null; // email não cadastrado
        }

        if (!usuario.getSenha().equals(senha)) {
            // senha incorreta, mas e-mail existe
            usuario.setSenha(null); // remove a senha pra segurança
            usuario.setEmail("senha-incorreta"); // gambizinha pra controller saber diferenciar
            return usuario;
        }

        return usuario;
    }
}
