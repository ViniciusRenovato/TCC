package com.example.approfisso.activity;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    public static  final String filename = "login";
    public static  final String Username = "username";
    public static  final String Password = "password";



    private EditText etEmail;
    private EditText etSenha;
    private EditText etRecuperar;
    private Button btLogar;
    private Button btCadastrar_Login;
    private Button btRecuperar;

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



        etRecuperar = (EditText) findViewById(R.id.etRecuperar);
        btRecuperar = findViewById(R.id.btRecuperar);

        btRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String recuperar = etRecuperar.getText().toString().trim();

                mAuth.getInstance().sendPasswordResetEmail(recuperar)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Log.d(TAG, "Email enviado");
                                }else{
                                    etRecuperar.setError("Email não cadastrado.");
                                }
                            }
                        });
            }
        });


        btCadastrar_Login = findViewById(R.id.btCadastro_login);

        btCadastrar_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaCadastrar_login();
            }
        });


        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);


//          looping sem login

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


        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]*[a-z.]*?";

        if(TextUtils.isEmpty(email)) {
            etEmail.setError("Insira um Email");
            return;
        }else{
            if(email.matches(emailPattern)){

            }else{
                etEmail.setError("Insira um Email válido");
                return;
            }
        }


        mAuth.fetchSignInMethodsForEmail(etEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean check = !task.getResult().getSignInMethods().isEmpty();

                        if(!check)
                        {
                            etEmail.setError("Email não Cadastrado.");
                        }
                    }
                });






        if(TextUtils.isEmpty(senha)) {
            etSenha.setError("Insira a sua senha.");
            return;
        }







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


                                etSenha.setError("Senha incorreta.");



                           // Toast.makeText(LoginActivity.this, "Login ou senha incorreta.", Toast.LENGTH_SHORT).show();
//                            etEmail.setText("");
                            etEmail.requestFocus();
//                            etSenha.setText("");



                        }
                    }
                });
    }

    private void receberDados() {
        u = new Usuario();
        u.setEmail_usuario(etEmail.getText().toString());
        u.setSenha_usuario(etSenha.getText().toString());

    }



    private void telaCadastrar_login() {
        startActivity(new Intent(this, CadastroActivity.class));
    }

}