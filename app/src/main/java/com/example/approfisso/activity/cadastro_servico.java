package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Pessoa;
import com.example.approfisso.entidades.Servicos;
import com.google.firebase.database.DatabaseReference;


public class cadastro_servico extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nome;
    private EditText duracao;
    private EditText valor;
    private EditText funcao;
    private Servicos servicos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_cadastro);


        nome=findViewById(R.id.Nome_Servico);
        duracao=findViewById(R.id.Duracao_Servico);
        valor=findViewById(R.id.Preco_Servico);
        funcao=findViewById(R.id.Funcao_Servico);
        Intent i = getIntent();
        servicos =(Servicos) i.getSerializableExtra("Servicos");


    }


    public void lista_emprego(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }


    public void botao_retornar (View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }


    public void botao_Confirmar (View view){
        servicos = new Servicos();

        servicos.setNome_servico(nome.getText().toString());
        servicos.setValor_servico(valor.getText().toString());
        servicos.setDuracao_servico(duracao.getText().toString());
        servicos.setFuncao_servico(funcao.getText().toString());

        Servicos.salvaServicos(servicos);
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
