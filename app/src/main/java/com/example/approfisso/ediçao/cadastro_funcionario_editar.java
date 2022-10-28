package com.example.approfisso.ediçao;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import java.util.HashMap;
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

    private String funcionario_editar_id;
    private String funcionario_editar_nome;

    private String funcionario_editar_funcao;
    private String funcionario_editar_telefone;
    private String funcionario_editar_aniversario;
    private String funcionario_editar_email;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_funcionario_popup);


          funcionario_editar_id = getIntent().getStringExtra("funcionarioid");
          funcionario_editar_nome = getIntent().getStringExtra("funcionarionome");
          funcionario_editar_funcao = getIntent().getStringExtra("funcionariofuncao");
          funcionario_editar_telefone = getIntent().getStringExtra("funcionariotelefone");
          funcionario_editar_aniversario = getIntent().getStringExtra("funcionarioaniversario");
          funcionario_editar_email = getIntent().getStringExtra("funcionarioemail");





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

        nome_funcionario.setText(funcionario_editar_nome);
        telefone_funcionario.setText(funcionario_editar_telefone);
        aniversario_funcionario.setText(funcionario_editar_aniversario);
        email_funcionario.setText(funcionario_editar_email);



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


        Button funcionarioeditar = findViewById(R.id.botao_Confirmar_Editar_Funcionario);

        funcionarioeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome_funcionario=findViewById(R.id.Editar_Nome_Funcionario);
                telefone_funcionario=findViewById(R.id.Editar_Telefone_Funcionario);
                aniversario_funcionario=findViewById(R.id.Editar_Aniversario_Funcionario);
                email_funcionario=findViewById(R.id.Editar_Email_Funcionario);
                spinner_funcao_funcionario = findViewById(R.id.Spinner_Funcao_Funcionario_Editar);

                updateData();
            }
        });

        Button funcionarioeditarvoltar = findViewById(R.id.Voltar_cadastro_Editar_Funcionario);

        funcionarioeditarvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void updateData(){


        String email_funcionario_edit = email_funcionario.getText().toString().trim();
        String nome_funcionario_edit = nome_funcionario.getText().toString().trim();
        String funcao_funcionario_edit = spinner_funcao_funcionario.getSelectedItem().toString().trim();
        String aniversario_funcionario_edit = aniversario_funcionario.getText().toString().trim();
        String telefone_funcionario_edit = telefone_funcionario.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]+[a-zA-Z0-9]*[a-zA-Z.]+[a-zA-Z.]*?";
        String namePattern = "[A-Za-z ]+[ ]+[A-Za-z ]*";

        if(TextUtils.isEmpty(nome_funcionario_edit)) {
            nome_funcionario.setError("Insira um Nome");

            return;
        }else {
            if (nome_funcionario_edit.matches(namePattern)) {

            } else {
                nome_funcionario.setError("Insira seu nome completo");
                return;
            }
        }


        if(TextUtils.isEmpty(email_funcionario_edit)) {
            email_funcionario.setError("Insira um Email");
            return;
        }else{
            if(email_funcionario_edit.matches(emailPattern)){

            }else{
                email_funcionario.setError("Insira um Email válido");
                return;
            }
        }



        if (funcao_funcionario_edit.matches("--Selecione--")){

            TextView errorText = (TextView)spinner_funcao_funcionario.getSelectedView();
            errorText.setError("Insira uma função valida");
            return;

        }



        if(TextUtils.isEmpty(telefone_funcionario_edit)) {
            telefone_funcionario.setError("Insira o numero do seu telefone ");
            return;
        }

        if(telefone_funcionario_edit.length() <= 9){
            telefone_funcionario.setError("Insira o numero válido");
            return;
        }

        if(telefone_funcionario_edit.length() <= 10){
            telefone_funcionario.setError("Insira o prefixo");
            return;
        }

        if(telefone_funcionario_edit.length() <= 14){
            telefone_funcionario.setError("Insira um prefixo válido");
            return;
        }

        if(TextUtils.isEmpty(aniversario_funcionario_edit)) {
            aniversario_funcionario.setError("Insira uma Data");
            return;
        }










        HashMap funcionario = new HashMap();
        funcionario.put("nome_funcionario",nome_funcionario.getText().toString());
        funcionario.put("telefone_funcionario",telefone_funcionario.getText().toString());
        funcionario.put("aniversario_funcionario",aniversario_funcionario.getText().toString());
        funcionario.put("email_funcionario",email_funcionario.getText().toString());
        funcionario.put("funcao_funcionario",spinner_funcao_funcionario.getSelectedItem().toString());

        databaseReference = FirebaseDatabase.getInstance().getReference("Funcionario");
        databaseReference.child(funcionario_editar_id).updateChildren(funcionario).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){

                    Toast.makeText(cadastro_funcionario_editar.this,"funcionario editado com sucesso.",Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }else{
                    Toast.makeText(cadastro_funcionario_editar.this,"falha na edição",Toast.LENGTH_SHORT).show();

                }
            }
        });

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

        aniversario_funcionario.setText(sdf.format(myCalendar.getTime()));
    }



}
