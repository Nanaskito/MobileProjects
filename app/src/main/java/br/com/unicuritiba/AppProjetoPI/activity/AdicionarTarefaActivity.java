package br.com.unicuritiba.AppProjetoPI.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

import br.com.unicuritiba.AppProjetoPI.R;
import br.com.unicuritiba.AppProjetoPI.helper.TarefaDAO;
import br.com.unicuritiba.AppProjetoPI.model.DatePickerFragment;
import br.com.unicuritiba.AppProjetoPI.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button button_data;
    private TextInputEditText editTarefa, editDesc;
    private Tarefa tarefaAtual;
    private DatePickerDialog.OnDateSetListener setDate;
    private int ano, mes, dia;
    private Tarefa tare;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        editTarefa = findViewById(R.id.textTarefaAdd);
        editDesc = findViewById(R.id.textDescricao);
        button_data = (Button) findViewById(R.id.button_data);


        //Recuperar caso seja edit
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
//Cofig caixa de texto
        if (tarefaAtual != null) {
            editTarefa.setText(tarefaAtual.getAtividade());
            editDesc.setText(tarefaAtual.getDescricao());
            button_data.setText(tarefaAtual.getEntrega());
            mes = tarefaAtual.getMes();


        }
        button_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        ano = year;
        mes = month;
        dia = day;
        date = DateFormat.getDateInstance().format(c.getTime());
        button_data.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                tare = new Tarefa();
                if (tarefaAtual != null) {
                    //editar
                    String nomeAtividade = editTarefa.getText().toString();
                    String nomeDesc = editDesc.getText().toString();
                  String data = button_data.getText().toString();


                    if (!nomeAtividade.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setAtividade(nomeAtividade);
                        tarefa.setDescricao(nomeDesc);
                        tarefa.setDia(dia);
                        tarefa.setMes(mes);
                        tarefa.setAno(ano);
                        tarefa.setEntrega(data);
                        tarefa.setId(tarefaAtual.getId());

//Atualizar no banco
                        if (tarefaDAO.atualizar(tarefa)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao Atualizar", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao Atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {//salvar
//Acao salvar
                    String nomeAtividade = editTarefa.getText().toString();
                    String nomeDesc = editDesc.getText().toString();
                    String data = button_data.getText().toString();

                    if (!nomeAtividade.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setAtividade(nomeAtividade);
                        tarefa.setDescricao(nomeDesc);
                        tarefa.setDia(dia);
                        tarefa.setMes(mes);
                        tarefa.setAno(ano);
                        tarefa.setEntrega(data);



                        if (tarefaDAO.salvar(tarefa)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}