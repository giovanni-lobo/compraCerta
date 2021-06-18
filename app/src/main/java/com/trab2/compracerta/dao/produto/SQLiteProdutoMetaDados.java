package com.trab2.compracerta.dao.produto;

public interface SQLiteProdutoMetaDados {
    public static final String TABLE = "PRODUTOS";

    public static final String[] PK = {"ITEM"};

    public static String[] METADADOSUPDATE = {"ITEM", "PRODUTO", "QTD", "PRECOUNID"};

    public static String METADADOSSELECT
            = TABLE + ".ITEM, "
            + TABLE + ".PRODUTO, "
            + TABLE + ".QTD, "
            + TABLE + ".PRECOUNID";

    public static String METADADOSCREATE
            = "create table IF NOT EXISTS " + TABLE + " " +
            "(" + PK[0] + " integer, " +
            "PRODUTO varchar(100), " +
            "QTD varchar(11), " +
            "PRECOUNID varchar(15), " +
            "CONSTRAINT PK_PRODUTOS PRIMARY KEY (" + PK[0] + "))";
}
