package com.example.approfisso.cadastrado;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.activity.Principal;
import com.example.approfisso.adapter.agendamentoAdapter;
import com.example.approfisso.cadastro.cadastro_agendamento;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.entidades.Agendamento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class agendamento_cadastrado extends AppCompatActivity {
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String login_cliente;
    private String id_usuario_agendamento;

    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    private EditText data_cliente;

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

    public ProgressBar progressBarAgendamento;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento_cadastrado);
        progressBarAgendamento = findViewById(R.id.progressbarAgendamento);
        progressBarAgendamento.setVisibility(View.INVISIBLE);
//        login_cliente = getIntent().getStringExtra("logincliente");

        data_cliente = findViewById(R.id.data_agendamento_cliente);
        data_cliente.setText(currentDate);

        data_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(agendamento_cadastrado.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();



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


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuários");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot usuario_info : snapshot.getChildren()){

                    Usuario usuario = snapshot.getValue(Usuario.class);
                    String UID_usuario = usuario_info.child("UID_usuario").getValue().toString();

                    if (current.equals(UID_usuario)) {

                        id_usuario_agendamento = usuario_info.child("id_usuario").getValue().toString();
                        login_cliente = id_usuario_agendamento;
                        updateLabel();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

                    if (login_cliente != null){

                    if (login_cliente.equals(postSnapshot.child("login_cliente").getValue(String.class))&&(data_cliente.getText().toString().trim().equals((postSnapshot.child("dia_agendamento").getValue(String.class))))){

                    Agendamento agendamento = postSnapshot.getValue(Agendamento.class);
                    Agendamento.add(agendamento);
                    }
                    }


                }
                preenche_agendamento();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    agendamentoAdapter AgendamentoAdapter;
    private void preenche_agendamento() {
        AgendamentoAdapter= new agendamentoAdapter(Agendamento);
        recyclerView.setAdapter(AgendamentoAdapter);
    }
    public void botao_cadastrar_agendamento(View view){
        Intent it = new Intent(this, cadastro_agendamento.class);
        progressBarAgendamento.setVisibility(View.GONE);
        progressBarAgendamento.setVisibility(View.VISIBLE);
        startActivity(it);
    }

    public void botao_retornar_busca (View view){
        //finish();
        Intent it = new Intent(this, Principal.class);
        startActivity(it);
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        data_cliente.setText(sdf.format(myCalendar.getTime()));

        listar_agendamento();
    }

}
