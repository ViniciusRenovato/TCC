package com.example.approfisso.ediçao;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class cadastro_servico_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    Spinner spinner_editar_funcao_servico;
    DatabaseReference spinner_info_funcao_servico;
    ArrayList<String> spinner_lista_servico;
    ArrayAdapter<String> adapter;


    private String servico_editar_id;
    private String servico_editar_nome;
    private String servico_editar_duracao;
    private String servico_editar_valor;
    private String servico_editar_funcao;


    private String funcaoID;
    private EditText nome_servico;
    private EditText duracao_servico;
    private EditText valor_servico;
    private EditText funcao_servico;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_servico_popup);

        servico_editar_id = getIntent().getStringExtra("servicoid");
        servico_editar_nome = getIntent().getStringExtra("serviconome");
        servico_editar_duracao = getIntent().getStringExtra("servicoduracao");
        servico_editar_valor = getIntent().getStringExtra("servicovalor");
        servico_editar_funcao = getIntent().getStringExtra("servicofuncao");



        spinner_editar_funcao_servico = findViewById(R.id.Spinner_Editar_Funcao_Servico);

        spinner_info_funcao_servico = FirebaseDatabase.getInstance().getReference("Funcao");

        spinner_lista_servico = new ArrayList<>();
        adapter = new ArrayAdapter<String>(cadastro_servico_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_servico);

        spinner_editar_funcao_servico.setAdapter(adapter);
        Showdata();


        nome_servico=findViewById(R.id.Editar_Nome_Servico);
        duracao_servico=findViewById(R.id.Editar_Dureacao_Servico);
        valor_servico=findViewById(R.id.Editar_Valor_Servico);


        nome_servico.setText(servico_editar_nome);
        duracao_servico.setText(servico_editar_duracao);
        valor_servico.setText(servico_editar_valor);



        Button funcao_editar = findViewById(R.id.botao_Confirmar_Editar_Funcionario);

        funcao_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome_servico=findViewById(R.id.Editar_Nome_Servico);
                duracao_servico=findViewById(R.id.Editar_Dureacao_Servico);
                valor_servico=findViewById(R.id.Editar_Valor_Servico);
                spinner_editar_funcao_servico = findViewById(R.id.Spinner_Editar_Funcao_Servico);

                updateData();
            }
        });

        Button funcao_editar_voltar = findViewById(R.id.Voltar_cadastro_Editar_Funcionario);

        funcao_editar_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void updateData(){

        HashMap servico = new HashMap();
        servico.put("nome_servico",nome_servico.getText().toString());
        servico.put("duracao_servico",duracao_servico.getText().toString());
        servico.put("valor_servico",valor_servico.getText().toString());
        servico.put("funcao_servico",spinner_editar_funcao_servico.getSelectedItem().toString());


        databaseReference = FirebaseDatabase.getInstance().getReference("Servicos");
        databaseReference.child(servico_editar_id).updateChildren(servico).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()){

                    Toast.makeText(cadastro_servico_editar.this,"funcionario editado com sucesso.",Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }else{

                    Toast.makeText(cadastro_servico_editar.this,"falha na edição",Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


    private void Showdata(){

        spinner_info_funcao_servico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {

                    String teste = item.child("nome_funcao").getValue(String.class);
                    spinner_lista_servico.add(teste);

//                    spinner_lista_servico.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
