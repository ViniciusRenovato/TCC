package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Endereco implements Serializable {

    private String id_endereco;
    private String rua;
    private String cidade;
    private String estado;
    private String CEP;
    private String pais;

    public String getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(String id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    @Override
    public String toString() {
        return "Endereço{" +
                " Rua=" + rua + '\'' +
                ", Cidade='" + cidade + '\'' +
                ", Estado='" + estado + '\'' +
                ", CEP='" + CEP + '\'' +
                ", Pais='" + pais + '\'' +
                '}';
    }


    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static void salvaEndereco(Endereco e){
        if(databaseReference==null){
            databaseReference.child("Endereco").child(e.getId_endereco().toString()
            ).setValue(e);
            databaseReference.child("Endereco").child(e.getRua().toString()
            ).setValue(e);
            databaseReference.child("Endereco").child(e.getCidade().toString()
            ).setValue(e);
            databaseReference.child("Endereco").child(e.getEstado().toString()
            ).setValue(e);
            databaseReference.child("Endereco").child(e.getCEP().toString()
            ).setValue(e);
            databaseReference.child("Endereco").child(e.getPais().toString()
            ).setValue(e);


        }
    }
    public static void excluirEndereco(Endereco e){
        databaseReference.child("Endereco").child(e.getId_endereco()+"").removeValue();
        databaseReference.child("Endereco").child(e.getRua()+"").removeValue();
        databaseReference.child("Endereco").child(e.getCidade()+"").removeValue();
        databaseReference.child("Endereco").child(e.getEstado()+"").removeValue();
        databaseReference.child("Endereco").child(e.getCEP()+"").removeValue();
        databaseReference.child("Endereco").child(e.getPais()+"").removeValue();
    }
    public static void editarEndereco(Endereco e){
     //   databaseReference.child("Endereco").child(e.getId_endereco().toString()
     //   ).child("Endereco").setValue(e.getId_endereco());
        databaseReference.child("Endereco").child(e.getId_endereco().toString()
        ).child("Rua").setValue(e.getRua());
        databaseReference.child("Endereco").child(e.getId_endereco().toString()
        ).child("Cidade").setValue(e.getCidade());
        databaseReference.child("Endereco").child(e.getId_endereco().toString()
        ).child("Estado").setValue(e.getEstado());
        databaseReference.child("Endereco").child(e.getId_endereco().toString()
        ).child("CEP").setValue(e.getCEP());
        databaseReference.child("Endereco").child(e.getId_endereco().toString()
        ).child("País").setValue(e.getPais());

    }














}
