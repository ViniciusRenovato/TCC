package com.example.approfisso.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class cadastro_funcao_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    private String funcaoID;
    private EditText nome_funcao;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_funcao_popup);

        nome_funcao=findViewById(R.id.Editar_Nome_Funcao);

        Button funcao_editar = findViewById(R.id.botao_Confirmar_Editar_Funcao);




    }
}
