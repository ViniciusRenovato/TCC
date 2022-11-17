package com.example.approfisso.adapter;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;


public class listaclientesAdapter extends RecyclerView.Adapter<listaclientesAdapter.PontosClientesHolder> {


    List<Usuario> dados;

    public listaclientesAdapter(List<Usuario> usuario) {
        this.dados=usuario;
    }

    @NonNull
    @Override
    public PontosClientesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_cliente_lista,viewGroup,false);
        return new PontosClientesHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PontosClientesHolder usuarioViewHolder, int i ) {
        Usuario usuario = dados.get(i);
        usuarioViewHolder.cliente_lista_nome.setText(usuario.getNome_usuario());
        usuarioViewHolder.cliente_lista_telefone.setText(usuario.getTelefone_usuario().toString());
        usuarioViewHolder.cliente_lista_aniversario.setText(usuario.getAniversario_usuario().toString());
        usuarioViewHolder.cliente_lista_email.setText(usuario.getEmail_usuario().toString());
        usuarioViewHolder.cliente_lista_ponto.setText(usuario.getPontos_usuario().toString());
        usuarioViewHolder.cliente_lista_falta.setText(usuario.getFaltas_usuario().toString());
    }


    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class PontosClientesHolder extends RecyclerView.ViewHolder {

        private TextView cliente_lista_nome;
        private TextView cliente_lista_telefone;
        private TextView cliente_lista_aniversario;
        private TextView cliente_lista_email;
        private TextView cliente_lista_ponto;
        private TextView cliente_lista_falta;


        public PontosClientesHolder(@NonNull View itemView) {
            super(itemView);

            cliente_lista_nome=itemView.findViewById(R.id.item_pessoa_nome_lista);
            cliente_lista_telefone=itemView.findViewById(R.id.item_pessoa_telefone_lista);
            cliente_lista_aniversario =itemView.findViewById(R.id.item_pessoa_aniversario_lista);
            cliente_lista_email =itemView.findViewById(R.id.item_pessoa_email_lista);
            cliente_lista_ponto = itemView.findViewById(R.id.item_pessoa_pontos_lista);
            cliente_lista_falta = itemView.findViewById(R.id.item_pessoa_faltas_lista);




        }
    }
}
