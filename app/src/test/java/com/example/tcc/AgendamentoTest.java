package com.example.tcc;

import static org.junit.Assert.assertNotNull;

import com.example.tcc.entidades.Agendamento;

import org.junit.Before;
import org.junit.Test;



public class AgendamentoTest {
    Agendamento agendamento;

    @Before
    public  void criaAgendamento(){

        agendamento = new Agendamento();
    }


    @Test(expected = NullPointerException.class)
    public void id_agendamento(){
        agendamento = new Agendamento();
        agendamento.setId_agendamento(1);
    }
    @Test(expected = NullPointerException.class)
    public void hora_agendamento(){
        agendamento = new Agendamento();
        agendamento.setHora_agendamento(12);
    }
    @Test(expected = NullPointerException.class)
    public void dia_agendamento(){
        agendamento = new Agendamento();
        agendamento.setDia_agendamento(agendamento.getDia_agendamento());
    }
    @Test(expected = NullPointerException.class)
    public void funcionario(){
        agendamento = new Agendamento();
        agendamento.setFuncionario(agendamento.getFuncionario());
    }
    @Test(expected = NullPointerException.class)
    public void servicos(){
        agendamento = new Agendamento();
        agendamento.setServicos(agendamento.getServicos());
    }
    @Test(expected = NullPointerException.class)
    public void cliente(){
        agendamento = new Agendamento();
        agendamento.setCliente(agendamento.getCliente());
    }

    @Test
    public void testeagendamento(){
        agendamento.setId_agendamento(1);
        agendamento.setHora_agendamento(13);
        agendamento.setDia_agendamento(agendamento.getDia_agendamento());
        agendamento.setFuncionario(agendamento.getFuncionario());
        agendamento.setServicos(agendamento.getServicos());
        agendamento.setCliente(agendamento.getCliente());

        assertNotNull(agendamento);
    }




    @Test
    public void testeIntegracao(){
        Agendamento agendamento = new Agendamento();
        agendamento.setId_agendamento(1);
        agendamento.setHora_agendamento(13);
        agendamento.setDia_agendamento(agendamento.getDia_agendamento());
        agendamento.setFuncionario(agendamento.getFuncionario());
        agendamento.setServicos(agendamento.getServicos());
        agendamento.setCliente(agendamento.getCliente());

      Agendamento.salvaAgendamento(agendamento);
      assertNotNull(agendamento);
    }



    //@After
}
