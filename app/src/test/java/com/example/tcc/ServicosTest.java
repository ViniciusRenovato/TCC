//package com.example.tcc;
//
//import static org.junit.Assert.assertNotNull;
//
//
//import com.example.approfisso.entidades.Servicos;
//
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class ServicosTest {
//    Servicos servicos;
//
//    @Before
//    public  void criaCliente(){
//        servicos = new Servicos();
//    }
//
//
//    @Test(expected = NullPointerException.class)
//    public void id_servicos(){
//        servicos = new Servicos();
//        servicos.setId_servico("");
//    }
//    @Test(expected = NullPointerException.class)
//    public void nome_servicos(){
//        servicos = new Servicos();
//        servicos.setNome_servico("");
//    }
//    @Test(expected = NullPointerException.class)
//    public void duracao_servicos(){
//        servicos = new Servicos();
//        servicos.setDuracao_servico("");
//    }
//    @Test(expected = NullPointerException.class)
//    public void valor_servicos(){
//        servicos = new Servicos();
//        servicos.setValor_servico("");
//    }
//    @Test(expected = NullPointerException.class)
//    public void Cliente_servicos(){
//        servicos = new Servicos();
//        servicos.setCliente(servicos.getCliente());
//    }
//    @Test(expected = NullPointerException.class)
//    public void funcionario_servicos(){
//        servicos = new Servicos();
//        servicos.setFuncionario(servicos.getFuncionario());
//    }
//    @Test(expected = NullPointerException.class)
//    public void agendamento_servicos(){
//        servicos = new Servicos();
//        servicos.setAgendamento(servicos.getAgendamento());
//    }
//    @Test(expected = NullPointerException.class)
//    public void produto_servicos(){
//        servicos = new Servicos();
//        servicos.setProduto(servicos.getProduto());
//    }
//
//
//
//
//    @Test
//    public void testeservicos(){
//        servicos.setId_servico("");
//        servicos.setNome_servico("");
//        servicos.setDuracao_servico("");
//        servicos.setValor_servico("");
//        servicos.setCliente(servicos.getCliente());
//        servicos.setFuncionario(servicos.getFuncionario());
//        servicos.setAgendamento(servicos.getAgendamento());
//        servicos.setProduto(servicos.getProduto());
//        assertNotNull(servicos);
//    }
//
//
//
//
//    @Test
//    public void testeIntegracao(){
//        Servicos servicos = new Servicos();
//        servicos.setId_servico("");
//        servicos.setNome_servico("");
//        servicos.setDuracao_servico("");
//        servicos.setValor_servico("");
//        servicos.setCliente(servicos.getCliente());
//        servicos.setFuncionario(servicos.getFuncionario());
//        servicos.setAgendamento(servicos.getAgendamento());
//        servicos.setProduto(servicos.getProduto());
//        Servicos.salvaServicos(servicos);
//        assertNotNull(servicos);
//    }
//
//
//    //@After
//}
