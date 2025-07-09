package br.ufsm.csi.trabalhopoow1spring.service;

import br.ufsm.csi.trabalhopoow1spring.dao.PermissaoDAO;
import br.ufsm.csi.trabalhopoow1spring.model.Permissao;

import java.util.ArrayList;

public class PermissaoService {

    private static PermissaoDAO dao = new PermissaoDAO();

    public String inserir(Permissao permissao) {
        return dao.inserir(permissao);
    }

    public ArrayList<Permissao> listar() {
        return dao.listar();
    }

    public String alterar(Permissao permissao) {
        return dao.alterar(permissao);
    }

    public String excluir(int id) {
        if(dao.excluir(id)){
            return "Excluído com sucesso!";
        }else{
            return "Erro ao excluir Permissão";
        }
    }

    public Permissao buscar(int id) {
        return dao.buscar(id);
    }

    public String alterarPermissaoUsuario(int idUp, int novaPermissaoId) {
        return dao.alterarPermissao(idUp, novaPermissaoId);
    }

}
