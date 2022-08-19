package com.example.approfisso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.activity.MainActivity;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DatabaseReference;

public class Cadastro_cliente extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nome;
    private EditText sobrenome;
    private EditText telefone;
    private EditText aniversario;
    private EditText email;
    private Pessoa Pessoas;


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

    public void botao_Cancelar (View view){

        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);

    }


    public void botao_Confirmar (View view){
        Pessoas = new Pessoa();
        Pessoas.setNome(nome.getText().toString());
        Pessoas.setSobrenome(sobrenome.getText().toString());
        Pessoas.setTelefone(telefone.getText().toString());
        Pessoas.setAniversario(aniversario.getText().toString());
        Pessoas.setEmail(email.getText().toString());
        Pessoa.salvaPessoa(Pessoas);
        onBackPressed();


    }





}