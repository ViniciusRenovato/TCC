package com.example.approfisso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.approfisso.classes.empregos;

public class Cadastro extends AppCompatActivity {
    private TextView Estado;
    private TextView Cidade;
    private TextView Periodo;
    private TextView Area_Da_Profissao;
    private TextView Salario;
    private TextView Email;
    private empregos Emprego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emprego_oferecido);
        Estado=findViewById(R.id.TextOfereceEstado);
        Cidade=findViewById(R.id.TextOfereceCidade);
        Periodo=findViewById(R.id.TextOferecePeriodo);
        Area_Da_Profissao=findViewById(R.id.TextOfereceProfissao);
        Salario=findViewById(R.id.TextOfereceSalario);
        Email=findViewById(R.id.TextOfereceEmail);
        Intent i = getIntent();
        Emprego= (empregos) i.getSerializableExtra("Emprego");
    }

}