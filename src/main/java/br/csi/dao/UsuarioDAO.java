package br.csi.dao;

import br.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public String alterar(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?"
            );

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar usuario");
        }

        return "Usuario alterado com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM usuario WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir usuario");
        }

        return true;
    }

    public String inserir(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)"
            );

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir usuario");
        }

        return "Inserido com sucesso";
    }

    public ArrayList<Usuario> listar() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario ORDER BY nome");
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));


                usuarios.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Drive não carregou");
            ex.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscar(int id) {
        Usuario usuario = new Usuario();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM usuario WHERE id = ?"
            );

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar usuario");
        }

        return usuario;
    }

    public Usuario buscar(String email) {
        Usuario usuario = new Usuario();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM usuario WHERE email = ?"
            );

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar usuario");
        }

        return usuario;
    }

}
