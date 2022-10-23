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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.approfisso.R;
import com.example.approfisso.classes.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    public static  final String filename = "login";
    public static  final String Username = "username";
    public static  final String Password = "password";


    private TextView TextoRecuperar;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etRecuperar;
    private Button btLogar;
    private Button btCadastrar_Login;
    private Button btRecuperar;
    private Button botao_recuperar_email;
    public ProgressBar progressBarLogin;


    private FirebaseAuth mAuth;

    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBarLogin = findViewById(R.id.progressbarLogin);
        progressBarLogin.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.email_login);
        etSenha = findViewById(R.id.senha_login);
        btLogar = findViewById(R.id.LogarLogin);

        TextoRecuperar = findViewById(R.id.texto_recuperar);

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

        TextoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(TextoRecuperar.getContext())
                        .setContentHolder(new ViewHolder(R.layout.recuperar_senha_email))
                        .setExpanded(true,600)
                        .create();

                View view1 = dialogPlus.getHolderView();
                EditText email_senha = view1.findViewById(R.id.email_recuperar_senha);

                dialogPlus.show();

                Button recuperar_senha = view1.findViewById(R.id.btn_email_recuperar_senha);



                recuperar_senha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String recuperar = email_senha.getText().toString().trim();

//                        if(TextUtils.isEmpty(email)) {
//                            etEmail.setError("Insira um Email");
//                            return;


                        if(TextUtils.isEmpty(recuperar)){
                            email_senha.setError("Insira um Email.");
                            return;
                        }
                        mAuth.getInstance().sendPasswordResetEmail(recuperar)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {



                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            Toast.makeText(TextoRecuperar.getContext(),"Email de recuperação enviado", Toast.LENGTH_LONG).show();
                                            Log.d(TAG, "Email enviado");
                                            dialogPlus.dismiss();
                                        }else{
                                            email_senha.setError("Email não cadastrado.");
                                        }
                                    }
                                });


                    }
                });

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
                            retrieveAndStoreToken();

                            startActivity(new Intent(LoginActivity.this,Principal.class));
                            progressBarLogin.setVisibility(View.GONE);
                            progressBarLogin.setVisibility(View.VISIBLE);

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

    private void retrieveAndStoreToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {

                        if(task.isSuccessful()  ){
                            String token = task.getResult();
                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            FirebaseDatabase.getInstance()
                                    .getReference("tokens")
                                    .child(userID).setValue(token);
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