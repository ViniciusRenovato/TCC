package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Pessoa;
import com.example.approfisso.entidades.Servicos;
import com.example.approfisso.entidades.Funcao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class cadastro_servico extends AppCompatActivity {

    DatabaseReference databaseReference;

    Spinner spinner_funcao_servico;
    DatabaseReference spinner_info_funcao_servico;
    ArrayList<String> spinner_lista_servico;
    ArrayAdapter<String> adapter;


    private EditText nome;
    private EditText duracao;
    private EditText valor;
    private EditText funcao;
    private Servicos servicos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_cadastro);


        spinner_funcao_servico = findViewById(R.id.spinner_funcao_servico);

        spinner_info_funcao_servico = FirebaseDatabase.getInstance().getReference("Funcao");

        spinner_lista_servico = new ArrayList<>();
        adapter = new ArrayAdapter<String>(cadastro_servico.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_servico);


        spinner_funcao_servico.setAdapter(adapter);
        Showdata();



        nome=findViewById(R.id.Nome_Servico);
        duracao=findViewById(R.id.Duracao_Servico);
        valor=findViewById(R.id.Preco_Servico);
        funcao=findViewById(R.id.Funcao_Servico);
        Intent i = getIntent();
        servicos =(Servicos) i.getSerializableExtra("Servicos");


    }

    private void Showdata(){

        spinner_info_funcao_servico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {
                    spinner_lista_servico.add(item.getValue().toString());
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


    public void botao_retornar (View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }


    public void botao_Confirmar (View view){
        servicos = new Servicos();

        servicos.setNome_servico(nome.getText().toString());
        servicos.setValor_servico(valor.getText().toString());
        servicos.setDuracao_servico(duracao.getText().toString());
//        servicos.setFuncao_servico(funcao.getText().toString());
        servicos.setFuncao_servico(spinner_funcao_servico.getSelectedItem().toString());

        Servicos.salvaServicos(servicos);
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
