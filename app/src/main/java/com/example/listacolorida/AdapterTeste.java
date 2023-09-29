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
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtView = view.findViewById(R.id.lblTexto);

        @SuppressLint("Range") String texto = cursor.getString(cursor.getColumnIndex("texto"));
        @SuppressLint("Range") int cor = cursor.getInt(cursor.getColumnIndex("cor"));
        txtView.setText(texto);
        txtView.setTextColor(cor);
    }
}