package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcionario;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DatabaseReference;


public class cadastro_funcionario extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nome;
    private EditText sobrenome;
    private EditText funcao;
    private EditText telefone;
    private EditText aniversario;
    private EditText email;
    private Funcionario Funcionarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcionario_cadastro);


        nome=findViewById(R.id.Nome_Funcionario);
        sobrenome=findViewById(R.id.Sobrenome_Funcionario);
        funcao=findViewById(R.id.Funcao_Funcionario);
        telefone=findViewById(R.id.Telefone_Funcionario);
        aniversario=findViewById(R.id.Aniversario_Funcionario);
        email=findViewById(R.id.Email_Funcionario);
        Intent i = getIntent();
        Funcionarios =(Funcionario) i.getSerializableExtra("Funcionario");


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
        Funcionarios = new Funcionario();
        Funcionarios.setNome_funcionario(nome.getText().toString());
        Funcionarios.setSobrenome_funcionario(sobrenome.getText().toString());
        Funcionarios.setFuncao_funcionario(funcao.getText().toString());
        Funcionarios.setTelefone_funcionario(telefone.getText().toString());
        Funcionarios.setAniversario_funcionario(aniversario.getText().toString());
        Funcionarios.setEmail_funcionario(email.getText().toString());
        Funcionario.salvaFuncionario(Funcionarios);
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
