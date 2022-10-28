package com.example.approfisso.entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Cliente implements Serializable {

    private Integer pontos_cliente;
    private Integer id_cliente;
    private Pessoa pessoa;

    public Integer getPontos_cliente() {
        return pontos_cliente;
    }

    public void setPontos_cliente(Integer pontos_cliente) {
        this.pontos_cliente = pontos_cliente;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


//    @Override
//    public String toString() {
//        return "Cliente{" +
//                " Nome='" + pessoa.getNome_pessoa() + '\'' +
//                ", Telefone='" + pessoa.getTelefone_pessoa() + '\'' +
//                ", Pontos do cliente='" + pontos_cliente + '\'' +
//                ", ID do cliente='" + id_cliente + '\'' +
//                '}';
//    }

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static void salvaCliente(Cliente c){
        if(databaseReference==null)
            databaseReference.child("Cliente").child(c.getId_cliente().toString()
            ).setValue(c);
            databaseReference.child("Cliente").child(c.getPontos_cliente().toString()
            ).setValue(c);
            databaseReference.child("Cliente").child(c.getPessoa().toString()
            ).setValue(c);


    }
    public static void excluirCliente(Cliente c){
        databaseReference.child("Cliente").child(c.getId_cliente()+"").removeValue();
        databaseReference.child("Cliente").child(c.getPontos_cliente()+"").removeValue();
        databaseReference.child("Cliente").child(c.getPessoa()+"").removeValue();

    }
    public static void editarCliente(Cliente c){
    //    databaseReference.child("Cliente").child(c.getId_cliente().toString()
    //    ).child("nome").setValue(c.getId_cliente());
        databaseReference.child("Cliente").child(c.getId_cliente().toString()
        ).child("Pontos").setValue(c.getPontos_cliente());
        databaseReference.child("Cliente").child(c.getId_cliente().toString()
        ).child("pessoa").setValue(c.getPessoa());


    }
}
