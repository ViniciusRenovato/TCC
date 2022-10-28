package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void salvarDadosFuncao(){

    }


    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static FirebaseFirestore fstore;


    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();

        fstore = FirebaseFirestore.getInstance();


    }

    @Override
    public String toString() {
        return  ", ID='" + id_funcao + '\'' +
                ", Nome='" + nome_funcao + '\'' + '}';
    }


    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }



    public static void salvaFuncao(Funcao fun){
        if(databaseReference==null)
            inicio();

            String id=databaseReference.child("Funcao").push().getKey();
            List<Funcao> funcao = new ArrayList();
            fun.setId_funcao(id);

            databaseReference.child("Funcao").child(id).child("id_funcao").setValue(id);
            databaseReference.child("Funcao").child(id).child("nome_funcao").setValue(fun.getNome_funcao());



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
