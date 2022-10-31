package com.example.approfisso.cadastro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.cadastrado.cliente_cadastrado;
import com.example.approfisso.entidades.Funcionario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class cadastro_funcionario extends AppCompatActivity {

    DatabaseReference databaseReference;


    Spinner spinner_funcao_funcionario;
    DatabaseReference spinner_info_funcao_funcionario;
    ArrayList<String> spinner_lista_funcionario;
    ArrayAdapter<String> adapter;




    private EditText nome;
    private EditText sobrenome;
    private EditText telefone;
    private EditText aniversario;
    private EditText email;
    private Funcionario Funcionarios;


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
        setContentView(R.layout.funcionario_cadastro);


        spinner_funcao_funcionario = findViewById(R.id.spinner_funcao_funcionario);

        spinner_info_funcao_funcionario = FirebaseDatabase.getInstance().getReference("Funcao");

        spinner_lista_funcionario = new ArrayList<>();
        adapter = new ArrayAdapter<String>(cadastro_funcionario.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_funcionario);


        spinner_funcao_funcionario.setAdapter(adapter);
        Showdata();


        nome=findViewById(R.id.Nome_Funcionario);


        telefone=findViewById(R.id.Telefone_Funcionario);
        aniversario=findViewById(R.id.Aniversario_Funcionario);
        email=findViewById(R.id.Email_Funcionario);
        Intent i = getIntent();
        Funcionarios =(Funcionario) i.getSerializableExtra("Funcionario");

        aniversario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_funcionario.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TextInputEditText phone = (TextInputEditText) findViewById(R.id.Telefone_Funcionario);
        phone.addTextChangedListener(textWatcher_funcionario);

    }

    TextWatcher textWatcher_funcionario = new TextWatcher() {
        private boolean mFormatting;
        private int mAfter;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            mAfter  =   after;

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!mFormatting) {
                mFormatting = true;
                if(mAfter!=0)
                {
                    String num =s.toString();
                    String data = PhoneNumberUtils.formatNumber(num, "BR");
                    if(data!=null)
                    {
                        s.clear();
                        s.append(data);
                        Log.i("Number", data);
                    }
                }
                mFormatting = false;
            }
        }
    };

    private void Showdata(){

        spinner_info_funcao_funcionario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                spinner_lista_funcionario.clear();
                spinner_lista_funcionario.add("--Selecione--");

                for (DataSnapshot item : snapshot.getChildren()) {

                    String teste = item.child("nome_funcao").getValue(String.class);
                    spinner_lista_funcionario.add(teste);

//                    spinner_lista_servico.add(item.getValue().toString());

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void updateLabel(){

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        aniversario.setText(sdf.format(myCalendar.getTime()));
    }


    public void lista_emprego(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }


    public void Botao_Cancelar_Funcionario (View view){
        super.onBackPressed();
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }


    public void botao_Confirmar (View view){

        String email_funcionario = email.getText().toString().trim();
        String nome_funcionario = nome.getText().toString().trim();
        String funcao_funcionario = spinner_funcao_funcionario.getSelectedItem().toString().trim();
        String aniversario_funcionario = aniversario.getText().toString().trim();
        String telefone_funcionario = telefone.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]+[a-zA-Z0-9]*[a-zA-Z.]+[a-zA-Z.]*?";
        String namePattern = "[A-Za-z ]+[ ]+[A-Za-z ]*";

        if(TextUtils.isEmpty(nome_funcionario)) {
            nome.setError("Insira um Nome");

            return;
        }else {
            if (nome_funcionario.matches(namePattern)) {

            } else {
                nome.setError("Insira seu nome completo");
                return;
            }
        }


        if(TextUtils.isEmpty(email_funcionario)) {
            email.setError("Insira um Email");
            return;
        }else{
            if(email_funcionario.matches(emailPattern)){

            }else{
                email.setError("Insira um Email válido");
                return;
            }
        }



        if (funcao_funcionario.matches("--Selecione--")){

            TextView errorText = (TextView)spinner_funcao_funcionario.getSelectedView();
            errorText.setError("Insira uma função valida");
            return;

        }



        if(TextUtils.isEmpty(telefone_funcionario)) {
            telefone.setError("Insira o numero do seu telefone ");
            return;
        }

        if(telefone_funcionario.length() <= 9){
            telefone.setError("Insira o numero válido");
            return;
        }

        if(telefone_funcionario.length() <= 10){
            telefone.setError("Insira o prefixo");
            return;
        }

        if(telefone_funcionario.length() <= 14){
            telefone.setError("Insira um prefixo válido");
            return;
        }

        if(TextUtils.isEmpty(aniversario_funcionario)) {
            aniversario.setError("Insira uma Data");
            return;
        }




        Funcionarios = new Funcionario();
        Funcionarios.setNome_funcionario(nome.getText().toString());
        Funcionarios.setSobrenome_funcionario(sobrenome.getText().toString());
        Funcionarios.setFuncao_funcionario(spinner_funcao_funcionario.getSelectedItem().toString());
        Funcionarios.setTelefone_funcionario(telefone.getText().toString());
        Funcionarios.setAniversario_funcionario(aniversario.getText().toString());
        Funcionarios.setEmail_funcionario(email.getText().toString());
        Funcionario.salvaFuncionario(Funcionarios);
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
