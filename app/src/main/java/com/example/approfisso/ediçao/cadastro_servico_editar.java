package com.example.approfisso.ediçao;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class cadastro_servico_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    Spinner spinner_editar_funcao_servico;
    DatabaseReference spinner_info_funcao_servico;
    ArrayList<String> spinner_lista_servico;
    ArrayAdapter<String> adapter;


    private String funcaoID;
    private EditText nome_servico;
    private EditText duracao_servico;
    private EditText valor_servico;
    private EditText funcao_servico;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_servico_popup);

        spinner_editar_funcao_servico = findViewById(R.id.Spinner_Editar_Funcao_Servico);

        spinner_info_funcao_servico = FirebaseDatabase.getInstance().getReference("Funcao");

        spinner_lista_servico = new ArrayList<>();
        adapter = new ArrayAdapter<String>(cadastro_servico_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_servico);

        spinner_editar_funcao_servico.setAdapter(adapter);
        Showdata();


        nome_servico=findViewById(R.id.Editar_Nome_Funcao);
        duracao_servico=findViewById(R.id.Editar_Nome_Funcao);
        valor_servico=findViewById(R.id.Editar_Nome_Funcao);


        Button funcao_editar = findViewById(R.id.botao_Confirmar_Editar_Funcao);

    }

    private void Showdata(){

        spinner_info_funcao_servico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {

                    String teste = item.child("nome_funcao").getValue(String.class);
                    spinner_lista_servico.add(teste);

//                    spinner_lista_servico.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
