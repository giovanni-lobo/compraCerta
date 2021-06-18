package com.trab2.compracerta.entidade;

import com.trab2.compracerta.dao.DAOFactory;
import com.trab2.compracerta.dao.produto.ProdutoDAO;

import java.util.List;

public class Produto {

    private String item;

    private String produto;

    private String qtd;

    private String precoUnid;

    public Produto(){
        this("","","","");
    }

    public Produto(String item, String produto, String qtd, String precoUnid) {
        setItem(item);
        setProduto(produto);
        setQtd(qtd);
        setPrecoUnid(precoUnid);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getPrecoUnid() {
        return precoUnid;
    }

    public void setPrecoUnid(String precoUnid) {
        this.precoUnid = precoUnid;
    }



    public boolean incluir() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProdutoDAO produtodao = factory.getProdutoDAO();
        return produtodao.incluir(this);
    }
    public int alterar() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProdutoDAO produtodao = factory.getProdutoDAO();
        return produtodao.alterar(this);
    }

    public int excluir() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProdutoDAO produtodao = factory.getProdutoDAO();
        return produtodao.excluir(this);
    }

    public List aplicarFiltro() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProdutoDAO produtodao = factory.getProdutoDAO();
        return produtodao.aplicarFiltro(this);
    }

    public boolean abrir() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProdutoDAO produtodao = factory.getProdutoDAO();
        List lista = produtodao.aplicarFiltro(this);
        if (!lista.isEmpty()) {
            Produto prod = (Produto) lista.iterator().next();
            setProduto(prod.getProduto());
            setQtd(prod.getQtd());
            setPrecoUnid(prod.getPrecoUnid());
            return true;
        } else {
            return false;
        }
    }
    public void apagarTabela() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProdutoDAO produtodao = factory.getProdutoDAO();
        produtodao.apagarTabela();
    }



}
