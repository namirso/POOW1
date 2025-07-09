package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.model.Usuario;
import br.ufsm.csi.trabalhopoow1spring.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, String email, String senha) {
        Usuario usuario = new LoginService().autenticar(email, senha);

        if (usuario == null) {
            model.addAttribute("msg", "E-mail não cadastrado!");
            return "index";
        } else if ("senha-incorreta".equals(usuario.getEmail())) {
            model.addAttribute("msg", "Senha incorreta!");
            return "index";
        } else {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/menu";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalida a sessão para fazer logout
        session.invalidate();
        return "redirect:/"; // Redireciona para a página de login
    }


}
