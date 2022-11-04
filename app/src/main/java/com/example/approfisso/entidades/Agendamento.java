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
    private String id_funcionario;
    private String servicos;
    private String nome_cliente;
    private String login_cliente;
    private Integer ponto_agendamento;
    private Cliente cliente;
    private String duracao_agendamento;

    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }

    public Integer getPonto_agendamento() {
        return ponto_agendamento;
    }

    public void setPonto_agendamento(Integer ponto_agendamento) {
        this.ponto_agendamento = ponto_agendamento;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
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

    public String getDuracao_agendamento() {
        return duracao_agendamento;
    }

    public void setDuracao_agendamento(String duracao_agendamento) {
        this.duracao_agendamento = duracao_agendamento;
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

    public String getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getServicos() {
        return servicos;
    }

    public void setServicos(String servicos) {
        this.servicos = servicos;
    }

    public String getLogin_cliente() {
        return login_cliente;
    }

    public void setLogin_cliente(String login_cliente) {
        this.login_cliente = login_cliente;
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
                ", Servicos='" + servicos + '\'' +
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
        if(databaseReference==null)

            inicio();
            String id=databaseReference.child("Agendamento").push().getKey();
            List<Agendamento> agendamento = new ArrayList();
            a.setId_agendamento(id);

            databaseReference.child("Agendamento").child(id).child("id_agendamento").setValue(id);
            databaseReference.child("Agendamento").child(id).child("nome_cliente").setValue(a.getNome_cliente());
            databaseReference.child("Agendamento").child(id).child("hora_agendamento").setValue(a.getHora_agendamento());
            databaseReference.child("Agendamento").child(id).child("dia_agendamento").setValue(a.getDia_agendamento());
            databaseReference.child("Agendamento").child(id).child("funcionario").setValue(a.getFuncionario());
            databaseReference.child("Agendamento").child(id).child("id_funcionario").setValue(a.getId_funcionario());
            databaseReference.child("Agendamento").child(id).child("servicos").setValue(a.getServicos());
            databaseReference.child("Agendamento").child(id).child("login_cliente").setValue(a.getLogin_cliente());
            databaseReference.child("Agendamento").child(id).child("duracao_agendamento").setValue(a.getDuracao_agendamento());
            databaseReference.child("Agendamento").child(id).child("ponto_agendamento").setValue(a.getPonto_agendamento());

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
