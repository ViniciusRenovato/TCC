package com.example.approfisso.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Funcionario;
import com.example.approfisso.entidades.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class agendamentoAdapter extends RecyclerView.Adapter<agendamentoAdapter.AgendamentoHolder> {








    List<Agendamento> dados;
    public agendamentoAdapter(List<Agendamento> agendamento) {
        this.dados=agendamento;
    }
    @NonNull
    @Override
    public agendamentoAdapter.AgendamentoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_agendamento,viewGroup,false);
        return new agendamentoAdapter.AgendamentoHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull agendamentoAdapter.AgendamentoHolder agendamentoViewHolder, int i) {
        Agendamento agendamento = dados.get(i);
        agendamentoViewHolder.agendamentohora.setText(agendamento.getHora_agendamento());
        agendamentoViewHolder.agendamentodia.setText(agendamento.getDia_agendamento());
        agendamentoViewHolder.agendamentofuncionario.setText(agendamento.getFuncionario());
        agendamentoViewHolder.agendamentoservico.setText(agendamento.getServicos());

    }
    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class AgendamentoHolder extends RecyclerView.ViewHolder {
        private TextView agendamentohora;
        private TextView agendamentodia;
        private TextView agendamentofuncionario;
        private TextView agendamentoservico;


        public AgendamentoHolder(@NonNull View itemView) {
            super(itemView);
            agendamentohora=itemView.findViewById(R.id.item_agendamento_hora);
            agendamentodia=itemView.findViewById(R.id.item_agendamento_dia);
            agendamentofuncionario=itemView.findViewById(R.id.item_agendamento_funcionario);
            agendamentoservico=itemView.findViewById(R.id.item_agendamento_servico);
        }
    }
}
