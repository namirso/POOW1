package br.csi.dao;

import br.csi.model.Tipo;
import java.sql.*;
import java.util.ArrayList;

public class TipoDAO {

    public String alterar(Tipo tipo) {
       try {
           Connection conn = ConectarBancoDados.conectarBancoPostgres();
           PreparedStatement stmt = conn.prepareStatement(
                   "UPDATE tipo SET nome = ? WHERE id = ?"
           );

           stmt.setString(1, tipo.getNome());
           stmt.setInt(2, tipo.getId());

           stmt.execute();

       } catch (SQLException | ClassNotFoundException e){
           System.out.println(e.getMessage());
           System.out.println("Erro ao alterar tipo");
       }
       return "Tipo alterado com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM tipo WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir tipo");
        }

        return true;
    }

    public String inserir(Tipo tipo) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tipo (nome) VALUES (?)"
            );

            stmt.setString(1, tipo.getNome());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir tipo");
        }

        return "Inserido com sucesso";
    }

    public ArrayList<Tipo> listar() {

        ArrayList<Tipo> tipos = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM tipo ORDER BY nome");
            while (rs.next()) {
                Tipo tip = new Tipo();
                tip.setId(rs.getInt("id"));
                tip.setNome(rs.getString("nome"));
                tipos.add(tip);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não carregou");
            ex.printStackTrace();
        }

        return tipos;
    }

    public Tipo buscar(int id) {
        Tipo tip = new Tipo();
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM tipo WHERE id = ?"
            );

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tip.setId(rs.getInt("id"));
                tip.setNome(rs.getString("nome"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar tipo");
        }

        return tip;
    }

    public Tipo buscar(String nome) {
        Tipo tip = new Tipo();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM tipo WHERE nome = ?"
            );

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tip.setId(rs.getInt("id"));
                tip.setNome(rs.getString("nome"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar tipo");
        }

        return tip;
    }

}
