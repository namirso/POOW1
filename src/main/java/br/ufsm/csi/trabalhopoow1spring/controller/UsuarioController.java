package br.ufsm.csi.trabalhopoow1spring.controller;

import br.ufsm.csi.trabalhopoow1spring.model.Permissao;
import br.ufsm.csi.trabalhopoow1spring.model.Usuario;
import br.ufsm.csi.trabalhopoow1spring.service.PermissaoService;
import br.ufsm.csi.trabalhopoow1spring.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", new UsuarioService().listar());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("permissoes", new PermissaoService().listar());

        return "pages/usuarios";
    }

    @PostMapping
    public String criarUsuario(Usuario usuario, RedirectAttributes attributes) {
        String retorno = new UsuarioService().inserir(usuario);
        attributes.addFlashAttribute("msg", retorno);

        return "redirect:/usuarios";
    }

    @PostMapping("/editar/{id}")
    public String salvarUsuario(Usuario usuario, RedirectAttributes attributes) {
        String retorno;
        retorno = new UsuarioService().alterar(usuario);
        attributes.addFlashAttribute("mensagem", retorno);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Usuario usuario = new UsuarioService().buscar(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", new UsuarioService().listar());
        return "pages/usuarios";
    }

    @GetMapping("/excluir/{usuarioId}")
    public String excluir(@PathVariable int usuarioId, RedirectAttributes attributes) {
        String retorno = new UsuarioService().excluir(usuarioId);
        attributes.addFlashAttribute("msg", retorno);
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/permissao")
    public String atualizarPermissao(@PathVariable("id") int id,
                                     @RequestParam("permissaoId") int permissaoId,
                                     RedirectAttributes redirect,
                                     HttpSession session) {
        try {
            String resultado = new PermissaoService().alterarPermissaoUsuario(id, permissaoId);
            redirect.addFlashAttribute("msg", resultado);

            Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioLogado");
            if (usuarioSessao != null && usuarioSessao.getId() == id) {
                Permissao novaPermissao = new PermissaoService().buscar(permissaoId);
                usuarioSessao.setPermissao(novaPermissao);
                session.setAttribute("usuarioLogado", usuarioSessao);
            }

        } catch (Exception e) {
            redirect.addFlashAttribute("mensagem", "Erro ao atualizar permissão: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }





}