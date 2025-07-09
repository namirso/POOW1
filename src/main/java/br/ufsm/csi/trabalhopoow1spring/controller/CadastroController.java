package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.model.Usuario;
import br.ufsm.csi.trabalhopoow1spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "pages/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        String retorno = usuarioService.inserir(usuario);
        if (retorno.equals("email-existente")) {
            model.addAttribute("erro", "Email já cadastrado");
            model.addAttribute("usuario", usuario);
            return "pages/cadastro";
        }
        model.addAttribute("msg", retorno);
        return "redirect:/";
    }
}
