package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agendamento implements Serializable {
    private String id_agendamento;
    private String hora_agendamento;
    private String dia_agendamento;
    private String funcionario;
    private Servicos servicos;
    private Cliente cliente;

    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }



    public String getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(String id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public String getHora_agendamento() {
        return hora_agendamento;
    }

    public void setHora_agendamento(String hora_agendamento) {
        this.hora_agendamento = hora_agendamento;
    }

    public String getDia_agendamento() {
        return dia_agendamento;
    }

    public void setDia_agendamento(String dia_agendamento) {
        this.dia_agendamento = dia_agendamento;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
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


    @Override
    public String toString() {
        return  ", ID='" + id_agendamento + '\'' +
                ", Nome='" + hora_agendamento + '\'' +
                ", Duração='" + dia_agendamento + '\'' +
                ", Função='" + funcionario + '\'' + '}';
    }

    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }



    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static void salvaAgendamento(Agendamento a){
        if(databaseReference==null){

            inicio();
            String id=databaseReference.child("Agendamento").push().getKey();
            List<Agendamento> agendamento = new ArrayList();
            a.setId_agendamento(id);

            databaseReference.child("Agendamento").child(id).child("id_agendamento").setValue(id);
            databaseReference.child("Agendamento").child(id).child("hora_agendamento").setValue(a.getHora_agendamento());
            databaseReference.child("Agendamento").child(id).child("dia_agendamento").setValue(a.getDia_agendamento());
            databaseReference.child("Agendamento").child(id).child("funcionario").setValue(a.getFuncionario());


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
