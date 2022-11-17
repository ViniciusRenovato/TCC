package com.example.approfisso.classes;

import com.example.approfisso.entidades.Funcionario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String id_usuario;
    private String UID_usuario;
    private String nome_usuario;
    private String email_usuario;
    private String telefone_usuario;
    private String aniversario_usuario;
    private String senha_usuario;
    private String tipo_usuario;
    private Integer pontos_usuario;
    private Integer faltas_usuario;
    private boolean salao;


    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }


    public String getUID_usuario() {
        return UID_usuario;
    }

    public void setUID_usuario(String UID_usuario) {
        this.UID_usuario = UID_usuario;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public Integer getPontos_usuario() {
        return pontos_usuario;
    }

    public void setPontos_usuario(Integer pontos_usuario) {
        this.pontos_usuario = pontos_usuario;
    }

    public Integer getFaltas_usuario() { return faltas_usuario; }

    public void setFaltas_usuario(Integer faltas_usuario) { this.faltas_usuario = faltas_usuario;    }

    public boolean isSalao() {
        return salao;
    }

    public void setSalao(boolean salao) {
        this.salao = salao;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getTelefone_usuario() {
        return telefone_usuario;
    }

    public void setTelefone_usuario(String telefone_usuario) {
        this.telefone_usuario = telefone_usuario;
    }

    public String getAniversario_usuario() {
        return aniversario_usuario;
    }

    public void setAniversario_usuario(String aniversario_usuario) {
        this.aniversario_usuario = aniversario_usuario;
    }

    public void salvarDados(){

    }

    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }



    public static void salvaUsuario(Usuario usuario){
        if(databaseReference==null)
            inicio();
        String id=databaseReference.child("usuários").push().getKey();
        List<Usuario> usuarios = new ArrayList();
        usuario.setId_usuario(id);

        databaseReference.child("usuários").child(id).child("id_usuario").setValue(id);
        databaseReference.child("usuários").child(id).child("nome_usuario").setValue(usuario.getNome_usuario());
        databaseReference.child("usuários").child(id).child("pontos_usuario").setValue(usuario.getPontos_usuario());
        databaseReference.child("usuários").child(id).child("faltas_usuario").setValue(usuario.getFaltas_usuario());
        databaseReference.child("usuários").child(id).child("UID_usuario").setValue(usuario.getUID_usuario());
        databaseReference.child("usuários").child(id).child("telefone_usuario").setValue(usuario.getTelefone_usuario());
        databaseReference.child("usuários").child(id).child("aniversario_usuario").setValue(usuario.getAniversario_usuario());
        databaseReference.child("usuários").child(id).child("email_usuario").setValue(usuario.getEmail_usuario());
        databaseReference.child("usuários").child(id).child("tipo_usuario").setValue(usuario.getTipo_usuario());




    }










}
