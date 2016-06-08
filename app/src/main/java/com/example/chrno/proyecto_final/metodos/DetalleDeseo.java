package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Deseo;
import com.example.chrno.proyecto_final.clases.Producto;
import com.example.chrno.proyecto_final.metodos.Util;

import java.util.List;

public class DetalleDeseo extends AppCompatActivity {
    private RatingBar rbLevel;
    private TextView tvNombreDeseo,tvFecha,tvDescripcion;
    private EditText etAnotations;
    private Producto product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deseo);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        Deseo d = b.getParcelable("key");
        tvNombreDeseo=(TextView)findViewById(R.id.tvNombre);
        tvFecha=(TextView)findViewById(R.id.tvFecha);
        tvDescripcion=(TextView)findViewById(R.id.tvDescripcionProducto);
        rbLevel = (RatingBar)findViewById(R.id.rbWatchLevel);
        etAnotations = (EditText)findViewById(R.id.etAnotation);
        List<Producto> listaProductos=Util.sacaProductos(getBaseContext(), 0);
        for(Producto p : listaProductos) {
            if (p.getId() == d.getIdProducto()) {
                product = p;
            }
        }
        tvNombreDeseo.setText(product.getNombre());
        tvDescripcion.setText(product.getDescripcion());
        tvFecha.setText(d.getFechaCreacion()+"");
        rbLevel.setRating(d.getNivel());
        etAnotations.setText(d.getAnotacion());
    }

    public void save(View v){
        int level = rbLevel.getNumStars();
        String anotations = etAnotations.getText().toString();

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://url-der-ruben/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        ApiRest apiRest = retrofit.create(ApiRest.class);
//        Call<String> call = apiRest.sendModifyWish(anotations, level);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Response<String> response, Retrofit retrofit) {
//                System.out.println("All ok");
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });
    }
}
