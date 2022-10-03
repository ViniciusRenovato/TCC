package com.example.approfisso.activity;

import static com.example.approfisso.activity.LoginActivity.Username;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Principal extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private FirebaseAuth mFirebaseAuth;
    private TextView mTVEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        mTVEmail = findViewById(R.id.User_Email);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser!=null){
            mTVEmail.setText(mFirebaseUser.getEmail());
        }
        else{
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
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
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }



    public void botao_procura(View view){
        Intent it = new Intent(this, busca_menu.class);
        startActivity(it);
    }

    public void botao_sair (View view){
        finish();

    }

}