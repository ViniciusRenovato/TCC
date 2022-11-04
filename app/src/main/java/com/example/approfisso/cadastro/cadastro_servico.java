package com.example.approfisso.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Servicos;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cadastro_servico extends AppCompatActivity {

    Spinner spinner_funcao_servico;
    DatabaseReference spinner_info_funcao_servico;
    ArrayList<String> spinner_lista_servico;
    ArrayAdapter<String> adapter;

    Spinner spinner_agendamento_horario;
    DatabaseReference spinner_info_agendamento_horario;
    ArrayList<String> spinner_lista_agendamento_horario;
    ArrayAdapter<String> adapter_agendamento_horario;

    private EditText nome;
    private EditText valor;
    private Servicos servicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_cadastro);

        spinner_funcao_servico = findViewById(R.id.spinner_funcao_servico);
        spinner_info_funcao_servico = FirebaseDatabase.getInstance().getReference("Funcao");
        spinner_lista_servico = new ArrayList<>();
        adapter = new ArrayAdapter<>(cadastro_servico.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_servico);
        spinner_funcao_servico.setAdapter(adapter);
        Showdata();

        spinner_agendamento_horario = findViewById(R.id.Duracao_Servico);
        spinner_info_agendamento_horario = FirebaseDatabase.getInstance().getReference("Duracao");
        spinner_lista_agendamento_horario = new ArrayList<>();
        adapter_agendamento_horario = new ArrayAdapter<>(cadastro_servico.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_horario);
        spinner_agendamento_horario.setAdapter(adapter_agendamento_horario);
        Showdata_Horario();

        nome=findViewById(R.id.Nome_Servico);
        valor=findViewById(R.id.Preco_Servico);

        Button confirmar_servico = findViewById(R.id.botao_Confirmar_Servico);
        Button cancelar_servico = findViewById(R.id.Cancelar_Servico);

        Intent i = getIntent();
        servicos =(Servicos) i.getSerializableExtra("Servicos");


        confirmar_servico.setOnClickListener(view -> {

            String nome_servico = nome.getText().toString().trim();
            String valor_servico = valor.getText().toString().trim();
            String horario_servico = spinner_agendamento_horario.getSelectedItem().toString().trim();
            String funcao_servico = spinner_funcao_servico.getSelectedItem().toString().trim();

            if(TextUtils.isEmpty(nome_servico))
            {
                nome.setError("Insira o nome do serviço.");
                return;
            }

            if (horario_servico.matches("--Selecione--"))
            {

                TextView errorText = (TextView)spinner_agendamento_horario.getSelectedView();
                errorText.setError("Insira uma função valida");
                return;
            }

            if(TextUtils.isEmpty(valor_servico))
            {
                valor.setError("Insira o valor do serviço.");
                return;
            }

            if (funcao_servico.matches("--Selecione--"))
            {
                TextView errorText = (TextView)spinner_funcao_servico.getSelectedView();
                errorText.setError("Insira uma função valida");
                return;
            }

            double valores = Double.parseDouble(valor_servico);
//                double base_pontos = 5;
            double resultado = valores/5;

            //registrando no firebase

            servicos = new Servicos();
            servicos.setNome_servico(nome_servico);
            servicos.setValor_servico(valor_servico);
            servicos.setDuracao_servico(spinner_agendamento_horario.getSelectedItem().toString());
            servicos.setFuncao_servico(spinner_funcao_servico.getSelectedItem().toString());
            servicos.setPontos_servico((int) resultado);

            Servicos.salvaServicos(servicos);
            Toast.makeText(cadastro_servico.this, "Cadastro da função realizada.", Toast.LENGTH_SHORT).show();

            onBackPressed();
        });

        cancelar_servico.setOnClickListener(view -> onBackPressed());
    }

    List<Agendamento> agendamento;
    private void Showdata_Horario(){
        agendamento= new ArrayList<>();
        spinner_info_agendamento_horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinner_lista_agendamento_horario.clear();
                spinner_lista_agendamento_horario.add("--Selecione--");
                for (DataSnapshot item : snapshot.getChildren()) {
                    Agendamento agendar = new Agendamento();

                    agendar.setHora_agendamento(item.getValue(String.class));

                    spinner_lista_agendamento_horario.add(agendar.getHora_agendamento());
                    agendamento.add(agendar);
                }
                adapter_agendamento_horario.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void Showdata()
    {
        spinner_info_funcao_servico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinner_lista_servico.clear();
                spinner_lista_servico.add("--Selecione--");
                for (DataSnapshot item : snapshot.getChildren()) {

                    String teste = item.child("nome_funcao").getValue(String.class);
                    spinner_lista_servico.add(teste);

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void Botao_Cancelar_Servico (View view){
        super.onBackPressed();
    }
}
