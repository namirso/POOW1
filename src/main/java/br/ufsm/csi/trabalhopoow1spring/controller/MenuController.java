package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String exibirMenu(Model model) {
        model.addAttribute("reviews", new ReviewService().listar());
        return "pages/menu";
    }
}
