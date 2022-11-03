package com.example.approfisso.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.cadastrado.agendamento_cadastrado;
import com.example.approfisso.cadastrado.cliente_cadastrado;
import com.example.approfisso.cadastrado.funcao_cadastrado;
import com.example.approfisso.cadastrado.funcionario_agendamento_cadastrado;
import com.example.approfisso.cadastrado.funcionario_cadastrado;
import com.example.approfisso.cadastrado.servico_cadastrado;
import com.example.approfisso.ediçao.cadastro_cliente_editar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Principal extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore db;
    private TextView mTVEmail;
    private TextView pnt_cliente;
    private Button estabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        mTVEmail = findViewById(R.id.User_Email);
        pnt_cliente = findViewById(R.id.pontos_cliente);
        mFirebaseAuth = FirebaseAuth.getInstance();
        estabelecimento = findViewById(R.id.salao);


        mTVEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this, cadastro_cliente_editar.class));
            }
        });
        estabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this, funcionario_agendamento_cadastrado.class));
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        db.collection("usuários")
                .whereEqualTo("id",current).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(DocumentSnapshot document : task.getResult()){
                                mTVEmail.setText((CharSequence) document.get("nome")) ;
                                pnt_cliente.setText((CharSequence) document.get("pontos").toString());

                            }
                        }
                    }
                });
    }

    public void botao_cadastro_cliente(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }

    public void botao_cadastro_servico(View view){
        Intent it = new Intent(this, servico_cadastrado.class);
        startActivity(it);
    }

    public void botao_cadastro_funcionario(View view){
        Intent it = new Intent(this, funcionario_cadastrado.class);
        startActivity(it);
    }

    public void botao_cadastro_funcao(View view){
        Intent it = new Intent(this, funcao_cadastrado.class);
        startActivity(it);
    }

    public void botao_cadastro_agendamento(View view){
        Intent it = new Intent(this, agendamento_cadastrado.class);
        startActivity(it);
    }

    public void logout(View view){
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        clearToken(FirebaseAuth.getInstance().getCurrentUser().getUid());

        mFirebaseAuth.signOut();

        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
        //onBackPressed();
    }

    public void clearToken(String UserID){
        FirebaseDatabase.getInstance().getReference("tokens").child(UserID).removeValue();
    }
}