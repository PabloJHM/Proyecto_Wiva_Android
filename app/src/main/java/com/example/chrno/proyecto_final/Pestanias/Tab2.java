package com.example.chrno.proyecto_final.Pestanias;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chrno.proyecto_final.Adaptador.Adaptador;
import com.example.chrno.proyecto_final.metodos.DetalleProducto;
import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.metodos.Util;

import java.util.List;

public class Tab2 extends Fragment {
    private List list;
    private View v;
    private ListView lv;
    private Adaptador adaptador;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Genero el listvew en el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab2, container, false);
        lv=(ListView) v.findViewById(R.id.lvTab2);
        list= Util.sacaProductos(getContext(), 0);
        generaAdaptador();
        return v;
    }

    public void generaAdaptador(){
        final List list = this.list;
        adaptador = new Adaptador(getActivity(), R.layout.item_product, list, 2);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getContext(),DetalleProducto.class);
                Bundle b=new Bundle();
                
                b.putParcelable("key", (Parcelable) list.get(position));
                i.putExtras(b);
                startActivity(i);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("asd")
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //borrar(position);
                                Toast.makeText(v.getContext(),"asfaffs",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).show();
                return false;
            }
        });
        lv.setAdapter(adaptador);
        registerForContextMenu(lv);
    }
/*    public void borrar(int position){
        list.remove(position);
        Toast.makeText(v.getContext(),"asfaffs",Toast.LENGTH_LONG).show();
        adaptador.notifyDataSetChanged();
    }*/

}



