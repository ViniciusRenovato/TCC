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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class agendamentofuncionarioAdapter extends RecyclerView.Adapter<agendamentofuncionarioAdapter.AgendamentoHolder> {




    List<Agendamento> dados;
    public agendamentofuncionarioAdapter(List<Agendamento> agendamento) {
        this.dados=agendamento;
    }
    @NonNull
    @Override
    public agendamentofuncionarioAdapter.AgendamentoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_estabelecimento_agendamento,viewGroup,false);
        return new agendamentofuncionarioAdapter.AgendamentoHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull agendamentofuncionarioAdapter.AgendamentoHolder agendamentoViewHolder, int i) {
        Agendamento agendamento = dados.get(i);

        agendamentoViewHolder.agendamentofuncionarioid = agendamento.getId_funcionario();
        agendamentoViewHolder.agendamentoclienteid = agendamento.getLogin_cliente();
        agendamentoViewHolder.agendamentoponto = agendamento.getPonto_agendamento();

        agendamentoViewHolder.agendamentohora.setText(agendamento.getHora_agendamento());
        agendamentoViewHolder.agendamentodia.setText(agendamento.getDia_agendamento());

        agendamentoViewHolder.agendamentoservico.setText(agendamento.getServicos());
        agendamentoViewHolder.agendamentoclientenome.setText(agendamento.getNome_cliente());


        agendamentoViewHolder.agendamentoconcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("usuários")
                        .whereEqualTo("id",agendamento.getLogin_cliente()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for(DocumentSnapshot document : task.getResult()){



                                        double ponto = (double) document.get("pontos");

                                        ponto = ponto + agendamento.getPonto_agendamento();


                                        Map<String,Object> map = new HashMap<>();
                                        map.put("pontos",ponto);

                                        db.collection("usuários")
                                                .document(agendamento.getLogin_cliente()).set(map, SetOptions.merge());

                                        FirebaseDatabase.getInstance().getReference().child("Agendamento").child(agendamento.getId_agendamento()).removeValue();




                                    }
                                }
                            }
                        });




                Intent it = new Intent(view.getContext(), cadastro_agendamento_editar.class);

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

        agendamentoViewHolder.agendamentofaltou.setOnClickListener(new View.OnClickListener() {
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
        private TextView agendamentoclientenome;
        private String agendamentoclienteid;
        private String agendamentofuncionarioid;
        private Integer agendamentoponto;
        private Button agendamentofaltou;
        private Button agendamentoconcluir;

        public AgendamentoHolder(@NonNull View itemView) {
            super(itemView);
            agendamentohora=itemView.findViewById(R.id.item_agendamento_hora_estabelecimento);
            agendamentodia=itemView.findViewById(R.id.item_agendamento_dia_estabelecimento);
            agendamentoservico=itemView.findViewById(R.id.item_agendamento_servico_estabelecimento);
            agendamentoclientenome=itemView.findViewById(R.id.item_agendamento_cliente_estabelecimento);
            agendamentofaltou =(Button)itemView.findViewById(R.id.button_faltou_agendamento_estabelecimento);
            agendamentoconcluir =(Button)itemView.findViewById(R.id.button_concluir_agendamento);
        }
    }
}
