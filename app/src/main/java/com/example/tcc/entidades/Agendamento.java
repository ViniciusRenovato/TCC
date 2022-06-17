package com.example.tcc.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Date;

public class Agendamento implements Serializable {
    private Integer id_agendamento;
    private Integer hora_agendamento;
    private Date dia_agendamento;
    private Funcionario funcionario;
    private Servicos servicos;
    private Cliente cliente;

    public Integer getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(Integer id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public Integer getHora_agendamento() {
        return hora_agendamento;
    }

    public void setHora_agendamento(Integer hora_agendamento) {
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



    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static void salvaAgendamento(Agendamento a){
        if(databaseReference==null){
            databaseReference.child("Agendamento").child(a.getId_agendamento().toString()
            ).setValue(a);
            databaseReference.child("Agendamento").child(a.getHora_agendamento().toString()
            ).setValue(a);
            databaseReference.child("Agendamento").child(a.getDia_agendamento().toString()
            ).setValue(a);
            databaseReference.child("Agendamento").child(a.getFuncionario().toString()
            ).setValue(a);
            databaseReference.child("Agendamento").child(a.getServicos().toString()
            ).setValue(a);
            databaseReference.child("Agendamento").child(a.getCliente().toString()
            ).setValue(a);



        }
    }
    public static void excluirAgendamento(Agendamento a){
        databaseReference.child("Agendamento").child(a.getId_agendamento()+"").removeValue();
        databaseReference.child("Agendamento").child(a.getHora_agendamento()+"").removeValue();
        databaseReference.child("Agendamento").child(a.getDia_agendamento()+"").removeValue();
        databaseReference.child("Agendamento").child(a.getFuncionario()+"").removeValue();
        databaseReference.child("Agendamento").child(a.getServicos()+"").removeValue();
        databaseReference.child("Agendamento").child(a.getCliente()+"").removeValue();

    }
    public static void editarAgendamento(Agendamento a){
       // databaseReference.child("Agendamento").child(a.getId_agendamento().toString()
       // ).child("nome").setValue(a.getId_agendamento());
        databaseReference.child("Agendamento").child(a.getId_agendamento().toString()
        ).child("horario").setValue(a.getHora_agendamento());
        databaseReference.child("Agendamento").child(a.getId_agendamento().toString()
        ).child("data").setValue(a.getDia_agendamento());
        databaseReference.child("Agendamento").child(a.getId_agendamento().toString()
        ).child("funcionario").setValue(a.getFuncionario());
        databaseReference.child("Agendamento").child(a.getId_agendamento().toString()
        ).child("serviço").setValue(a.getServicos());
        databaseReference.child("Agendamento").child(a.getId_agendamento().toString()
        ).child("cliente").setValue(a.getCliente());


    }







}
