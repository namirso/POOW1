package br.csi.controller;

import br.csi.model.Obra;
import br.csi.service.ObraService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("obras")
public class ObraServlet extends HttpServlet {

    private static ObraService service = new ObraService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("idobra"));
        String nome = req.getParameter("nome");
        String genero = req.getParameter("genero");
        String direcao = req.getParameter("direcao");


        Obra obra = new Obra();
        obra.setNome(nome);
        obra.setGenero(genero);
        obra.setDirecao(direcao);

        String retorno;

        if(id > 0){
            obra.setId(id);
            retorno = new ObraService().alterar(obra);
        } else {
            retorno = new ObraService().inserir(obra);
        }

        req.setAttribute("retorno", retorno);
        req.setAttribute("obras", new ObraService().listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/obras.jsp");

        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if(opcao != null){

            if(opcao.equals("editar")) {

                Obra ob = service.buscar(Integer.parseInt(info));
                req.setAttribute("obra", ob);

                ArrayList<Obra> obras = new ObraService().listar();
                req.setAttribute("obras", obras);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/obras.jsp");
                rd.forward(req, resp);

            }else if(opcao.equals("excluir")) {

                String ob = service.excluir(Integer.parseInt(info));
                req.setAttribute("obra", ob);

                ArrayList<Obra> obras = new ObraService().listar();
                req.setAttribute("obras", obras);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/obras.jsp");
                rd.forward(req, resp);
            }
        }else{
            ArrayList<Obra> obras = new ObraService().listar();
            req.setAttribute("obras", obras);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/obras.jsp");
            rd.forward(req, resp);
        }
    }
}
