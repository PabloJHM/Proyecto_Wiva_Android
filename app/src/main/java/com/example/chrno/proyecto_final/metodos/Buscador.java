package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.metodos.Util;

public class Buscador extends AppCompatActivity {
    private EditText edBuscar;
    private Intent i;
    private int tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);
        setTitle(getString(R.string.titulo_buscador));
        edBuscar = (EditText) findViewById(R.id.edBuscador);
        i=getIntent();
    }

    public void buscar(View v){
        String chainToSearch = edBuscar.getText().toString();
        if(chainToSearch.length()>0){
            Toast.makeText(getBaseContext(), R.string.searching, Toast.LENGTH_LONG).show();
            Util.search(edBuscar.getText().toString(), 0);
        } else {
            Toast.makeText(getBaseContext(), R.string.stringEmpty, Toast.LENGTH_LONG).show();
        }
    }
}
