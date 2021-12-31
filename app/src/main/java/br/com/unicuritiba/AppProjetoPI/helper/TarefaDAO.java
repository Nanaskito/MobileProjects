package br.com.unicuritiba.AppProjetoPI.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.unicuritiba.AppProjetoPI.activity.AdicionarTarefaActivity;
import br.com.unicuritiba.AppProjetoPI.activity.ListaActivity;
import br.com.unicuritiba.AppProjetoPI.model.Tarefa;

public class TarefaDAO implements ITarefaDAO {
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    public TarefaDAO() {

    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getAtividade());
        cv.put("descricao", tarefa.getDescricao());
        cv.put("entrega", tarefa.getEntrega());
        cv.put("mes", tarefa.getMes());
        cv.put("dia", tarefa.getDia());
        cv.put("ano", tarefa.getDia());


        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);

            Log.i("INFO", "Sucesoo ao salvar tarefa");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar tarefa" + e.getMessage());
            return false;

        }


        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getAtividade());
        cv.put("descricao", tarefa.getDescricao());
        cv.put("entrega", tarefa.getEntrega());
        cv.put("mes", tarefa.getMes());
        cv.put("dia", tarefa.getDia());
        cv.put("ano", tarefa.getAno());

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=? ", args);
            Log.i("INFO", "Sucesoo ao atualizar tarefa");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao atualizar tarefa" + e.getMessage());
            return false;

        }


        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        String[] args = {tarefa.getId().toString()};
        try {
            escreve.delete(DbHelper.TABELA_TAREFAS, "id=?", args);
            Log.i("INFO", "Atividade removida");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao remover atividade" + e.getMessage());
            return false;

        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + "  ; ";
        Cursor c = le.rawQuery(sql, null);
        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String atividadeNome = c.getString(c.getColumnIndex("nome"));
            @SuppressLint("Range") String atividadeDesc = c.getString(c.getColumnIndex("descricao"));
            tarefa.setDescricao(atividadeDesc);
            tarefa.setId(id);
            tarefa.setAtividade(atividadeNome);
            tarefas.add(tarefa);

        }
        return tarefas;

    }

    public List<Tarefa> listarPorMes(int mesSelecionado) {


        List<Tarefa> tarefaspormes = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " WHERE mes = " + mesSelecionado + " ORDER BY dia";
        Cursor c = le.rawQuery(sql, null);

//alterar query por mes
        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String atividadeNome = c.getString(c.getColumnIndex("nome"));
            @SuppressLint("Range") String atividadeDesc = c.getString(c.getColumnIndex("descricao"));
            @SuppressLint("Range") int mes = c.getInt(c.getColumnIndex("mes"));
            @SuppressLint("Range") int dia = c.getInt(c.getColumnIndex("dia"));
            @SuppressLint("Range") int ano = c.getInt(c.getColumnIndex("ano"));
            @SuppressLint("Range") String entrega = c.getString(c.getColumnIndex("entrega"));

            tarefa.setId(id);
            tarefa.setAtividade(atividadeNome);
            tarefa.setDescricao(atividadeDesc);
            tarefa.setMes(mes);
            tarefa.setDia(dia);
            tarefa.setAno(ano);
            tarefa.setEntrega(entrega);
            tarefaspormes.add(tarefa);

        }
        return tarefaspormes;

    }
}
