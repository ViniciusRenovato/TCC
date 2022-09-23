package com.example.approfisso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.activity.Principal;
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


        nome=findViewById(R.id.Nome_Cliente);
        sobrenome=findViewById(R.id.Sobrenome_Cliente);
        telefone=findViewById(R.id.Telefone_Cliente);
        aniversario=findViewById(R.id.Aniversario_Cliente);
        email=findViewById(R.id.Email_Cliente);
        Intent i = getIntent();
        Pessoas =(Pessoa) i.getSerializableExtra("Pessoa");


    }

    public void botao_Cancelar (View view){

        Intent it = new Intent(this, Principal.class);
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