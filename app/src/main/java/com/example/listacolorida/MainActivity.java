package com.example.listacolorida;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    AdapterTeste adapter;
    Cursor cursor;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsere = findViewById(R.id.btnInsereTexto);
        ListView lista = findViewById(R.id.lista);
        db = new Database(this);

        cursor = db.obterDados();

        // criar o adapter, passando o contexto e o cursor
        adapter = new AdapterTeste(this, cursor);

        // associar a lista com o adapter criado
        lista.setAdapter(adapter);

        EscutadorLista el = new EscutadorLista();
        lista.setOnItemClickListener(el);
        lista.setOnItemLongClickListener(el);
        btnInsere.setOnClickListener(new EscutadorInsere());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String texto = data.getStringExtra("text");
            int cor = data.getIntExtra("color", 0);

            Texto texto1 = new Texto(texto, cor);
            db.inserirDados(texto1.getTexto(), texto1.getCor());
            atualizarDados();
        }
    }

    private void atualizarDados() {
        Cursor cursor = db.obterDados();
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
    }

    private class EscutadorInsere implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, ColorActivity.class);
            startActivityForResult(i, 1);
        }
    }

    private class EscutadorLista implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapter.getItem(i);
            if (cursor != null) {
                @SuppressLint("Range") String texto = cursor.getString(cursor.getColumnIndex("texto"));
                @SuppressLint("Range") int numCor = cursor.getInt(cursor.getColumnIndex("cor"));
                String cor = "";

                if (numCor == Color.RED) {
                    cor = "Vermelho";
                } else if (numCor == Color.GREEN) {
                    cor = "Verde";
                } else if (numCor == Color.BLUE) {
                    cor = "Azul";
                }

                Toast.makeText(MainActivity.this, texto + "\n" + cor, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapter.getItem(i);

            if (cursor != null) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("_id"));

                db.excluirDado(id);

                atualizarDados();

                Toast.makeText(MainActivity.this, "Texto excluído!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}