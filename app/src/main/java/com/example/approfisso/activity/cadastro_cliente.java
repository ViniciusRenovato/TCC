package com.example.approfisso.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.DatabaseReference;

import com.example.approfisso.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class cadastro_cliente extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nome;
    private EditText sobrenome;
    private EditText telefone;

    private EditText aniversario;
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





    private EditText email;
    private Pessoa Clientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_cadastro);







        nome=findViewById(R.id.Nome_Servico);
        sobrenome=findViewById(R.id.Duracao_Servico);
        telefone=findViewById(R.id.Preco_Servico);
        aniversario=findViewById(R.id.Funcao_Servico);
        email=findViewById(R.id.Email_cadastro);
        Intent i = getIntent();
        Clientes =(Pessoa) i.getSerializableExtra("Clientes");

        aniversario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_cliente.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }


    public void lista_emprego(View view){
        Intent it = new Intent(this, cliente_cadastrado.class);
        startActivity(it);
    }


    public void Botao_Cancelar_Cliente (View view){
        super.onBackPressed();
        finish();
    }

    public void botao_sair (View view) {
        finish();
    }

    private void updateLabel(){

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        aniversario.setText(sdf.format(myCalendar.getTime()));
    }

    public void botao_Confirmar (View view){
        Clientes = new Pessoa();
        Clientes.setNome(nome.getText().toString());
        Clientes.setSobrenome(sobrenome.getText().toString());
        Clientes.setTelefone(telefone.getText().toString());
        Clientes.setAniversario(aniversario.getText().toString());
        Clientes.setEmail(email.getText().toString());
        Pessoa.salvaPessoa(Clientes);
        finish();
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
