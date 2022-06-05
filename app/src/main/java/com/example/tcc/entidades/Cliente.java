package com.example.tcc.entidades;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {

    private int pontos_cliente;
    private int id_cliente;
    private Pessoa pessoa;

    public int getPontos_cliente() {
        return pontos_cliente;
    }

    public void setPontos_cliente(int pontos_cliente) {
        this.pontos_cliente = pontos_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
