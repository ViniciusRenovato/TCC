package com.example.tcc;

import static org.junit.Assert.assertNotNull;


import com.example.tcc.entidades.Venda;

import org.junit.Before;
import org.junit.Test;

public class VendaTest {
    Venda venda;

    @Before
    public  void criaVenda(){
        venda = new Venda();
    }


    @Test(expected = NullPointerException.class)
    public void id_venda(){
        venda = new Venda();
        venda.setId_venda(1);
    }
    @Test(expected = NullPointerException.class)
    public void tipo_pagamento(){
        venda = new Venda();
        venda.setTipo_pagamento("");
    }
    @Test(expected = NullPointerException.class)
    public void valor_total(){
        venda = new Venda();
        venda.setValor_total(210.00);
    }
    @Test(expected = NullPointerException.class)
    public void Agendamento(){
        venda = new Venda();
        venda.setAgendamento(venda.getAgendamento());
    }



    @Test
    public void testevenda(){
        venda.setId_venda(1);
        venda.setTipo_pagamento("");
        venda.setValor_total(210.00);
        venda.setAgendamento(venda.getAgendamento());

        assertNotNull(venda);
    }




    @Test
    public void testeIntegracao(){
        venda.setId_venda(1);
        venda.setTipo_pagamento("");
        venda.setValor_total(210.00);
        venda.setAgendamento(venda.getAgendamento());

        Venda.salvaVenda(venda);
        assertNotNull(venda);
    }



    //@After
}
