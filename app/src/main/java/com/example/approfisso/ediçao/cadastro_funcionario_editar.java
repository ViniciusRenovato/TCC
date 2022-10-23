package com.example.approfisso.ediçao;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class cadastro_funcionario_editar extends AppCompatActivity {

    DatabaseReference databaseReference;

    Spinner spinner_funcao_funcionario;
    DatabaseReference spinner_info_funcao_funcionario;
    ArrayList<String> spinner_lista_funcionario;
    ArrayAdapter<String> adapter;



    private String funcaoID;
    private EditText nome_funcionario;
    private EditText telefone_funcionario;
    private EditText aniversario_funcionario;
    private EditText email_funcionario;

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
        setContentView(R.layout.update_funcionario_popup);

        spinner_funcao_funcionario = findViewById(R.id.Spinner_Funcao_Funcionario_Editar);

        spinner_info_funcao_funcionario = FirebaseDatabase.getInstance().getReference("Funcao");

        spinner_lista_funcionario = new ArrayList<>();
        adapter = new ArrayAdapter<String>(cadastro_funcionario_editar.this, android.R.layout.simple_spinner_dropdown_item, spinner_lista_funcionario);


        spinner_funcao_funcionario.setAdapter(adapter);
        Showdata();




        nome_funcionario=findViewById(R.id.Editar_Nome_Funcionario);
        telefone_funcionario=findViewById(R.id.Editar_Telefone_Funcionario);
        aniversario_funcionario=findViewById(R.id.Editar_Aniversario_Funcionario);
        email_funcionario=findViewById(R.id.Editar_Email_Funcionario);




        Button funcao_editar = findViewById(R.id.botao_Confirmar_Editar_Funcionario);

        aniversario_funcionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_funcionario_editar.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TextInputEditText phone = (TextInputEditText) findViewById(R.id.Editar_Telefone_Funcionario);
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

        aniversario_funcionario.setText(sdf.format(myCalendar.getTime()));
    }



}
