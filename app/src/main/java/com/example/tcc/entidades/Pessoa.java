package com.example.tcc.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Date;

public class Pessoa implements Serializable {
    private String nome_pessoa;
    private Integer id_pessoa;
    private String telefone_pessoa;
    private String data_aniversario;
    private String email_pessoa;
    private Endereco endereco;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

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

    public String getData_aniversario() {
        return data_aniversario;
    }

    public void setData_aniversario(String data_aniversario) {
        this.data_aniversario = data_aniversario;
    }

    public String getEmail_pessoa() {
        return email_pessoa;
    }

    public void setEmail_pessoa(String email_pessoa) {
        this.email_pessoa = email_pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public Integer getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(Integer id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public static void salvaPessoa(Pessoa p){
        if(databaseReference==null){
            databaseReference.child("Pessoa").child(p.getId_pessoa().toString()
            ).setValue(p);
            databaseReference.child("Pessoa").child(p.getNome_pessoa().toString()
            ).setValue(p);
            databaseReference.child("Pessoa").child(p.getTelefone_pessoa().toString()
            ).setValue(p);
            databaseReference.child("Pessoa").child(p.getData_aniversario().toString()
            ).setValue(p);
            databaseReference.child("Pessoa").child(p.getEmail_pessoa().toString()
            ).setValue(p);
        }
    }
    public static void excluirPessoa(Pessoa p){
        databaseReference.child("Pessoa").child(p.getId_pessoa()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getNome_pessoa()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getTelefone_pessoa()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getData_aniversario()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getEmail_pessoa()+"").removeValue();
    }
    public static void editarPessoa(Pessoa p){
        databaseReference.child("Pessoa").child(p.getId_pessoa().toString()
        ).child("nome").setValue(p.getId_pessoa());
        databaseReference.child("Pessoa").child(p.getId_pessoa().toString()
        ).child("nome").setValue(p.getNome_pessoa());
        databaseReference.child("Pessoa").child(p.getId_pessoa().toString()
        ).child("telefone").setValue(p.getTelefone_pessoa());
        databaseReference.child("Pessoa").child(p.getId_pessoa().toString()
        ).child("email").setValue(p.getEmail_pessoa());
        databaseReference.child("Pessoa").child(p.getId_pessoa().toString()
        ).child("data").setValue(p.getData_aniversario());
    }
}
