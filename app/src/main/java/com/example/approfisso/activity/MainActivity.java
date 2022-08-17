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

    public void botao_oferece(View view){
        Intent it = new Intent(this, empresa_oferece.class);
        startActivity(it);
    }

    public void botao_procura(View view){
        Intent it = new Intent(this, busca_menu.class);
        startActivity(it);
    }

    public void botao_sair (View view){
        finish();

    }

}