package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Deseo;

public class DetalleDeseoAmigo extends AppCompatActivity {
    private RatingBar rbLevel;
    private EditText etAnotations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deseo);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        Deseo d = b.getParcelable("key");
        rbLevel = (RatingBar)findViewById(R.id.rbWatchLevel);
        etAnotations = (EditText)findViewById(R.id.etAnotation);
        rbLevel.setRating(d.getNivel());
        rbLevel.setIsIndicator(true);
        etAnotations.setText(d.getAnotacion());
        etAnotations.setEnabled(false);
    }
}
