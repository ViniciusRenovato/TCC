package com.example.approfisso.cadastrado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.adapter.servicoAdapter;
import com.example.approfisso.cadastro.cadastro_servico;
import com.example.approfisso.entidades.Servicos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class servico_cadastrado extends AppCompatActivity {

    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;



    DataFirebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_cadastrado);


//        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_servicos_cadastrados);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Servico= new LinkedList<>();
        //chamada firebase
        listar_servico();

    }



    List<Servicos> Servico;
    public void listar_servico()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataSnapshot dataSnapshot = snapshot.child("Servicos");
                Servico.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Servicos servico = postSnapshot.getValue(Servicos.class);
                    Servico.add(servico);

                }
                preenche_servico();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    servicoAdapter ServicoAdapter;
    private void preenche_servico() {
        ServicoAdapter= new servicoAdapter(Servico);
        recyclerView.setAdapter(ServicoAdapter);

    }

    public void botao_oferece_servico(View view){
        Intent it = new Intent(this, cadastro_servico.class);
        startActivity(it);
    }


    public void botao_retornar_busca (View view){
        finish();

    }

}
