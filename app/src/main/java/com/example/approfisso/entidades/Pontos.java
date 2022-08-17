package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;


public class Pontos implements Serializable {

    private Integer id_pontos;
    private Integer pontos_cliente;
    private Venda venda;
    private Cliente cliente;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public Integer getId_pontos() {
        return id_pontos;
    }

    public void setId_pontos(Integer id_pontos) {
        this.id_pontos = id_pontos;
    }

    public Integer getPontos_cliente() {
        return pontos_cliente;
    }

    public void setPontos_cliente(Integer pontos_cliente) {
        this.pontos_cliente = pontos_cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public static void salvaPontos(Pontos po){
        if(databaseReference==null){
            databaseReference.child("Pontos").child(po.getId_pontos().toString()
            ).setValue(po);
            databaseReference.child("Pontos").child(po.getPontos_cliente().toString()
            ).setValue(po);
            databaseReference.child("Pontos").child(po.getVenda().toString()
            ).setValue(po);
            databaseReference.child("Pontos").child(po.getCliente().toString()
            ).setValue(po);
        }
    }
    public static void excluirPontos(Pontos po){
        databaseReference.child("Pontos").child(po.getId_pontos()+"").removeValue();
        databaseReference.child("Pontos").child(po.getPontos_cliente()+"").removeValue();
        databaseReference.child("Pontos").child(po.getVenda()+"").removeValue();
        databaseReference.child("Pontos").child(po.getCliente()+"").removeValue();
    }
    public static void editarPontos(Pontos po){

        databaseReference.child("Pontos").child(po.getId_pontos().toString()
        ).child("pontos").setValue(po.getPontos_cliente());
        databaseReference.child("Pontos").child(po.getId_pontos().toString()
        ).child("Cliente").setValue(po.getCliente());
        databaseReference.child("Pontos").child(po.getId_pontos().toString()
        ).child("Venda").setValue(po.getVenda());
    }
}
