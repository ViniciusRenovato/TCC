package com.example.approfisso.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.cadastrado.agendamento_cadastrado;
import com.example.approfisso.cadastrado.cliente_cadastrado;
import com.example.approfisso.cadastrado.funcao_cadastrado;
import com.example.approfisso.cadastrado.funcionario_agendamento_cadastrado;
import com.example.approfisso.cadastrado.funcionario_cadastrado;
import com.example.approfisso.cadastrado.servico_cadastrado;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.ediçao.cadastro_cliente_editar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


public class Principal_Cliente extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private String pontos_usuario;
    private String nome_usuario;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore db;
    private TextView mTVEmail;
    private TextView pnt_cliente;
    private Button estabelecimento;
    public ProgressBar progressBarprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_cliente);
        progressBarprincipal = findViewById(R.id.progressbarPrincipal);
        progressBarprincipal.setVisibility(View.INVISIBLE);

        mTVEmail = findViewById(R.id.User_Email);
        pnt_cliente = findViewById(R.id.pontos_cliente);
        mFirebaseAuth = FirebaseAuth.getInstance();
        estabelecimento = findViewById(R.id.salao);


        mTVEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal_Cliente.this, cadastro_cliente_editar.class));
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuários");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot usuario_info : snapshot.getChildren()){

                    Usuario usuario = snapshot.getValue(Usuario.class);
                    String UID_usuario = usuario_info.child("UID_usuario").getValue().toString();

                    if (current.equals(UID_usuario)) {


                        nome_usuario = usuario_info.child("nome_usuario").getValue().toString();
                        pontos_usuario = usuario_info.child("pontos_usuario").getValue().toString();


                        mTVEmail.setText(nome_usuario);
                        pnt_cliente.setText(pontos_usuario);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });















//        db.collection("usuários")
//                .whereEqualTo("id",current).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for(DocumentSnapshot document : task.getResult()){
//                                mTVEmail.setText((CharSequence) document.get("nome")) ;
//                                pnt_cliente.setText((CharSequence) document.get("pontos").toString());
//
//                            }
//                        }
//                    }
//                });
    }

    public void botao_cadastro_cliente(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        progressBarprincipal.setVisibility(View.GONE);
        progressBarprincipal.setVisibility(View.VISIBLE);
        startActivity(it);
    }

    public void botao_cadastro_servico(View view){
        Intent it = new Intent(this, servico_cadastrado.class);
        progressBarprincipal.setVisibility(View.GONE);
        progressBarprincipal.setVisibility(View.VISIBLE);
        startActivity(it);
    }

    public void botao_cadastro_funcionario(View view){
        Intent it = new Intent(this, funcionario_cadastrado.class);
        progressBarprincipal.setVisibility(View.GONE);
        progressBarprincipal.setVisibility(View.VISIBLE);
        startActivity(it);
    }

    public void botao_cadastro_funcao(View view){
        Intent it = new Intent(this, funcao_cadastrado.class);
        progressBarprincipal.setVisibility(View.GONE);
        progressBarprincipal.setVisibility(View.VISIBLE);
        startActivity(it);
    }

    public void botao_cadastro_agendamento(View view){
        Intent it = new Intent(this, agendamento_cadastrado.class);
        progressBarprincipal.setVisibility(View.GONE);
        progressBarprincipal.setVisibility(View.VISIBLE);
        startActivity(it);
    }

    public void logout(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(Principal_Cliente.this);
        builder.setMessage("Você quer mesmo sair?");
        builder.setTitle("Confirmar saída");
        builder.setCancelable(false);

        builder.setPositiveButton("Sim", (DialogInterface.OnClickListener) (dialog, which) -> {

            SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            clearToken(FirebaseAuth.getInstance().getCurrentUser().getUid());
            mFirebaseAuth.signOut();

            Intent it = new Intent(this, LoginActivity.class);
            startActivity(it);
        });
        builder.setNegativeButton("Não", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void clearToken(String UserID){
        FirebaseDatabase.getInstance().getReference("tokens").child(UserID).removeValue();
    }
}