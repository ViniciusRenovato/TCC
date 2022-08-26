package com.example.approfisso.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Pessoa;

import java.util.ArrayList;
import java.util.List;


public class funcaoAdapter extends RecyclerView.Adapter<funcaoAdapter.PessoaHolder> {


    private Context context;
    ArrayList<Pessoa> list = new ArrayList<>();
    public funcaoAdapter(Context ctx){this.context = ctx;}
    public  void  setItems(ArrayList<Pessoa> Pes) {list.addAll(Pes);}


    List<Pessoa> dados;

    public funcaoAdapter(List<Pessoa> clientes) {
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

        clientesViewHolder.txt_option.setOnClickListener(v->
        {

            PopupMenu popupMenu=new PopupMenu(context,clientesViewHolder.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:


                        break;
                    case R.id.menu_remove:


                        break;
                }
                return false;
            });
            popupMenu.show();
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
//        vh.txt_option.setOnClickListener(v->
//                {
//
//                            PopupMenu popupMenu=new PopupMenu(context,vh.txt_option);
//                            popupMenu.inflate(R.menu.option_menu);
//                            popupMenu.setOnMenuItemClickListener(item->
//                            {
//                                        switch (item.getItemId())
//                                        {
//                                            case R.id.menu_edit:
//
//
//                                                break;
//                                            case R.id.menu_remove:
//
//
//                                                break;
//                                        }
//                                  return false;
//                            });
//                            popupMenu.show();
//               });
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
        private TextView txt_option;

        public PessoaHolder(@NonNull View itemView) {
            super(itemView);
            pessoaNome=itemView.findViewById(R.id.item_servico_nome);
            pessoaSobrenome=itemView.findViewById(R.id.item_servico_duracao);
            pessoaTelefone=itemView.findViewById(R.id.item_servico_preco);
            pessoaAniversario=itemView.findViewById(R.id.item_servico_funcao);
            pessoaEmail=itemView.findViewById(R.id.item_servico_funcao);
            txt_option=itemView.findViewById(R.id.txt_option);

        }
    }
}
