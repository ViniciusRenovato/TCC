package com.example.approfisso.ediçao;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.approfisso.R;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.entidades.Pessoa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
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
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class cadastro_cliente_editar extends AppCompatActivity {

    DatabaseReference databaseReference;
    private String userID;
    private EditText nome;
    private EditText telefone;
    private EditText aniversario;
    private String telefone_usuario;
    private String nome_usuario;
    private String aniversario_usuario;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

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
        setContentView(R.layout.cliente_cadastro_editar);

        nome=findViewById(R.id.Nome_Cliente_Editar);
        telefone=findViewById(R.id.Telefone_Cliente_Editar);
        aniversario=findViewById(R.id.Aniversario_Cliente_Editar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuários");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot usuario_info : snapshot.getChildren()){

                    Usuario usuario = snapshot.getValue(Usuario.class);
                    String UID_usuario = usuario_info.child("UID_usuario").getValue().toString();

                    if (current.equals(UID_usuario)) {

                        nome_usuario = usuario_info.child("nome_usuario").getValue().toString();
                        telefone_usuario = usuario_info.child("telefone_usuario").getValue().toString();
                        aniversario_usuario = usuario_info.child("aniversario_usuario").getValue().toString();

                        nome.setText(nome_usuario);
                        telefone.setText(telefone_usuario);
                        aniversario.setText(aniversario_usuario);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button clienteeditar = findViewById(R.id.botao_Confirmar_Editar_Cliente);

        TextInputEditText phone = (TextInputEditText) findViewById(R.id.Telefone_Cliente_Editar);
        //Add to mask
        telefone.addTextChangedListener(textWatcher);

        aniversario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cadastro_cliente_editar.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        clienteeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String current = user.getUid();

                String nome_editar = nome.getText().toString().trim();
                String telefone_editar = telefone.getText().toString().trim();

                String namePattern = "[A-Za-z ]+[ ]+[A-Za-z ]*";

                if(TextUtils.isEmpty(nome_editar)) {
                    nome.setError("Insira um Nome");
                    return;
                }else {
                    if (nome_editar.matches(namePattern)) {

                    } else {
                        nome.setError("Insira seu nome completo");
                        return;
                    }
                }

                if(TextUtils.isEmpty(telefone_editar)) {
                    telefone.setError("Insira o numero do seu telefone ");
                    return;
                }

                if(telefone_editar.length() <= 9){
                    telefone.setError("Insira o numero válido");
                    return;
                }

                if(telefone_editar.length() <= 10){
                    telefone.setError("Insira o prefixo");
                    return;
                }


                if(telefone_editar.length() <= 14){
                    telefone.setError("Insira um prefixo válido");
                    return;
                }

                Intent i = getIntent();
                Clientes =(Pessoa) i.getSerializableExtra("Clientes");

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuários");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot usuario_info : snapshot.getChildren()){

                            Usuario usuario = snapshot.getValue(Usuario.class);
                            String UID_usuario = usuario_info.child("UID_usuario").getValue().toString();

                            if (current.equals(UID_usuario)) {

                                String id_usuario_agendamento = usuario_info.child("id_usuario").getValue().toString();


                                Map<String,Object> map = new HashMap<>();
                                map.put("nome_usuario",nome.getText().toString());
                                map.put("telefone_usuario",telefone.getText().toString());
                                map.put("aniversario_usuario",aniversario.getText().toString());

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuários");
                                databaseReference.child(id_usuario_agendamento).updateChildren(map).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()){

                                            Toast.makeText(view.getContext(),"Perfil editado com sucesso.",Toast.LENGTH_SHORT).show();
                                            onBackPressed();

                                        }else{
                                            Toast.makeText(view.getContext(),"falha na edição",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
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

    public void Botao_Cancelar_Cliente (View view){
        super.onBackPressed();
        finish();
    }

    private void updateLabel(){

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        aniversario.setText(sdf.format(myCalendar.getTime()));
    }

}
