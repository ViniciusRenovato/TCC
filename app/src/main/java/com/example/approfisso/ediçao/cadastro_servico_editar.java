package com.example.approfisso.ediçao;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cadastro_servico_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    Spinner spinner_editar_funcao_servico;
    DatabaseReference spinner_info_funcao_servico;
    ArrayList<String> spinner_lista_servico;
    ArrayAdapter<String> adapter;

    private String servico_editar_id;
    private String servico_editar_duracao;
    private String servico_editar_valor;
    private String servico_editar_funcao;
    private String servico_editar_nome;
    private Integer pontos_servico;

    private double resultado;

    private String funcaoID;
    private EditText nome_servico;
    private EditText duracao_servico;
    private EditText valor_servico;
    private EditText funcao_servico;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    Spinner spinner_agendamento_horario;
    DatabaseReference spinner_info_agendamento_horario;
    ArrayList<String> spinner_lista_agendamento_horario;
    ArrayAdapter<String> adapter_agendamento_horario;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_servico_popup);

        servico_editar_id = getIntent().getStringExtra("servicoid");
        servico_editar_nome = getIntent().getStringExtra("serviconome");
        servico_editar_duracao = getIntent().getStringExtra("servicoduracao");
        servico_editar_valor = getIntent().getStringExtra("servicovalor");
        servico_editar_funcao = getIntent().getStringExtra("servicofuncao");

        spinner_editar_funcao_servico = findViewById(R.id.Spinner_Editar_Funcao_Servico);
        spinner_info_funcao_servico = FirebaseDatabase.getInstance().getReference("Funcao");
        spinner_lista_servico = new ArrayList<>();
        adapter = new ArrayAdapter<String>(cadastro_servico_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_servico);
        spinner_editar_funcao_servico.setAdapter(adapter);
        Showdata();

        nome_servico=findViewById(R.id.Editar_Nome_Servico);
        valor_servico=findViewById(R.id.Editar_Valor_Servico);

        nome_servico.setText(servico_editar_nome);
        valor_servico.setText(servico_editar_valor);

        spinner_agendamento_horario = findViewById(R.id.Editar_Duracao_Servico);
        spinner_info_agendamento_horario = FirebaseDatabase.getInstance().getReference("Duracao");
        spinner_lista_agendamento_horario = new ArrayList<>();
        adapter_agendamento_horario = new ArrayAdapter<String>(cadastro_servico_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_horario);
        spinner_agendamento_horario.setAdapter(adapter_agendamento_horario);
        Showdata_Horario();

        Button funcao_editar = findViewById(R.id.botao_Confirmar_Editar_Funcionario);

        funcao_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome_servico=findViewById(R.id.Editar_Nome_Servico);
                valor_servico=findViewById(R.id.Editar_Valor_Servico);
//                Integer pontos = Integer.valueOf(valor_servico.getText().toString()) /5;
//                pontos_servico = pontos;

                String nome_servico_editar = nome_servico.getText().toString().trim();
                String valor_servico_editar = valor_servico.getText().toString().trim();
                String horario_servico = spinner_agendamento_horario.getSelectedItem().toString().trim();
                String funcao_servico = spinner_editar_funcao_servico.getSelectedItem().toString().trim();

                double valores = Double.parseDouble(valor_servico_editar);
//                double base_pontos = 5;
                resultado = valores/5;

//                spinner_editar_funcao_servico = findViewById(R.id.Spinner_Editar_Funcao_Servico);

                if(TextUtils.isEmpty(nome_servico_editar)) {
                    nome_servico.setError("Insira o nome do serviço.");
                    return;
                }

                if (horario_servico.matches("--Selecione--")){

                    TextView errorText = (TextView)spinner_agendamento_horario.getSelectedView();
                    errorText.setError("Insira uma função valida");
                    return;

                }

                if(TextUtils.isEmpty(valor_servico_editar)) {
                    valor_servico.setError("Insira o valor do serviço.");
                    return;
                }

                if (funcao_servico.matches("--Selecione--")){

                    TextView errorText = (TextView)spinner_editar_funcao_servico.getSelectedView();
                    errorText.setError("Insira uma função valida");
                    return;

                }




                updateData();
            }
        });

        Button funcao_editar_voltar = findViewById(R.id.Voltar_cadastro_Editar_Funcionario);

        funcao_editar_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void updateData(){



        HashMap servico = new HashMap();
        servico.put("nome_servico",nome_servico.getText().toString());
        servico.put("duracao_servico",spinner_agendamento_horario.getSelectedItem().toString());
        servico.put("valor_servico",valor_servico.getText().toString());
        servico.put("funcao_servico",spinner_editar_funcao_servico.getSelectedItem().toString());
        servico.put("pontos_servico",resultado);

        databaseReference = FirebaseDatabase.getInstance().getReference("Servicos");
        databaseReference.child(servico_editar_id).updateChildren(servico).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()){

                    Toast.makeText(cadastro_servico_editar.this,"funcionario editado com sucesso.",Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }else{

                    Toast.makeText(cadastro_servico_editar.this,"falha na edição",Toast.LENGTH_SHORT).show();

                }

            }
        });


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

    private void Showdata(){

        spinner_info_funcao_servico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                spinner_lista_servico.clear();
                spinner_lista_servico.add("--Selecione--");

                for (DataSnapshot item : snapshot.getChildren()) {

                    String teste = item.child("nome_funcao").getValue(String.class);
                    spinner_lista_servico.add(teste);

//                    spinner_lista_servico.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
