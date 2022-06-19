package com.example.tcc;

import static org.junit.Assert.assertNotNull;


import com.example.tcc.entidades.Funcionario;


import org.junit.Before;
import org.junit.Test;

public class FuncionarioTest {
    Funcionario funcionario;

    @Before
    public  void criaFuncionario(){
        funcionario = new Funcionario();
    }


    @Test(expected = NullPointerException.class)
    public void id_funcionario(){
        funcionario = new Funcionario();
        funcionario.setId_funcionario("");
    }
    @Test(expected = NullPointerException.class)
    public void nome_funcionario(){
        funcionario = new Funcionario();
        funcionario.setNome_funcionario("");
    }
    @Test(expected = NullPointerException.class)
    public void email_funcionario(){
        funcionario = new Funcionario();
        funcionario.setEmail_funcionario("");
    }
    @Test(expected = NullPointerException.class)
    public void pessoa_funcionario(){
        funcionario = new Funcionario();
        funcionario.setPessoa(funcionario.getPessoa());
    }


    @Test
    public void testefuncionario(){
        funcionario.setId_funcionario("");
        funcionario.setNome_funcionario("");
        funcionario.setEmail_funcionario("");
        funcionario.setPessoa(funcionario.getPessoa());

        assertNotNull(funcionario);
    }




    @Test
    public void testeIntegracao(){
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario("");
        funcionario.setNome_funcionario("");
        funcionario.setEmail_funcionario("");
        funcionario.setPessoa(funcionario.getPessoa());
        Funcionario.salvaFuncionario(funcionario);
        assertNotNull(funcionario);
    }



    //@After
}
