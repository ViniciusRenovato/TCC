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
import com.example.approfisso.adapter.listaclientesAdapter;
import com.example.approfisso.adapter.pontosclientesAdapter;
import com.example.approfisso.classes.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class Lista_Cliente extends AppCompatActivity {

    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String login_cliente;
    private String ID_funcionario;

    private Button pts_clientes;
    private Button retornar_de_lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_lista);

    pts_clientes = findViewById(R.id.button_resgate_de_pontos);
    retornar_de_lista = findViewById(R.id.button_retornar_de_lista_cliente);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        login_cliente = current;
        ID_funcionario = getIntent().getStringExtra("funcionarioid");

        //        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_listagem_clientes);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Usuario = new LinkedList<>();
        //chamada firebase
        listar_lista_usuario();

        pts_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Lista_Cliente.this,Pontos_clientes.class));
            }
        });

        retornar_de_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

   }

    List<Usuario> Usuario;
    public void listar_lista_usuario()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("usuários");
                Usuario.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Usuario usuario = postSnapshot.getValue(Usuario.class);



                    String tipo_do_cliente = postSnapshot.child("tipo_usuario").getValue().toString();

                    if (tipo_do_cliente.equals("cliente")){

                        Usuario.add(usuario);

                    }










                }
                preenche_lista_ponto();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    listaclientesAdapter ListaclienteAdapter;
    private void preenche_lista_ponto() {
        ListaclienteAdapter= new listaclientesAdapter(Usuario);
        recyclerView.setAdapter(ListaclienteAdapter);
    }


}
