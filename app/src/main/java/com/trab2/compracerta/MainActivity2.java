package com.trab2.compracerta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.trab2.compracerta.dao.DAOFactory;
import com.trab2.compracerta.entidade.Produto;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView lbItem;
    private EditText item;
    private TextView lbProduto;
    private EditText produto;
    private TextView lbQtd;
    private EditText qtd;
    private TextView lbPrecoUnid;
    private EditText precoUnid;
    private Button btnSalvar;
    private Button btnAlterar;
    private Button btnExcluir;
    private Button btnLimpar;
    private Button btnConsultar;
    private Button btnListar;
    private Button btnExcluirBD;
    private Button btnFechar;
    private EditText listaProdutos;
    private EditText subTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lbItem = findViewById(R.id.LbItem);
        item = findViewById(R.id.TxtItem);
        lbProduto = findViewById(R.id.LbProduto);
        produto = findViewById(R.id.TxtProduto);
        lbQtd = findViewById(R.id.LbQtd);
        qtd = findViewById(R.id.TxtQtd);
        lbPrecoUnid = findViewById(R.id.LbPrecoUnid);
        precoUnid = findViewById(R.id.TxtPrecoUnid);
        btnSalvar = findViewById(R.id.BtnSalvar);
        btnAlterar = findViewById(R.id.BtnAlterar);
        btnExcluir = findViewById(R.id.BtnExcluir);
        btnLimpar = findViewById(R.id.BtnLimpar);
        btnConsultar = findViewById(R.id.BtnConsultar);
        btnListar = findViewById(R.id.BtnListar);
        btnExcluirBD = findViewById(R.id.BtnExcluirBD);
        btnFechar = findViewById(R.id.BtnFechar);
        listaProdutos = findViewById(R.id.listaProdutos);
        subTotal = findViewById(R.id.Subtotal);

        DAOFactory.setContext(getApplicationContext());
    }

    public void clickBtnSalvar(View v) {
        if (!item.getText().toString().equals("")) {
            Produto prod = new Produto();
            prod.setItem(item.getText().toString());
            prod.setProduto(produto.getText().toString());
            prod.setQtd(qtd.getText().toString());
            prod.setPrecoUnid(precoUnid.getText().toString());
            boolean resultado = prod.incluir();
            setResult(RESULT_CANCELED);
            if (resultado == true) {
                Toast.makeText(MainActivity2.this, "Item incluído com sucesso!", Toast.LENGTH_SHORT).show();
                hideSoftKeyboard(v);
                item.setText("");
                produto.setText("");
                qtd.setText("");
                precoUnid.setText("");
            } else {
                Toast.makeText(MainActivity2.this, "Inclusão não realizada!", Toast.LENGTH_SHORT).show();
            }
        }else{
            item.requestFocus();
        }
        clickListar(v);
    }

    public void clickBtnAlterar(View v) {
        if (!item.getText().toString().equals("")) {
            Produto prod = new Produto();
            prod.setItem(item.getText().toString());
            prod.setProduto(produto.getText().toString());
            prod.setQtd(qtd.getText().toString());
            prod.setPrecoUnid(precoUnid.getText().toString());
            prod.alterar();
            clickListar(v);
        } else {
            Toast.makeText(MainActivity2.this, "Digite um item!", Toast.LENGTH_SHORT).show();
            item.requestFocus();
        }
    }

    public void clickBtnExcluir(View v) {
        if (!item.getText().toString().equals("")) {
            Produto prod = new Produto();
            prod.setItem(item.getText().toString());
            boolean resultadoConsulta = prod.abrir();
            if (resultadoConsulta == true) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity2.this);
                dialogo.setTitle("Excluir produto");
                dialogo.setMessage("Deseja excluir o produto?");
                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int resultadoExclusao = prod.excluir();
                        if (resultadoExclusao != 0) {
                            Toast.makeText(MainActivity2.this, "Exclusão realizada com sucesso!", Toast.LENGTH_SHORT).show();
                            clickListar(v);
                            item.setText("");
                            produto.setText("");
                            qtd.setText("");
                            precoUnid.setText("");
                            hideSoftKeyboard(v);
                        } else {
                            Toast.makeText(MainActivity2.this, "Exclusão não realizada!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogo.show();
            } else {
                Toast.makeText(MainActivity2.this, "Item não encontrado!", Toast.LENGTH_SHORT).show();
                item.requestFocus();
            }
        } else {
            Toast.makeText(MainActivity2.this, "Digite um item!", Toast.LENGTH_SHORT).show();
            item.requestFocus();
        }
    }

    public void clickBtnLimpar(View v) {
        item.setText("");
        produto.setText("");
        qtd.setText("");
        precoUnid.setText("");
        hideSoftKeyboard(v);
        item.requestFocus();
    }

    public void clickBtnConsultar(View v){
        if (!item.getText().toString().equals("")) {
            Produto prod = new Produto();
            prod.setItem(item.getText().toString());
            boolean resultadoConsulta = prod.abrir();
            if (resultadoConsulta == true) {
                produto.setText(prod.getProduto());
                qtd.setText(prod.getQtd());
                precoUnid.setText(prod.getPrecoUnid());
                Toast.makeText(MainActivity2.this, "Item encontrado!", Toast.LENGTH_SHORT).show();
                item.requestFocus();
                hideSoftKeyboard(v);
            } else {
                Toast.makeText(MainActivity2.this, "Item não encontrado!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity2.this, "Digite um número de item!", Toast.LENGTH_SHORT).show();
            item.requestFocus();
        }
    }

    public void clickListar(View v) {
        Produto prod = new Produto();
        List lista = prod.aplicarFiltro();
        String saida = "";
        double subtotal = 0;
        for (int i = 0; i < lista.size(); i++) {
            Produto produ = (Produto) lista.get(i);
            double pr = Double.parseDouble(produ.getQtd());
            double val = Double.parseDouble(produ.getPrecoUnid());
            double valor = pr * val;
            DecimalFormat formato = new DecimalFormat("###.##");
            Double valorF = Double.valueOf(formato.format(valor));
            saida = saida + produ.getItem() + ".  " + produ.getProduto() + "   ->   " + produ.getQtd() + "    *    " + produ.getPrecoUnid() + " =   R$ " + valorF + "\n" ;
            subtotal =  subtotal + valorF;
        }
        listaProdutos.setText(saida);
        subTotal.setText("SUBTOTAL:  R$ " + subtotal);
        hideSoftKeyboard(v);
    }

    public void clickExcluirBD (View v){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity2.this);
        dialogo.setTitle("Apagar banco de dados");
        dialogo.setMessage("Deseja apagar a lista de compras?");
        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Produto prod = new Produto();
                prod.apagarTabela();
                Toast.makeText(MainActivity2.this, "Lista apagada!", Toast.LENGTH_SHORT).show();
                clickListar(v);
                hideSoftKeyboard(v);
            }
        });
        dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogo.show();
    }

    public void clickBtnFechar(View v) {
        hideSoftKeyboard(v);
        finish();
    }


    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}