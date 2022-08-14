package com.example.tcc;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.tcc.R;

import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

//    Button botaodocliente;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void botao_cliente(View view){
        Intent it = new Intent(this, Tela_Cliente.class);
        startActivity(it);
    }




}
//    botaodocliente=findViewById(R.id.button_cadastro_cliente);
//
//    botaodocliente.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            Intent tela_do_cliente=new Intent(getApplicationContext(),Tela_Cliente.class);
//            startActivity(tela_do_cliente);
//        }
//    });
//
//    }
//}


