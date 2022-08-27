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
import com.example.approfisso.entidades.Funcao;
import com.example.approfisso.entidades.Pessoa;

import java.util.ArrayList;
import java.util.List;


public class funcaoAdapter extends RecyclerView.Adapter<funcaoAdapter.FuncaoHolder> {


//    private Context context;
//    ArrayList<Pessoa> list = new ArrayList<>();
//    public funcaoAdapter(Context ctx){this.context = ctx;}
//    public  void  setItems(ArrayList<Pessoa> Pes) {list.addAll(Pes);}


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


//        clientesViewHolder.txt_option.setOnClickListener(v->
//        {
//
//            PopupMenu popupMenu=new PopupMenu(context,clientesViewHolder.txt_option);
//            popupMenu.inflate(R.menu.option_menu);
//            popupMenu.setOnMenuItemClickListener(item->
//            {
//                switch (item.getItemId())
//                {
//                    case R.id.menu_edit:
//
//
//                        break;
//                    case R.id.menu_remove:
//
//
//                        break;
//                }
//                return false;
//            });
//            popupMenu.show();
//        });


    }

//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Pessoa p)
//    {
//        PessoaHolder vh = (PessoaHolder) holder;
//        Pessoa pes = p==null? list.get(position):p;
//        vh.pessoaNome.setText(pes.getNome());
//        vh.pessoaSobrenome.setText(pes.getNome());
//        vh.pessoaTelefone.setText(pes.getNome());
//        vh.pessoaAniversario.setText(pes.getNome());
//        vh.pessoaEmail.setText(pes.getNome());
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



    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class FuncaoHolder extends RecyclerView.ViewHolder {
        private TextView funcaonome;

        private TextView txt_option_funcao;

        public FuncaoHolder(@NonNull View itemView) {
            super(itemView);
            funcaonome=itemView.findViewById(R.id.item_funcao_nome);

            txt_option_funcao=itemView.findViewById(R.id.txt_option_funcao);

        }
    }
}
