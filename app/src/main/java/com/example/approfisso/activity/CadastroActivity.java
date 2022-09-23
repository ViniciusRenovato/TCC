package com.example.approfisso.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.approfisso.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Switch sEstabelecimento;
    private Button btCadastrarCadastro;

    private FirebaseAuth mAuth;

    private Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.NomeCadastro);
        etEmail = findViewById(R.id.EmailCadastro);
        etSenha = findViewById(R.id.SenhaCadastro);
        sEstabelecimento = findViewById(R.id.sEstabelecimento);
        btCadastrarCadastro = findViewById(R.id.btCadastrarCadastro);

        mAuth = FirebaseAuth.getInstance();

        btCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarDados();
                criarLogin();
            }
        });

    }

    private void criarLogin() {
        mAuth.createUserWithEmailAndPassword(u.getEmail_usuario(),u.getSenha_usuario())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId_usuario(user.getUid());
                            u.salvarDados();
                            startActivity(new Intent(CadastroActivity.this,Principal.class));
                        }else{
                            Toast.makeText(CadastroActivity.this,"Erro ao criar um login.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void recuperarDados() {
        if(etNome.getText().toString()==""||etEmail.getText().toString()==""||etSenha.getText().toString()==""){
            Toast.makeText(this, "Você deve preencer todos os dados",Toast.LENGTH_LONG);
        }else{
        u = new Usuario();
        u.setNome_usuario(etNome.getText().toString());
        u.setEmail_usuario(etEmail.getText().toString());
        u.setSenha_usuario(etSenha.getText().toString());
        u.setSalao(sEstabelecimento.getShowText());
        }




    }
}