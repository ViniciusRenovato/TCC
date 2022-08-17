package com.example.approfisso.classes;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class empregos implements Serializable {

    private String id;
    private String Estado;
    private String Cidade;
    private String Periodo;
    private String Area_da_profissao;
    private String Salario;
    private String Email;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }

    public empregos(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String periodo) {
        Periodo = periodo;
    }

    public String getArea_da_profissao() {
        return Area_da_profissao;
    }

    public void setArea_da_profissao(String area_da_profissao) { Area_da_profissao = area_da_profissao;    }

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String salario) {
        Salario = salario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }




    @Override
    public String toString() {
        return  ", estado='" + Estado + '\'' +
                ", cidade='" + Cidade + '\'' +
                ", emprego='" + Area_da_profissao + '\'' +
                ", salario='" + Salario + '\'' + '}';
    }



    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }
    public static void salvar(empregos e){
        if(databaseReference==null){
            inicio();
            String id=databaseReference.child("Emprego").push().getKey();
            List<empregos> empregos = new ArrayList();
            e.setId(id);
                    databaseReference.child("Emprego").child(id).child("id").setValue(id);
                    databaseReference.child("Emprego").child(id).child("Cidade").setValue(e.getCidade());
                    databaseReference.child("Emprego").child(id).child("Email").setValue(e.getEmail());
                    databaseReference.child("Emprego").child(id).child("Estado").setValue(e.getEstado());
                    databaseReference.child("Emprego").child(id).child("Periodo").setValue(e.getPeriodo());
                    databaseReference.child("Emprego").child(id).child("Profissao").setValue(e.getArea_da_profissao());
                    databaseReference.child("Emprego").child(id).child("salario").setValue(e.getSalario());

        }
    }

    public static void excluir(empregos e){
        databaseReference.child("Emprego").child(e.getId()+"").removeValue();
    }
    public static void editar(empregos e) {
        databaseReference.child("Emprego").child(e.getId().toString()).child("Cidade").setValue(e.getCidade());
        databaseReference.child("Emprego").child(e.getId().toString()).child("Email").setValue(e.getEmail());
        databaseReference.child("Emprego").child(e.getId().toString()).child("Estado").setValue(e.getEstado());
        databaseReference.child("Emprego").child(e.getId().toString()).child("Periodo").setValue(e.getPeriodo());
        databaseReference.child("Emprego").child(e.getId().toString()).child("Profissao").setValue(e.getArea_da_profissao());
        databaseReference.child("Emprego").child(e.getId().toString()).child("Salario").setValue(e.getSalario());

    }





}

