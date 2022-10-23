package com.example.approfisso.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.adapter.pessoaAdapter;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;




public class busca_menu extends AppCompatActivity {


    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busca_menu);



        //recycle view
        recyclerView=findViewById(R.id.lista_emprego_buscado);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Pessoas= new LinkedList<>();
        //chamada firebase
        listar();


    }

//    List<empregos> Empregos;
//    public void listar(){
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                DataSnapshot dataSnapshot = snapshot.child("Emprego");
//                Empregos.clear();
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    empregos Emprego = postSnapshot.getValue(empregos.class);
//                    Empregos.add(Emprego);
//
//                }
//                preenche();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    List<Pessoa> Pessoas;
    public void listar(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Pessoas");
                Pessoas.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Pessoa pessoa = postSnapshot.getValue(Pessoa.class);
                    Pessoas.add(pessoa);

                }
                preenche();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


//    EmpregoAdapter empregosAdapter;
//    private void preenche() {
//        empregosAdapter= new EmpregoAdapter(Empregos);
//        recyclerView.setAdapter(empregosAdapter);
//
//    }


    pessoaAdapter PessoaAdapter;
    private void preenche() {
        PessoaAdapter= new pessoaAdapter(Pessoas);
        recyclerView.setAdapter(PessoaAdapter);

    }








    public void botao_retornar_busca(View view){

        finish();
    }

    public void botao_buscar(View view){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
       /// lista.setLayoutManager(layoutManager);
        findViewById(R.id.lista_emprego_buscado);


        finish();

    }
}
