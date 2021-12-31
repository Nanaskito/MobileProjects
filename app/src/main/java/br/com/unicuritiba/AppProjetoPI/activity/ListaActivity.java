package br.com.unicuritiba.AppProjetoPI.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.unicuritiba.AppProjetoPI.R;
import br.com.unicuritiba.AppProjetoPI.adapter.TarefaAdapter;
import br.com.unicuritiba.AppProjetoPI.helper.RecyclerItemClickListener;
import br.com.unicuritiba.AppProjetoPI.helper.TarefaDAO;
import br.com.unicuritiba.AppProjetoPI.model.Tarefa;

public class ListaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private Tarefa tarefaSelecionada;
    private int mesSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        recyclerView = findViewById(R.id.recyclerView);
        mesSelecionado = getIntent().getIntExtra("mes", mesSelecionado);

//Filtro por mes, salvar o dia e o mes e fazer listagem por dia e mes

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Recuperar tarefa para editar
                        Tarefa tarefaSelecionada = listaTarefas.get(position);

                        //Enviar a tarefa
                        Intent intent = new Intent(ListaActivity.this, AdicionarTarefaActivity.class);
                        intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        tarefaSelecionada = listaTarefas.get(position);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(ListaActivity.this);

                        dialog.setTitle("Confirmar exclusao");
                        dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getAtividade() + "?");
                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                if (tarefaDAO.deletar(tarefaSelecionada)) {
                                    carregarListatarefas();
                                    Toast.makeText(getApplicationContext(), "Sucesso ao Excluir", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Erro ao Excluir", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        dialog.setNegativeButton("Não", null);

                        //Exibir
                        dialog.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));


    }

    public void carregarListatarefas() {
        //Listar tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        listaTarefas = tarefaDAO.listarPorMes(mesSelecionado);


        tarefaAdapter = new TarefaAdapter(listaTarefas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);

    }

    @Override
    protected void onStart() {
        carregarListatarefas();
        super.onStart();
    }
}