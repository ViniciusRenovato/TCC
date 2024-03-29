package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Servicos implements Serializable {
    private String nome_servico;
    private String duracao_servico;
    private String valor_servico;
    private String id_servico;
    private String funcao_servico;
    private Cliente cliente;
    private Funcionario funcionario;
    private Agendamento agendamento;
    private Produto produto;
    private Integer pontos_servico;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }

    public Integer getPontos_servico() {
        return pontos_servico;
    }

    public void setPontos_servico(Integer pontos_servico) {
        this.pontos_servico = pontos_servico;
    }

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

    public String getFuncao_servico() {
        return funcao_servico;
    }

    public void setFuncao_servico(String funcao_servico) {
        this.funcao_servico = funcao_servico;
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


    @Override
    public String toString() {
        return  ", ID='" + id_servico + '\'' +
                ", Nome='" + nome_servico + '\'' +
                ", Duração='" + duracao_servico + '\'' +
                ", Preço='" + valor_servico + '\'' +
                ", Função='" + funcao_servico + '\'' + '}';
    }

    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }



    public static void salvaServicos(Servicos s){
        if(databaseReference==null)
            inicio();
            String id=databaseReference.child("Servicos").push().getKey();
            List<Servicos> servico = new ArrayList();
            s.setId_servico(id);


            databaseReference.child("Servicos").child(id).child("id_servico").setValue(id);
            databaseReference.child("Servicos").child(id).child("nome_servico").setValue(s.getNome_servico());
            databaseReference.child("Servicos").child(id).child("duracao_servico").setValue(s.getDuracao_servico());
            databaseReference.child("Servicos").child(id).child("valor_servico").setValue(s.getValor_servico());
            databaseReference.child("Servicos").child(id).child("funcao_servico").setValue(s.getFuncao_servico());
            databaseReference.child("Servicos").child(id).child("pontos_servico").setValue(s.getPontos_servico());

    }
    public static void excluirServicos(Servicos s){
        databaseReference.child("Servicos").child(s.getId_servico()+"").removeValue();
        databaseReference.child("Servicos").child(s.getDuracao_servico()+"").removeValue();
        databaseReference.child("Servicos").child(s.getValor_servico()+"").removeValue();
        databaseReference.child("Servicos").child(s.getNome_servico()+"").removeValue();
        databaseReference.child("Servicos").child(s.getProduto()+"").removeValue();
        databaseReference.child("Servicos").child(s.getCliente()+"").removeValue();
        databaseReference.child("Servicos").child(s.getAgendamento()+"").removeValue();
        databaseReference.child("Servicos").child(s.getFuncionario()+"").removeValue();
    }
    public static void editarServicos(Servicos s){

        databaseReference.child("Servicos").child(s.getId_servico().toString()
        ).child("duracao").setValue(s.getDuracao_servico());
        databaseReference.child("Servicos").child(s.getId_servico().toString()
        ).child("valor").setValue(s.getValor_servico());
        databaseReference.child("Servicos").child(s.getId_servico().toString()
        ).child("nome").setValue(s.getNome_servico());
        databaseReference.child("Servicos").child(s.getId_servico().toString()
        ).child("Produto").setValue(s.getProduto());
        databaseReference.child("Servicos").child(s.getId_servico().toString()
        ).child("Cliente").setValue(s.getCliente());
        databaseReference.child("Servicos").child(s.getId_servico().toString()
        ).child("Agendamento").setValue(s.getAgendamento());
        databaseReference.child("Servicos").child(s.getId_servico().toString()
        ).child("Funcionario").setValue(s.getFuncionario());
    }
}
