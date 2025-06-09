package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("usuarios")
public class UsuarioServlet extends HttpServlet {

    private static UsuarioService service = new UsuarioService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("idusuario"));
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");


        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        String retorno;

        if(id > 0){
            usuario.setId(id);
            retorno = new UsuarioService().alterar(usuario);
        } else {
            retorno = new UsuarioService().inserir(usuario);
        }

        req.setAttribute("retorno", retorno);
        req.setAttribute("usuarios", new UsuarioService().listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");

        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if(opcao != null){

            if(opcao.equals("editar")) {

                Usuario u1 = service.buscar(Integer.parseInt(info));
                req.setAttribute("usuario", u1);

                ArrayList<Usuario> usuarios = new UsuarioService().listar();
                req.setAttribute("usuarios", usuarios);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");
                rd.forward(req, resp);

            }else if(opcao.equals("excluir")) {

                String valor =  service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);

                ArrayList<Usuario> usuarios = new UsuarioService().listar();
                req.setAttribute("usuarios", usuarios);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");
                rd.forward(req, resp);
            }
        }else{
            ArrayList<Usuario> usuarios = new UsuarioService().listar();
            req.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");

            rd.forward(req, resp);
        }

    }
}

