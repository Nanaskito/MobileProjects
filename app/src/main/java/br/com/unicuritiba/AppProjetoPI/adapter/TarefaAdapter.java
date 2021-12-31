package br.com.unicuritiba.AppProjetoPI.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.unicuritiba.AppProjetoPI.R;
import br.com.unicuritiba.AppProjetoPI.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {
    private List<Tarefa> listTarefas;

    public TarefaAdapter(List<Tarefa> lista) {
        this.listTarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_tarefa_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tarefa tarefa = listTarefas.get(position);
        holder.tarefaNome.setText(tarefa.getAtividade());
        holder.textdata.setText(tarefa.getEntrega());


    }

    @Override
    public int getItemCount() {
        return this.listTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tarefaNome;
        private TextView textdata;
        // private TextView tarefaDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefaNome = itemView.findViewById(R.id.textTarefa);
            textdata = itemView.findViewById(R.id.textData);
            // tarefaDesc = itemView.findViewById(R.id.textDescricao);
        }
    }


}
