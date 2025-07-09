package br.ufsm.csi.trabalhopoow1spring.service;

import br.ufsm.csi.trabalhopoow1spring.dao.TipoDAO;
import br.ufsm.csi.trabalhopoow1spring.model.Tipo;

import java.util.ArrayList;

public class TipoService {

    private static TipoDAO dao = new TipoDAO();

    public String excluir(int id) {
        if (dao.temObrasAssociadas(id)) {
            return "Não é possível excluir: o tipo possui obras cadastradas.";
        }

        boolean sucesso = dao.excluir(id);

        if (sucesso) {
            return "Tipo excluído com sucesso.";
        } else {
            return "Erro ao excluir o tipo.";
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
