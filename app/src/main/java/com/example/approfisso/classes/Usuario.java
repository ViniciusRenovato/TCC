package com.example.approfisso.classes;

public class Usuario {

    private String id_usuario;
    private String nome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private boolean salao;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public boolean isSalao() {
        return salao;
    }

    public void setSalao(boolean salao) {
        this.salao = salao;
    }

    public void salvarDados(){

    }
}
