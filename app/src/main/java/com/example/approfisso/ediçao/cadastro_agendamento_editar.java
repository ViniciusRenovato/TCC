package com.example.approfisso.ediçao;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.cadastro.cadastro_agendamento;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Servicos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class cadastro_agendamento_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    Spinner spinner_funcao_agendamento_funcionario;
    DatabaseReference spinner_info_funcao_agendamento_funcionario;
    ArrayList<String> spinner_lista_agendamento_funcionario;
    ArrayAdapter<String> adapter_agendamento_funcionario;

    Spinner spinner_funcao_agendamento_servico;
    DatabaseReference spinner_info_funcao_agendamento_servico;
    ArrayList<String> spinner_lista_agendamento_servico;
    ArrayAdapter<String> adapter_agendamento_servico;

    Spinner spinner_agendamento_horario;
    DatabaseReference spinner_info_agendamento_horario;
    ArrayList<String> spinner_lista_agendamento_horario;
    ArrayAdapter<String> adapter_agendamento_horario;


    private EditText dia_agendamento;
    private Agendamento Agendamentos;

    private String nome_cliente;
    private String login_cliente;
    private String id_funcionario;



    private String agendamento_editar_id_agendamento;
    private String agendamento_editar_dia_agendamento;
    private String agendamento_editar_hora_agendamento;
    private String agendamento_editar_id_funcionario;
    private String agendamento_editar_nome_funcionario;
    private String agendamento_editar_login_cliente;
    private String agendamento_editar_nome_cliente;
    private String agendamento_editar_nome_servico;


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



    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_agendamento_popup);

          agendamento_editar_id_agendamento = getIntent().getStringExtra("idagendamento");
          agendamento_editar_dia_agendamento = getIntent().getStringExtra("diaagendamento");
          agendamento_editar_hora_agendamento = getIntent().getStringExtra("horaagendamento");
          agendamento_editar_id_funcionario = getIntent().getStringExtra("idfuncionario");
          agendamento_editar_nome_funcionario = getIntent().getStringExtra("nomefuncionario");
          agendamento_editar_login_cliente = getIntent().getStringExtra("logincliente");
          agendamento_editar_nome_cliente = getIntent().getStringExtra("nomecliente");
          agendamento_editar_nome_servico = getIntent().getStringExtra("nomeservico");



        spinner_funcao_agendamento_funcionario = findViewById(R.id.Editar_spinner_funcao_agendamento_funcionario);
        spinner_info_funcao_agendamento_funcionario = FirebaseDatabase.getInstance().getReference("Funcionario");
        spinner_lista_agendamento_funcionario = new ArrayList<>();
        adapter_agendamento_funcionario = new ArrayAdapter<String>(cadastro_agendamento_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_funcionario);
        spinner_funcao_agendamento_funcionario.setAdapter(adapter_agendamento_funcionario);
        Showdata_Funcionario();

        spinner_funcao_agendamento_servico = findViewById(R.id.Editar_spinner_funcao_agendamento_servico);
        spinner_info_funcao_agendamento_servico = FirebaseDatabase.getInstance().getReference("Servicos");
        spinner_lista_agendamento_servico = new ArrayList<>();
        adapter_agendamento_servico = new ArrayAdapter<String>(cadastro_agendamento_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_servico);
        spinner_funcao_agendamento_servico.setAdapter(adapter_agendamento_servico);
        Showdata_Servico();

        spinner_agendamento_horario = findViewById(R.id.spinner_agendamento_horario_editar);
        spinner_info_agendamento_horario = FirebaseDatabase.getInstance().getReference("Horario");
        spinner_lista_agendamento_horario = new ArrayList<>();
        adapter_agendamento_horario = new ArrayAdapter<String>(cadastro_agendamento_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_horario);
        spinner_agendamento_horario.setAdapter(adapter_agendamento_horario);
        Showdata_Horario();



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        db.collection("usuários")
                .whereEqualTo("id",current).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(DocumentSnapshot document : task.getResult()){

                                nome_cliente = (String) document.get("nome");
                                login_cliente = (String) document.get("id");

                            }
                        }
                    }
                });



        dia_agendamento=findViewById(R.id.Editar_Dia_Agendamento);


        dia_agendamento.setText(agendamento_editar_dia_agendamento);

        dia_agendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_agendamento_editar.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        Intent i = getIntent();
        Agendamentos =(Agendamento) i.getSerializableExtra("Agendamento");
        spinner_funcao_agendamento_servico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Showdata_Funcionario();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button agendamento_editar = findViewById(R.id.botao_Confirmar_Editar_Agendamento);

        agendamento_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dia_agendamento=findViewById(R.id.Editar_Dia_Agendamento);

                spinner_agendamento_horario = findViewById(R.id.spinner_agendamento_horario_editar);
                spinner_funcao_agendamento_servico = findViewById(R.id.Editar_spinner_funcao_agendamento_servico);
                spinner_funcao_agendamento_funcionario = findViewById(R.id.Editar_spinner_funcao_agendamento_funcionario);



                String data_agendamento = dia_agendamento.getText().toString().trim();
                String horario_agendamento = spinner_agendamento_horario.getSelectedItem().toString().trim();
                String servico_agendamento = spinner_funcao_agendamento_servico.getSelectedItem().toString().trim();
                String funcionario_agendamento = spinner_funcao_agendamento_funcionario.getSelectedItem().toString().trim();


                if(TextUtils.isEmpty(data_agendamento)) {
                    dia_agendamento.setError("Insira a data do agendamento.");
                    return;
                }

                if (horario_agendamento.matches("--Selecione--")){

                    TextView errorText = (TextView)spinner_agendamento_horario.getSelectedView();
                    errorText.setError("Insira uma função valida");
                    return;

                }


                if (servico_agendamento.matches("--Selecione--")){

                    TextView errorText = (TextView)spinner_funcao_agendamento_servico.getSelectedView();
                    errorText.setError("Insira uma função valida");
                    return;

                }

                if (funcionario_agendamento.matches("--Selecione--")){

                    TextView errorText = (TextView)spinner_funcao_agendamento_funcionario.getSelectedView();
                    errorText.setError("Insira uma função valida");
                    return;

                }



                updateData();

            }
        });

        Button agendamento_voltar = findViewById(R.id.Voltar_cadastro_Editar_Agendamento);

        agendamento_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void updateData() {

        HashMap agendamento = new HashMap();


        agendamento.put("nome_cliente",nome_cliente);
        agendamento.put("usuario",login_cliente);
        agendamento.put("hora_agendamento",spinner_agendamento_horario.getSelectedItem().toString());
        agendamento.put("dia_agendamento",dia_agendamento.getText().toString());
        agendamento.put("servicos",spinner_funcao_agendamento_servico.getSelectedItem().toString());
        agendamento.put("funcionario",spinner_funcao_agendamento_funcionario.getSelectedItem().toString());
        agendamento.put("id_funcionario",id_funcionario);






        databaseReference = FirebaseDatabase.getInstance().getReference("Agendamento");
        databaseReference.child(agendamento_editar_id_agendamento).updateChildren(agendamento).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){

                    Toast.makeText(cadastro_agendamento_editar.this,"agendamento editado com sucesso.",Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }else{
                    Toast.makeText(cadastro_agendamento_editar.this,"falha na edição",Toast.LENGTH_SHORT).show();

                }
            }
        });






    }


    private void Showdata_Funcionario(){

        if(serviços!=null&&spinner_funcao_agendamento_servico.getSelectedItem()!=null) {
            List<Servicos> collect = serviços.stream().filter
                    (c -> c.getNome_servico().equals(spinner_funcao_agendamento_servico.getSelectedItem().toString())).collect(Collectors.toList());
            spinner_info_funcao_agendamento_funcionario.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    spinner_lista_agendamento_funcionario.clear();
                    spinner_lista_agendamento_funcionario.add("--Selecione--");


                    for (DataSnapshot item : snapshot.getChildren()) {

                        String nome_do_funcionario = item.child("nome_funcionario").getValue(String.class);
                        String id_do_funcionario = item.child("id_funcionario").getValue(String.class);


                        if (item.child("funcao_funcionario").getValue(String.class).equals(collect.get(0).getFuncao_servico()))
                            spinner_lista_agendamento_funcionario.add(nome_do_funcionario);
                            id_funcionario = id_do_funcionario;

//                    spinner_lista_agendamento_funcionario.add(item.getValue().toString());
                    }
                    adapter_agendamento_funcionario.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    List<Servicos> serviços;
    private void Showdata_Servico(){
        serviços= new ArrayList<>();
        spinner_info_funcao_agendamento_servico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                spinner_lista_agendamento_servico.clear();
//                spinner_lista_agendamento_servico.add("---Selecione---");
                for (DataSnapshot item : snapshot.getChildren()) {
                    Servicos serv= new Servicos();
                    serv.setNome_servico(item.child("nome_servico").getValue(String.class));
                    serv.setFuncao_servico(item.child("funcao_servico").getValue(String.class));
                    spinner_lista_agendamento_servico.add(serv.getNome_servico());
                    serviços.add(serv);

//                    spinner_lista_agendamento_funcionario.add(item.getValue().toString());
                }
                adapter_agendamento_servico.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    List<Agendamento> agendamento;
    private void Showdata_Horario(){
        agendamento= new ArrayList<>();
        spinner_info_agendamento_horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinner_lista_agendamento_horario.clear();
                spinner_lista_agendamento_horario.add("--Selecione--");
                for (DataSnapshot item : snapshot.getChildren()) {
                    Agendamento agendar = new Agendamento();

                    agendar.setHora_agendamento(item.getValue(String.class));

                    spinner_lista_agendamento_horario.add(agendar.getHora_agendamento());
                    agendamento.add(agendar);

                }
                adapter_agendamento_horario.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void updateLabel(){

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        dia_agendamento.setText(sdf.format(myCalendar.getTime()));
    }



}
