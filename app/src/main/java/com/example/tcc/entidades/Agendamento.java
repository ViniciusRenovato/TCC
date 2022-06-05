package com.example.tcc.entidades;

import java.io.Serializable;
import java.util.Date;

public class Agendamento implements Serializable {
    private int id_agendamento;
    private int hora_agendamento;
    private Date dia_agendamento;
    private Funcionario funcionario;
    private Servicos servicos;
    private Cliente cliente;

    public int getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(int id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public int getHora_agendamento() {
        return hora_agendamento;
    }

    public void setHora_agendamento(int hora_agendamento) {
        this.hora_agendamento = hora_agendamento;
    }

    public Date getDia_agendamento() {
        return dia_agendamento;
    }

    public void setDia_agendamento(Date dia_agendamento) {
        this.dia_agendamento = dia_agendamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Servicos getServicos() {
        return servicos;
    }

    public void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
