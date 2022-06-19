package com.example.tcc;

import static org.junit.Assert.assertNotNull;

import com.example.tcc.entidades.Cliente;


import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
    Cliente cliente;

    @Before
    public  void criaCliente(){
        cliente = new Cliente();
    }


    @Test(expected = NullPointerException.class)
    public void id_pontos(){
        cliente = new Cliente();
        cliente.setId_cliente(1);
    }
    @Test(expected = NullPointerException.class)
    public void ponto_pessoa(){
        cliente = new Cliente();
        cliente.setPontos_cliente(33);
    }
    @Test(expected = NullPointerException.class)
    public void cliente_pessoa(){
        cliente = new Cliente();
        cliente.setPessoa(cliente.getPessoa());
    }


    @Test
    public void testecliente(){
        cliente.setPontos_cliente(2);
        cliente.setPontos_cliente(33);
        cliente.setPessoa(cliente.getPessoa());

        assertNotNull(cliente);
    }




    @Test
    public void testeIntegracao(){
        Cliente cliente = new Cliente();
        cliente.setPontos_cliente(2);
        cliente.setPontos_cliente(33);
        cliente.setPessoa(cliente.getPessoa());
      Cliente.salvaCliente(cliente);
      assertNotNull(cliente);
    }



    //@After
}
