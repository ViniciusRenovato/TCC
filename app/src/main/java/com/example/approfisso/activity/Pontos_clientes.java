package com.example.approfisso.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.adapter.agendamentofuncionarioAdapter;
import com.example.approfisso.adapter.pontosclientesAdapter;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pontos_clientes extends AppCompatActivity {

    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String login_cliente;
    private String ID_funcionario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_pontuados);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        login_cliente = current;
        ID_funcionario = getIntent().getStringExtra("funcionarioid");

        //        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_pontos_clientes);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Usuario = new LinkedList<>();
        //chamada firebase
        listar_ponto_usuario();

   }

    List<Usuario> Usuario;
    public void listar_ponto_usuario()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("usuários");
                Usuario.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Usuario usuario = postSnapshot.getValue(Usuario.class);
                    Integer pontos_do_cliente = Integer.parseInt(postSnapshot.child("pontos_usuario").getValue().toString());

                    if (pontos_do_cliente >= 20){

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
    pontosclientesAdapter PontosclienteAdapter;
    private void preenche_lista_ponto() {
        PontosclienteAdapter= new pontosclientesAdapter(Usuario);
        recyclerView.setAdapter(PontosclienteAdapter);
    }


}
