package com.example.approfisso.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class Principal extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore db;

    private TextView mTVEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        mTVEmail = findViewById(R.id.User_Email);
        mFirebaseAuth = FirebaseAuth.getInstance();



        mTVEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,cadastro_cliente_editar.class));
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
        mFirebaseAuth.signOut();
        onBackPressed();
    }

}