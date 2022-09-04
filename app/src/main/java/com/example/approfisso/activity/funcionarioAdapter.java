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
import com.example.approfisso.entidades.Funcionario;
import com.example.approfisso.entidades.Pessoa;
import com.google.firebase.database.FirebaseDatabase;

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

        funcionarioViewHolder.funcionariodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(funcionarioViewHolder.funcionarionome.getContext());
                builder.setTitle("Você tem certeza?");
                builder.setMessage("Informação deletada nao pode ser recuperada.");

                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Funcionario").child(funcionario.getId_funcionario()).removeValue();
                    }
                });


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(funcionarioViewHolder.funcionarionome.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
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
    public class FuncionarioHolder extends RecyclerView.ViewHolder {
        private TextView funcionarionome;
        private TextView funcionariosobrenome;
        private TextView funcionariofuncao;
        private TextView funcionariotelefone;
        private TextView funcionarioaniversario;
        private TextView funcionarioemail;
        private TextView txt_option_funcionario;
        private Button funcionariodelete;


        public FuncionarioHolder(@NonNull View itemView) {
            super(itemView);
            funcionarionome=itemView.findViewById(R.id.item_funcionario_nome);
            funcionariosobrenome=itemView.findViewById(R.id.item_funcionario_sobrenome);
            funcionariofuncao=itemView.findViewById(R.id.item_funcionario_funcao);
            funcionariotelefone=itemView.findViewById(R.id.item_funcionario_telefone);
            funcionarioaniversario=itemView.findViewById(R.id.item_funcionario_aniversario);
            funcionarioemail=itemView.findViewById(R.id.item_funcionario_email);

            funcionariodelete =(Button)itemView.findViewById(R.id.button_remover_funcionario);

        }
    }
}
