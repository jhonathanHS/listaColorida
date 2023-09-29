package com.example.listacolorida;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ListaColorida";
    private static final String TABLE_NAME = "listaColorida";
    private static final String CREATE_DATABASE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, texto VARCHAR, cor INTEGER)";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_DATABASE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long inserirDados(String texto, int cor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("texto", texto);
        contentValues.put("cor", cor);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor obterDados() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT _id, texto, cor FROM " + TABLE_NAME, null);
    }

    public void excluirDado(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "_id=?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }
}

