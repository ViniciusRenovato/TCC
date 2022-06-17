package com.example.tcc.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Funcionario implements Serializable {

    private String nome_funcionario;
    private String email_funcionario;
    private String id_funcionario;
    private Pessoa pessoa;



    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getEmail_funcionario() {
        return email_funcionario;
    }

    public void setEmail_funcionario(String email_funcionario) {
        this.email_funcionario = email_funcionario;
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }



    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static void salvaFuncionario(Funcionario funciona){
        if(databaseReference==null){
            databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
            ).setValue(funciona);
            databaseReference.child("Funcionario").child(funciona.getNome_funcionario().toString()
            ).setValue(funciona);
            databaseReference.child("Funcionario").child(funciona.getEmail_funcionario().toString()
            ).setValue(funciona);
            databaseReference.child("Funcionario").child(funciona.getPessoa().toString()
            ).setValue(funciona);


        }
    }
    public static void excluirFuncionario(Funcionario funciona){
        databaseReference.child("Funcionario").child(funciona.getId_funcionario()+"").removeValue();
        databaseReference.child("Funcionario").child(funciona.getNome_funcionario()+"").removeValue();
        databaseReference.child("Funcionario").child(funciona.getEmail_funcionario()+"").removeValue();
        databaseReference.child("Funcionario").child(funciona.getPessoa()+"").removeValue();
    }
    public static void editarFuncionario(Funcionario funciona){
       // databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
       // ).child("nome").setValue(funciona.getNome_funcionario());
        databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
        ).child("nome").setValue(funciona.getNome_funcionario());
        databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
        ).child("Email").setValue(funciona.getEmail_funcionario());
        databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
        ).child("Pessoa").setValue(funciona.getPessoa());

    }


}
