package com.example.tcc;

import static org.junit.Assert.assertNotNull;


import com.example.tcc.entidades.Pontos;


import org.junit.Before;
import org.junit.Test;

public class PontosTest {
    Pontos pontos;

    @Before
    public  void criaPontos(){
        pontos = new Pontos();
    }


    @Test(expected = NullPointerException.class)
    public void id_pontos(){
        pontos = new Pontos();
        pontos.setId_pontos(1);
    }
    @Test(expected = NullPointerException.class)
    public void pontos_cliente(){
        pontos = new Pontos();
        pontos.setPontos_cliente(3);
    }
    @Test(expected = NullPointerException.class)
    public void venda_pontos(){
        pontos = new Pontos();
        pontos.setVenda(pontos.getVenda());
    }
    @Test(expected = NullPointerException.class)
    public void cliente_pontos(){
        pontos = new Pontos();
        pontos.setCliente(pontos.getCliente());
    }

    @Test
    public void testepontos(){
        pontos.setId_pontos(1);
        pontos.setPontos_cliente(3);
        pontos.setVenda(pontos.getVenda());
        pontos.setCliente(pontos.getCliente());

        assertNotNull(pontos);
    }




    @Test
    public void testeIntegracao(){
        Pontos pontos = new Pontos();
        pontos.setId_pontos(1);
        pontos.setPontos_cliente(3);
        pontos.setVenda(pontos.getVenda());
        pontos.setCliente(pontos.getCliente());
        Pontos.salvaPontos(pontos);
        assertNotNull(pontos);
    }



    //@After
}
