package com.trab2.compracerta.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.trab2.compracerta.dao.produto.SQLiteProdutoMetaDados;

public class SQLiteBDHelper extends SQLiteOpenHelper implements SQLiteDadosBanco, SQLiteProdutoMetaDados {

    public SQLiteBDHelper(Context context) {
        super(context, SQLiteDadosBanco.DATABASE, null, SQLiteDadosBanco.VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteProdutoMetaDados.METADADOSCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}