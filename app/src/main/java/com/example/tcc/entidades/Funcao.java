package com.example.tcc.entidades;

import java.io.Serializable;

public class Funcao implements Serializable {

    private String nome_funcao;
    private String id_funcao;

    public String getNome_funcao() {
        return nome_funcao;
    }

    public void setNome_funcao(String nome_funcao) {
        this.nome_funcao = nome_funcao;
    }

    public String getId_funcao() {
        return id_funcao;
    }

    public void setId_funcao(String id_funcao) {
        this.id_funcao = id_funcao;
    }
}
