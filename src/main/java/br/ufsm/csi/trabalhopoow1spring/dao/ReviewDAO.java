package br.ufsm.csi.trabalhopoow1spring.dao;

import br.ufsm.csi.trabalhopoow1spring.model.Obra;
import br.ufsm.csi.trabalhopoow1spring.model.Review;
import br.ufsm.csi.trabalhopoow1spring.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public String alterar(Review review) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE review SET titulo=?, descricao=?, nota=? WHERE id=?");

            stmt.setString(1, review.getTitulo());
            stmt.setString(2, review.getDescricao());
            stmt.setInt(3, review.getNota());
            stmt.setInt(4, review.getId());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar Review");
        }

        return "Review alterado com sucesso";
    }

    public boolean excluir(int id) {

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM review WHERE id=?");
            stmt.setInt(1, id);
            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir Review");
        }

        return true;

    }

    public String inserir(Review review) {

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO review (idobra, idusuario, titulo, descricao, nota) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, review.getObra().getId());
            stmt.setInt(2, review.getUsuario().getId());
            stmt.setString(3, review.getTitulo());
            stmt.setString(4, review.getDescricao());
            stmt.setInt(5, review.getNota());

            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir Review");
        }

        return "Review feita!";
    }

    public ArrayList<Review> listar() {

        ArrayList<Review> reviews = new ArrayList<>();

        try{
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reviews_user_obra ORDER BY idreview");
            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("iduser"));
                usuario.setNome(rs.getString("nomeuser"));

                Obra obra = new Obra();
                obra.setId(rs.getInt("idobra"));
                obra.setNome(rs.getString("nomeobra"));
                obra.setImagemURL(rs.getString("imagemurl"));

                Review review = new Review();
                review.setId(rs.getInt("idreview"));
                review.setTitulo(rs.getString("titulo"));
                review.setDescricao(rs.getString("descricao"));
                review.setNota(rs.getInt("nota"));
                review.setObra(obra);
                review.setUsuario(usuario);

                reviews.add(review);


            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar Reviews");
            e.printStackTrace();
        }
        return reviews;
    }

    public Review buscar(int id) {
        Review review = null;

        try  {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            String sql = "SELECT * FROM review WHERE id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));

                Obra obra = new Obra();
                obra.setId(rs.getInt("idobra"));

                review = new Review();
                review.setId(rs.getInt("id"));
                review.setTitulo(rs.getString("titulo"));
                review.setDescricao(rs.getString("descricao"));
                review.setNota(rs.getInt("nota"));
                review.setUsuario(usuario);
                review.setObra(obra);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // ou use um logger
        }

        return review;
    }



    public List<Review> buscarPorUsuario(int idUsuario) {
        List<Review> reviews = new ArrayList<>();
        try (Connection con = new ConectarBancoDados().conectarBancoPostgres()) {
            String sql = "SELECT r.id AS review_id, r.titulo, r.descricao, r.nota, r.idobra, o.id AS obra_id, o.nome, o.imagemurl " +
                    "FROM review r JOIN obra o ON r.idobra = o.id WHERE r.idusuario = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("review_id"));
                r.setTitulo(rs.getString("titulo"));
                r.setDescricao(rs.getString("descricao"));
                r.setNota(rs.getInt("nota"));

                Obra obra = new Obra();
                obra.setId(rs.getInt("obra_id"));
                obra.setNome(rs.getString("nome"));
                obra.setImagemURL(rs.getString("imagemurl"));
                r.setObra(obra);

                reviews.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }


}
