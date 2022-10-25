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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

    private EditText nome_funcao;

    private String funcao_editar_id;
    private String funcao_editar_nome;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_funcao_popup);

        nome_funcao = findViewById(R.id.Editar_Nome_Funcao);

        funcao_editar_id = getIntent().getStringExtra("funcaoid");
        funcao_editar_nome = getIntent().getStringExtra("funcaonome");

        nome_funcao.setText(funcao_editar_nome);

        Button funcaoeditar = findViewById(R.id.botao_Confirmar_Editar_Funcao);

        funcaoeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome_funcao = findViewById(R.id.Editar_Nome_Funcao);

                updateData();
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

    private void updateData(){

        HashMap funcao = new HashMap();
        funcao.put("nome_funcao",nome_funcao.getText().toString());

        databaseReference = FirebaseDatabase.getInstance().getReference("Funcao");
        databaseReference.child(funcao_editar_id).updateChildren(funcao).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){

                    Toast.makeText(cadastro_funcao_editar.this,"função editada.",Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }else{
                    Toast.makeText(cadastro_funcao_editar.this,"falha na edição",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
