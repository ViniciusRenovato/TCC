package com.example.approfisso.adapter;

import android.app.AlertDialog;
import android.content.Context;
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

        clientesViewHolder.clienteeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(clientesViewHolder.pessoaSobrenome.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.update_cliente_popup))
//                        .setExpanded(true,1500)
//                        .create();
//
//
//                View view1 = dialogPlus.getHolderView();
//                EditText name = view1.findViewById(R.id.txtnome_cliente);
//                EditText sobrenome = view1.findViewById(R.id.txtsobrenome_cliente);
//                EditText telefone = view1.findViewById(R.id.txttelefone_cliente);
//                EditText aniversario = view1.findViewById(R.id.txtaniversario_cliente);
//                EditText email = view1.findViewById(R.id.txtemail_cliente);
//
//                dialogPlus.show();
//
//                Button clienteeditar = view1.findViewById(R.id.btneditar_cliente);
//
//                name.setText(clientes.getNome());
//                sobrenome.setText(clientes.getSobrenome());
//                telefone.setText(clientes.getTelefone());
//                aniversario.setText(clientes.getAniversario());
//                email.setText(clientes.getEmail());
//
//                dialogPlus.show();
//
//                clienteeditar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String,Object> map = new HashMap<>();
//                        map.put("Nome",name.getText().toString());
//                        map.put("Sobrenome",sobrenome.getText().toString());
//                        map.put("Telefone",telefone.getText().toString());
//                        map.put("Aniversario",aniversario.getText().toString());
//                        map.put("Email",email.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Pessoa")
//                                .child(clientes.getID()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>(){
//                                    @Override
//                                    public void onSuccess(Void unused){
//                                        Toast.makeText(clientesViewHolder.pessoaNome.getContext(),"Edição Concluida", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(clientesViewHolder.pessoaNome.getContext(),"Erro na Edição", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                    }
//                });
            }
        });



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
        private Button clienteeditar;

        public PessoaHolder(@NonNull View itemView) {
            super(itemView);
            pessoaNome=itemView.findViewById(R.id.item_pessoa_nome);
            pessoaSobrenome=itemView.findViewById(R.id.item_pessoa_sobrenome);
            pessoaTelefone=itemView.findViewById(R.id.item_pessoa_telefone);
            pessoaAniversario=itemView.findViewById(R.id.item_pessoa_aniversario);
            pessoaEmail=itemView.findViewById(R.id.item_pessoa_email);
            clientedelete =(Button)itemView.findViewById(R.id.button_remover_cliente);
            clienteeditar =(Button)itemView.findViewById(R.id.button_editar_cliente);
        }
    }
}
