package com.example.approfisso.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.approfisso.R;
import com.example.approfisso.entidades.Funcionario;
import com.example.approfisso.entidades.Pessoa;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public void onBindViewHolder(@NonNull FuncionarioHolder funcionarioViewHolder, int i ) {
        Funcionario funcionario = dados.get(i);
        funcionarioViewHolder.funcionarionome.setText(funcionario.getNome_funcionario());
        funcionarioViewHolder.funcionariosobrenome.setText(funcionario.getSobrenome_funcionario());
        funcionarioViewHolder.funcionariofuncao.setText(funcionario.getFuncao_funcionario());
        funcionarioViewHolder.funcionariotelefone.setText(funcionario.getTelefone_funcionario());
        funcionarioViewHolder.funcionarioaniversario.setText(funcionario.getAniversario_funcionario());
        funcionarioViewHolder.funcionarioemail.setText(funcionario.getEmail_funcionario());

        funcionarioViewHolder.funcionarioeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(funcionarioViewHolder.funcionariosobrenome.getContext())
                        .setContentHolder(new ViewHolder(R.layout.uptade_popup))
                        .setExpanded(true,1500)
                        .create();







                View view1 = dialogPlus.getHolderView();
                EditText name = view1.findViewById(R.id.txtnome);
                EditText sobrenome = view1.findViewById(R.id.txtsobrenome);
                EditText telefone = view1.findViewById(R.id.txttelefone);
                EditText aniversario = view1.findViewById(R.id.txtaniversario);
                EditText email = view1.findViewById(R.id.txtemail);

                dialogPlus.show();

                Button funcionarioeditar = view1.findViewById(R.id.btneditar);

                name.setText(funcionario.getNome_funcionario());
                sobrenome.setText(funcionario.getSobrenome_funcionario());
                telefone.setText(funcionario.getTelefone_funcionario());
                aniversario.setText(funcionario.getAniversario_funcionario());
                email.setText(funcionario.getEmail_funcionario());

                dialogPlus.show();

                funcionarioeditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("nome_funcionario",name.getText().toString());
                        map.put("sobrenome_funcionario",sobrenome.getText().toString());
                        map.put("telefone_funcionario",telefone.getText().toString());
                        map.put("aniversario_funcionario",aniversario.getText().toString());
                        map.put("email_funcionario",email.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Funcionario")
                                .child(funcionario.getId_funcionario()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>(){
                                    @Override
                                    public void onSuccess(Void unused){
                                        Toast.makeText(funcionarioViewHolder.funcionarionome.getContext(),"Edição Concluida", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(funcionarioViewHolder.funcionarionome.getContext(),"Erro na Edição", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });








            }
        });

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
        private Button funcionariodelete;
        private Button funcionarioeditar;


        public FuncionarioHolder(@NonNull View itemView) {
            super(itemView);
            funcionarionome=itemView.findViewById(R.id.item_funcionario_nome);
            funcionariosobrenome=itemView.findViewById(R.id.item_funcionario_sobrenome);
            funcionariofuncao=itemView.findViewById(R.id.item_funcionario_funcao);
            funcionariotelefone=itemView.findViewById(R.id.item_funcionario_telefone);
            funcionarioaniversario=itemView.findViewById(R.id.item_funcionario_aniversario);
            funcionarioemail=itemView.findViewById(R.id.item_funcionario_email);

            funcionariodelete =(Button)itemView.findViewById(R.id.button_remover_funcionario);
            funcionarioeditar =(Button)itemView.findViewById(R.id.button_editar_funcionario);

        }
    }
}
