package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agendamento_Encerrado implements Serializable {


    private String id_funcionario;
    private String nome_funcionario;
    private String nome_estabelecimento;
    private String id_ganho;
    private String ganho_funcionario;
    private String ganho_estabelecimento;
    private String ano_agendamento_encerrado;
    private String mes_agendamento_encerrado;



    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getGanho_funcionario() {
        return ganho_funcionario;
    }

    public void setGanho_funcionario(String ganho_funcionario) {
        this.ganho_funcionario = ganho_funcionario;
    }

    public String getGanho_estabelecimento() {
        return ganho_estabelecimento;
    }

    public void setGanho_estabelecimento(String ganho_estabelecimento) {
        this.ganho_estabelecimento = ganho_estabelecimento;
    }

    public String getId_ganho() {
        return id_ganho;
    }

    public void setId_ganho(String id_ganho) {
        this.id_ganho = id_ganho;
    }

    public String getAno_agendamento_encerrado() {
        return ano_agendamento_encerrado;
    }

    public void setAno_agendamento_encerrado(String ano_agendamento_encerrado) {
        this.ano_agendamento_encerrado = ano_agendamento_encerrado;
    }

    public String getMes_agendamento_encerrado() {
        return mes_agendamento_encerrado;
    }

    public void setMes_agendamento_encerrado(String mes_agendamento_encerrado) {
        this.mes_agendamento_encerrado = mes_agendamento_encerrado;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getNome_estabelecimento() {
        return nome_estabelecimento;
    }

    public void setNome_estabelecimento(String nome_estabelecimento) {
        this.nome_estabelecimento = nome_estabelecimento;
    }

    @Override
    public String toString() {
        return  ", id_funcionario='" + id_funcionario + '\'' +
                ", ganho_estabelecimento='" + ganho_estabelecimento + '\'' +
                ", ganho_funcionario='" + ganho_funcionario + '}';
    }

    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }



    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static void salvaAgendamentoEncerrado(Agendamento_Encerrado a){
        if(databaseReference==null)

            inicio();
            String id=databaseReference.child("Agendamento_Encerrado").push().getKey();
            List<Agendamento_Encerrado> agendamento = new ArrayList();
            a.setId_ganho(id);

//            databaseReference.child("Agendamento_Encerrado").child(a.getAno_agendamento_encerrado()).child(a.getMes_agendamento_encerrado()).child(a.getId_funcionario()).child("id_ganho").setValue(id);
            databaseReference.child("Agendamento_Encerrado").child(a.getAno_agendamento_encerrado()).child(a.getMes_agendamento_encerrado()).child(a.getId_funcionario()).child("id_funcionario").setValue(a.getId_funcionario());
            databaseReference.child("Agendamento_Encerrado").child(a.getAno_agendamento_encerrado()).child(a.getMes_agendamento_encerrado()).child(a.getId_funcionario()).child("nome_funcionario").setValue(a.getNome_funcionario());
            databaseReference.child("Agendamento_Encerrado").child(a.getAno_agendamento_encerrado()).child(a.getMes_agendamento_encerrado()).child(a.getId_funcionario()).child("ganho_funcionario"+" "+a.getId_funcionario()).setValue(a.getGanho_funcionario());

//
//            databaseReference.child("Agendamento_Encerrado_Estabelecimento").child(a.getAno_agendamento_encerrado()).child(a.getMes_agendamento_encerrado()).child("Estabelecimento").child("ganho_estabelecimento").setValue(a.getGanho_estabelecimento());
//            databaseReference.child("Agendamento_Encerrado_Estabelecimento").child(a.getAno_agendamento_encerrado()).child(a.getMes_agendamento_encerrado()).child("Estabelecimento").child("nome_estabelecimento").setValue(a.getNome_estabelecimento());
    }

    public static void salvaAgendamentoEncerradoEstabelecimento(Agendamento_Encerrado b){
        if(databaseReference==null)

            inicio();
        String id=databaseReference.child("Agendamento_Encerrado").push().getKey();
        List<Agendamento_Encerrado> agendamento = new ArrayList();
        b.setId_ganho(id);

//            databaseReference.child("Agendamento_Encerrado").child(a.getAno_agendamento_encerrado()).child(a.getMes_agendamento_encerrado()).child(a.getId_funcionario()).child("id_ganho").setValue(id);


        databaseReference.child("Agendamento_Encerrado_Estabelecimento").child(b.getAno_agendamento_encerrado()).child(b.getMes_agendamento_encerrado()).child("Estabelecimento").child("ganho_estabelecimento").setValue(b.getGanho_estabelecimento());
        databaseReference.child("Agendamento_Encerrado_Estabelecimento").child(b.getAno_agendamento_encerrado()).child(b.getMes_agendamento_encerrado()).child("Estabelecimento").child("nome_estabelecimento").setValue(b.getNome_estabelecimento());
    }

}
