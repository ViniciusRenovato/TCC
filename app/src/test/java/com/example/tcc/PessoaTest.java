package com.example.tcc;

import static org.junit.Assert.*;

import com.example.tcc.entidades.Pessoa;

import org.junit.Before;
import org.junit.Test;

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
    @Test
    public void teste(){
        pessoa.setNome_pessoa("Andre");
        pessoa.setTelefone_pessoa("99912165");
        assertNotNull(pessoa);
    }

    @Test
    public void testeIntegracao(){
        pessoa.setNome_pessoa("Anmdre");
        pessoa.setTelefone_pessoa("99912165");
      Pessoa.salvaPessoa(pessoa);
      assertNotNull(pessoa);
    }



    //@After
}
