//package com.example.tcc;
//
//import static org.junit.Assert.assertNotNull;
//
//
//import com.example.approfisso.entidades.Funcao;
//
//
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class FuncaoTest {
//    Funcao funcao;
//
//    @Before
//    public  void criaFuncao(){
//        funcao = new Funcao();
//    }
//
//
//    @Test(expected = NullPointerException.class)
//    public void id_funcao(){
//        funcao = new Funcao();
//        funcao.setId_funcao("");
//    }
//    @Test(expected = NullPointerException.class)
//    public void nome_funcao(){
//        funcao = new Funcao();
//        funcao.setNome_funcao("");
//    }
//
//
//
//    @Test
//    public void testefuncao(){
//        funcao.setId_funcao("");
//        funcao.setNome_funcao("");
//
//
//        assertNotNull(funcao);
//    }
//
//
//
//
//    @Test
//    public void testeIntegracao(){
//        funcao.setId_funcao("");
//        funcao.setNome_funcao("");
//        Funcao.salvaFuncao(funcao);
//        assertNotNull(funcao);
//    }
//
//
//    //@After
//}
