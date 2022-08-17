package com.example.approfisso;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.example.approfisso.classes.empregos;


public class DataFirebase {
   private static FirebaseDatabase firebaseDatabase;
   private static DatabaseReference databaseReference;
    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
     //   firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }

    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }

    public static void salvar(empregos empregos){
        if(databaseReference==null)
            inicio();
        databaseReference.child("Emprego").child(
                empregos.getId().toString()
        ).child("Profissao").setValue(empregos.getArea_da_profissao());
    }
    public void remover(empregos empregos){

        databaseReference.child("Emprego").child(empregos.getId()+"").removeValue();

    }





}
