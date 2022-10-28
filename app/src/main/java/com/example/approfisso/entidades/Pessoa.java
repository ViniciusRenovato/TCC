package com.example.approfisso.entidades;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Pessoa implements Serializable {
    private String Nome;
    private String Sobrenome;
    private String ID;
    private String Telefone;
    private String Aniversario;
    private String Email;
    private Endereco endereco;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getAniversario() {
        return Aniversario;
    }

    public void setAniversario(String aniversario) {
        Aniversario = aniversario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }







    @Override
    public String toString() {
        return  ", ID='" + ID + '\'' +
                ", Nome='" + Nome + '\'' +
                ", Sobrenome='" + Sobrenome + '\'' +
                ", Telefone='" + Telefone + '\'' +
                ", Aniversario='" + Aniversario + '\'' +
                ", Email='" + Email + '\'' + '}';
    }






    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }


    public static void salvaPessoa(Pessoa p){
        if(databaseReference==null)
            inicio();
            String id=databaseReference.child("Pessoa").push().getKey();
            List<Pessoa> pessoa = new ArrayList();
            p.setID(id);

            databaseReference.child("Pessoa").child(id).child("id").setValue(id);
            databaseReference.child("Pessoa").child(id).child("Nome").setValue(p.getNome());
            databaseReference.child("Pessoa").child(id).child("Sobrenome").setValue(p.getSobrenome());
            databaseReference.child("Pessoa").child(id).child("Telefone").setValue(p.getTelefone());
            databaseReference.child("Pessoa").child(id).child("Aniversario").setValue(p.getAniversario());
            databaseReference.child("Pessoa").child(id).child("Email").setValue(p.getEmail());


    }




    public static void excluirPessoa(Pessoa p){
        databaseReference.child("Pessoa").child(p.getID()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getNome()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getSobrenome()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getTelefone()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getAniversario()+"").removeValue();
        databaseReference.child("Pessoa").child(p.getEmail()+"").removeValue();




    }
    public static void editarPessoa(Pessoa p){
        databaseReference.child("Pessoa").child(p.getID().toString()
        ).child("nome").setValue(p.getID());
        databaseReference.child("Pessoa").child(p.getID().toString()
        ).child("nome").setValue(p.getNome());
        databaseReference.child("Pessoa").child(p.getID().toString()
        ).child("telefone").setValue(p.getSobrenome());
        databaseReference.child("Pessoa").child(p.getID().toString()
        ).child("email").setValue(p.getTelefone());
        databaseReference.child("Pessoa").child(p.getID().toString()
        ).child("data").setValue(p.getEmail());
        databaseReference.child("Pessoa").child(p.getID().toString()
        ).child("data").setValue(p.getAniversario());
    }
}
