package com.example.approfisso.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.adapter.pontosclientesAdapter;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.entidades.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pontos_clientes extends AppCompatActivity {



     RecyclerView recycleView;
     ArrayList<User> userArrayList;
     pontosclientesAdapter myAdapter;
     FirebaseFirestore db;
     ProgressDialog progressDialog;



    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_pontuados);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Recebendo informações...");
        progressDialog.show();



        recycleView = findViewById(R.id.lista_pontos_clientes);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myAdapter = new pontosclientesAdapter(Pontos_clientes.this,userArrayList);

        recycleView.setAdapter(myAdapter);

        EventChangeListener();

//        dados = new ArrayList<>();
//        usersListAdapter = new pontosclientesAdapter(, dados);
//
//        recycleView = findViewById(R.id.lista_pontos_clientes);
//        linearLayoutManager = new LinearLayoutManager(this);
//        recycleView.setLayoutManager(linearLayoutManager);
//
//
//        mFirestore = FirebaseFirestore.getInstance();
//
//        Usuarios = new LinkedList<>();
//
//        listar_pontos();
//
//
//
//
//
//
//
//
//    }
//
//    List<Usuario> Usuarios;
//    public  void  listar_pontos(){
//        mFirestore.collection("usuários").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (error != null){
//                    Log.d(TAG,"erro:"+ error.getMessage());
//                }
//
//                for (DocumentChange doc: value.getDocumentChanges()){
//
//
//                    Usuario usuario = doc.getDocument().toObject(Usuario.class);
//                    Usuarios.add(usuario);
//
//
////                    if(doc.getType()== DocumentChange.Type.ADDED){
////
////                        Usuario usuario = doc.getDocument().toObject(Usuario.class);
////                        dados.add(usuario);
////
////                        usersListAdapter.notifyDataSetChanged();
////
//////                        String nome = doc.getDocument().getString("nome");
//////                        String pontos = doc.getDocument().getString("pontos");
//////
//////                        Log.d(TAG,"nome:"+ nome);
//////                        Log.d(TAG,"pontos:"+ pontos);
////                  }
//                }
//                preenche_usuario();
//            }
//        });
//    }
//
//    pontosclientesAdapter PontosClientesAdapter;
//    private void preenche_usuario(){
//        PontosClientesAdapter = new pontosclientesAdapter(, Usuarios);
//        recycleView.setAdapter(PontosClientesAdapter);
//
//    }
//
//
////    funcaoAdapter FuncaoAdapter;
////    private void preenche_funcao() {
////        FuncaoAdapter= new funcaoAdapter(Funcoes);
////        recyclerView.setAdapter(FuncaoAdapter);
////




   }

    private void EventChangeListener() {

        db.collection("usuários").orderBy("nome", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();


                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc: value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){
                                userArrayList.add(dc.getDocument().toObject(User.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }
}
