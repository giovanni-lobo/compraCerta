package com.trab2.compracerta.dao;

import com.trab2.compracerta.dao.produto.ProdutoDAO;
import com.trab2.compracerta.dao.produto.SQLiteProdutoDAO;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SQLiteDAOFactory extends DAOFactory implements SQLiteDadosBanco {
    public ProdutoDAO getProdutoDAO() {
        return new SQLiteProdutoDAO();
    }

    protected String preparaSQL(String valor) {
        if (valor != null) {
            return valor.replaceAll("\'", "''");
        } else {
            return "";
        }
    }


    public String implode(String separator, Collection collection) {
        StringBuffer textBufferReturn = new StringBuffer();
        @SuppressWarnings("rawtypes")
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String text = (String) it.next();
            textBufferReturn.append(text);
            if (it.hasNext()) {
                textBufferReturn.append(separator);
            }
        }
        return textBufferReturn.toString();
    }

    public String montaFiltro(List<String> lista, String separador) {
        StringBuffer filtro = new StringBuffer();
        Iterator<String> filtroIt = lista.iterator();
        while (filtroIt.hasNext()) {
            String texto = filtroIt.next();
            filtro.append(texto);
            if (filtroIt.hasNext()) {
                filtro.append(" " + separador + " ");
            }
        }
        return filtro.toString();
    }
}

