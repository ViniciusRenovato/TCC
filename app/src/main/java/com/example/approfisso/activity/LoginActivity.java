package com.example.approfisso.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    public static  final String filename = "login";
    public static  final String Username = "username";
    public static  final String Password = "password";



    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;

    private FirebaseAuth mAuth;

    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.email_login);
        etSenha = findViewById(R.id.senha_login);
        btLogar = findViewById(R.id.LogarLogin);

        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(Username)){
            Intent i = new Intent(LoginActivity.this, Principal.class);
            startActivity(i);
        }





        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receberDados();
                logar();
            }
        });

    }

    private void logar() {
        mAuth.signInWithEmailAndPassword(u.getEmail_usuario(), u.getSenha_usuario())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        String username = etEmail.getText().toString();
                        String password = etSenha.getText().toString();

                        if(task.isSuccessful()) {

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Username,username);
                            editor.putString(Password,password);
                            editor.commit();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,Principal.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Autenticação falhou", Toast.LENGTH_SHORT).show();
                            etEmail.setText("");
                            etEmail.requestFocus();
                            etSenha.setText("");

                        }
                    }
                });
    }

    private void receberDados() {
        u = new Usuario();
        u.setEmail_usuario(etEmail.getText().toString());
        u.setSenha_usuario(etSenha.getText().toString());

    }
}