package com.example.approfisso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcao;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.LinkedList;
import java.util.List;


public class funcao_cadastrado extends AppCompatActivity {

    DatabaseReference databaseReference;

    FirebaseFirestore fstore;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Funcao Funcoes_delete;


    DataFirebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcao_cadastrado);


//        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_funcao_cadastrado);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Funcoes= new LinkedList<>();
        //chamada firebase
        listar_funcao();









    }



    List<Funcao> Funcoes;
    public void listar_funcao()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Funcao");
                Funcoes.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Funcao funcao = postSnapshot.getValue(Funcao.class);
                    Funcoes.add(funcao);

                }
                preenche_funcao();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    funcaoAdapter FuncaoAdapter;
    private void preenche_funcao() {
        FuncaoAdapter= new funcaoAdapter(Funcoes);
        recyclerView.setAdapter(FuncaoAdapter);

    }

    public void botao_oferece(View view){
        Intent it = new Intent(this, CadastroFuncaoActivity.class);
        startActivity(it);
    }


    public void botao_Remover_Funcao (View view){

        Funcoes_delete = new Funcao();
        Funcao.excluirFuncao(Funcoes_delete);

        onBackPressed();

    }





    public void botao_retornar_busca (View view){
        finish();

    }

}
