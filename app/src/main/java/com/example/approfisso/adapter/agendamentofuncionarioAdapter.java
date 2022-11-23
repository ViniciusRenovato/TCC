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
import com.example.approfisso.ediçao.cadastro_agendamento_editar;
import com.example.approfisso.ediçao.cadastro_funcionario_editar;
import com.example.approfisso.entidades.Agendamento;
import com.example.approfisso.entidades.Agendamento_Encerrado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
        agendamentoViewHolder.agendamentonomefuncionario = agendamento.getFuncionario();

        agendamentoViewHolder.agendamentohora.setText(agendamento.getHora_agendamento());
        agendamentoViewHolder.agendamentodia.setText(agendamento.getDia_agendamento());
        agendamentoViewHolder.agendamentovalor.setText(agendamento.getValor_servico());
        agendamentoViewHolder.agendamentoservico.setText(agendamento.getServicos());
        agendamentoViewHolder.agendamentoclientenome.setText(agendamento.getNome_cliente());


        agendamentoViewHolder.agendamentoconcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                String current = agendamento.getLogin_cliente();


                String id_funcionario_agendamento_ganho = agendamento.getId_funcionario();
                String mes_ano_listagem =agendamento.getDia_agendamento().substring(3,10);
                String ano_ganho_listagem =agendamento.getDia_agendamento().substring(6,10);
                String mes_ganho_listagem = agendamento.getDia_agendamento().substring(3,5);

                DatabaseReference databaseReference_funcionario_listagem = FirebaseDatabase.getInstance().getReference("Agendamento_Encerrado").child(ano_ganho_listagem).child(mes_ganho_listagem);
                databaseReference_funcionario_listagem.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                        for (DataSnapshot filtra_ususario : snapshot.getChildren()){


                                    if (snapshot.exists()) {

                                        for (DataSnapshot calculo_mensal : snapshot.getChildren()) {

                                            Agendamento_Encerrado agendamento_encerrado = snapshot.getValue(Agendamento_Encerrado.class);

//

                                            if ( calculo_mensal.child("id_funcionario").getValue().toString().equals(agendamento.getId_funcionario())) {

                                                double ganho_soma_mensal = Double.parseDouble(calculo_mensal.child("ganho_funcionario").getValue().toString());
                                                double valor_servico_ganho = Double.parseDouble(agendamento.getValor_servico());
                                                double ganho_funcionario = (valor_servico_ganho / 100.0f) * 50;
//                                    double ganho_estabelecimento = (valor_servico_ganho / 100.0f) * 50;
                                                double resultado_ganho = ganho_funcionario + ganho_soma_mensal;

                                                String ano_ganho = agendamento.getDia_agendamento().substring(6, 10);
                                                String mes_ganho = agendamento.getDia_agendamento().substring(3, 5);
//                                    String mes_ano = agendamento.getDia_agendamento().substring(3, 10);
                                                HashMap ganho_funcionario_map = new HashMap();
                                                ganho_funcionario_map.put("nome_funcionario", agendamento.getFuncionario());
                                                ganho_funcionario_map.put("ganho_funcionario", resultado_ganho);
                                                DatabaseReference databaseReference_funcionario = FirebaseDatabase.getInstance().getReference("Agendamento_Encerrado");
                                                databaseReference_funcionario.child(ano_ganho).child(mes_ganho).child(id_funcionario_agendamento_ganho).updateChildren(ganho_funcionario_map).addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {

                                                        return;
                                                    }
                                                });


//


                                            }else {



                                                    String mes_ganho = agendamento.getDia_agendamento().substring(6, 10);
                                                    String ano_ganho = agendamento.getDia_agendamento().substring(3, 5);
                                                    double valor_servico_ganho = Double.parseDouble(agendamento.getValor_servico());
                                                    double ganho_funcionario = (valor_servico_ganho / 100.0f) * 50;


                                                    Agendamento_Encerrado agendamentos_encerrados_salvar;

                                                    agendamentos_encerrados_salvar = new Agendamento_Encerrado();
                                                    agendamentos_encerrados_salvar.setId_funcionario(agendamento.getId_funcionario());
                                                    agendamentos_encerrados_salvar.setNome_funcionario(agendamento.getFuncionario());
                                                    agendamentos_encerrados_salvar.setGanho_funcionario((long) ganho_funcionario);
                                                    agendamentos_encerrados_salvar.setAno_agendamento_encerrado(mes_ganho);
                                                    agendamentos_encerrados_salvar.setMes_agendamento_encerrado(ano_ganho);
                                                    Agendamento_Encerrado.salvaAgendamentoEncerrado(agendamentos_encerrados_salvar);



                                            }
                                        }
                                    }








                            }







                            return;

                        }else{

                            String mes_ganho = agendamento.getDia_agendamento().substring(6, 10);
                            String ano_ganho = agendamento.getDia_agendamento().substring(3, 5);
                            double valor_servico_ganho = Double.parseDouble(agendamento.getValor_servico());
                            double ganho_funcionario = (valor_servico_ganho / 100.0f) * 50;


                            Agendamento_Encerrado agendamentos_encerrados_salvar;

                            agendamentos_encerrados_salvar = new Agendamento_Encerrado();
                            agendamentos_encerrados_salvar.setId_funcionario(agendamento.getId_funcionario());
                            agendamentos_encerrados_salvar.setNome_funcionario(agendamento.getFuncionario());
                            agendamentos_encerrados_salvar.setGanho_funcionario((long) ganho_funcionario);
                            agendamentos_encerrados_salvar.setAno_agendamento_encerrado(mes_ganho);
                            agendamentos_encerrados_salvar.setMes_agendamento_encerrado(ano_ganho);
                            Agendamento_Encerrado.salvaAgendamentoEncerrado(agendamentos_encerrados_salvar);



                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                DatabaseReference databaseReference_estabelecimento_listagem = FirebaseDatabase.getInstance().getReference("Agendamento_Encerrado_Estabelecimento").child(ano_ganho_listagem).child(mes_ganho_listagem);
                databaseReference_estabelecimento_listagem.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()){

                            for (DataSnapshot calculo_mensal : snapshot.getChildren()){

                                Agendamento_Encerrado agendamento_encerrado = snapshot.getValue(Agendamento_Encerrado.class);



                                double ganho_soma_mensal = Double.parseDouble(calculo_mensal.child("ganho_estabelecimento").getValue().toString());
                                double valor_servico_ganho = Double.parseDouble(agendamento.getValor_servico());
                                double ganho_estabelecimento = (valor_servico_ganho/100.0f) *50;
                                double resultado_ganho = ganho_estabelecimento + ganho_soma_mensal;

                                String ano_ganho =agendamento.getDia_agendamento().substring(6,10);
                                String mes_ganho = agendamento.getDia_agendamento().substring(3,5);
                                HashMap ganho_estabelecimento_map = new HashMap();
                                ganho_estabelecimento_map.put("nome_estabelecimento","Salão");
                                ganho_estabelecimento_map.put("ganho_estabelecimento",resultado_ganho);
                                DatabaseReference databaseReference_estabelecimento = FirebaseDatabase.getInstance().getReference("Agendamento_Encerrado_Estabelecimento");
                                databaseReference_estabelecimento.child(ano_ganho).child(mes_ganho).child("Estabelecimento").updateChildren(ganho_estabelecimento_map).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {

                                    }
                                });

                                return;

                            }

                        }else{
                            String mes_ganho =agendamento.getDia_agendamento().substring(6,10);
                            String ano_ganho = agendamento.getDia_agendamento().substring(3,5);
                            double valor_servico_ganho = Double.parseDouble(agendamento.getValor_servico());
                            double ganho_estabelecimento = (valor_servico_ganho/100.0f) *50;
                            String nome_salao = "salão";

                            Agendamento_Encerrado agendamentos_encerrados_salvar_estabelecimento;

                            agendamentos_encerrados_salvar_estabelecimento = new Agendamento_Encerrado();

                            agendamentos_encerrados_salvar_estabelecimento.setNome_estabelecimento(nome_salao);
                            agendamentos_encerrados_salvar_estabelecimento.setGanho_estabelecimento(String.valueOf(ganho_estabelecimento));
                            agendamentos_encerrados_salvar_estabelecimento.setAno_agendamento_encerrado(mes_ganho);
                            agendamentos_encerrados_salvar_estabelecimento.setMes_agendamento_encerrado(ano_ganho);
                            Agendamento_Encerrado.salvaAgendamentoEncerradoEstabelecimento(agendamentos_encerrados_salvar_estabelecimento);

                            return;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





















                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuários");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot usuario_info : snapshot.getChildren()){

                            Usuario usuario = snapshot.getValue(Usuario.class);
                            String UID_usuario = usuario_info.child("id_usuario").getValue().toString();

                            if (current.equals(UID_usuario)) {


                                String id_usuario_agendamento = usuario_info.child("id_usuario").getValue().toString();
                                Integer pontos_usuario = Integer.parseInt(usuario_info.child("pontos_usuario").getValue().toString());




                                DatabaseReference databaseReference_estabelecimento = FirebaseDatabase.getInstance().getReference("Agendamento_Encerrado");


                                Double ponto = Double.parseDouble(pontos_usuario.toString()) ;
                                Double pontuacao = Double.parseDouble(ponto.toString()) ;
                                Double resultado = pontuacao + agendamento.getPonto_agendamento();

                                HashMap ponto_cliente = new HashMap();
                                ponto_cliente.put("pontos_usuario",resultado);

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuários");
                                databaseReference.child(id_usuario_agendamento).updateChildren(ponto_cliente).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()){

                                            Toast.makeText(view.getContext(),"Serviço fechado.",Toast.LENGTH_SHORT).show();
                                            FirebaseDatabase.getInstance().getReference().child("Agendamento").child(agendamento.getId_agendamento()).removeValue();

                                        }else{
                                            Toast.makeText(view.getContext(),"falha na adição de pontos",Toast.LENGTH_SHORT).show();

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




//                db.collection("usuários")
//                        .whereEqualTo("id",current).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()){
//                                    for(DocumentSnapshot document : task.getResult()){
//
////                                        pontuacao.setText((CharSequence) document.get("nome"));
////                                        Integer pontuacao = (Integer) document.get("pontos");
//                                        Double ponto = Double.parseDouble(document.get("pontos").toString()) ;
//
//                                        Double pontuacao = Double.parseDouble(ponto.toString()) ;
////                                        ponto.doubleValue();
//
//                                        Double resultado = pontuacao + agendamento.getPonto_agendamento();
//
//
//                                        Map<String,Object> map = new HashMap<>();
//                                        map.put("pontos",resultado);
//
//                                        db.collection("usuários")
//                                                .document(agendamento.getLogin_cliente()).set(map, SetOptions.merge());
//
//
//                                        FirebaseDatabase.getInstance().getReference().child("Agendamento").child(agendamento.getId_agendamento()).removeValue();
//
//
//
//                                    }
//                                }
//                            }
//                        });
//


//
//                Intent it = new Intent(view.getContext(), cadastro_agendamento_editar.class);
//
//                it.putExtra("idagendamento",agendamento.getId_agendamento());
//                it.putExtra("diaagendamento",agendamento.getDia_agendamento());
//                it.putExtra("horaagendamento",agendamento.getHora_agendamento());
//                it.putExtra("idfuncionario",agendamento.getId_funcionario());
//                it.putExtra("nomefuncionario",agendamento.getFuncionario());
//                it.putExtra("logincliente",agendamento.getLogin_cliente());
//                it.putExtra("nomecliente",agendamento.getNome_cliente());
//                it.putExtra("nomeservico",agendamento.getServicos());
//
//                view.getContext().startActivity(it);

            }
        });

        agendamentoViewHolder.agendamentofaltou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String current = agendamento.getLogin_cliente();

                AlertDialog.Builder builder = new AlertDialog.Builder(agendamentoViewHolder.agendamentohora.getContext());
                builder.setTitle("Você tem certeza?");
                builder.setMessage("Informação deletada nao pode ser recuperada.");

                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {



                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuários");
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot usuario_info : snapshot.getChildren()){

                                    Usuario usuario = snapshot.getValue(Usuario.class);
                                    String UID_usuario = usuario_info.child("id_usuario").getValue().toString();

                                    if (current.equals(UID_usuario)) {


                                        String id_usuario_agendamento = usuario_info.child("id_usuario").getValue().toString();
                                        Integer faltas_usuario = Integer.parseInt(usuario_info.child("faltas_usuario").getValue().toString());




                                        DatabaseReference databaseReference_estabelecimento = FirebaseDatabase.getInstance().getReference("Agendamento_Encerrado");


                                        Double ponto = Double.parseDouble(faltas_usuario.toString()) ;
                                        Double pontuacao = Double.parseDouble(ponto.toString()) ;
                                        Double resultado = pontuacao + 1;

                                        HashMap ponto_cliente = new HashMap();
                                        ponto_cliente.put("faltas_usuario",resultado);

                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuários");
                                        databaseReference.child(id_usuario_agendamento).updateChildren(ponto_cliente).addOnCompleteListener(new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                if (task.isSuccessful()){

                                                    Toast.makeText(view.getContext(),"Serviço fechado.",Toast.LENGTH_SHORT).show();
                                                    FirebaseDatabase.getInstance().getReference().child("Agendamento").child(agendamento.getId_agendamento()).removeValue();

                                                }else{
                                                    Toast.makeText(view.getContext(),"falha na adição de pontos",Toast.LENGTH_SHORT).show();

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
        private TextView agendamentovalor;
        private TextView agendamentoservico;
        private TextView agendamentoclientenome;
        private String agendamentonomefuncionario;
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
            agendamentovalor=itemView.findViewById(R.id.item_agendamento_valor_estabelecimento);
            agendamentofaltou =(Button)itemView.findViewById(R.id.button_faltou_agendamento_estabelecimento);
            agendamentoconcluir =(Button)itemView.findViewById(R.id.button_concluir_agendamento);
        }
    }
}
