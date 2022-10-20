package com.example.approfisso.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class cadastro_funcao extends AppCompatActivity {

    private EditText etNome_Funcao;


    private Button btCadastrarFuncao;
    private Button btCancelarFuncao;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    private Usuario u;
    private Funcao f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcao_cadastro);


        btCadastrarFuncao = findViewById(R.id.botao_Confirmar_Funcao);
        btCancelarFuncao = findViewById(R.id.Voltar_cadastro);

        etNome_Funcao = findViewById(R.id.Nome_Funcao_Cadastro);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btCadastrarFuncao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarDados();
                criarLogin();
            }
        });

        btCancelarFuncao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void criarLogin() {



        String nome = etNome_Funcao.getText().toString().trim();


        if(TextUtils.isEmpty(nome)) {
            etNome_Funcao.setError("Insira um Nome para a função do(a) profissional");
            return;
        }

        //registrando no firebase


        Toast.makeText(cadastro_funcao.this, "Cadastro da função realizada.", Toast.LENGTH_SHORT).show();
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("estabelecimento").document(userID).collection("função").document(userID);





        Map<String,Object> cadastro_funcao = new HashMap<>();
        cadastro_funcao.put("id_cadastro",userID);
        cadastro_funcao.put("id_funçao",documentReference.get().toString() );
        cadastro_funcao.put("nome",nome);

        documentReference.set(cadastro_funcao);

        onBackPressed();

    }





    private void recuperarDados() {
        if(etNome_Funcao.getText().toString()==""){
            Toast.makeText(this, "Você deve preencer todos os dados",Toast.LENGTH_LONG);
        }else{
        f = new Funcao();
        f.setNome_funcao(etNome_Funcao.getText().toString());
        f.setId_funcao(userID);
        }
    }
}