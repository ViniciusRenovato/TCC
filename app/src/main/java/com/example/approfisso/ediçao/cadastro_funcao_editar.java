package com.example.approfisso.ediçao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cadastro_funcao_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    List<Funcao> dados;



    private String funcaoID;
    private EditText nome_funcao;

    private String funcao_editar;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private Funcao funcao;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_funcao_popup);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Funcao");







        nome_funcao = findViewById(R.id.Editar_Nome_Funcao);

        funcao_editar = getIntent().getStringExtra("funcaoid");




        nome_funcao.setText(funcao_editar);







        //        Intent id_funcao = getIntent();


//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//
//                   nome_funcao.setText(snapshot.child("nome_funcao").getValue().toString());
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseDatabase teste = FirebaseDatabase.getInstance().getReference().child("Funcao")
//                .child();






        nome_funcao=findViewById(R.id.Editar_Nome_Funcao);




Button funcaoeditar = findViewById(R.id.botao_Confirmar_Editar_Funcao);

       funcaoeditar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {



           }
       });


        Button funcaoeditarvoltar = findViewById(R.id.Voltar_cadastro_Editar_Funcao);

        funcaoeditarvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }
}
