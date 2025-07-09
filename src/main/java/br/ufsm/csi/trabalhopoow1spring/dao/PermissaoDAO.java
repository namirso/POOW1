package br.ufsm.csi.trabalhopoow1spring.dao;

import br.ufsm.csi.trabalhopoow1spring.model.Permissao;
import br.ufsm.csi.trabalhopoow1spring.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class PermissaoDAO {

    public String alterarPermissao(int idUsuario, int idPermissao) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE usuario_permissao SET idpermissao = ? WHERE idusuario = ?"
            );
            stmt.setInt(1, idPermissao);
            stmt.setInt(2, idUsuario);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {

                stmt = conn.prepareStatement(
                        "INSERT INTO usuario_permissao (idusuario, idpermissao) VALUES (?, ?)"
                );
                stmt.setInt(1, idUsuario);
                stmt.setInt(2, idPermissao);
                stmt.executeUpdate();
            }

            return "Permissão atualizada com sucesso.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao atualizar permissão: " + e.getMessage();
        }
    }




    public String inserir(Permissao permissao) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO permissao (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, permissao.getNome());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                permissao.setId(rs.getInt(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir permissao");
        }
        return "Permissão inserida com sucesso";
    }

    public ArrayList<Permissao> listar() {
        ArrayList<Permissao> permissoes = new ArrayList<>();
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM permissao ORDER BY nome");
            while (rs.next()) {
                Permissao p = new Permissao();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                permissoes.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar permissoes");
            e.printStackTrace();
        }
        return permissoes;
    }

    public String alterar(Permissao permissao) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE permissao SET nome = ? WHERE id = ?"
            );
            stmt.setString(1, permissao.getNome());
            stmt.setInt(2, permissao.getId());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar permissao");
        }
        return "Permissão alterada com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM permissao WHERE id = ?"
            );
            stmt.setInt(1, id);
            stmt.execute();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir permissao");
        }
        return false;
    }

    public Permissao buscar(int id) {
        Permissao permissao = null;

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM permissao WHERE id = ?"
            );

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                permissao = new Permissao();
                permissao.setId(rs.getInt("id"));
                permissao.setNome(rs.getString("nome"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar permissão");
        }

        return permissao;
    }


}
