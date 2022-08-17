package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import com.example.approfisso.classes.empregos;



public class emprego_oferecido extends AppCompatActivity {

    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

//    private List<empregos> dados;
//
//    private ListView lista;

    DataFirebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emprego_oferecido);


//        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_emprego_oferecido);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Pessoas= new LinkedList<>();
        //chamada firebase
        listar();


//        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent it = new Intent(getApplicationContext(),empregos.class);
//                //it.putExtra("Produto", (Produto)Dados.getLista().get(i));
//                it.putExtra("empregos", dados.get(i));
//
//                // Produto o=(Produto)Dados.getLista().get(i);
//               /* it.putExtra("nome",o.getNome());
//                it.putExtra("quatidade",o.getQuantidade());*/
//
//            }
//        });
//
//        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                db.remover(dados.get(i));
//
//
//                return true;
//            }
//        });
    }

 /  List<empregos> Empregos;
    public void listar(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Emprego");
                Empregos.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    empregos Emprego = postSnapshot.getValue(empregos.class);
                    Empregos.add(Emprego);

                }
                preenche();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    List<Pessoa> Pessoas;
    public void listar(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Pessoas");
                Pessoas.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Pessoa Pessoas = postSnapshot.getValue(Pessoa.class);
                    Pessoas.add(Pessoas);

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

//    }





    public void botao_oferece(View view){
        Intent it = new Intent(this, empresa_oferece.class);
        startActivity(it);
    }


    public void botao_retornar_busca (View view){
        finish();

    }



}
