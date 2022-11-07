package com.example.approfisso.cadastrado;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.adapter.agendamentoAdapter;
import com.example.approfisso.adapter.agendamentofuncionarioAdapter;
import com.example.approfisso.cadastro.cadastro_agendamento;
import com.example.approfisso.entidades.Agendamento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class agendamento_cadastrado_estabelecimento extends AppCompatActivity {
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String login_cliente;
    private String ID_funcionario;


    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    private EditText data_funcionario;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento_cadastrado_estabelecimento);

        data_funcionario = findViewById(R.id.data_agendamento_funcionario);
        data_funcionario.setText(currentDate);

        data_funcionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(agendamento_cadastrado_estabelecimento.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//        login_cliente = getIntent().getStringExtra("logincliente");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        login_cliente = current;
        ID_funcionario = getIntent().getStringExtra("funcionarioid");

//        lista= findViewById(R.id.lista_emprego_oferecido);

        //recycle view
        recyclerView=findViewById(R.id.lista_agendamento_cadastrado);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Agendamento = new LinkedList<>();
        //chamada firebase
        listar_agendamento();
    }
    List<Agendamento> Agendamento;
    public void listar_agendamento()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Agendamento");
                Agendamento.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {



                    if (ID_funcionario.equals(postSnapshot.child("id_funcionario").getValue(String.class))&&(data_funcionario.getText().toString().trim().equals((postSnapshot.child("dia_agendamento").getValue(String.class))))){

                    Agendamento agendamento = postSnapshot.getValue(Agendamento.class);
                    Agendamento.add(agendamento);
                    }
                }
                preenche_agendamento();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    agendamentofuncionarioAdapter AgendamentofuncionarioAdapter;
    private void preenche_agendamento() {
        AgendamentofuncionarioAdapter= new agendamentofuncionarioAdapter(Agendamento);
        recyclerView.setAdapter(AgendamentofuncionarioAdapter);
    }
    public void botao_cadastrar_agendamento(View view){
        Intent it = new Intent(this, cadastro_agendamento.class);
        startActivity(it);
    }

    public void botao_retornar_busca (View view){
        finish();

    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        data_funcionario.setText(sdf.format(myCalendar.getTime()));

        listar_agendamento();
    }
}
