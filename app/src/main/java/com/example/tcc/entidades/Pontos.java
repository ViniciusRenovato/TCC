package com.example.tcc.entidades;

import java.io.Serializable;

public class Pontos implements Serializable {

    private int id_pontos;
    private int incluir_pontos;
    private Venda venda;

    public int getId_pontos() {
        return id_pontos;
    }

    public void setId_pontos(int id_pontos) {
        this.id_pontos = id_pontos;
    }

    public int getIncluir_pontos() {
        return incluir_pontos;
    }

    public void setIncluir_pontos(int incluir_pontos) {
        this.incluir_pontos = incluir_pontos;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
