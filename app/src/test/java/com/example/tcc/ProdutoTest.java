package com.example.tcc;

import static org.junit.Assert.assertNotNull;


import com.example.approfisso.entidades.Produto;


import org.junit.Before;
import org.junit.Test;

public class ProdutoTest {
    Produto produto;

    @Before
    public  void criaProduto(){
        produto = new Produto();
    }


    @Test(expected = NullPointerException.class)
    public void id_produto(){
        produto = new Produto();
        produto.setId_produto(1);
    }
    @Test(expected = NullPointerException.class)
    public void consumo_produto(){
        produto = new Produto();
        produto.setConsumo_produto(2);
    }
    @Test(expected = NullPointerException.class)
    public void preco_produto(){
        produto = new Produto();
        produto.setPreco_produto(3);
    }


    @Test
    public void testeproduto(){
        produto.setId_produto(1);
        produto.setConsumo_produto(2);
        produto.setPreco_produto(3);

        assertNotNull(produto);
    }




    @Test
    public void testeIntegracao(){
        produto.setId_produto(1);
        produto.setConsumo_produto(2);
        produto.setPreco_produto(3);
        Produto.salvaProduto(produto);
        assertNotNull(produto);
    }



    //@After
}
