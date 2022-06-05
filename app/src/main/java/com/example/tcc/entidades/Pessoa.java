package com.example.tcc.entidades;

import java.io.Serializable;
import java.util.Date;

public class Pessoa implements Serializable {
    private String nome_pessoa;
    private String telefone_pessoa;
    private Date data_aniversario;
    private Endereco endereco;

    public String getNome_pessoa() {
        return nome_pessoa;
    }

    public void setNome_pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }

    public String getTelefone_pessoa() {
        return telefone_pessoa;
    }

    public void setTelefone_pessoa(String telefone_pessoa) {
        this.telefone_pessoa = telefone_pessoa;
    }

    public Date getData_aniversario() {
        return data_aniversario;
    }

    public void setData_aniversario(Date data_aniversario) {
        this.data_aniversario = data_aniversario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
