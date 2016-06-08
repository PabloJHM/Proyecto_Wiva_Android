package com.example.chrno.proyecto_final.metodos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chrno.proyecto_final.Adaptador.Adaptador;
import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Deseo;
import com.example.chrno.proyecto_final.clases.ListaDeseo;

import java.util.List;

public class DetalleListaDeseo extends AppCompatActivity {
    private List deseos;
    private Adaptador adaptador;
    private ListView lv;
    private int idUsuario;
    private static final int EDITARDESEO=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista_deseo);

        setTitle("Deseos");
        lv = (ListView) findViewById(R.id.lv_detalle_lista_deseo);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        ListaDeseo dl = b.getParcelable("key");
        idUsuario = dl.getIdUsuario();
        String nombreLista = dl.getNombre();

        deseos = Util.sacaDeseos(idUsuario, nombreLista);

        generaAdaptador();
    }

    public void generaAdaptador(){
        final List list = this.deseos;
        adaptador = new Adaptador(this, R.layout.item_wish, list, 1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), DetalleDeseo.class);
                Bundle b = new Bundle();
                b.putParcelable("key", (Parcelable) list.get(position));
                i.putExtras(b);
                startActivity(i);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetalleListaDeseo.this);
                builder.setMessage(R.string.deleteWish)
                        .setPositiveButton(getResources().getText(R.string.si), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //borrar(position);
                                list.remove(position);
                                adaptador.notifyDataSetChanged();
                                Toast.makeText(getBaseContext(), R.string.removedWish, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(DetalleListaDeseo.this.getText(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.out.println("");
                            }
                        }).show();
                return false;
            }
        });
        lv.setAdapter(adaptador);
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {//creamos nuestro menu contextual
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual_deseo, menu);
    }
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo vistainfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int posicion = vistainfo.position;
        switch(item.getItemId()){
            case R.id.editar:
                Toast.makeText(this, "edita "+posicion, Toast.LENGTH_LONG).show();
                editar(posicion);
                return true;
            case R.id.borrar:
                Toast.makeText(this, "borrar "+posicion, Toast.LENGTH_LONG).show();
                return true;
            default: return super.onContextItemSelected(item);
        }
    }

    public boolean editar(int pos){
        Deseo d=(Deseo) deseos.get(pos);
        Intent i=new Intent(this, EditaDeseo.class);
        Bundle b=new Bundle();
        b.putParcelable("key",d);
        b.putInt("pos", pos);
        i.putExtras(b);
        startActivityForResult(i, EDITARDESEO);
        return false;
    }
    public boolean borrar(int pos){
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==EDITARDESEO){
                Bundle b=data.getExtras();
                Deseo d=b.getParcelable("key");
                int pos=b.getInt("pos");

                Util.editarDeseo(idUsuario,d);

                deseos.set(pos,d);
                adaptador.notifyDataSetChanged();
            }
        }
    }
    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_contextual_deseo, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        return super.onOptionsItemSelected(item);
//    }
}
