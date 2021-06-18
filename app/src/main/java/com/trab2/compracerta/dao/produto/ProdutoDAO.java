package com.trab2.compracerta.dao.produto;

import java.util.List;

public interface ProdutoDAO {
    public boolean incluir(Object obj);

    public int alterar(Object obj);

    public int excluir(Object obj);

    public List aplicarFiltro(Object obj);

    public void apagarTabela();

}
