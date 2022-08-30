package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Funcionario;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cadastro_agendamento extends AppCompatActivity {
    DatabaseReference databaseReference;

    Spinner spinner_funcao_agendamento_funcionario;
    DatabaseReference spinner_info_funcao_agendamento_funcionario;
    ArrayList<String> spinner_lista_agendamento_funcionario;
    ArrayAdapter<String> adapter_agendamento_funcionario;

    Spinner spinner_funcao_agendamento_servico;
    DatabaseReference spinner_info_funcao_agendamento_servico;
    ArrayList<String> spinner_lista_agendamento_servico;
    ArrayAdapter<String> adapter_agendamento_servico;


    private EditText hora;
    private EditText dia;
    private Agendamento Agendamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento_cadastro);

        spinner_funcao_agendamento_funcionario = findViewById(R.id.spinner_funcao_agendamento_funcionario);
        spinner_info_funcao_agendamento_funcionario = FirebaseDatabase.getInstance().getReference("Funcionario");
        spinner_lista_agendamento_funcionario = new ArrayList<>();
        adapter_agendamento_funcionario = new ArrayAdapter<String>(cadastro_agendamento.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_funcionario);
        spinner_funcao_agendamento_funcionario.setAdapter(adapter_agendamento_funcionario);
        Showdata_Funcionario();

        spinner_funcao_agendamento_servico = findViewById(R.id.spinner_funcao_agendamento_servico);
        spinner_info_funcao_agendamento_servico = FirebaseDatabase.getInstance().getReference("Servicos");
        spinner_lista_agendamento_servico = new ArrayList<>();
        adapter_agendamento_servico = new ArrayAdapter<String>(cadastro_agendamento.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_servico);
        spinner_funcao_agendamento_servico.setAdapter(adapter_agendamento_servico);
        Showdata_Servico();



        hora=findViewById(R.id.Hora_Agendamento);
        dia=findViewById(R.id.Dia_Agendamento);
        Intent i = getIntent();
        Agendamentos =(Agendamento) i.getSerializableExtra("Agendamento");

    }

    private void Showdata_Funcionario(){

        spinner_info_funcao_agendamento_funcionario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {

                    String nome_do_funcionario = item.child("nome_funcionario").getValue(String.class);
                    spinner_lista_agendamento_funcionario.add(nome_do_funcionario);

//                    spinner_lista_agendamento_funcionario.add(item.getValue().toString());
                }
                adapter_agendamento_funcionario.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void Showdata_Servico(){

        spinner_info_funcao_agendamento_servico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {

                    String nome_do_servico = item.child("nome_servico").getValue(String.class);
                    spinner_lista_agendamento_servico.add(nome_do_servico);

//                    spinner_lista_agendamento_funcionario.add(item.getValue().toString());
                }
                adapter_agendamento_servico.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




    public void botao_Confirmar (View view){
        Agendamentos = new Agendamento();
        Agendamentos.setHora_agendamento(hora.getText().toString());
        Agendamentos.setDia_agendamento(dia.getText().toString());
        Agendamentos.setFuncionario(spinner_funcao_agendamento_funcionario.getSelectedItem().toString());
        Agendamentos.setServicos(spinner_funcao_agendamento_servico.getSelectedItem().toString());
        Agendamento.salvaAgendamento(Agendamentos);
        finish();
        onBackPressed();

    }

    public void Botao_Cancelar_Agendamento (View view){
        super.onBackPressed();
        finish();
    }

}
