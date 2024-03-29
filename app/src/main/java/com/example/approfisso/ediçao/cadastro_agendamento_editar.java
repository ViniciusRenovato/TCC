package com.example.approfisso.ediçao;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.cadastro.cadastro_agendamento;
import com.example.approfisso.classes.Usuario;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class cadastro_agendamento_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    DatabaseReference spinner_info_agendados;

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
    private String id_usuario_agendamento;


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


    Calendar meucalendario;
    SimpleDateFormat formatodata;
    SimpleDateFormat formatohora;
    String Datahoje;
    String Horahoje;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_agendamento_popup);


        meucalendario = Calendar.getInstance();
        formatodata = new SimpleDateFormat("dd/MM/yyyy");
        formatohora = new SimpleDateFormat("HH:mm");
        Datahoje=formatodata.format(meucalendario.getTime());
        Horahoje=formatohora.format(meucalendario.getTime());



        agendamento_editar_id_agendamento = getIntent().getStringExtra("idagendamento");
          agendamento_editar_dia_agendamento = getIntent().getStringExtra("diaagendamento");
          agendamento_editar_hora_agendamento = getIntent().getStringExtra("horaagendamento");
          agendamento_editar_id_funcionario = getIntent().getStringExtra("idfuncionario");
          agendamento_editar_nome_funcionario = getIntent().getStringExtra("nomefuncionario");
          agendamento_editar_login_cliente = getIntent().getStringExtra("logincliente");
          agendamento_editar_nome_cliente = getIntent().getStringExtra("nomecliente");
          agendamento_editar_nome_servico = getIntent().getStringExtra("nomeservico");

        spinner_info_agendados= FirebaseDatabase.getInstance().getReference("Agendamento");

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


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuários");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot usuario_info : snapshot.getChildren()){

                    Usuario usuario = snapshot.getValue(Usuario.class);
                    String UID_usuario = usuario_info.child("UID_usuario").getValue().toString();

                    if (current.equals(UID_usuario)) {

                        id_usuario_agendamento = usuario_info.child("id_usuario").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });













        dia_agendamento=findViewById(R.id.Editar_Dia_Agendamento);

