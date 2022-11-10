package com.example.approfisso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.classes.Usuario;
import com.example.approfisso.entidades.User;

import java.util.ArrayList;
import java.util.List;

public class pontosclientesAdapter extends RecyclerView.Adapter<pontosclientesAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public pontosclientesAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public pontosclientesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.linhas_pontos_clientes,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull pontosclientesAdapter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.pontosclientenome.setText(user.getNome());
        holder.pontosclienteponto.setText(user.getPontos());
        holder.pontosresgate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView pontosclientenome;
        TextView pontosclienteponto;
        Button pontosresgate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pontosclientenome=itemView.findViewById(R.id.item_agendamento_hora);
            pontosclienteponto=itemView.findViewById(R.id.item_agendamento_dia);
            pontosresgate =(Button)itemView.findViewById(R.id.button_resgatar_pontos);


        }
    }


//    Context context;
//    List<Usuario> dados;
//    public pontosclientesAdapter(Context context, List<Usuario> usuarios) {
//
//        this.context = this.context;
//        this.dados=usuarios;
//    }
//    @NonNull
//    @Override
//    public pontosclientesAdapter.AgendamentoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
//        View view =layoutInflater.inflate(R.layout.linhas_pontos_clientes,viewGroup,false);
//        return new pontosclientesAdapter.AgendamentoHolder(view);
//    }
//    @Override
//    public void onBindViewHolder(@NonNull pontosclientesAdapter.AgendamentoHolder agendamentoViewHolder, int i) {
//        Usuario usuario = dados.get(i);
//        agendamentoViewHolder.pontosclientenome.setText(usuario.getNome_usuario());
//        agendamentoViewHolder.pontosclienteponto.setText(usuario.getPontos_usuario());
//
//
//        agendamentoViewHolder.pontosresgate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent it = new Intent(view.getContext(), cadastro_agendamento_editar.class);
////                progressBarAgendamento = findViewById(R.id.progressbarPrincipal);
////                progressBarAgendamento.setVisibility(View.INVISIBLE);
//
////                it.putExtra("idagendamento",agendamento.getId_agendamento());
////                it.putExtra("diaagendamento",agendamento.getDia_agendamento());
////                it.putExtra("horaagendamento",agendamento.getHora_agendamento());
////                it.putExtra("idfuncionario",agendamento.getId_funcionario());
////                it.putExtra("nomefuncionario",agendamento.getFuncionario());
////                it.putExtra("logincliente",agendamento.getLogin_cliente());
////                it.putExtra("nomecliente",agendamento.getNome_cliente());
////                it.putExtra("nomeservico",agendamento.getServicos());
//
////                view.getContext().startActivity(it);
//
//            }
//        });
//
////        agendamentoViewHolder.agendamentodelete.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                AlertDialog.Builder builder = new AlertDialog.Builder(agendamentoViewHolder.agendamentohora.getContext());
////                builder.setTitle("Você tem certeza?");
////                builder.setMessage("Informação deletada nao pode ser recuperada.");
////
////                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int which) {
////                        FirebaseDatabase.getInstance().getReference().child("Agendamento").child(agendamento.getId_agendamento()).removeValue();
////                    }
////                });
////
////
////                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int which) {
////                        Toast.makeText(agendamentoViewHolder.agendamentohora.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
////                    }
////                });
////                builder.show();
////            }
////        });
//
//    }
//    @Override
//    public int getItemCount() {
//        return dados.size();
//    }
//    public class AgendamentoHolder extends RecyclerView.ViewHolder {
//        private TextView pontosclientenome;
//        private TextView pontosclienteponto;
//
//
//        private Button pontosresgate;
//
//        public AgendamentoHolder(@NonNull View itemView) {
//            super(itemView);
//            pontosclientenome=itemView.findViewById(R.id.item_agendamento_hora);
//            pontosclienteponto=itemView.findViewById(R.id.item_agendamento_dia);
//
//
//            pontosresgate =(Button)itemView.findViewById(R.id.button_resgatar_pontos);
//        }
//    }
}
