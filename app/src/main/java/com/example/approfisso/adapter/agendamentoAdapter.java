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
import com.example.approfisso.ediçao.cadastro_agendamento_editar;
import com.example.approfisso.entidades.Agendamento;
import com.google.firebase.database.FirebaseDatabase;
import com.example.approfisso.cadastrado.agendamento_cadastrado;

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

        agendamentoViewHolder.agendamentoeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(view.getContext(), cadastro_agendamento_editar.class);
//                progressBarAgendamento = findViewById(R.id.progressbarPrincipal);
//                progressBarAgendamento.setVisibility(View.INVISIBLE);

                it.putExtra("idagendamento",agendamento.getId_agendamento());
                it.putExtra("diaagendamento",agendamento.getDia_agendamento());
                it.putExtra("horaagendamento",agendamento.getHora_agendamento());
                it.putExtra("idfuncionario",agendamento.getId_funcionario());
                it.putExtra("nomefuncionario",agendamento.getFuncionario());
                it.putExtra("logincliente",agendamento.getLogin_cliente());
                it.putExtra("nomecliente",agendamento.getNome_cliente());
                it.putExtra("nomeservico",agendamento.getServicos());

                view.getContext().startActivity(it);

            }
        });

        agendamentoViewHolder.agendamentodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(agendamentoViewHolder.agendamentohora.getContext());
                builder.setTitle("Você tem certeza?");
                builder.setMessage("Informação deletada nao pode ser recuperada.");

                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Agendamento").child(agendamento.getId_agendamento()).removeValue();
                    }
                });


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(agendamentoViewHolder.agendamentohora.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

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
        private Button agendamentodelete;
        private Button agendamentoeditar;

        public AgendamentoHolder(@NonNull View itemView) {
            super(itemView);
            agendamentohora=itemView.findViewById(R.id.item_agendamento_hora);
            agendamentodia=itemView.findViewById(R.id.item_agendamento_dia);
            agendamentofuncionario=itemView.findViewById(R.id.item_agendamento_funcionario);
            agendamentoservico=itemView.findViewById(R.id.item_agendamento_servico);
            agendamentodelete =(Button)itemView.findViewById(R.id.button_remover_agendamento);
            agendamentoeditar =(Button)itemView.findViewById(R.id.button_editar_agendamento);
        }
    }
}
