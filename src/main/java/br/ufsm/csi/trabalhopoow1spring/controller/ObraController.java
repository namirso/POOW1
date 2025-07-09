package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.model.Obra;
import br.ufsm.csi.trabalhopoow1spring.service.ObraService;
import br.ufsm.csi.trabalhopoow1spring.service.TipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/obras")
public class ObraController {

    private final ObraService obraService = new ObraService();
    private final TipoService tipoService = new TipoService();

    @GetMapping
    public String listarObras(Model model) {
        model.addAttribute("obras", obraService.listar());
        model.addAttribute("tipos", tipoService.listar());
        model.addAttribute("obra", new Obra());
        return "pages/obras";
    }

    @PostMapping()
    public String salvarObra(@ModelAttribute Obra obra, RedirectAttributes attributes, Model model) {
        String retorno;
        if (obra.getId() > 0) {
            retorno = obraService.alterar(obra);
        } else {
            retorno = obraService.inserir(obra);
        }
        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/obras";
    }

    @GetMapping("/editar/{id}")
    public String editarObra(@PathVariable int id, Model model) {
        Obra obra = obraService.buscar(id);
        model.addAttribute("obra", obra);
        model.addAttribute("tipos", tipoService.listar());
        model.addAttribute("obras", obraService.listar());
        return "pages/obras";
    }

    @GetMapping("/excluir/{id}")
    public String excluirObra(@PathVariable int id, RedirectAttributes attributes) {
        String retorno = obraService.excluir(id);
        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/obras";
    }
}
