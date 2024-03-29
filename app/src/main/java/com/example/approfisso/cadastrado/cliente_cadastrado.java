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
import com.example.approfisso.adapter.pessoaAdapter;
import com.example.approfisso.cadastro.cadastro_cliente;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class cliente_cadastrado extends AppCompatActivity {

    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;



    DataFirebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_cadastrado);


//        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_cliente_cadastrado);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Pessoas= new LinkedList<>();
        //chamada firebase
        listar_pessoa_cliente();

    }



    List<Pessoa> Pessoas;
    public void listar_pessoa_cliente()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Pessoa");
                Pessoas.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Pessoa pessoa = postSnapshot.getValue(Pessoa.class);
                    Pessoas.add(pessoa);

                }
                preenche_cliente();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    pessoaAdapter PessoaAdapter;
    private void preenche_cliente() {
        PessoaAdapter= new pessoaAdapter(Pessoas);
        recyclerView.setAdapter(PessoaAdapter);

    }

    public void botao_oferece(View view){
        Intent it = new Intent(this, cadastro_cliente.class);
        startActivity(it);
    }


    public void botao_retornar_busca (View view){
        finish();

    }

}
