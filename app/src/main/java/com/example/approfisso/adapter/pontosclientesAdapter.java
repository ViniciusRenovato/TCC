package com.example.approfisso.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.ediçao.cadastro_funcionario_editar;
import com.example.approfisso.entidades.Funcionario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;


public class pontosclientesAdapter extends RecyclerView.Adapter<pontosclientesAdapter.PontosClientesHolder> {


    List<Usuario> dados;

    public pontosclientesAdapter(List<Usuario> usuario) {
        this.dados=usuario;
    }

    @NonNull
    @Override
    public PontosClientesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_pontos_clientes,viewGroup,false);
        return new PontosClientesHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PontosClientesHolder usuarioViewHolder, int i ) {
        Usuario usuario = dados.get(i);
        usuarioViewHolder.pontosclientenome.setText(usuario.getNome_usuario());
        usuarioViewHolder.pontosclienteponto.setText(usuario.getPontos_usuario().toString());



        usuarioViewHolder.pontosresgate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String current = usuario.getId_usuario();

                DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("usuários");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                       for (DataSnapshot usuario_resgate: snapshot.getChildren()){

                           Usuario usuario = snapshot.getValue(Usuario.class);
                           String usuario_do_resgate = usuario_resgate.child("id_usuario").getValue().toString();

                           if (current.equals(usuario_do_resgate)){

                               String id_usuario_pontuacao = usuario_resgate.child("id_usuario").getValue().toString();
                               Integer postos_para_resgatar = Integer.parseInt(usuario_resgate.child("pontos_usuario").getValue().toString());

                               Double ponto = Double.parseDouble(postos_para_resgatar.toString());
                               Double pontuacao = Double.parseDouble(ponto.toString());
                               Double resultado = pontuacao - 50;

                               HashMap resgate_ponto_cliente = new HashMap();
                               resgate_ponto_cliente.put("pontos_usuario",resultado);

                               DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuários");
                               databaseReference.child(id_usuario_pontuacao).updateChildren(resgate_ponto_cliente).addOnCompleteListener(new OnCompleteListener() {
                                   @Override
                                   public void onComplete(@NonNull Task task) {
                                       if (task.isSuccessful()){

                                           Toast.makeText(view.getContext(),"Pontos Resgatados.",Toast.LENGTH_SHORT).show();

                                       }else{
                                           Toast.makeText(view.getContext(),"falha no resgate dos pontos",Toast.LENGTH_SHORT).show();

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


    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class PontosClientesHolder extends RecyclerView.ViewHolder {

        private TextView pontosclientenome;
        private TextView pontosclienteponto;

        private Button pontosresgate;

        public PontosClientesHolder(@NonNull View itemView) {
            super(itemView);

            pontosclientenome=itemView.findViewById(R.id.item_ponto_cliente_nome);
            pontosclienteponto=itemView.findViewById(R.id.item_ponto_cliente_pontuacao);

            pontosresgate =(Button)itemView.findViewById(R.id.button_resgatar_pontos);
        }
    }
}
