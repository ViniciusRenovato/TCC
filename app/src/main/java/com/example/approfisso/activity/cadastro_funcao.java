package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcao;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DatabaseReference;


public class cadastro_funcao extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nome;

    private Funcao Funcoes;


    private Button button_salvar_funcao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcao_cadastro);


        nome=findViewById(R.id.Nome_Funcao);
        Intent i = getIntent();
        Funcoes =(Funcao) i.getSerializableExtra("Funcao");

        button_salvar_funcao = findViewById(R.id.botao_Confirmar_Funcao);


        button_salvar_funcao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void  onClick(View view){


                Funcoes =new

                        Funcao();

                Funcoes.setNome_funcao(nome.getText().

                        toString());
                Funcao.salvaFuncao(Funcoes);

                onBackPressed();




            }

        });











    }


    public void lista_emprego(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }


    public void Botao_Cancelar_Funcao (View view){
        super.onBackPressed();
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }


    public void botao_Confirmar (View view){

                Funcoes =new

                        Funcao();

                Funcoes.setNome_funcao(nome.getText().

                        toString());
                Funcao.salvaFuncao(Funcoes);

        super.onBackPressed();
        finish();



            }







    public void botao_Remover_Funcao (View view){

        Funcoes = new Funcao();
        Funcao.excluirFuncao(Funcoes);

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
