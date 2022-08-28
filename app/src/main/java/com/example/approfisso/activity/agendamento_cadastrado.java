package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Funcionario;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class agendamento_cadastrado extends AppCompatActivity {
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento_cadastrado);


//        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_agendamento_cadastrado);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Agendamento = new LinkedList<>();
        //chamada firebase
        listar_agendamento();

    }
    List<Agendamento> Agendamento;
    public void listar_agendamento()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Agendamento");
                Agendamento.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Agendamento agendamento = postSnapshot.getValue(Agendamento.class);
                    Agendamento.add(agendamento);

                }
                preenche_agendamento();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    agendamentoAdapter AgendamentoAdapter;
    private void preenche_agendamento() {
        AgendamentoAdapter= new agendamentoAdapter(Agendamento);
        recyclerView.setAdapter(AgendamentoAdapter);

    }

    public void botao_cadastrar_agendamento(View view){
        Intent it = new Intent(this, cadastro_agendamento.class);
        startActivity(it);
    }


    public void botao_retornar_busca (View view){
        finish();

    }

}
