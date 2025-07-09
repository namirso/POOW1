package br.ufsm.csi.trabalhopoow1spring.model;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private Permissao permissao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public boolean isAdmin() {
        return this.permissao != null && "ADMINISTRADOR".equalsIgnoreCase(this.permissao.getNome());
    }

    public boolean isUsuario() {
        return this.permissao != null && "USUARIO".equalsIgnoreCase(this.permissao.getNome());
    }


}
