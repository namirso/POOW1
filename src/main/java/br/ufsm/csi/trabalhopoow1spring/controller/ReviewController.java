package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.model.Review;
import br.ufsm.csi.trabalhopoow1spring.model.Usuario;
import br.ufsm.csi.trabalhopoow1spring.service.ObraService;
import br.ufsm.csi.trabalhopoow1spring.service.ReviewService;
import br.ufsm.csi.trabalhopoow1spring.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/nova")
    public String novaReview(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("review", new Review());
        model.addAttribute("obras", new ObraService().listar());
        model.addAttribute("usuarioLogado", usuario);

        return "pages/review-form";
    }


    @PostMapping()
    public String salvarReview(Review review, RedirectAttributes attributes, Model model, HttpSession session) {
        String retorno;
        boolean isEdicao = review.getId() > 0;

        if (isEdicao) {
            retorno = reviewService.alterar(review);
        } else {
            retorno = reviewService.inserir(review);
        }

        if (retorno.contains("erro") || retorno.contains("já criou")) {
            model.addAttribute("msg", retorno);
            model.addAttribute("review", review);

            model.addAttribute("obras", new ObraService().listar());
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            model.addAttribute("usuarioLogado", usuario);

            return "pages/review-form";
        }

        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/menu";
    }


    @GetMapping("/{id}/editar")
    public String editarReview(@PathVariable int id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/";

        Review review = reviewService.buscar(id);

        if (!usuario.isAdmin() && review.getUsuario().getId() != usuario.getId()) {
            return "redirect:/menu";
        }

        model.addAttribute("review", review);
        model.addAttribute("obras", new ObraService().listar());
        return "pages/review-form";
    }

    @GetMapping("/{id}/excluir")
    public String excluirReview(@PathVariable int id, RedirectAttributes attributes, HttpSession session) {
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");

        Review review = reviewService.buscar(id);
        if (!logado.isAdmin() && review.getUsuario().getId() != logado.getId()) {
            attributes.addFlashAttribute("msg", "Você não tem permissão para excluir esta review.");
            return "redirect:/menu";
        }

        String retorno = reviewService.excluir(id);
        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/menu";
    }


    @GetMapping("/minhas")
    public String minhasReviews(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        List<Review> minhasReviews = new ReviewService().buscarPorUsuario(usuario.getId());
        model.addAttribute("reviews", minhasReviews);

        return "pages/minhas-reviews";
    }


}
