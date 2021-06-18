package com.trab2.compracerta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

public class MainActivity extends AppCompatActivity {

    private TextView txtOrcamento;
    private EditText campoOrcamento;
    private Button btnLimparOrcamento;
    private Button btnConfirmaOrcamento;
    private CalendarView calendario;
    private Button btnIncluir;
    private Button btnFecharApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOrcamento = findViewById(R.id.TxtOrcamento);
        campoOrcamento = findViewById(R.id.CampoOrcamento);
        btnLimparOrcamento = findViewById(R.id.BtnLimparOrcamento);
        btnConfirmaOrcamento = findViewById(R.id.BtnConfirmaOrcamento);
        calendario = findViewById(R.id.calendarView);
        btnIncluir = findViewById(R.id.BtnIncluir);
        btnFecharApp = findViewById(R.id.BtnFecharApp);
    }

    public void clickBtnLimparOrcamento (View v){
        campoOrcamento.setText("");
    }

    public void clickConfirmaOrcamento (View v){
        campoOrcamento.clearFocus();
        btnIncluir.setBackgroundColor(259);
        hideSoftKeyboard(v);
    }

    public void clickIncluir (View v){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void clickFecharApp (View v){
        System.exit(0);
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}