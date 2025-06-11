package br.csi.controller;

import br.csi.model.Obra;
import br.csi.model.Tipo;
import br.csi.service.ObraService;
import br.csi.service.TipoService;
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
    private static TipoService tipoService = new TipoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("idobra"));
        String nome = req.getParameter("nome");
        String direcao = req.getParameter("direcao");
        int idtp = Integer.parseInt(req.getParameter("tipo"));


        Tipo tipo = tipoService.buscar(idtp);
        Obra obra = new Obra();
        obra.setNome(nome);
        obra.setDirecao(direcao);
        obra.setTipo(tipo);

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
                Tipo tipo = tipoService.buscar(ob.getTipo().getId());
                ob.setTipo(tipo);
                req.setAttribute("obra", ob);

                ArrayList<Tipo> tipos = new TipoService().listar();
                req.setAttribute("tipos", tipos);

                ArrayList<Obra> obras = new ObraService().listar();
                req.setAttribute("obras", obras);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/obras.jsp");
                rd.forward(req, resp);

            }else if(opcao.equals("excluir")) {

                String ob = service.excluir(Integer.parseInt(info));
                req.setAttribute("obra", ob);

                ArrayList<Tipo> tipos = new TipoService().listar();
                req.setAttribute("tipos", tipos);

                ArrayList<Obra> obras = new ObraService().listar();
                req.setAttribute("obras", obras);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/obras.jsp");
                rd.forward(req, resp);
            }
        }else{

            ArrayList<Tipo> tipos = new TipoService().listar();
            req.setAttribute("tipos", tipos);

            ArrayList<Obra> obras = new ObraService().listar();
            req.setAttribute("obras", obras);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/obras.jsp");
            rd.forward(req, resp);
        }
    }
}