//        dia_agendamento.setText(agendamento_editar_dia_agendamento);










        dia_agendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_agendamento_editar.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

        spinner_funcao_agendamento_funcionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateLabel();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

        spinner_agendamento_horario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            List<String> horarios= new LinkedList();

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Showdata_Horario(horarios);
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

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date strDate = null;
                try {
                    strDate = sdf.parse(Datahoje);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date data_escolhida = null;
                try {
                    data_escolhida = sdf.parse(data_agendamento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (data_escolhida.before(strDate)){

                    dia_agendamento.setError("insira uma data após o dia atual");
                    return;
                }







                if (horario_agendamento.matches("--Selecione--")){

                    TextView errorText = (TextView)spinner_agendamento_horario.getSelectedView();
                    errorText.setError("Insira uma função valida");
                    return;

                }


                SimpleDateFormat sdf_hour = new SimpleDateFormat("HH:mm");
                Date strHour = null;
                try {
                    strHour = sdf_hour.parse(Horahoje);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date horario_escolhido = null;
                try {
                    horario_escolhido = sdf_hour.parse(horario_agendamento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (data_escolhida.equals(strDate)) {

                    if (horario_escolhido.before(strHour)) {

                        TextView errorText = (TextView) spinner_agendamento_horario.getSelectedView();
                        errorText.setError("Insira um horário após o atual");
                        return;
                    }
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
        agendamento.put("login_cliente",id_usuario_agendamento);
        agendamento.put("hora_agendamento",spinner_agendamento_horario.getSelectedItem().toString());
        agendamento.put("ponto_agendamento",serviços.get(spinner_funcao_agendamento_servico.getSelectedItemPosition()).getPontos_servico());
        agendamento.put("dia_agendamento",dia_agendamento.getText().toString());
        agendamento.put("servicos",spinner_funcao_agendamento_servico.getSelectedItem().toString());
        agendamento.put("funcionario",spinner_funcao_agendamento_funcionario.getSelectedItem().toString());
        agendamento.put("id_funcionario",func.get(spinner_funcao_agendamento_funcionario.getSelectedItemPosition()).getId_funcionario());
        agendamento.put("duracao_agendamento",serviços.get(spinner_funcao_agendamento_servico.getSelectedItemPosition()).getDuracao_servico());






        databaseReference = FirebaseDatabase.getInstance().getReference("Agendamento");
        databaseReference.child(agendamento_editar_id_agendamento).updateChildren(agendamento).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){

                    Toast.makeText(cadastro_agendamento_editar.this,"Agendamento editado com sucesso.",Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }else{
                    Toast.makeText(cadastro_agendamento_editar.this,"Falha na edição",Toast.LENGTH_SHORT).show();

                }
            }
        });

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
                    updateLabel();
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
                    serv.setPontos_servico(item.child("pontos_servico").getValue(Integer.class));
                    serv.setDuracao_servico(item.child("duracao_servico").getValue(String.class));
                    serviços.add(serv);
                    spinner_lista_agendamento_servico.add(
//                            serviços.size()-1,
                            serv.getNome_servico());
                }
                adapter_agendamento_servico.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    List<Agendamento> agendamento;
    private void Showdata_Horario(List ocupados){
        agendamento= new ArrayList<>();
        spinner_info_agendamento_horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinner_lista_agendamento_horario.clear();
                spinner_lista_agendamento_horario.add("--Selecione--");
                for (DataSnapshot item : snapshot.getChildren()) {
                    Agendamento agendar = new Agendamento();
                    agendar.setHora_agendamento(item.getValue(String.class));
                    String hora_agendamento = agendar.getHora_agendamento();

                    if(!ocupados.contains(hora_agendamento))
                            spinner_lista_agendamento_horario.add(hora_agendamento);
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

        List<String> horarios= new LinkedList();


        spinner_info_agendados.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                for (DataSnapshot dados : snapshot.getChildren() ) {


                    if (spinner_funcao_agendamento_funcionario.getSelectedItem().toString().trim().equals(dados.child("funcionario").getValue(String.class))){


                        if (dia_agendamento.getText().toString().trim().equals(dados.child("dia_agendamento").getValue(String.class))){

                            horarios.add(dados.child("hora_agendamento").getValue(String.class));
                            //calculo


                            String inicio_serv = (dados.child("hora_agendamento").getValue(String.class));
                            String duracao_serv = (dados.child("duracao_agendamento").getValue(String.class));

                            if (duracao_serv != null){

                                int hora_inicio = Integer.parseInt(inicio_serv.substring(0,2));
                                int min_inicio = Integer.parseInt(inicio_serv.substring(3,5));




                                int hora_duracao = Integer.parseInt(duracao_serv.substring(0,2));
                                int min_duracao = Integer.parseInt(duracao_serv.substring(3,5));

                                int base_hora_duracao = hora_duracao*60;
                                int base_hora_total = base_hora_duracao + min_duracao;

                                int base_calculo = base_hora_total / 30;


//                    LocalTime inicio = LocalTime.of(hora_inicio,min_inicio);
//                    LocalTime duracaoo = LocalTime.of(hora_duracao,min_duracao);
                                LocalTime inicio = LocalTime.of(hora_inicio,min_inicio);
                                String resposta;

//                    if (hora_duracao != null)

                                for (int i=1;i < base_calculo;i=i+1){

                                    int min_duracao_calculado = 30;


                                    LocalTime duracaoo = LocalTime.of(0,min_duracao_calculado);
                                    LocalTime total = inicio.plusHours(duracaoo.getHour()).plusMinutes(duracaoo.getMinute());
                                    resposta = total.toString();

                                    inicio = total;

                                    horarios.add(resposta);

                                }

                            }
                        }
                    }




                    //    String texto ="07:00";
//        int hora=Integer.parseInt(texto.substring(0,1));
//        int min=Integer.parseInt(texto.substring(3,4));
//
//
//        LocalTime primeiro = LocalTime.of(hora, min); // 12:00
//        LocalTime segundo  = LocalTime.of(0, 30); //  5:45
//
//        LocalTime total = primeiro.plusHours(segundo.getHour())
//                .plusMinutes(segundo.getMinute());
//


                }
                Showdata_Horario(horarios);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Showdata_Horario(horarios);

    }

}
