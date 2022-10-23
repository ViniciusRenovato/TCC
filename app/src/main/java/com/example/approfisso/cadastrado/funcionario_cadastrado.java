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
import com.example.approfisso.adapter.funcionarioAdapter;
import com.example.approfisso.cadastro.cadastro_funcionario;
import com.example.approfisso.entidades.Funcionario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class funcionario_cadastrado extends AppCompatActivity {

    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;



    DataFirebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcionario_cadastrado);


//        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_funcionario_cadastrado);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Funcionario= new LinkedList<>();
        //chamada firebase
        listar_funcionario();

    }



    List<Funcionario> Funcionario;
    public void listar_funcionario()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Funcionario");
                Funcionario.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Funcionario funcionario = postSnapshot.getValue(Funcionario.class);
                    Funcionario.add(funcionario);

                }
                preenche_funcionario();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    funcionarioAdapter FuncionarioAdapter;
    private void preenche_funcionario() {
        FuncionarioAdapter= new funcionarioAdapter(Funcionario);
        recyclerView.setAdapter(FuncionarioAdapter);

    }

    public void botao_cadastrar_funcionario(View view){
        Intent it = new Intent(this, cadastro_funcionario.class);
        startActivity(it);
    }


    public void botao_retornar_busca (View view){
        finish();

    }

}
