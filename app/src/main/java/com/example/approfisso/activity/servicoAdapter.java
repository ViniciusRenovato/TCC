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
import com.example.approfisso.entidades.Servicos;

import java.util.ArrayList;
import java.util.List;


public class servicoAdapter extends RecyclerView.Adapter<servicoAdapter.ServicosHolder> {


//    private Context context;
//    ArrayList<Servicos> list = new ArrayList<>();
//    public servicoAdapter(Context ctx){this.context = ctx;}
//    public  void  setItems(ArrayList<Servicos> Pes) {list.addAll(Pes);}


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

//        serviViewHolder.txt_option_funcao.setOnClickListener(v->
//        {
//
////            PopupMenu popupMenu=new PopupMenu(context,serviViewHolder.txt_option_funcao);
////            popupMenu.inflate(R.menu.option_menu);
////            popupMenu.setOnMenuItemClickListener(item->
////            {
////                switch (item.getItemId())
////                {
////                    case R.id.menu_edit:
////
////
////                        break;
////                    case R.id.menu_remove:
////
////
////                        break;
////                }
////                return false;
////            });
////            popupMenu.show();
//        });


    }

//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Pessoa p)
//    {
//        ServicosHolder vh = (ServicosHolder) holder;
//        Pessoa pes = p==null? list.get(position):p;
//        vh.pessoaNome.setText(pes.getNome());
//        vh.pessoaSobrenome.setText(pes.getNome());
//        vh.pessoaTelefone.setText(pes.getNome());
//        vh.pessoaAniversario.setText(pes.getNome());
//        vh.pessoaEmail.setText(pes.getNome());
////        vh.txt_option.setOnClickListener(v->
////                {
////
////                            PopupMenu popupMenu=new PopupMenu(context,vh.txt_option);
////                            popupMenu.inflate(R.menu.option_menu);
////                            popupMenu.setOnMenuItemClickListener(item->
////                            {
////                                        switch (item.getItemId())
////                                        {
////                                            case R.id.menu_edit:
////
////
////                                                break;
////                                            case R.id.menu_remove:
////
////
////                                                break;
////                                        }
////                                  return false;
////                            });
////                            popupMenu.show();
////               });
//    }


    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class ServicosHolder extends RecyclerView.ViewHolder {
        private TextView serviconome;
        private TextView servicoduracao;
        private TextView servicovalor;
        private TextView servicofuncao;
        private TextView txt_option_funcao;

        public ServicosHolder(@NonNull View itemView) {
            super(itemView);
            serviconome=itemView.findViewById(R.id.item_servico_nome);
            servicoduracao=itemView.findViewById(R.id.item_servico_duracao);
            servicovalor=itemView.findViewById(R.id.item_servico_preco);
            servicofuncao=itemView.findViewById(R.id.item_servico_funcao);
            txt_option_funcao=itemView.findViewById(R.id.txt_option_servico);

        }
    }
}
