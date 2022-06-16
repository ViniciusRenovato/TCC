package com.example.tcc.entidades;

import java.io.Serializable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Venda implements Serializable {

    private Integer id_venda;
    private String tipo_pagamento;
    private Number valor_total;
    private Agendamento agendamento;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }

    public Number getValor_total() {
        return valor_total;
    }

    public void setValor_total(Number valor_total) {
        this.valor_total = valor_total;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public static void salvaVenda(Venda v){
        if(databaseReference==null){
            databaseReference.child("Venda").child(v.getId_venda().toString()
            ).setValue(v);
            databaseReference.child("Venda").child(v.getAgendamento().toString()
            ).setValue(v);
            databaseReference.child("Venda").child(v.getTipo_pagamento().toString()
            ).setValue(v);
            databaseReference.child("Venda").child(v.getValor_total().toString()
            ).setValue(v);
        }
    }
    public static void excluirVenda(Venda v){
        databaseReference.child("Venda").child(v.getId_venda()+"").removeValue();
        databaseReference.child("Venda").child(v.getAgendamento()+"").removeValue();
        databaseReference.child("Venda").child(v.getTipo_pagamento()+"").removeValue();
        databaseReference.child("Venda").child(v.getValor_total()+"").removeValue();
    }
    public static void editarServicos(Venda v){

        databaseReference.child("Servicos").child(v.getId_venda().toString()
        ).child("Agendamento").setValue(v.getAgendamento());
        databaseReference.child("Servicos").child(v.getId_venda().toString()
        ).child("pagamento").setValue(v.getTipo_pagamento());
        databaseReference.child("Servicos").child(v.getId_venda().toString()
        ).child("valor").setValue(v.getValor_total());
    }
}
