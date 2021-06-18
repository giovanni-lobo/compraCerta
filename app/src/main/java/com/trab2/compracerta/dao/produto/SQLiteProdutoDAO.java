package com.trab2.compracerta.dao.produto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.trab2.compracerta.dao.SQLiteBDHelper;
import com.trab2.compracerta.dao.SQLiteDAOFactory;
import com.trab2.compracerta.entidade.Produto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLiteProdutoDAO extends SQLiteDAOFactory implements ProdutoDAO, SQLiteProdutoMetaDados {

    private SQLiteBDHelper dbHelper;

    public SQLiteProdutoDAO() {
        dbHelper = new SQLiteBDHelper(getContext());
    }

    private List select(List<String> filtros, String ordem) {
        List lista = new LinkedList();
        String[] colunas = METADADOSSELECT.split(",");
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(TABLE, colunas, montaFiltro(filtros, " and "), null, null, null, ordem, null);
            while (cursor.moveToNext()) {
                Produto prod = new Produto();
                prod.setItem(cursor.getString(cursor.getColumnIndex(PK[0])));
                prod.setProduto(cursor.getString(cursor.getColumnIndex("PRODUTO")));
                prod.setQtd(cursor.getString(cursor.getColumnIndex("QTD")));
                prod.setPrecoUnid(cursor.getString(cursor.getColumnIndex("PRECOUNID")));
                lista.add(prod);
            }
            cursor.close();
            cursor = null;
            db.close();
            db = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    ;
                }
                cursor = null;
            }
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    ;
                }
                db = null;
            }
        }
        return lista;
    }

    public boolean incluir(Object obj) {
        if (obj != null) {
            Produto prod = (Produto) obj;
            SQLiteDatabase db = null;
            boolean res = false;
            try {
                db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(METADADOSUPDATE[0], preparaSQL(prod.getItem()));
                valores.put(METADADOSUPDATE[1], preparaSQL(prod.getProduto()));
                valores.put(METADADOSUPDATE[2], preparaSQL(prod.getQtd()));
                valores.put(METADADOSUPDATE[3], preparaSQL(prod.getPrecoUnid()));
                db.insert(TABLE, null, valores);
                db.close();
                db = null;
                res = true;
            } catch (Exception e) {
                System.out.println(e);
                res = false;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return false;
    }

    public int alterar(Object obj) {
        if (obj != null) {
            Produto prod = (Produto) obj;
            SQLiteDatabase db = null;
            int res = 0;
            try {
                db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(METADADOSUPDATE[0], preparaSQL(prod.getItem()));
                valores.put(METADADOSUPDATE[1], preparaSQL(prod.getProduto()));
                valores.put(METADADOSUPDATE[2], preparaSQL(prod.getQtd()));
                valores.put(METADADOSUPDATE[3], preparaSQL(prod.getPrecoUnid()));
                String selecao = PK[0] + " = ? ";
                String[] selecaoArgumentos = {preparaSQL(prod.getItem())};
                db.update(TABLE, valores, selecao, selecaoArgumentos);
                db.close();
                db = null;
                res = 1;
            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return 0;
    }

    public int excluir(Object obj) {
        if (obj != null) {
            Produto prod = (Produto) obj;
            SQLiteDatabase db = null;
            StringBuilder sql = new StringBuilder();
            int res = 0;
            try {
                db = dbHelper.getWritableDatabase();
                String selecao = PK[0] + " = ? ";
                String[] selecaoArgumentos = {preparaSQL(prod.getItem())};
                db.delete(TABLE, selecao, selecaoArgumentos);
                db.close();
                db = null;
                res = 1;
            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return 0;
    }


    public List aplicarFiltro(Object obj) {
        if (obj != null) {
            Produto prod = (Produto) obj;

            List<String> filtros = new ArrayList<String>();

            if (prod.getItem() != "") {
                filtros.add(TABLE + "." + PK[0] + "= '" + prod.getItem() + "'");
            }

            if (!prod.getProduto().equals("")) {
                filtros.add(TABLE + ".PRODUTO like '" + prod.getProduto() + "'");
            }


            return select(filtros, PK[0]);
        } else {
            return null;
        }
    }

    public void apagarTabela() {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.delete(TABLE, null, null);
            db.close();
            db = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    ;
                }
                db = null;
            }
        }
    }

}

