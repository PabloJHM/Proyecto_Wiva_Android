package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Localization;
import com.example.chrno.proyecto_final.clases.Producto;

import java.util.ArrayList;

public class DetalleProducto extends AppCompatActivity {

    private TextView tvName, tvDesc, tvCategory, tvViews, tvLikes, tvDate;
    private ImageView iv;
    private Producto p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        p = b.getParcelable("key");
        setTitle(p.getNombre());

        tvName=(TextView)findViewById(R.id.tvProductName);
        tvDesc=(TextView)findViewById(R.id.tvProductDetails);
        tvCategory=(TextView)findViewById(R.id.tvProductCategory);
        tvViews=(TextView)findViewById(R.id.tvNumberViews);
        tvLikes=(TextView)findViewById(R.id.tvNumberLikes);
        tvDate=(TextView)findViewById(R.id.tvDate);

        iv=(ImageView) findViewById(R.id.iv_detalle_producto);

        tvName.setText(p.getNombre());
        tvDesc.setText(p.getDescripcion());
        tvCategory.setText(""+ p.getIdCategoria());//TODO: Savar el nombre de la categoria
        tvViews.setText(p.getNumVisitas()+"");
        tvLikes.setText(p.getLikes()+"");
        tvDate.setText(p.getFecha()+"");
        iv.setImageBitmap(p.getBm());
    }

    public void showMap(View v){
        Intent i = new Intent(this,MapsActivity.class);
        Bundle b = new Bundle();
        ArrayList<Localization> test= new ArrayList<Localization>();
        test.add(new Localization(1, 1, 10));
        p.setLocations(test);
        b.putParcelableArrayList("key", p.getLocations());
        startActivity(i);
    }

    public void Like (View v){
        p.setLikes(p.getLikes()+1);
    }

}
