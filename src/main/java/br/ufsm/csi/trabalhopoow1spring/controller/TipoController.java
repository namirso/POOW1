package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.model.Tipo;
import br.ufsm.csi.trabalhopoow1spring.service.TipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipos")
public class TipoController {

    @GetMapping
    public String listarTipos(Model model) {
        model.addAttribute("tipos", new TipoService().listar());
        model.addAttribute("tipo", new Tipo());
        return "pages/tipos";
    }

    @PostMapping
    public String criarTipo(Tipo tipo, RedirectAttributes attributes) {
        String retorno = new TipoService().inserir(tipo);
        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/tipos";
    }

    @PostMapping("/editar/{id}")
    public String salvarTipo(Tipo tipo, RedirectAttributes attributes) {
        String retorno = new TipoService().alterar(tipo);
        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/tipos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable int id, RedirectAttributes attributes) {
        String retorno = new TipoService().excluir(id);
        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/tipos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Tipo tipo = new TipoService().buscar(id);
        model.addAttribute("tipo", tipo);
        model.addAttribute("tipos", new TipoService().listar());
        return "pages/tipos";
    }
}
