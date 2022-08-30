package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DatabaseReference;

import com.example.approfisso.R;


public class cadastro_cliente extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nome;
    private EditText sobrenome;
    private EditText telefone;
    private EditText aniversario;
    private EditText email;
    private Pessoa Clientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_cadastro);


        nome=findViewById(R.id.Nome_Servico);
        sobrenome=findViewById(R.id.Duracao_Servico);
        telefone=findViewById(R.id.Preco_Servico);
        aniversario=findViewById(R.id.Funcao_Servico);
        email=findViewById(R.id.Email_cadastro);
        Intent i = getIntent();
        Clientes =(Pessoa) i.getSerializableExtra("Clientes");


    }


    public void lista_emprego(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }


    public void Botao_Cancelar_Cliente (View view){
        super.onBackPressed();
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }


    public void botao_Confirmar (View view){
        Clientes = new Pessoa();
        Clientes.setNome(nome.getText().toString());
        Clientes.setSobrenome(sobrenome.getText().toString());
        Clientes.setTelefone(telefone.getText().toString());
        Clientes.setAniversario(aniversario.getText().toString());
        Clientes.setEmail(email.getText().toString());
        Pessoa.salvaPessoa(Clientes);
        finish();
        onBackPressed();

    }


 //   public void botao_Salvar (View view){
 //      Emprego = new empregos();
 //       Emprego.setEstado(Estado.getText().toString());
 //       Emprego.setCidade(Cidade.getText().toString());
 //       Emprego.setPeriodo(Periodo.getText().toString());
 //       Emprego.setArea_da_profissao(Profissao.getText().toString());
 //       Emprego.setSalario(Salario.getText().toString());
 //       Emprego.setEmail(Email.getText().toString());
 //       empregos.salvar(Emprego);
 //       onBackPressed();
//
//
 //   }

}
