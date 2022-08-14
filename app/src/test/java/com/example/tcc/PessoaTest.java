package com.example.tcc;

import static org.junit.Assert.*;

import com.example.tcc.entidades.Pessoa;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PessoaTest {
    Pessoa pessoa;

    @Before
    public  void criaPessoa(){
        pessoa = new Pessoa();
    }


    @Test(expected = NullPointerException.class)
    public void nome(){
        pessoa = new Pessoa();
        pessoa.setNome_pessoa("");
    }
    @Test(expected = NullPointerException.class)
    public void telefone(){
        pessoa = new Pessoa();
        pessoa.setTelefone_pessoa("");
    }
    @Test(expected = NullPointerException.class)
    public void aniversario(){
        pessoa = new Pessoa();
        pessoa.setData_aniversario("");
    }
    @Test(expected = NullPointerException.class)
    public void email(){
        pessoa = new Pessoa();
        pessoa.setEmail_pessoa("");
    }
    @Test(expected = NullPointerException.class)
    public void enderecol(){
        pessoa = new Pessoa();
        pessoa.setEndereco(pessoa.getEndereco());
    }
    @Test
    public void testepessoa(){
        pessoa.setNome_pessoa("Andre");
        pessoa.setTelefone_pessoa("99912165");
        pessoa.setData_aniversario("21/10");
        pessoa.setEmail_pessoa("matheus-a-c@hotmail.com");
        pessoa.setEndereco(pessoa.getEndereco());

        assertNotNull(pessoa);
    }




    @Test
    public void testeIntegracao(){

//        Pessoa pessoa = new Pessoa();
//        pessoa.setNome_pessoa("Andre");
//        pessoa.setTelefone_pessoa("99912165");
//        pessoa.setData_aniversario("21/10");
//        pessoa.setEmail_pessoa("matheus-a-c@hotmail.com");
//        pessoa.setEndereco(pessoa.getEndereco());
//      Pessoa.salvaPessoa(pessoa);
//      assertNotNull(pessoa);
//    }

//    @Test
//    public void Salvando_Exito() throws IOException {
//        Pessoa pessoa = new Pessoa();
//        pessoa.setId_pessoa(1);
//        pessoa.setNome_pessoa("qualquer pessoa");
//        pessoa.setNome("Bruno Formiga");
//        pessoa.setCpf("07464993101");
//        pessoa.setCnpj("11162692000190");
//        pessoa.setEndereco("Bom dia ao lado do boa noite");
//        pessoa.setTelefone("67996002604");
//        pessoa.setEmail("eobrunoformiga@gmail.com");
//        pessoa.setLogin("formigabruno");
//        pessoa.setSenha("beemovieee");
//        pessoa.setAvaliacaoPrestador(5.0);
//        pessoa.setAvaliacaoCliente(4.5);
//        pessoa.setPrestador(true);
//        pessoa.setAtivo(true);

    //    Pessoa.salvaPessoa(pessoa);
    }







    //@After
}
