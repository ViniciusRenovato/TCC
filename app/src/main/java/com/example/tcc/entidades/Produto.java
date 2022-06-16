package com.example.tcc.entidades;

import java.io.Serializable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Produto implements Serializable {

    private Integer id_produto;
    private Integer consumo_produto;
    private Integer preco_produto;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getConsumo_produto() {
        return consumo_produto;
    }

    public void setConsumo_produto(Integer consumo_produto) {
        this.consumo_produto = consumo_produto;
    }

    public Integer getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(Integer preco_produto) {
        this.preco_produto = preco_produto;
    }
    public static void salvaProduto(Produto pr){
        if(databaseReference==null){
            databaseReference.child("Produto").child(pr.getId_produto().toString()
            ).setValue(pr);
            databaseReference.child("Produto").child(pr.getPreco_produto().toString()
            ).setValue(pr);
            databaseReference.child("Produto").child(pr.getConsumo_produto().toString()
            ).setValue(pr);
        }
    }
    public static void excluirProduto(Produto pr){
        databaseReference.child("Produto").child(pr.getId_produto()+"").removeValue();
        databaseReference.child("Produto").child(pr.getConsumo_produto()+"").removeValue();
        databaseReference.child("Produto").child(pr.getPreco_produto()+"").removeValue();
    }
    public static void editarPontos(Produto pr){

        databaseReference.child("Produto").child(pr.getId_produto().toString()
        ).child("consumo").setValue(pr.getConsumo_produto());
        databaseReference.child("Produto").child(pr.getId_produto().toString()
        ).child("preco").setValue(pr.getPreco_produto());
    }
}
