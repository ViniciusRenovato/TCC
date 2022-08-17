package com.example.approfisso.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.classes.empregos;

import java.util.List;

public class EmpregoAdapter extends RecyclerView.Adapter<EmpregoAdapter.empregosHolder> {


    List<empregos> dados;

    public EmpregoAdapter(List<empregos> trabalhos) {
        this.dados=trabalhos;
    }

    @NonNull
    @Override
    public empregosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas,viewGroup,false);
        return new empregosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull empregosHolder trabalhoViewHolder, int i) {
        empregos trabaio = dados.get(i);
        trabalhoViewHolder.nomeProfissao.setText(trabaio.getArea_da_profissao());
        trabalhoViewHolder.nomeCidade.setText(trabaio.getCidade());
        trabalhoViewHolder.nomeSalario.setText(trabaio.getSalario());
        trabalhoViewHolder.nomeEmail.setText(trabaio.getEmail());
        trabalhoViewHolder.nomePeriodo.setText(trabaio.getPeriodo());
    }
    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class empregosHolder extends RecyclerView.ViewHolder {
        private TextView nomeProfissao;
        private TextView nomeCidade;
        private TextView nomeSalario;
        private TextView nomeEmail;
        private TextView nomePeriodo;

        public empregosHolder(@NonNull View itemView) {
            super(itemView);
            nomeProfissao=itemView.findViewById(R.id.nomeItemLinha);
            nomeCidade=itemView.findViewById(R.id.nomeItemLinha2);
            nomeSalario=itemView.findViewById(R.id.nomeItemLinha3);
            nomeEmail=itemView.findViewById(R.id.nomeItemLinha4);
            nomePeriodo=itemView.findViewById(R.id.nomeItemLinha5);

        }
    }
}





















//    public EmpregoAdapter(Object p0) {
//    }
//}
