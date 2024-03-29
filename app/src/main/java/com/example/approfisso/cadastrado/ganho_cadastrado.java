package com.example.approfisso.cadastrado;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.DataFirebase;
import com.example.approfisso.R;
import com.example.approfisso.activity.Principal;
import com.example.approfisso.adapter.funcaoAdapter;
import com.example.approfisso.adapter.ganhoAdapter;
import com.example.approfisso.cadastro.cadastro_funcao;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.entidades.Agendamento_Encerrado;
import com.example.approfisso.entidades.Funcao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class ganho_cadastrado extends AppCompatActivity {

    DatabaseReference databaseReference;

    FirebaseFirestore fstore;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Funcao Funcoes_delete;

    private Button retornar_do_ganho;

    private TextView ganho_salao;

    private String data_listada;
    private String mes;
    private String ano;

    private EditText data_selecionada;
    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

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

    DataFirebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumo_ganho_estabelecimento);



        retornar_do_ganho = findViewById(R.id.retornar_resumo_mensal);
        ganho_salao = findViewById(R.id.ganho_estabelecimento_mensal);

//        lista= findViewById(R.id.lista_emprego_oferecido);


        data_selecionada = findViewById(R.id.data_mes_resumo_ganho);
        data_selecionada.setText(currentDate);

        mes = data_selecionada.getText().toString().trim().substring(3,5);
        ano = data_selecionada.getText().toString().trim().substring(6,10);
        data_listada = mes+"/"+ano;

        data_selecionada.setText(data_listada);



        data_selecionada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ganho_cadastrado.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        retornar_do_ganho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        //recycle view
        recyclerView=findViewById(R.id.lista_resumo_ganho_mensal);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //fim recycle view

        //firebase
        databaseReference= DataFirebase.getDatabaseReference();

        Agendamentos_Encerrados= new LinkedList<>();
        //chamada firebase
        listar_ganho();









    }

    @Override
    protected void onStart(){
        super.onStart();


        DatabaseReference reference_ganho_salao = FirebaseDatabase.getInstance().getReference().child("Agendamento_Encerrado_Estabelecimento").child(ano).child(mes);
        reference_ganho_salao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot usuario_info : snapshot.getChildren()){

                    Agendamento_Encerrado agendamento_encerrado = snapshot.getValue(Agendamento_Encerrado.class);

                    String ganho_do_salao = usuario_info.child("ganho_estabelecimento").getValue().toString();






                        ganho_salao.setText(ganho_do_salao);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });















//        db.collection("usuários")
//                .whereEqualTo("id",current).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for(DocumentSnapshot document : task.getResult()){
//                                mTVEmail.setText((CharSequence) document.get("nome")) ;
//                                pnt_cliente.setText((CharSequence) document.get("pontos").toString());
//
//                            }
//                        }
//                    }
//                });
    }







    List<Agendamento_Encerrado> Agendamentos_Encerrados;
    public void listar_ganho()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Agendamento_Encerrado").child(ano).child(mes);
                Agendamentos_Encerrados.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {




                    Agendamento_Encerrado agendamento_encerrado = postSnapshot.getValue(Agendamento_Encerrado.class);
                    Agendamentos_Encerrados.add(agendamento_encerrado);



                }
                preenche_ganho();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    ganhoAdapter GanhoAdapter;
    private void preenche_ganho() {
        GanhoAdapter= new ganhoAdapter(Agendamentos_Encerrados);
        recyclerView.setAdapter(GanhoAdapter);

    }

    public void botao_oferece(View view){
        Intent it = new Intent(this, cadastro_funcao.class);
        startActivity(it);
    }


    public void botao_Remover_Funcao (View view){

        Funcoes_delete = new Funcao();
        Funcao.excluirFuncao(Funcoes_delete);

        onBackPressed();

    }





    public void botao_retornar_busca (View view){
        //finish();
        Intent it = new Intent(this, Principal.class);
        startActivity(it);
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        data_selecionada.setText(sdf.format(myCalendar.getTime()));

        mes = data_selecionada.getText().toString().trim().substring(3,5);
        ano = data_selecionada.getText().toString().trim().substring(6,10);

        data_listada = mes+"/"+ano;


        data_selecionada.setText(data_listada);



        ganho_salao.setText("0");

        DatabaseReference reference_ganho_salao = FirebaseDatabase.getInstance().getReference().child("Agendamento_Encerrado_Estabelecimento").child(ano).child(mes);
        reference_ganho_salao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot usuario_info : snapshot.getChildren()){

                    Agendamento_Encerrado agendamento_encerrado = snapshot.getValue(Agendamento_Encerrado.class);

                    String ganho_do_salao = usuario_info.child("ganho_estabelecimento").getValue().toString();






                    ganho_salao.setText(ganho_do_salao);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        listar_ganho();
    }

}
