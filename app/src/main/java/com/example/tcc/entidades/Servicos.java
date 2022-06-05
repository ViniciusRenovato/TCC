package com.example.tcc.entidades;

import java.io.Serializable;
import java.sql.Time;

public class Servicos implements Serializable {
    private String nome_servico;
    private String duracao_servico;
    private String valor_servico;
    private String id_servico;
    private Cliente cliente;
    private Funcionario funcionario;
    private Agendamento agendamento;
    private Produto produto;


    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }

    public String getDuracao_servico() {
        return duracao_servico;
    }

    public void setDuracao_servico(String duracao_servico) {
        this.duracao_servico = duracao_servico;
    }

    public String getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(String valor_servico) {
        this.valor_servico = valor_servico;
    }

    public String getId_servico() {
        return id_servico;
    }

    public void setId_servico(String id_servico) {
        this.id_servico = id_servico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
