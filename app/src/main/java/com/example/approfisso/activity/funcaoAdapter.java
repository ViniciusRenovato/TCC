package com.example.approfisso.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcao;
import com.google.firebase.database.DatabaseReference;
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
    public void onBindViewHolder(@NonNull FuncaoHolder funcaoViewHolder, int i) {
        Funcao funcao = dados.get(i);


        funcaoViewHolder.funcaonome.setText(funcao.getNome_funcao());


        funcaoViewHolder.funcaodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(funcaoViewHolder.funcaonome.getContext());
                builder.setTitle("Você tem certeza?");
                builder.setMessage("Informação deletada nao pode ser recuperada.");

                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Funcao").removeValue();
//                                .child(getRef(position).getkey()).removeValue();
                    }
                });


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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


        private TextView funcaonome;

        private TextView txt_option_funcao;


        public FuncaoHolder(@NonNull View itemView) {
            super(itemView);
            funcaonome=itemView.findViewById(R.id.item_funcao_nome);

            txt_option_funcao=itemView.findViewById(R.id.txt_option_funcao);
            funcaodelete =(Button)itemView.findViewById(R.id.button_remover);

        }
    }
}
