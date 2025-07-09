package br.ufsm.csi.trabalhopoow1spring.service;

import br.ufsm.csi.trabalhopoow1spring.dao.ObraDAO;
import br.ufsm.csi.trabalhopoow1spring.model.Obra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public class  ObraService {

    private static ObraDAO dao = new ObraDAO();


    public String excluir(int id) {
        if (dao.temReviewsAssociadas(id)) {
            return "Não é possível excluir: esta obra possui reviews cadastradas.";
        }
        boolean sucesso = dao.excluir(id);

        if (sucesso) {
            return "Obra excluída com sucesso.";
        } else {
            return "Erro ao excluir a obra.";
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
        ArrayList<Obra> obrasExistentes = dao.listar();

        for (Obra existente : obrasExistentes) {
            if (existente.getNome().equalsIgnoreCase(obra.getNome()) &&
                    existente.getTipo().getId() == obra.getTipo().getId()) {
                return "Obra já existente com o mesmo nome e tipo!";
            }
        }

        return dao.inserir(obra);
    }


}
