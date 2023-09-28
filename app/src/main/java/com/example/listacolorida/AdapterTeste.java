package com.example.listacolorida;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class AdapterTeste extends CursorAdapter {
    public AdapterTeste(Context context, Cursor cursor) {
        // transfere o contexto e o cursor com dados para a classe mãe
        super(context, cursor, 0);
    }

    // método newView: apenas infla o xml com o desenho do item da lista,
    // e retorna o objeto "inflado" (view)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // recupera o objeto inflador
        LayoutInflater inflater = LayoutInflater.from(context);
        // infla o xml, gerando a visualização (view)
        View v = inflater.inflate(R.layout.item_lista, parent, false);
        // retorna o objeto inflado (a view gerada)
        return v;
    }

    // método bindView: recebe a view inflada (o item da lista)
// e coloca os dados nos seus objetos gráficos internos
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // recuperando os objetos gráficos de dentro da view recebid
        TextView lblNome = view.findViewById(R.id.txtTexto);
        TextView lblEmail = view.findViewById(R.id.lblEmail);
        // o cursor já vem posicionado na linha correta...
        // basta recuperarmos os dados
        @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("nome"));
        @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
        // finalmente, colocamos os dados nos objetos gráficos que estão dentro
        // do item da lista
        lblNome.setText(nome);
        lblEmail.setText(email);
    }
}