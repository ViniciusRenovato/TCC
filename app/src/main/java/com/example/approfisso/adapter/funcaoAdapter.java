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
import com.example.approfisso.entidades.Funcao;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class funcaoAdapter extends RecyclerView.Adapter<funcaoAdapter.FuncaoHolder> {




    List<Funcao> dados;

    public funcaoAdapter(List<Funcao> funcao) {
        this.dados=funcao;
    }

    @NonNull
    @Override
    public FuncaoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_funcao,viewGroup,false);
        return new FuncaoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FuncaoHolder funcaoViewHolder, int i) {
        Funcao funcao = dados.get(i);


        funcaoViewHolder.funcaonome.setText(funcao.getNome_funcao());

        funcaoViewHolder.funcaoeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(view.getContext(), cadastro_funcao_editar.class);
                view.getContext().startActivity(it);



//                final DialogPlus dialogPlus = DialogPlus.newDialog(funcaoViewHolder.funcaonome.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.update_funcao_popup))
//                        .setExpanded(true,1200)
//                        .create();
//
//
//                View view1 = dialogPlus.getHolderView();
//                EditText name = view1.findViewById(R.id.txtnome_funcao);
//
//                dialogPlus.show();
//
//                Button funcaoeditar = view1.findViewById(R.id.btneditar_funcao);
//
//                name.setText(funcao.getNome_funcao());
//
//                dialogPlus.show();
//
//                funcaoeditar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String,Object> map = new HashMap<>();
//                        map.put("nome_funcao",name.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Funcao")
//                                .child(funcao.getId_funcao()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>(){
//                                    @Override
//                                    public void onSuccess(Void unused){
//                                        Toast.makeText(funcaoViewHolder.funcaonome.getContext(),"Edição Concluida", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(funcaoViewHolder.funcaonome.getContext(),"Erro na Edição", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                    }
//                });
            }
        });

        funcaoViewHolder.funcaodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(funcaoViewHolder.funcaonome.getContext());
                builder.setTitle("Você tem certeza?");
                builder.setMessage("Informação deletada nao pode ser recuperada.");

                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Funcao")
                                .child(funcao.getId_funcao()).removeValue();
//                                .child(getRef(position).getkey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(funcaoViewHolder.funcaonome.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
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

    public class FuncaoHolder extends RecyclerView.ViewHolder {

        private Button funcaodelete;
        private Button funcaoeditar;
        private TextView funcaonome;

        public FuncaoHolder(@NonNull View itemView) {
            super(itemView);
            funcaonome=itemView.findViewById(R.id.item_funcao_nome);
            funcaodelete =(Button)itemView.findViewById(R.id.button_remover_funcao);
            funcaoeditar =(Button)itemView.findViewById(R.id.button_editar_funcao);

        }
    }
}
