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

import com.example.approfisso.ediçao.cadastro_servico_editar;
import com.example.approfisso.entidades.Servicos;
import com.google.firebase.database.FirebaseDatabase;


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

        serviViewHolder.servicoeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(view.getContext(), cadastro_servico_editar.class);
                view.getContext().startActivity(it);




//                final DialogPlus dialogPlus = DialogPlus.newDialog(serviViewHolder.serviconome.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.update_servico_popup))
//                        .setExpanded(true,1150)
//                        .create();
//
//
//                View view1 = dialogPlus.getHolderView();
//                EditText name = view1.findViewById(R.id.txtnome_servico);
//                EditText duracao = view1.findViewById(R.id.txtduracao_servico);
//                EditText valor = view1.findViewById(R.id.txtvalor_servico);
//                EditText funcao = view1.findViewById(R.id.txtfuncao_servico);
//
//                dialogPlus.show();
//
//                Button servicoeditar = view1.findViewById(R.id.btneditar_servico);
//
//                name.setText(servi.getNome_servico());
//                duracao.setText(servi.getDuracao_servico());
//                valor.setText(servi. getValor_servico());
//                funcao.setText(servi.getFuncao_servico());
//                //email.setText(funcionario.getEmail_funcionario());
//
//                dialogPlus.show();
//
//                servicoeditar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String,Object> map = new HashMap<>();
//                        map.put("nome_servico",name.getText().toString());
//                        map.put("duracao_servico",duracao.getText().toString());
//                        map.put("valor_servico",valor.getText().toString());
//                        map.put("funcao_servico",funcao.getText().toString());
//                        //map.put("email_funcionario",email.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Servicos")
//                                .child(servi.getId_servico()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>(){
//                                    @Override
//                                    public void onSuccess(Void unused){
//                                        Toast.makeText(serviViewHolder.serviconome.getContext(),"Edição Concluida", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(serviViewHolder.serviconome.getContext(),"Erro na Edição", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                    }
//                });
            }
        });

        serviViewHolder.servicodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(serviViewHolder.serviconome.getContext());
                builder.setTitle("Você tem certeza?");
                builder.setMessage("Informação deletada nao pode ser recuperada.");

                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Servicos").child(servi.getId_servico()).removeValue();
                    }
                });


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(serviViewHolder.serviconome.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
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
    public class ServicosHolder extends RecyclerView.ViewHolder {
        private TextView serviconome;
        private TextView servicoduracao;
        private TextView servicovalor;
        private TextView servicofuncao;
        private Button servicodelete;
        private Button servicoeditar;

        public ServicosHolder(@NonNull View itemView) {
            super(itemView);
            serviconome=itemView.findViewById(R.id.item_servico_nome);
            servicoduracao=itemView.findViewById(R.id.item_servico_duracao);
            servicovalor=itemView.findViewById(R.id.item_servico_preco);
            servicofuncao=itemView.findViewById(R.id.item_servico_funcao);
            servicodelete =(Button)itemView.findViewById(R.id.button_remover_servico);
            servicoeditar =(Button)itemView.findViewById(R.id.button_editar_servico);

        }
    }
}
