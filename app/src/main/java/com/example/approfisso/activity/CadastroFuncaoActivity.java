package com.example.approfisso.activity;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CadastroFuncaoActivity extends AppCompatActivity {

    private EditText etNome_Funcao;


    private Button btCadastrarFuncao;
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

    }

    private void criarLogin() {



        String nome = etNome_Funcao.getText().toString().trim();


        if(TextUtils.isEmpty(nome)) {
            etNome_Funcao.setError("Insira um Nome para a função do(a) profissional");
            return;
        }

        //registrando no firebase


        Toast.makeText(CadastroFuncaoActivity.this, "Usuário Criado", Toast.LENGTH_SHORT).show();
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("estabelecimento").document(userID).collection("função").document(userID);





        Map<String,Object> cadastro_funcao = new HashMap<>();
        cadastro_funcao.put("id",userID);
        cadastro_funcao.put("nome",nome);

        documentReference.set(cadastro_funcao);



//
//        documentReference.set(cadastro_funcao).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
//            Log.d(TAG,"onSuccess: Perfil de usuário criado para "+userID);
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG,"onFailure: " + e.toString());
//            }
//        });



//        FirebaseUser user = mAuth.getCurrentUser();
//        u.setId_usuario(user.getUid());
//        u.salvarDados();
//        startActivity(new Intent(CadastroFuncaoActivity.this,Principal.class));





//        mAuth.createUserWithEmailAndPassword(u.getEmail_usuario(),u.getSenha_usuario())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(CadastroFuncaoActivity.this, "Usuário Criado", Toast.LENGTH_SHORT).show();
//                            userID = mAuth.getCurrentUser().getUid();
//                            DocumentReference documentReference = fStore.collection("funcão").document(userID);
//
//
//                            Map<String,Object> cadastro_funcao = new HashMap<>();
//                            cadastro_funcao.put("id",userID);
//                            cadastro_funcao.put("nome",nome);
//
//
//                            documentReference.set(cadastro_funcao).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
//                                Log.d(TAG,"onSuccess: Perfil de usuário criado para "+userID);
//                        }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.d(TAG,"onFailure: " + e.toString());
//                                }
//                            });
//
//
//
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            u.setId_usuario(user.getUid());
//                            u.salvarDados();
//                            startActivity(new Intent(CadastroFuncaoActivity.this,Principal.class));
//                        }else{
//                            Toast.makeText(CadastroFuncaoActivity.this,"Erro ao criar um login.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
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