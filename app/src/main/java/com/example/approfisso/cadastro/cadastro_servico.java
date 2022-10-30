package com.example.approfisso.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.cadastrado.cliente_cadastrado;
import com.example.approfisso.ediçao.cadastro_servico_editar;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Servicos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class cadastro_servico extends AppCompatActivity {


    DatabaseReference databaseReference;

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
    private EditText funcao;
    private Servicos servicos;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    private Button confirmar_servico;
    private Button cancelar_servico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_cadastro);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        spinner_funcao_servico = findViewById(R.id.spinner_funcao_servico);
        spinner_info_funcao_servico = FirebaseDatabase.getInstance().getReference("Funcao");
        spinner_lista_servico = new ArrayList<>();
        adapter = new ArrayAdapter<String>(cadastro_servico.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_servico);
        spinner_funcao_servico.setAdapter(adapter);
        Showdata();



        nome=findViewById(R.id.Nome_Servico);

        valor=findViewById(R.id.Preco_Servico);

        confirmar_servico = findViewById(R.id.botao_Confirmar_Servico);
        cancelar_servico = findViewById(R.id.Cancelar_Servico);

        Intent i = getIntent();
        servicos =(Servicos) i.getSerializableExtra("Servicos");

        spinner_agendamento_horario = findViewById(R.id.Duracao_Servico);
        spinner_info_agendamento_horario = FirebaseDatabase.getInstance().getReference("Duracao");
        spinner_lista_agendamento_horario = new ArrayList<>();
        adapter_agendamento_horario = new ArrayAdapter<String>(cadastro_servico.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_horario);
        spinner_agendamento_horario.setAdapter(adapter_agendamento_horario);
        Showdata_Horario();

        confirmar_servico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome_servico = nome.getText().toString().trim();

                String valor_servico = valor.getText().toString().trim();


                if(TextUtils.isEmpty(nome_servico)) {
                    nome.setError("Insira o nome do serviço.");
                    return;
                }

                if(TextUtils.isEmpty(valor_servico)) {
                    valor.setError("Insira o valor do serviço.");
                    return;
                }


                //registrando no firebase


                Toast.makeText(cadastro_servico.this, "Cadastro da função realizada.", Toast.LENGTH_SHORT).show();
                userID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("estabelecimento").document(userID).collection("serviço").document(userID);





                Map<String,Object> cadastro_funcao = new HashMap<>();
                cadastro_funcao.put("id_cadastro",userID);
                cadastro_funcao.put("id_serviço",documentReference.get().toString() );
                cadastro_funcao.put("nome",nome_servico);
                cadastro_funcao.put("duração",spinner_agendamento_horario.getSelectedItem().toString());
                cadastro_funcao.put("preço",valor_servico);
                cadastro_funcao.put("função,", spinner_funcao_servico.getSelectedItem().toString());

                documentReference.set(cadastro_funcao);

                servicos = new Servicos();

                servicos.setNome_servico(nome_servico);
                servicos.setValor_servico(valor_servico);
                servicos.setDuracao_servico(spinner_agendamento_horario.getSelectedItem().toString());
                servicos.setFuncao_servico(spinner_funcao_servico.getSelectedItem().toString());

                Servicos.salvaServicos(servicos);
                onBackPressed();






                onBackPressed();

            }
        });

        cancelar_servico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


//     mDataRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            list.clear();
//            for (DataSnapshot ds : snapshot.getChildren()) {
//                String name  = String.valueOf(ds.child(snapshot.getKey()).child("name"));
//                list.add(name);
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//        }
//    });

    List<Agendamento> agendamento;
    private void Showdata_Horario(){
        agendamento= new ArrayList<>();
        spinner_info_agendamento_horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

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



    public void lista_emprego(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }


    public void Botao_Cancelar_Servico (View view){
        super.onBackPressed();
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }


//    public void botao_Confirmar (View view){
//        servicos = new Servicos();
//
//        servicos.setNome_servico(nome.getText().toString());
//        servicos.setValor_servico(valor.getText().toString());
//        servicos.setDuracao_servico(duracao.getText().toString());
//        servicos.setFuncao_servico(funcao.getText().toString());
//        servicos.setFuncao_servico(spinner_funcao_servico.getSelectedItem().toString());
//
//        Servicos.salvaServicos(servicos);
//        onBackPressed();
//
//    }


}
