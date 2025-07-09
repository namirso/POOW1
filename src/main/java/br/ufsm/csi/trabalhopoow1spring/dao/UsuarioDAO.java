package br.ufsm.csi.trabalhopoow1spring.dao;

import br.ufsm.csi.trabalhopoow1spring.model.Permissao;
import br.ufsm.csi.trabalhopoow1spring.model.Usuario;

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

    public String excluir(int id) {

        try {
            Connection con = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            return "Usuário excluído com sucesso!";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao excluir usuário: " + e.getMessage();
        }
    }

    public boolean temReviewsAssociadas(int usuarioId) {
        try  {
            Connection con = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = con.prepareStatement("SELECT 1 FROM review WHERE idusuario = ? LIMIT 1");
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }


    public String inserir(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            conn.setAutoCommit(false);

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            if (rs.getInt(1) > 0) {
                usuario.setId(rs.getInt(1));
            }
            stmt = conn.prepareStatement("INSERT INTO usuario_permissao (idusuario, idpermissao) VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, 2);
            stmt.execute();

            rs = stmt.getGeneratedKeys();
            rs.next();

            if (rs.getInt(1) > 0) {
                conn.commit();
            }

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

    public Usuario buscarEmail(String email) {
        Usuario usuario = null;
        Permissao permissao = null;

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM usuario WHERE email = ?"
            );

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            } else {
                return null;
            }

            stmt = conn.prepareStatement("SELECT * FROM usuario_com_permissao WHERE iduser = ?");
            stmt.setInt(1, usuario.getId());

            rs = stmt.executeQuery();
            if (rs.next()) {
                permissao = new Permissao();
                permissao.setId(rs.getInt("idpermissao"));
                permissao.setNome(rs.getString("nome_permissao"));
            }

            usuario.setPermissao(permissao);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar usuario");
        }

        return usuario;
    }

    public ArrayList<Usuario> buscarUsuariosComPermissoes() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT u.id, u.nome, u.email, u.senha, " +
                    "p.id AS permissao_id, p.nome AS permissao_nome " +
                    "FROM usuario u " +
                    "LEFT JOIN usuario_permissao up ON u.id = up.idusuario " +
                    "LEFT JOIN permissao p ON up.idpermissao = p.id " +
                    "ORDER BY u.nome";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));

                int permissaoId = rs.getInt("permissao_id");
                if (!rs.wasNull()) {
                    Permissao permissao = new Permissao();
                    permissao.setId(permissaoId);
                    permissao.setNome(rs.getString("permissao_nome"));
                    usuario.setPermissao(permissao);
                } else {
                    usuario.setPermissao(null);
                }

                usuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return usuarios;
    }




}
