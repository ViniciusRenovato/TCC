package com.example.approfisso.activity;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcao;
import com.example.approfisso.entidades.Pessoa;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class cadastro_funcao extends AppCompatActivity {

    DatabaseReference databaseReference;
    private FirebaseFirestore fstore;
    private FirebaseAuth mAuth;
    private String userID;

    private EditText nome;

    private Funcao Funcoes;


    private Button button_salvar_funcao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcao_cadastro);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        nome=findViewById(R.id.Nome_Funcao_Cadastro);
        Intent i = getIntent();
        Funcoes =(Funcao) i.getSerializableExtra("Funcao");

        button_salvar_funcao = findViewById(R.id.botao_Confirmar_Funcao);


        button_salvar_funcao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void  onClick(View view){

                Funcoes = new Funcao();

                Funcoes.setNome_funcao(nome.getText().toString());
                Funcao.salvaFuncao(Funcoes);

                userID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("estabelecimento").document(userID).collection("função").document(userID);


                Map<String,Object> funcao_cadastro = new HashMap<>();
                funcao_cadastro.put("id_cadastro",userID);
                funcao_cadastro.put("id_funcao",userID);
                funcao_cadastro.put("nome", nome );


                documentReference.set(funcao_cadastro).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                    Log.d(TAG,"onSuccess: Perfil de usuário criado para "+userID);
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"onFailure: " + e.toString());
                    }
                });





                onBackPressed();

            }

        });











    }


    public void lista_emprego(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }


    public void Botao_Cancelar_Funcao (View view){
        super.onBackPressed();
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }


//    public void botao_Confirmar (View view){
//
//                Funcoes =new
//
//                        Funcao();
//
//                Funcoes.setNome_funcao(nome.getText().
//
//                        toString());
//                Funcao.salvaFuncao(Funcoes);
//
//        super.onBackPressed();
//        finish();
//
//
//
//            }







    public void botao_Remover_Funcao (View view){

        Funcoes = new Funcao();
        Funcao.excluirFuncao(Funcoes);

        onBackPressed();

    }





 //   public void botao_Salvar (View view){
 //      Emprego = new empregos();
 //       Emprego.setEstado(Estado.getText().toString());
 //       Emprego.setCidade(Cidade.getText().toString());
 //       Emprego.setPeriodo(Periodo.getText().toString());
 //       Emprego.setArea_da_profissao(Profissao.getText().toString());
 //       Emprego.setSalario(Salario.getText().toString());
 //       Emprego.setEmail(Email.getText().toString());
 //       empregos.salvar(Emprego);
 //       onBackPressed();
//
//
 //   }

}
