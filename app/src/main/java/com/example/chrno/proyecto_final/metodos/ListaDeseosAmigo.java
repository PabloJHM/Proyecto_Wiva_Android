package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chrno.proyecto_final.Adaptador.Adaptador;
import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.ListaDeseo;
import com.example.chrno.proyecto_final.clases.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListaDeseosAmigo extends AppCompatActivity {
    private List deseos;
    private Adaptador adaptador;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista_deseo);
        setTitle(getResources().getString(R.string.titulo_deseo));
        lv = (ListView) findViewById(R.id.lv_detalle_lista_deseo);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        ListaDeseo dl = b.getParcelable("key");
        Usuario u = b.getParcelable("user");
        int usuario = dl.getIdUsuario();
        String nombreLista = dl.getNombre();

        deseos = Util.sacaDeseos(usuario, nombreLista);

        generaAdaptador();
    }

    public void generaAdaptador(){
        final List list = this.deseos;
        adaptador = new Adaptador(this, R.layout.item_wish, list, 1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getBaseContext(), DetalleDeseoAmigo.class);
                Bundle b=new Bundle();
                b.putParcelable("key", (Parcelable) list.get(position));
                i.putExtras(b);
                startActivity(i);
            }
        });
        lv.setAdapter(adaptador);
        registerForContextMenu(lv);
    }

    public boolean isCloseToBirthday(Date Birthday){
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        long diff = Birthday.getTime() - new Date().getTime();
        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        if (diff < 7) {
            return true;
        }
        return false;
    }
}
