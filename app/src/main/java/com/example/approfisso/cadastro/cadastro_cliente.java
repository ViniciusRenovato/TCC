package com.example.approfisso.cadastro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.cadastrado.cliente_cadastrado;
import com.example.approfisso.entidades.Pessoa;
import com.google.android.material.textfield.TextInputEditText;
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

        nome=findViewById(R.id.Nome_Cliente);
        sobrenome=findViewById(R.id.Sobrenome_Cliente);
        telefone=findViewById(R.id.Telefone_Cliente);
        aniversario=findViewById(R.id.Aniversario_Cliente);

        email=findViewById(R.id.Email_Cliente);
        Intent i = getIntent();
        Clientes =(Pessoa) i.getSerializableExtra("Clientes");

        aniversario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_cliente.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TextInputEditText phone = (TextInputEditText) findViewById(R.id.Telefone_Cliente);
        //Add to mask
        phone.addTextChangedListener(textWatcher);
    }


    TextWatcher textWatcher = new TextWatcher() {
        private boolean mFormatting; // this is a flag which prevents the  stack overflow.
        private int mAfter;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // nothing to do here..
        }

        //called before the text is changed...
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //nothing to do here...
            mAfter  =   after; // flag to detect backspace..

        }

        @Override
        public void afterTextChanged(Editable s) {
            // Make sure to ignore calls to afterTextChanged caused by the work done below
            if (!mFormatting) {
                mFormatting = true;
                // using US or RU formatting...
                if(mAfter!=0) // in case back space ain't clicked...
                {
                    String num =s.toString();
                    String data = PhoneNumberUtils.formatNumber(num, "BR");
                    if(data!=null)
                    {
                        s.clear();
                        s.append(data);
                        Log.i("Number", data);//8 (999) 123-45-67 or +7 999 123-45-67
                    }

                }
                mFormatting = false;
            }
        }
    };




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
}
