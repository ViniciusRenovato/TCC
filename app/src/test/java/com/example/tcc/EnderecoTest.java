package com.example.tcc;

import static org.junit.Assert.assertNotNull;


import com.example.tcc.entidades.Endereco;


import org.junit.Before;
import org.junit.Test;

public class EnderecoTest {
    Endereco endereco;

    @Before
    public  void criaEndereco(){
        endereco = new Endereco();
    }


    @Test(expected = NullPointerException.class)
    public void id_endereco(){
        endereco = new Endereco();
        endereco.setId_endereco("");
    }
    @Test(expected = NullPointerException.class)
    public void rua(){
        endereco = new Endereco();
        endereco.setRua("");
    }
    @Test(expected = NullPointerException.class)
    public void cidade(){
        endereco = new Endereco();
        endereco.setCidade("");
    }
    @Test(expected = NullPointerException.class)
    public void estado(){
        endereco = new Endereco();
        endereco.setEstado("");
    }
    @Test(expected = NullPointerException.class)
    public void CEP(){
        endereco = new Endereco();
        endereco.setCEP("");
    }
    @Test(expected = NullPointerException.class)
    public void pais(){
        endereco = new Endereco();
        endereco.setPais("");
    }



    @Test
    public void testeendereco(){
        endereco.setId_endereco("");
        endereco.setRua("");
        endereco.setCidade("");
        endereco.setEstado("");
        endereco.setCEP("");
        endereco.setPais("");


        assertNotNull(endereco);
    }




    @Test
    public void testeIntegracao(){
        Endereco endereco = new Endereco();
        endereco.setId_endereco("");
        endereco.setRua("");
        endereco.setCidade("");
        endereco.setEstado("");
        endereco.setCEP("");
        endereco.setPais("");
        Endereco.salvaEndereco(endereco);
        assertNotNull(endereco);
    }



    //@After
}
