package br.csi.controller;


import br.csi.model.Tipo;
import br.csi.service.TipoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("tipos")
public class TipoServlet extends HttpServlet {

    private static TipoService tipoService = new TipoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("idtipo"));
        String nome = req.getParameter("nome");

        Tipo tipo = new Tipo();
        tipo.setNome(nome);

        String retorno;

        if(id > 0){
            tipo.setId(id);
            retorno = new TipoService().alterar(tipo);
        } else {
            retorno = new TipoService().inserir(tipo);
        }

        req.setAttribute("retorno", retorno);
        req.setAttribute("tipos", new TipoService().listar());

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/pages/tipos.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if(opcao != null){

            if(opcao.equals("editar")) {

                Tipo tp = tipoService.buscar(Integer.parseInt(info));
                req.setAttribute("tipo", tp);

                ArrayList<Tipo> tipos = new TipoService().listar();
                req.setAttribute("tipos", tipos);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/tipos.jsp");
                rd.forward(req, resp);

            }else if(opcao.equals("excluir")) {

                String tp = tipoService.excluir(Integer.parseInt(info));
                req.setAttribute("tipo", tp);

                ArrayList<Tipo> tipos = new TipoService().listar();
                req.setAttribute("tipos", tipos);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/tipos.jsp");
                rd.forward(req, resp);
            }
        }else{
            ArrayList<Tipo> tipos = new TipoService().listar();
            req.setAttribute("tipos", tipos);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/tipos.jsp");
            rd.forward(req, resp);
        }
    }
}
