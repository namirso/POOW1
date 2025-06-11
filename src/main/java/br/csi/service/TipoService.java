package br.csi.service;

import br.csi.dao.TipoDAO;
import br.csi.model.Tipo;

import java.util.ArrayList;

public class TipoService {

    private static TipoDAO dao = new TipoDAO();

    public  String excluir(int id) {
        if(dao.excluir(id)){
            return "Sucesso ao excluir usuario";
        }else{
            return "Erro ao excluir usuario";
        }
    }

    public ArrayList<Tipo> listar(){
        return dao.listar();
    }

    public Tipo buscar(int obraId) {
        return dao.buscar(obraId);
    }

    public Tipo buscar(String email) {
        return dao.buscar(email);
    }

    public String alterar(Tipo tipo) {
        return dao.alterar(tipo);
    }

    public String inserir(Tipo tipo) {
        return dao.inserir(tipo);
    }

}
