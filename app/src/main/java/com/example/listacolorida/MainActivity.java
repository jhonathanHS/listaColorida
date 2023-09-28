package com.example.listacolorida;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // referência para o banco de dados
    SQLiteDatabase bd;
    // referência para o adapter da lista
    AdapterTeste adapter;
    // referência para o cursor que popula a lista
    Cursor cursor;
    // atributos relativos aos objetos gráficos
    private EditText txtNome;
    private EditText txtEmail;
    private Button btnInsere;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ligando atributos aos ID's na interface
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        btnInsere = findViewById(R.id.btnInsere);
        lista = findViewById(R.id.lista);
        // configurando escutador no botão insere
        btnInsere.setOnClickListener(new EscutadorInsere());

        // banco de dados...
        // abrindo ou criando o banco de dados
        bd = openOrCreateDatabase("BancoTeste", MODE_PRIVATE, null);
        // string para comandos SQL
        String cmd;
        // criar a tabela, se a mesma não existir
        cmd = "CREATE TABLE IF NOT EXISTS contatos (nome VARCHAR, email VARCHAR)";
        // executa comando no bd
        bd.execSQL(cmd);

        // configurando a lista...
        // consulta para gerar o Cursor
        cursor = bd.rawQuery("SELECT _rowid_ _id, nome, email FROM contatos", null);
        // criar o adapter, passando o contexto e o cursor
        adapter = new AdapterTeste(this, cursor);
        // associar a lista com o adapter criado
        lista.setAdapter(adapter);
    }

    private class EscutadorInsere implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // variáveis para pegar os dados
            String nome, email;
            // pegando os dados na interface
            nome = txtNome.getText().toString();
            email = txtEmail.getText().toString();
            // montando SQL para inserir dados
            String cmd = "INSERT INTO contatos (nome, email) VALUES ('";
            cmd = cmd + nome;
            cmd = cmd + "', '";
            cmd = cmd + email;
            cmd = cmd + "')";
            // executando comando
            bd.execSQL(cmd);
            // limpando a interface
            txtNome.setText("");
            txtEmail.setText("");
            // trocando o cursor do adapter
            cursor = bd.rawQuery("SELECT _rowid_ _id, nome, email FROM contatos", null);
            adapter.changeCursor(cursor);
        }
    }

}