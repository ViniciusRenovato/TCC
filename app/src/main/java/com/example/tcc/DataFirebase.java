package com.example.tcc;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.tcc.entidades.Pessoa;

public class DataFirebase {
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
       // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }
    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }


    public static void salvar( Pessoa pessoa){
        if(databaseReference==null)
            inicio();
        databaseReference.child("pessoa").child(
                pessoa.getId_pessoa().toString()
        ).child("pessoa").setValue(pessoa.getNome_pessoa());
    }













}
