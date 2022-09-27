package com.example.approfisso.activity;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.approfisso.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etAniversario;
    private EditText etTelefone;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etRepetirSenha;
    private Switch sEstabelecimento;
    private Button btCadastrarCadastro;
    private String userID;

    private FirebaseAuth mAuth;

    private FirebaseFirestore fStore;

    private Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.NomeCadastro);
        etAniversario = findViewById(R.id.AniverrsárioCadastro);
        etTelefone = findViewById(R.id.TelefoneCadastro);
        etEmail = findViewById(R.id.EmailCadastro);
        etSenha = findViewById(R.id.SenhaCadastro);
        etRepetirSenha = findViewById(R.id.RepetirSenhaCadastro);
        sEstabelecimento = findViewById(R.id.sEstabelecimento);
        btCadastrarCadastro = findViewById(R.id.btCadastrarCadastro);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarDados();
                criarLogin();
            }
        });

    }

    private void criarLogin() {

        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();
        String senharepetida = etRepetirSenha.getText().toString().trim();

        String nome = etNome.getText().toString();

        String aniversario = etAniversario.getText().toString();
        String telefone = etTelefone.getText().toString();



        if(TextUtils.isEmpty(email)){
            etEmail.setError("Insira um Email");
        }

        if(TextUtils.isEmpty(senha)) {
            etSenha.setError("Insira uma Senha");
            return;
        }

        if(senha.length() < 6){
            etSenha.setError("A Senha tem que ter no mínimo 6 caracteres.");
            return;
        }

        if(TextUtils.isEmpty(senharepetida)) {
            etSenha.setError("Insira uma Senha");
            return;
        }

        if(senharepetida.length() < 6){
            etSenha.setError("A Senha tem que ter no mínimo 6 caracteres.");
            return;
        }

        if(senharepetida.equals(senha)){

        }else{
            etRepetirSenha.setError("A deve ser igual.");
        }


        //registrando no firebase

        mAuth.createUserWithEmailAndPassword(u.getEmail_usuario(),u.getSenha_usuario())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CadastroActivity.this, "Usuário Criado", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("usuários").document(userID);

                            Map<String,Object> usuario_cadastro = new HashMap<>();
                            usuario_cadastro.put("nome",nome);
                            usuario_cadastro.put("email",email);
                            usuario_cadastro.put("telefone",telefone);
                            usuario_cadastro.put("aniversario",aniversario);

                            documentReference.set(usuario_cadastro).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                                Log.d(TAG,"onSuccess: Perfil de usuário criado para "+userID);
                        }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: " + e.toString());
                                }
                            });



                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId_usuario(user.getUid());
                            u.salvarDados();
                            startActivity(new Intent(CadastroActivity.this,Principal.class));
                        }else{
                            Toast.makeText(CadastroActivity.this,"Erro ao criar um login.",
                                    Toast.LENGTH_SHORT).show();
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