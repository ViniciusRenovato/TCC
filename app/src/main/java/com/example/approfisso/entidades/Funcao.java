package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Funcao implements Serializable {

    private String nome_funcao;
    private String id_funcao;

    public String getNome_funcao() {
        return nome_funcao;
    }

    public void setNome_funcao(String nome_funcao) {
        this.nome_funcao = nome_funcao;
    }

    public String getId_funcao() {
        return id_funcao;
    }

    public void setId_funcao(String id_funcao) {
        this.id_funcao = id_funcao;
    }


    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static void salvaFuncao(Funcao fun){
        if(databaseReference==null){
            databaseReference.child("Funcionario").child(fun.getId_funcao().toString()
            ).setValue(fun);
            databaseReference.child("Funcionario").child(fun.getNome_funcao().toString()
            ).setValue(fun);



        }
    }
    public static void excluirFuncao(Funcao fun){
        databaseReference.child("Funcionario").child(fun.getId_funcao()+"").removeValue();
        databaseReference.child("Funcionario").child(fun.getNome_funcao()+"").removeValue();

    }
    public static void editarFuncao(Funcao fun){
    //    databaseReference.child("Funcionario").child(fun.getId_funcao().toString()
    //    ).child("nome").setValue(fun.getId_funcao());
        databaseReference.child("Funcionario").child(fun.getId_funcao().toString()
        ).child("nome").setValue(fun.getNome_funcao());


    }





}
