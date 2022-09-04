package com.example.approfisso.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class pessoaAdapter extends RecyclerView.Adapter<pessoaAdapter.PessoaHolder> {


    private Context context;
    ArrayList<Pessoa> list = new ArrayList<>();
    public pessoaAdapter(Context ctx){this.context = ctx;}
    public  void  setItems(ArrayList<Pessoa> Pes) {list.addAll(Pes);}


    List<Pessoa> dados;

    public pessoaAdapter(List<Pessoa> clientes) {

        this.dados=clientes;
    }

    @NonNull
    @Override
    public PessoaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_cliente,viewGroup,false);
        return new PessoaHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PessoaHolder clientesViewHolder, int i) {
        Pessoa clientes = dados.get(i);
        clientesViewHolder.pessoaNome.setText(clientes.getNome());
        clientesViewHolder.pessoaSobrenome.setText(clientes.getSobrenome());
        clientesViewHolder.pessoaTelefone.setText(clientes.getTelefone());
        clientesViewHolder.pessoaAniversario.setText(clientes.getAniversario());
        clientesViewHolder.pessoaEmail.setText(clientes.getEmail());

        clientesViewHolder.clientedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(clientesViewHolder.pessoaNome.getContext());
                builder.setTitle("Você tem certeza?");
                builder.setMessage("Informação deletada nao pode ser recuperada.");

                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Pessoa").child(clientes.getID()).removeValue();
                    }
                });


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(clientesViewHolder.pessoaNome.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Pessoa p)
    {
        PessoaHolder vh = (PessoaHolder) holder;
        Pessoa pes = p==null? list.get(position):p;
        vh.pessoaNome.setText(pes.getNome());
        vh.pessoaSobrenome.setText(pes.getNome());
        vh.pessoaTelefone.setText(pes.getNome());
        vh.pessoaAniversario.setText(pes.getNome());
        vh.pessoaEmail.setText(pes.getNome());
//
    }


    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class PessoaHolder extends RecyclerView.ViewHolder {
        private TextView pessoaNome;
        private TextView pessoaSobrenome;
        private TextView pessoaTelefone;
        private TextView pessoaAniversario;
        private TextView pessoaEmail;
        private Button clientedelete;

        public PessoaHolder(@NonNull View itemView) {
            super(itemView);
            pessoaNome=itemView.findViewById(R.id.item_pessoa_nome);
            pessoaSobrenome=itemView.findViewById(R.id.item_pessoa_sobrenome);
            pessoaTelefone=itemView.findViewById(R.id.item_pessoa_telefone);
            pessoaAniversario=itemView.findViewById(R.id.item_pessoa_aniversario);
            pessoaEmail=itemView.findViewById(R.id.item_pessoa_email);
            clientedelete =(Button)itemView.findViewById(R.id.button_remover_cliente);

        }
    }
}
