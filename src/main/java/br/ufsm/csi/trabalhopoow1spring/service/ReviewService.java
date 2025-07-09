package br.ufsm.csi.trabalhopoow1spring.service;

import br.ufsm.csi.trabalhopoow1spring.dao.ReviewDAO;
import br.ufsm.csi.trabalhopoow1spring.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewDAO dao = new ReviewDAO();


    public String inserir(Review review) {
        if (existeReview(review.getUsuario().getId(), review.getObra().getId())) {
            return "Você já criou uma review para esta obra!";
        }

        // chama o DAO para inserir
        return dao.inserir(review);
    }


    public String alterar(Review review) {
        return dao.alterar(review);
    }

    public String excluir(int id) {
        if(dao.excluir(id)){
            return "Excluído com sucesso!";
        }else{
            return "Erro ao excluir Review";
        }
    }

    public ArrayList<Review> listar() {
        return dao.listar();
    }

    public List<Review> buscarPorUsuario(int idUsuario) {
        return new ReviewDAO().buscarPorUsuario(idUsuario);
    }

    public Review buscar(int id) {
        return dao.buscar(id);
    }

    public boolean existeReview(int usuarioId, int obraId) {
        List<Review> reviews = dao.buscarPorUsuario(usuarioId);
        for (Review r : reviews) {
            if (r.getObra().getId() == obraId) {
                return true;
            }
        }
        return false;
    }





}
