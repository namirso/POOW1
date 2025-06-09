package br.csi.service;

import br.csi.dao.ObraDAO;
import br.csi.model.Obra;
import java.util.ArrayList;

public class  ObraService {

    private static ObraDAO dao = new ObraDAO();

    public String excluir(int id){

        if(dao.excluir(id)){
            return "Sucesso ao excluir usuario";
        }else{
            return "Erro ao excluir usuario";
        }

    }

    public ArrayList<Obra> listar(){
        return dao.listar();
    }

    public Obra buscar(int obraId) {
        return dao.buscar(obraId);
    }

    public Obra buscar(String email) {
        return dao.buscar(email);
    }

    public String alterar(Obra obra) {
        return dao.alterar(obra);
    }

    public String inserir(Obra obra) {
        return dao.inserir(obra);
    }

}
