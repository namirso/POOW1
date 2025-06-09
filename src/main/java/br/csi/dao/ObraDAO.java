package br.csi.dao;

import br.csi.model.Obra;

import java.sql.*;
import java.util.ArrayList;

public class ObraDAO {

    public String alterar(Obra obra) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE obra SET nome = ?, genero = ?, direcao = ? WHERE id = ?"
            );

            stmt.setString(1, obra.getNome());
            stmt.setString(2, obra.getGenero());
            stmt.setString(3, obra.getDirecao());
            stmt.setInt(4, obra.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar obra");
        }

        return "Obra alterada com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM obra WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir obra");
        }

        return true;
    }

    public String inserir(Obra obra) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO obra (nome, genero, direcao) VALUES (?, ?, ?)"
            );

            stmt.setString(1, obra.getNome());
            stmt.setString(2, obra.getGenero());
            stmt.setString(3, obra.getDirecao());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir obra");
        }

        return "Inserida com sucesso";
    }

    public ArrayList<Obra> listar() {

        ArrayList<Obra> obras = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM obra ORDER BY nome");
            while (rs.next()) {
                Obra ob = new Obra();
                ob.setId(rs.getInt("id"));
                ob.setNome(rs.getString("nome"));
                ob.setGenero(rs.getString("genero"));
                ob.setDirecao(rs.getString("direcao"));


                obras.add(ob);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Drive não carregou");
            ex.printStackTrace();
        }

        return obras;
    }

    public Obra buscar(int id) {
        Obra ob = new Obra();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM obra WHERE id = ?"
            );

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ob.setId(rs.getInt("id"));
                ob.setNome(rs.getString("nome"));
                ob.setGenero(rs.getString("genero"));
                ob.setDirecao(rs.getString("direcao"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar obra");
        }

        return ob;
    }

    public Obra buscar(String nome) {
        Obra ob = new Obra();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM obra WHERE nome = ?"
            );

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ob.setId(rs.getInt("id"));
                ob.setNome(rs.getString("nome"));
                ob.setGenero(rs.getString("genero"));
                ob.setDirecao(rs.getString("direcao"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar obra");
        }

        return ob;
    }

}
