package br.com.unicuritiba.AppProjetoPI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.unicuritiba.AppProjetoPI.R;
import br.com.unicuritiba.AppProjetoPI.adapter.TarefaAdapter;
import br.com.unicuritiba.AppProjetoPI.helper.TarefaDAO;
import br.com.unicuritiba.AppProjetoPI.model.Tarefa;

public class MainActivity extends AppCompatActivity {


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button janeiro = findViewById(R.id.button_janiero);
            Button fev = findViewById(R.id.button_fev);
            Button mar = findViewById(R.id.button_mar);
            Button abr = findViewById(R.id.button_abr);
            Button may = findViewById(R.id.button_may);
            Button jun = findViewById(R.id.button_jun);
            Button jul = findViewById(R.id.button_jul);
            Button ago = findViewById(R.id.button_ago);
            Button set = findViewById(R.id.button_set);
            Button out = findViewById(R.id.button_out);
            Button nov = findViewById(R.id.button_nov);
            Button dez = findViewById(R.id.button_dez);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });

        janeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 0);

                startActivity(intent);
            }
        });

        fev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 1);

                startActivity(intent);

            }
        });

        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 2);
                startActivity(intent);

            }
        });

        abr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 3);
                startActivity(intent);

            }
        });

        may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 4);
                startActivity(intent);

            }
        });

        jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 5);
                startActivity(intent);

            }
        });

        jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 6);
                startActivity(intent);

            }
        });

        ago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 7);
                startActivity(intent);

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 8);
                startActivity(intent);

            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 9);
                startActivity(intent);

            }
        });

        nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 10);
                startActivity(intent);

            }
        });

        dez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                intent.putExtra("mes", 11);
                startActivity(intent);

            }
        });


    }


}