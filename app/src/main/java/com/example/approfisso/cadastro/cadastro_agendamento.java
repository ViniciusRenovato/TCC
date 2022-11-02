package com.example.approfisso.cadastro;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.cadastrado.agendamento_cadastrado;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Funcionario;
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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class cadastro_agendamento extends AppCompatActivity {

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



    private EditText dia;
    private String nome_cliente;
    private String login_cliente;
    private double pontos_cliente;
    private String id_funcionario;

    private Agendamento Agendamentos;

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
        setContentView(R.layout.agendamento_cadastro);

        spinner_funcao_agendamento_funcionario = findViewById(R.id.spinner_funcao_agendamento_funcionario);
        spinner_info_funcao_agendamento_funcionario = FirebaseDatabase.getInstance().getReference("Funcionario");
        spinner_lista_agendamento_funcionario = new ArrayList<>();
        adapter_agendamento_funcionario = new ArrayAdapter<String>(cadastro_agendamento.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_funcionario);
        spinner_funcao_agendamento_funcionario.setAdapter(adapter_agendamento_funcionario);
        Showdata_Funcionario();

        spinner_funcao_agendamento_servico = findViewById(R.id.spinner_funcao_agendamento_servico);
        spinner_info_funcao_agendamento_servico = FirebaseDatabase.getInstance().getReference("Servicos");
        spinner_lista_agendamento_servico = new ArrayList<>();
        adapter_agendamento_servico = new ArrayAdapter<String>(cadastro_agendamento.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_servico);
        spinner_funcao_agendamento_servico.setAdapter(adapter_agendamento_servico);
        Showdata_Servico();

        spinner_agendamento_horario = findViewById(R.id.spinner_agendamento_horario);
        spinner_info_agendamento_horario = FirebaseDatabase.getInstance().getReference("Horario");
        spinner_lista_agendamento_horario = new ArrayList<>();
        adapter_agendamento_horario = new ArrayAdapter<String>(cadastro_agendamento.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_agendamento_horario);
        spinner_agendamento_horario.setAdapter(adapter_agendamento_horario);
        Showdata_Horario();

//         it.putExtra("logincliente",agendamento.getLogin_cliente());
//          e receber via
//        String id_cliente_usuario = getIntent().getStringExtra("logincliente");
//
//
//
//        receber do adapter via
//        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        db.collection("usuários")
//                .whereEqualTo("id",id_cliente_usuario).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for(DocumentSnapshot document : task.getResult()){
//
//                                pontos_cliente = (double) document.get("pontos");
//                                pontos_cliente = pontos_cliente + ponto_serviço;
//
//                            }
//                        }
//                    }
//                });





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

//        hora=findViewById(R.id.Hora_Agendamento);
        dia=findViewById(R.id.Dia_Agendamento);
        Intent i = getIntent();
        Agendamentos =(Agendamento) i.getSerializableExtra("Agendamento");

        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_agendamento.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });





        spinner_funcao_agendamento_servico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Showdata_Funcionario();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_agendamento_horario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Showdata_Horario();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



//        Button cadastrar_agendamento = findViewById(R.id.botao_Confirmar_Agendamento);
//
//        cadastrar_agendamento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Agendamentos = new Agendamento();
//                Agendamentos.setNome_cliente(nome_cliente);
//                Agendamentos.setLogin_cliente(login_cliente);
//                Agendamentos.setHora_agendamento(hora.getText().toString());
//                Agendamentos.setDia_agendamento(dia.getText().toString());
//                Agendamentos.setFuncionario(spinner_funcao_agendamento_funcionario.getSelectedItem().toString());
//                Agendamentos.setServicos(spinner_funcao_agendamento_servico.getSelectedItem().toString());
//                Agendamentos.setId_funcionario(id_funcionario);
//                Agendamento.salvaAgendamento(Agendamentos);
//                onBackPressed();
//            }
//        });

    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        dia.setText(sdf.format(myCalendar.getTime()));

    }


List<Funcionario> func;
    private void Showdata_Funcionario(){
        func = new ArrayList();
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
                            {
                                Funcionario f = new Funcionario();
                                f.setNome_funcionario(nome_do_funcionario);
                                f.setId_funcionario(id_do_funcionario);
                                func.add(f);
                                spinner_lista_agendamento_funcionario.add(func.size()-1,f.getNome_funcionario());


                            }





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
//                spinner_lista_agendamento_servico.add("--Selecione--");

                for (DataSnapshot item : snapshot.getChildren()) {
                        Servicos serv= new Servicos();
                    serv.setNome_servico(item.child("nome_servico").getValue(String.class));
                    serv.setFuncao_servico(item.child("funcao_servico").getValue(String.class));
                    serv.setPontos_servico(item.child("pontos_servico").getValue(Integer.class));
                    serviços.add(serv);
                    spinner_lista_agendamento_servico.add(serv.getNome_servico());



//                    Funcionario f = new Funcionario();
//                    f.setNome_funcionario(nome_do_funcionario);
//                    f.setId_funcionario(id_do_funcionario);
//                    func.add(f);
//                    spinner_lista_agendamento_funcionario.add(func.size()-1,f.getNome_funcionario());



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


    public void botao_Confirmar (View view){

        String data_agendamento = dia.getText().toString().trim();
        String horario_agendamento = spinner_agendamento_horario.getSelectedItem().toString().trim();
        String servico_agendamento = spinner_funcao_agendamento_servico.getSelectedItem().toString().trim();
        String funcionario_agendamento = spinner_funcao_agendamento_funcionario.getSelectedItem().toString().trim();


        if(TextUtils.isEmpty(data_agendamento)) {
            dia.setError("Insira a data do agendamento.");
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










        Agendamentos = new Agendamento();
        Agendamentos.setNome_cliente(nome_cliente);
        Agendamentos.setLogin_cliente(login_cliente);


        Agendamentos.setHora_agendamento(spinner_agendamento_horario.getSelectedItem().toString());

        Agendamentos.setPonto_agendamento(serviços.get(spinner_funcao_agendamento_servico.getSelectedItemPosition()).getPontos_servico());

        Agendamentos.setDia_agendamento(dia.getText().toString());
        Agendamentos.setFuncionario(spinner_funcao_agendamento_funcionario.getSelectedItem().toString());
        Agendamentos.setServicos(spinner_funcao_agendamento_servico.getSelectedItem().toString());
        Agendamentos.setId_funcionario(func.get(spinner_funcao_agendamento_funcionario.getSelectedItemPosition()).getId_funcionario());
        Agendamento.salvaAgendamento(Agendamentos);

      onBackPressed();

    }

    public void Botao_Cancelar_Agendamento (View view){
        super.onBackPressed();
        finish();
    }

}
