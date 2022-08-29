package com.example.approfisso.activity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;

import com.example.approfisso.entidades.Servicos;


import java.util.List;


public class servicoAdapter extends RecyclerView.Adapter<servicoAdapter.ServicosHolder> {

    List<Servicos> dados;

    public servicoAdapter(List<Servicos> servicos) {
        this.dados=servicos;
    }

    @NonNull
    @Override
    public ServicosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_servico,viewGroup,false);
        return new ServicosHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ServicosHolder serviViewHolder, int i) {
        Servicos servi = dados.get(i);
        serviViewHolder.serviconome.setText(servi.getNome_servico());
        serviViewHolder.servicoduracao.setText(servi.getDuracao_servico());
        serviViewHolder.servicovalor.setText(servi.getValor_servico());
        serviViewHolder.servicofuncao.setText(servi.getFuncao_servico());

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class ServicosHolder extends RecyclerView.ViewHolder {
        private TextView serviconome;
        private TextView servicoduracao;
        private TextView servicovalor;
        private TextView servicofuncao;
        private TextView txt_option_funcao;

        public ServicosHolder(@NonNull View itemView) {
            super(itemView);
            serviconome=itemView.findViewById(R.id.item_servico_nome);
            servicoduracao=itemView.findViewById(R.id.item_servico_duracao);
            servicovalor=itemView.findViewById(R.id.item_servico_preco);
            servicofuncao=itemView.findViewById(R.id.item_servico_funcao);
            txt_option_funcao=itemView.findViewById(R.id.txt_option_servico);

        }
    }
}
