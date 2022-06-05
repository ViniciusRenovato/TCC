package com.example.tcc.entidades;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id_produto;
    private int consumo_produto;
    private int preco_produto;

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getConsumo_produto() {
        return consumo_produto;
    }

    public void setConsumo_produto(int consumo_produto) {
        this.consumo_produto = consumo_produto;
    }

    public int getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(int preco_produto) {
        this.preco_produto = preco_produto;
    }
}
