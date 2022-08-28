package com.example.approfisso.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;






public class MainActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void botao_cadastro_cliente(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }

    public void botao_cadastro_servico(View view){
        Intent it = new Intent(this, servico_cadastrado.class);
        startActivity(it);
    }

    public void botao_cadastro_funcionario(View view){
        Intent it = new Intent(this, funcionario_cadastrado.class);
        startActivity(it);
    }

    public void botao_cadastro_funcao(View view){
        Intent it = new Intent(this, funcao_cadastrado.class);
        startActivity(it);
    }
    public void botao_cadastro_agendamento(View view){
        Intent it = new Intent(this, agendamento_cadastrado.class);
        startActivity(it);
    }

//    public void botao_procura(View view){
//        Intent it = new Intent(this, busca_menu.class);
//        startActivity(it);
//    }

    public void botao_sair (View view){
        finish();

    }

}