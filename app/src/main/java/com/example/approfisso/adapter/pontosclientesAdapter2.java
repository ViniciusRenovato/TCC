//package com.example.approfisso.adapter;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.approfisso.R;
//import com.example.approfisso.classes.Usuario;
//import com.example.approfisso.ediçao.cadastro_funcionario_editar;
//import com.example.approfisso.entidades.Funcionario;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.List;
//
//public class pontosclientesAdapter2 extends RecyclerView.Adapter<pontosclientesAdapter2.FuncionarioHolder> {
//
//
//
//
//
//
//
//
//
//
//
//    List<Usuario> dados;
//
//    public pontosclientesAdapter2(List<Usuario> usuario) {
//        this.dados=usuario;
//    }
//
//    @NonNull
//    @Override
//    public funcionarioAdapter.FuncionarioHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
//        View view =layoutInflater.inflate(R.layout.linhas_funcionario,viewGroup,false);
//        return new funcionarioAdapter.FuncionarioHolder(view);
//    }
//    @Override
//    public void onBindViewHolder(@NonNull funcionarioAdapter.FuncionarioHolder funcionarioViewHolder, int i ) {
//        Funcionario funcionario = dados.get(i);
//        funcionarioViewHolder.funcionarionome.setText(funcionario.getNome_funcionario());
//        funcionarioViewHolder.funcionariofuncao.setText(funcionario.getFuncao_funcionario());
//        funcionarioViewHolder.funcionariotelefone.setText(funcionario.getTelefone_funcionario());
//        funcionarioViewHolder.funcionarioaniversario.setText(funcionario.getAniversario_funcionario());
//        funcionarioViewHolder.funcionarioemail.setText(funcionario.getEmail_funcionario());
//
//        funcionarioViewHolder.funcionarioeditar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent it = new Intent(view.getContext(), cadastro_funcionario_editar.class);
//
//
//
//                it.putExtra("funcionarioid",funcionario.getId_funcionario());
//                it.putExtra("funcionarionome",funcionario.getNome_funcionario());
//                it.putExtra("funcionariofuncao",funcionario.getFuncao_funcionario());
//                it.putExtra("funcionariotelefone",funcionario.getTelefone_funcionario());
//                it.putExtra("funcionarioaniversario",funcionario.getAniversario_funcionario());
//                it.putExtra("funcionarioemail",funcionario.getEmail_funcionario());
//
//                view.getContext().startActivity(it);
//
//
////                final DialogPlus dialogPlus = DialogPlus.newDialog(funcionarioViewHolder.funcionariosobrenome.getContext())
////                        .setContentHolder(new ViewHolder(R.layout.update_funcionario_popup))
////                        .setExpanded(true,1500)
////                        .create();
////
////
////                View view1 = dialogPlus.getHolderView();
////                EditText name = view1.findViewById(R.id.txtnome_funcionario);
////                EditText sobrenome = view1.findViewById(R.id.txtsobrenome_funcionario);
////                EditText telefone = view1.findViewById(R.id.txttelefone_funcionario);
////                EditText aniversario = view1.findViewById(R.id.txtaniversario_funcionario);
////                EditText email = view1.findViewById(R.id.txtemail_funcionario);
////
////                dialogPlus.show();
////
////                Button funcionarioeditar = view1.findViewById(R.id.btneditar_funcionario);
////
////                name.setText(funcionario.getNome_funcionario());
////                sobrenome.setText(funcionario.getSobrenome_funcionario());
////                telefone.setText(funcionario.getTelefone_funcionario());
////                aniversario.setText(funcionario.getAniversario_funcionario());
////                email.setText(funcionario.getEmail_funcionario());
////
////                dialogPlus.show();
////
////                funcionarioeditar.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        Map<String,Object> map = new HashMap<>();
////                        map.put("nome_funcionario",name.getText().toString());
////                        map.put("sobrenome_funcionario",sobrenome.getText().toString());
////                        map.put("telefone_funcionario",telefone.getText().toString());
////                        map.put("aniversario_funcionario",aniversario.getText().toString());
////                        map.put("email_funcionario",email.getText().toString());
////
////                        FirebaseDatabase.getInstance().getReference().child("Funcionario")
////                                .child(funcionario.getId_funcionario()).updateChildren(map)
////                                .addOnSuccessListener(new OnSuccessListener<Void>(){
////                                    @Override
////                                    public void onSuccess(Void unused){
////                                        Toast.makeText(funcionarioViewHolder.funcionarionome.getContext(),"Edição Concluida", Toast.LENGTH_SHORT).show();
////                                        dialogPlus.dismiss();
////                                    }
////                        })
////                                .addOnFailureListener(new OnFailureListener() {
////                                    @Override
////                                    public void onFailure(@NonNull Exception e) {
////                                        Toast.makeText(funcionarioViewHolder.funcionarionome.getContext(),"Erro na Edição", Toast.LENGTH_SHORT).show();
////                                    }
////                                });
////                    }
////                });
//            }
//        });
//
//        funcionarioViewHolder.funcionariodelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(funcionarioViewHolder.funcionarionome.getContext());
//                builder.setTitle("Você tem certeza?");
//                builder.setMessage("Informação deletada nao pode ser recuperada.");
//
//                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        FirebaseDatabase.getInstance().getReference().child("Funcionario").child(funcionario.getId_funcionario()).removeValue();
//                    }
//                });
//
//
//                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        Toast.makeText(funcionarioViewHolder.funcionarionome.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.show();
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return dados.size();
//    }
//    public class FuncionarioHolder extends RecyclerView.ViewHolder {
//        private TextView funcionarionome;
//
//        private TextView funcionariofuncao;
//        private TextView funcionariotelefone;
//        private TextView funcionarioaniversario;
//        private TextView funcionarioemail;
//        private Button funcionariodelete;
//        private Button funcionarioeditar;
//
//
//        public FuncionarioHolder(@NonNull View itemView) {
//            super(itemView);
//            funcionarionome=itemView.findViewById(R.id.item_funcionario_nome);
//
//            funcionariofuncao=itemView.findViewById(R.id.item_funcionario_funcao);
//            funcionariotelefone=itemView.findViewById(R.id.item_funcionario_telefone);
//            funcionarioaniversario=itemView.findViewById(R.id.item_funcionario_aniversario);
//            funcionarioemail=itemView.findViewById(R.id.item_funcionario_email);
//
//            funcionariodelete =(Button)itemView.findViewById(R.id.button_remover_funcionario);
//            funcionarioeditar =(Button)itemView.findViewById(R.id.button_editar_funcionario);
//
//        }
//    }
//
////    Context context;
////    ArrayList<User> userArrayList;
////
////    public pontosclientesAdapter(Context context, ArrayList<User> userArrayList) {
////        this.context = context;
////        this.userArrayList = userArrayList;
////    }
////
////    @NonNull
////    @Override
////    public pontosclientesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////
////        View v = LayoutInflater.from(context).inflate(R.layout.linhas_pontos_clientes,parent,false);
////
////        return new MyViewHolder(v);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull pontosclientesAdapter.MyViewHolder holder, int position) {
////
////        User user = userArrayList.get(position);
////
////        holder.pontosclientenome.setText(user.getNome());
////        holder.pontosclienteponto.setText(user.getPontos());
////        holder.pontosresgate.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////            }
////        });
////
////    }
////
////    @Override
////    public int getItemCount() {
////        return userArrayList.size();
////    }
////
////    public static class MyViewHolder extends  RecyclerView.ViewHolder{
////
////        TextView pontosclientenome;
////        TextView pontosclienteponto;
////        Button pontosresgate;
////
////        public MyViewHolder(@NonNull View itemView) {
////            super(itemView);
////
////            pontosclientenome=itemView.findViewById(R.id.item_agendamento_hora);
////            pontosclienteponto=itemView.findViewById(R.id.item_agendamento_dia);
////            pontosresgate =(Button)itemView.findViewById(R.id.button_resgatar_pontos);
////
////
////        }
////    }
////
//
////    Context context;
////    List<Usuario> dados;
////    public pontosclientesAdapter(Context context, List<Usuario> usuarios) {
////
////        this.context = this.context;
////        this.dados=usuarios;
////    }
////    @NonNull
////    @Override
////    public pontosclientesAdapter.AgendamentoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
////        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
////        View view =layoutInflater.inflate(R.layout.linhas_pontos_clientes,viewGroup,false);
////        return new pontosclientesAdapter.AgendamentoHolder(view);
////    }
////    @Override
////    public void onBindViewHolder(@NonNull pontosclientesAdapter.AgendamentoHolder agendamentoViewHolder, int i) {
////        Usuario usuario = dados.get(i);
////        agendamentoViewHolder.pontosclientenome.setText(usuario.getNome_usuario());
////        agendamentoViewHolder.pontosclienteponto.setText(usuario.getPontos_usuario());
////
////
////        agendamentoViewHolder.pontosresgate.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
//////                Intent it = new Intent(view.getContext(), cadastro_agendamento_editar.class);
//////                progressBarAgendamento = findViewById(R.id.progressbarPrincipal);
//////                progressBarAgendamento.setVisibility(View.INVISIBLE);
////
//////                it.putExtra("idagendamento",agendamento.getId_agendamento());
//////                it.putExtra("diaagendamento",agendamento.getDia_agendamento());
//////                it.putExtra("horaagendamento",agendamento.getHora_agendamento());
//////                it.putExtra("idfuncionario",agendamento.getId_funcionario());
//////                it.putExtra("nomefuncionario",agendamento.getFuncionario());
//////                it.putExtra("logincliente",agendamento.getLogin_cliente());
//////                it.putExtra("nomecliente",agendamento.getNome_cliente());
//////                it.putExtra("nomeservico",agendamento.getServicos());
////
//////                view.getContext().startActivity(it);
////
////            }
////        });
////
//////        agendamentoViewHolder.agendamentodelete.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                AlertDialog.Builder builder = new AlertDialog.Builder(agendamentoViewHolder.agendamentohora.getContext());
//////                builder.setTitle("Você tem certeza?");
//////                builder.setMessage("Informação deletada nao pode ser recuperada.");
//////
//////                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
//////                    @Override
//////                    public void onClick(DialogInterface dialogInterface, int which) {
//////                        FirebaseDatabase.getInstance().getReference().child("Agendamento").child(agendamento.getId_agendamento()).removeValue();
//////                    }
//////                });
//////
//////
//////                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//////                    @Override
//////                    public void onClick(DialogInterface dialogInterface, int which) {
//////                        Toast.makeText(agendamentoViewHolder.agendamentohora.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
//////                    }
//////                });
//////                builder.show();
//////            }
//////        });
////
////    }
////    @Override
////    public int getItemCount() {
////        return dados.size();
////    }
////    public class AgendamentoHolder extends RecyclerView.ViewHolder {
////        private TextView pontosclientenome;
////        private TextView pontosclienteponto;
////
////
////        private Button pontosresgate;
////
////        public AgendamentoHolder(@NonNull View itemView) {
////            super(itemView);
////            pontosclientenome=itemView.findViewById(R.id.item_agendamento_hora);
////            pontosclienteponto=itemView.findViewById(R.id.item_agendamento_dia);
////
////
////            pontosresgate =(Button)itemView.findViewById(R.id.button_resgatar_pontos);
////        }
////    }
//}
