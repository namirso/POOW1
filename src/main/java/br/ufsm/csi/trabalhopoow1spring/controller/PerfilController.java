package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.model.Usuario;
import br.ufsm.csi.trabalhopoow1spring.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/"; // se não tiver logado, volta pro login
        }

        model.addAttribute("usuario", usuario);
        return "pages/perfil"; // <- importante!
    }


    @PostMapping("/perfil")
    public String atualizarPerfil(@RequestParam String nome,
                                  @RequestParam String senha,
                                  @RequestParam String email,
                                  HttpSession session,
                                  Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        usuario.setNome(nome);
        if (senha != null && !senha.isBlank()) {
            usuario.setSenha(senha);
        }

        new UsuarioService().alterar(usuario);

        session.setAttribute("usuarioLogado", usuario);
        model.addAttribute("msg", "Perfil atualizado com sucesso!");

        return "redirect:/menu";
    }
}
