package com.example.approfisso.entidades;

import com.example.approfisso.classes.empregos;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Funcionario implements Serializable {

    private String id_funcionario;
    private String nome_funcionario;
    private String sobrenome_funcionario;
    private String funcao_funcionario;
    private String telefone_funcionario;
    private String aniversario_funcionario;
    private String email_funcionario;
    private Pessoa pessoa;
    private String imagem_funcionario;


    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }


    public String getFuncao_funcionario() {
        return funcao_funcionario;
    }

    public void setFuncao_funcionario(String funcao_funcionario) {
        this.funcao_funcionario = funcao_funcionario;
    }

    public String getSobrenome_funcionario() {
        return sobrenome_funcionario;
    }

    public void setSobrenome_funcionario(String sobrenome_funcionario) {
        this.sobrenome_funcionario = sobrenome_funcionario;
    }

    public String getTelefone_funcionario() {
        return telefone_funcionario;
    }

    public void setTelefone_funcionario(String telefone_funcionario) {
        this.telefone_funcionario = telefone_funcionario;
    }

    public String getAniversario_funcionario() {
        return aniversario_funcionario;
    }

    public void setAniversario_funcionario(String aniversario_funcionario) {
        this.aniversario_funcionario = aniversario_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getEmail_funcionario() {
        return email_funcionario;
    }

    public void setEmail_funcionario(String email_funcionario) {
        this.email_funcionario = email_funcionario;
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


    public String getImagem_funcionario() {
        return imagem_funcionario;
    }

    public void setImagem_funcionario(String imagem_funcionario) {
        this.imagem_funcionario = imagem_funcionario;
    }

    @Override
    public String toString() {
        return  ", ID='" + id_funcionario + '\'' +
                ", Nome='" + nome_funcionario + '\'' +
                ", Sobrenome='" + sobrenome_funcionario + '\'' +
                ", funcao='" + funcao_funcionario + '\'' +
                ", Telefone='" + telefone_funcionario + '\'' +
                ", Aniversario='" + aniversario_funcionario + '\'' +
                ", Email='" + email_funcionario + '\'' + '}';
    }



    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }









    public static void salvaFuncionario(Funcionario funciona){
        if(databaseReference==null){
            inicio();
            String id=databaseReference.child("Funcionario").push().getKey();
            List<Funcionario> funcionario = new ArrayList();
            funciona.setId_funcionario(id);

            databaseReference.child("Funcionario").child(id).child("id_funcionario").setValue(id);
            databaseReference.child("Funcionario").child(id).child("nome_funcionario").setValue(funciona.getNome_funcionario());
            databaseReference.child("Funcionario").child(id).child("sobrenome_funcionario").setValue(funciona.getSobrenome_funcionario());
            databaseReference.child("Funcionario").child(id).child("funcao_funcionario").setValue(funciona.getFuncao_funcionario());
            databaseReference.child("Funcionario").child(id).child("telefone_funcionario").setValue(funciona.getTelefone_funcionario());
            databaseReference.child("Funcionario").child(id).child("aniversario_funcionario").setValue(funciona.getAniversario_funcionario());
            databaseReference.child("Funcionario").child(id).child("email_funcionario").setValue(funciona.getEmail_funcionario());



        }
    }
    public static void excluirFuncionario(Funcionario funciona){
        databaseReference.child("Funcionario").child(funciona.getId_funcionario()+"").removeValue();
        databaseReference.child("Funcionario").child(funciona.getNome_funcionario()+"").removeValue();
        databaseReference.child("Funcionario").child(funciona.getEmail_funcionario()+"").removeValue();
        databaseReference.child("Funcionario").child(funciona.getPessoa()+"").removeValue();
    }



//    public static void editar_funcionario(Funcionario funciona) {
//
//        databaseReference.child("Funcionario").child(id).child("id_funcionario").setValue(id);
//        databaseReference.child("Funcionario").child(id).child("nome_funcionario").setValue(funciona.getNome_funcionario());
//        databaseReference.child("Funcionario").child(id).child("sobrenome_funcionario").setValue(funciona.getSobrenome_funcionario());
//        databaseReference.child("Funcionario").child(id).child("funcao_funcionario").setValue(funciona.getFuncao_funcionario());
//        databaseReference.child("Funcionario").child(id).child("telefone_funcionario").setValue(funciona.getTelefone_funcionario());
//        databaseReference.child("Funcionario").child(id).child("aniversario_funcionario").setValue(funciona.getAniversario_funcionario());
//        databaseReference.child("Funcionario").child(id).child("email_funcionario").setValue(funciona.getEmail_funcionario());
//
//
//
//
//
//        databaseReference.child("Emprego").child(e.getId().toString()).child("Cidade").setValue(e.getCidade());
//        databaseReference.child("Emprego").child(e.getId().toString()).child("Email").setValue(e.getEmail());
//        databaseReference.child("Emprego").child(e.getId().toString()).child("Estado").setValue(e.getEstado());
//        databaseReference.child("Emprego").child(e.getId().toString()).child("Periodo").setValue(e.getPeriodo());
//        databaseReference.child("Emprego").child(e.getId().toString()).child("Profissao").setValue(e.getArea_da_profissao());
////        databaseReference.child("Emprego").child(e.getId().toString()).child("Salario").setValue(e.getSalario());
//
//    }















//    public static void editarFuncionario(Funcionario funciona){
//       // databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
//       // ).child("nome").setValue(funciona.getNome_funcionario());
//        databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
//        ).child("nome").setValue(funciona.getNome_funcionario());
//        databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
//        ).child("Email").setValue(funciona.getEmail_funcionario());
//        databaseReference.child("Funcionario").child(funciona.getId_funcionario().toString()
//        ).child("Pessoa").setValue(funciona.getPessoa());
//
//    }


}
