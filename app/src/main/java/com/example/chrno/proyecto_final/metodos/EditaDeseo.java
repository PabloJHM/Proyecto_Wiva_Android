package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Deseo;

public class EditaDeseo extends AppCompatActivity {

    private RatingBar rbEditStars;
    private Deseo d;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_deseo);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        d=b.getParcelable("key");
        pos=b.getInt("pos");
        rbEditStars = (RatingBar)findViewById(R.id.rbEditStars);
        rbEditStars.setNumStars(d.getNivel());
        System.out.println(d.getIdProducto());
    }
    public void editar(View v){
        Intent i=new Intent();
        Bundle b=new Bundle();

        d.setIdDeseo(1);

        b.putParcelable("key", d);
        b.putInt("pos", pos);
        i.putExtras(b);

        setResult(RESULT_OK, i);
        finish();
    }
}
