package com.example.approfisso.activity;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.approfisso.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etAniversario;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,
                              int year,
                              int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };




    private EditText etTelefone;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etRepetirSenha;
    private Switch sEstabelecimento;
    private Button btCadastrarCadastro;
    private String userID;

    private FirebaseAuth mAuth;

    private FirebaseFirestore fStore;

    private Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.NomeCadastro);
        etAniversario = findViewById(R.id.AniverrsárioCadastro);
        etTelefone = findViewById(R.id.TelefoneCadastro);
        etEmail = findViewById(R.id.EmailCadastro);
        etSenha = findViewById(R.id.SenhaCadastro);
        etRepetirSenha = findViewById(R.id.RepetirSenhaCadastro);
        sEstabelecimento = findViewById(R.id.sEstabelecimento);
        btCadastrarCadastro = findViewById(R.id.btCadastrarCadastro);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarDados();
                criarLogin();
            }
        });

        etAniversario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CadastroActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        TextInputEditText phone = (TextInputEditText) findViewById(R.id.TelefoneCadastro);
        //Add to mask
        phone.addTextChangedListener(textWatcher);

    }


public void checkEmail(View view)
{




    mAuth.fetchSignInMethodsForEmail(etEmail.getText().toString())
            .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                @Override
                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                    boolean check = !task.getResult().getSignInMethods().isEmpty();

                    if(!check)
                    {

                    }
                    else{
                        etEmail.setError("Email ja Cadastrado.");
                    }

                }
            });
}










//    public boolean validateUpperCase(String name) {
//        Pattern ps = Pattern.compile("([A-Z]*)");
//        Matcher ms = ps.matcher(name);
//        return ms.matches();
//    }




    private void criarLogin() {

        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();
        String senharepetida = etRepetirSenha.getText().toString().trim();

        String nome = etNome.getText().toString().trim();

        String aniversario = etAniversario.getText().toString();
        String telefone = etTelefone.getText().toString();
        String tipo_login = sEstabelecimento.toString();

        String emailPattern = "[a-zA-Z0-9._-]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]+[a-zA-Z0-9]*[a-zA-Z.]+[a-zA-Z.]*?";
        String namePattern = "[A-Za-z ]+[ ]+[A-Za-z ]*";
//        String datePattern = "[01-31]+[/]+[01-12]*";




        if(TextUtils.isEmpty(nome)) {
            etNome.setError("Insira um Nome");
            return;
        }else {
            if (nome.matches(namePattern)) {

            } else {
                etNome.setError("Insira seu nome completo");
                return;
            }
        }
        if(TextUtils.isEmpty(email)) {
            etEmail.setError("Insira um Email");
            return;
        }else{
            if(email.matches(emailPattern)){

            }else{
                etEmail.setError("Insira um Email válido");
                return;
            }
        }

        mAuth.fetchSignInMethodsForEmail(etEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean check = !task.getResult().getSignInMethods().isEmpty();

                        if(!check)
                        {

                        }
                        else{
                            etEmail.setError("Email ja Cadastrado.");
                        }

                    }
                });






        if(TextUtils.isEmpty(telefone)) {
            etTelefone.setError("Insira o numero do seu telefone ");
            return;
        }

        if(telefone.length() <= 9){
            etTelefone.setError("Insira o numero válido");
            return;
        }

        if(telefone.length() <= 10){
            etTelefone.setError("Insira o prefixo");
            return;
        }


        if(telefone.length() <= 14){
            etTelefone.setError("Insira um prefixo válido");
            return;
        }


        if(TextUtils.isEmpty(aniversario)) {
            etAniversario.setError("Insira uma Data");
            return;
        }


        if(TextUtils.isEmpty(senha)) {
            etSenha.setError("Insira uma Senha");
            return;
        }

        if(senha.length() < 6){
            etSenha.setError("A Senha tem que ter no mínimo 6 caracteres.");
            return;
        }

        if(TextUtils.isEmpty(senha)) {
            etSenha.setError("Insira uma Senha");
            return;
        }

        if(senha.length() < 6){
            etSenha.setError("A Senha tem que ter no mínimo 6 caracteres.");
            return;
        }

        if(TextUtils.isEmpty(senharepetida)) {
            etRepetirSenha.setError("Insira novamente a Senha");
            return;
        }


        if(senha.equals(senharepetida)){

        }else{
            etRepetirSenha.setError("A Senha deve ser igual.");
            return;
        }

        //registrando no firebase


        mAuth.createUserWithEmailAndPassword(u.getEmail_usuario(),u.getSenha_usuario())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CadastroActivity.this, "Usuário Criado", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("usuários").document(userID);


                            Map<String,Object> usuario_cadastro = new HashMap<>();
                            usuario_cadastro.put("id",userID);
                            usuario_cadastro.put("nome",nome);
                            usuario_cadastro.put("email",email);
                            usuario_cadastro.put("telefone",telefone);
                            usuario_cadastro.put("aniversario",aniversario);
                            usuario_cadastro.put("tipo+login",tipo_login);

                            documentReference.set(usuario_cadastro).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                                Log.d(TAG,"onSuccess: Perfil de usuário criado para "+userID);
                        }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: " + e.toString());
                                }
                            });



                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId_usuario(user.getUid());
                            u.salvarDados();
                            startActivity(new Intent(CadastroActivity.this,Principal.class));
                        }else{
                            Toast.makeText(CadastroActivity.this,"Erro ao criar um login.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateLabel(){

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        etAniversario.setText(sdf.format(myCalendar.getTime()));
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



    private void recuperarDados() {
        if(etNome.getText().toString()==""||etEmail.getText().toString()==""||etSenha.getText().toString()==""){
            Toast.makeText(this, "Você deve preencer todos os dados",Toast.LENGTH_LONG);
        }else{
        u = new Usuario();
        u.setNome_usuario(etNome.getText().toString());
        u.setEmail_usuario(etEmail.getText().toString());
        u.setSenha_usuario(etSenha.getText().toString());
        u.setSalao(sEstabelecimento.getShowText());
        }




    }
}