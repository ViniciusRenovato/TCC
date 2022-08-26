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
import com.example.approfisso.entidades.Funcionario;
import com.example.approfisso.entidades.Pessoa;

import java.util.ArrayList;
import java.util.List;


public class funcionarioAdapter extends RecyclerView.Adapter<funcionarioAdapter.FuncionarioHolder> {


//    private Context context;
//    ArrayList<Pessoa> list = new ArrayList<>();
//    public funcionarioAdapter(Context ctx){this.context = ctx;}
//    public  void  setItems(ArrayList<Pessoa> Pes) {list.addAll(Pes);}


    List<Funcionario> dados;

    public funcionarioAdapter(List<Funcionario> funcionario) {
        this.dados=funcionario;
    }

    @NonNull
    @Override
    public FuncionarioHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.linhas_funcionario,viewGroup,false);
        return new FuncionarioHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FuncionarioHolder funcionarioViewHolder, int i) {
        Funcionario funcionario = dados.get(i);
        funcionarioViewHolder.funcionarionome.setText(funcionario.getNome_funcionario());
        funcionarioViewHolder.funcionariosobrenome.setText(funcionario.getSobrenome_funcionario());
        funcionarioViewHolder.funcionariofuncao.setText(funcionario.getFuncao_funcionario());
        funcionarioViewHolder.funcionariotelefone.setText(funcionario.getTelefone_funcionario());
        funcionarioViewHolder.funcionarioaniversario.setText(funcionario.getAniversario_funcionario());
        funcionarioViewHolder.funcionarioemail.setText(funcionario.getEmail_funcionario());

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
    public class FuncionarioHolder extends RecyclerView.ViewHolder {
        private TextView funcionarionome;
        private TextView funcionariosobrenome;
        private TextView funcionariofuncao;
        private TextView funcionariotelefone;
        private TextView funcionarioaniversario;
        private TextView funcionarioemail;
        private TextView txt_option_funcionario;

        public FuncionarioHolder(@NonNull View itemView) {
            super(itemView);
            funcionarionome=itemView.findViewById(R.id.item_funcionario_nome);
            funcionariosobrenome=itemView.findViewById(R.id.item_funcionario_sobrenome);
            funcionariofuncao=itemView.findViewById(R.id.item_funcionario_funcao);
            funcionariotelefone=itemView.findViewById(R.id.item_funcionario_telefone);
            funcionarioaniversario=itemView.findViewById(R.id.item_funcionario_aniversario);
            funcionarioemail=itemView.findViewById(R.id.item_funcionario_email);
            txt_option_funcionario=itemView.findViewById(R.id.txt_option_funcionario);

        }
    }
}
