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
import com.google.firebase.database.FirebaseDatabase;

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
