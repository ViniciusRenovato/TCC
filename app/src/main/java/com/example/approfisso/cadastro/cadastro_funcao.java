package com.example.approfisso.cadastro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Agendamento;
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
    private  Funcao Funcoes;

//    private String userID;
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore fStore;
//
//    private Usuario u;
//    private Funcao f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcao_cadastro);


        btCadastrarFuncao = findViewById(R.id.botao_Confirmar_Funcao);
        btCancelarFuncao = findViewById(R.id.Voltar_cadastro);

        etNome_Funcao = findViewById(R.id.Nome_Funcao_Cadastro);

//        mAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();

        btCadastrarFuncao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                etNome_Funcao = findViewById(R.id.Nome_Funcao_Cadastro);
                String nomefuncao = etNome_Funcao.getText().toString().trim();


                if(TextUtils.isEmpty(nomefuncao)) {
                    etNome_Funcao.setError("Insira um Nome");
                    return;
                }




                Funcoes = new Funcao();
                Funcoes.setNome_funcao(etNome_Funcao.getText().toString());
                Funcao.salvaFuncao(Funcoes);
                onBackPressed();
            }
        });

        btCancelarFuncao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }

}
