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
import com.example.approfisso.ediçao.cadastro_funcao_editar;
import com.example.approfisso.entidades.Agendamento_Encerrado;
import com.example.approfisso.entidades.Funcao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class ganhoAdapter extends RecyclerView.Adapter<ganhoAdapter.FuncaoHolder> {



    List<Agendamento_Encerrado> dados;

    public ganhoAdapter(List<Agendamento_Encerrado> funcao) {
        this.dados=funcao;
    }

    @NonNull
    @Override
    public FuncaoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_funcionario_ganhos_mensais,viewGroup,false);
        return new FuncaoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FuncaoHolder funcaoViewHolder, int i) {
        Agendamento_Encerrado agendamento_encerrado = dados.get(i);


        funcaoViewHolder.ganhonome.setText(agendamento_encerrado.getNome_funcionario());
        funcaoViewHolder.ganhovalor.setText(agendamento_encerrado.getGanho_funcionario().toString());


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class FuncaoHolder extends RecyclerView.ViewHolder {


        private TextView ganhonome;
        private TextView ganhovalor;
        private DatabaseReference funcaoid;

        public FuncaoHolder(@NonNull View itemView) {
            super(itemView);

            ganhonome=itemView.findViewById(R.id.item_funcionario_nome_ganho_mensal);
            ganhovalor=itemView.findViewById(R.id.item_funcionario_valor_ganho_mensal);


        }
    }
}
