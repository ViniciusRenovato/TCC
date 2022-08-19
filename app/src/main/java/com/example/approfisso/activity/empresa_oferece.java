package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

import com.example.approfisso.R;
import com.example.approfisso.classes.empregos;



public class empresa_oferece extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nome;
    private EditText sobrenome;
    private EditText telefone;
    private EditText aniversario;
    private EditText email;
    private Pessoa Pessoas;




//    private EditText Estado;
//    private EditText Cidade;
//    private EditText Periodo;
//    private EditText Profissao;
//    private EditText Salario;
//    private EditText Email;
//    private empregos Emprego;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_cadastro);


        nome=findViewById(R.id.Nome_cadastro);
        sobrenome=findViewById(R.id.Sobrenome_cadastro);
        telefone=findViewById(R.id.Telefone_cadastro);
        aniversario=findViewById(R.id.Data_cadastro);
        email=findViewById(R.id.Email_cadastro);
        Intent i = getIntent();
        Pessoas =(Pessoa) i.getSerializableExtra("Pessoa");


    }




  //  @Override
  //  protected void onCreate(Bundle savedInstanceState) {
  //      super.onCreate(savedInstanceState);
   //     setContentView(R.layout.empresa_oferece);
//
     //   Estado=findViewById(R.id.TextOfereceEstado);
    //    Cidade=findViewById(R.id.TextOfereceCidade);
     //   Periodo=findViewById(R.id.TextOferecePeriodo);
     //   Profissao=findViewById(R.id.TextOfereceProfissao);
      //  Salario=findViewById(R.id.TextOfereceSalario);
   //     Email=findViewById(R.id.TextOfereceEmail);
    //    Intent i = getIntent();
  //      Emprego= (empregos) i.getSerializableExtra("Empregos");
//
//
 //   }

    public void lista_emprego(View view){
        Intent it = new Intent(this, emprego_oferecido.class);
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
        Pessoas = new Pessoa();
        Pessoas.setNome(nome.getText().toString());
        Pessoas.setSobrenome(sobrenome.getText().toString());
        Pessoas.setTelefone(telefone.getText().toString());
        Pessoas.setAniversario(aniversario.getText().toString());
        Pessoas.setEmail(email.getText().toString());
        Pessoa.salvaPessoa(Pessoas);
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
