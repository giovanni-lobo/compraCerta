package com.trab2.compracerta.dao;

import android.content.Context;

import com.trab2.compracerta.dao.produto.ProdutoDAO;

public abstract class DAOFactory {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DAOFactory.context = context;
    }

    public static DAOFactory getDAOFactory() {
        return new SQLiteDAOFactory();
    }

    public abstract ProdutoDAO getProdutoDAO();
}
