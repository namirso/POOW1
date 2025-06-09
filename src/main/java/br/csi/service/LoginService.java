package br.csi.service;

import br.csi.model.Usuario;

public class LoginService {

    public boolean autenticar(String email, String senha) {

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.buscar(email);

        return senha.equals(usuario.getSenha());

    }

}
