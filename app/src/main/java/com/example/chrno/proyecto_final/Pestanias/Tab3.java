package com.example.chrno.proyecto_final.Pestanias;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.chrno.proyecto_final.Adaptador.Adaptador;
import com.example.chrno.proyecto_final.metodos.Buscador;
import com.example.chrno.proyecto_final.metodos.DetalleAmigos;
import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Usuario;
import com.example.chrno.proyecto_final.metodos.Util;

import java.util.List;


public class Tab3 extends Fragment {
    private List list;
    private View v;
    private ListView lv;
    private Adaptador adaptador;
    private Usuario usuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Genero el listvew en el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab3, container, false);
        lv=(ListView) v.findViewById(R.id.lvTab3);
        list= Util.sacaAmigos(getContext(), 0);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(v.getContext(), Buscador.class);
                Bundle b=new Bundle();
                b.putInt("tipo", 0);
                i.putExtras(b);
                startActivity(i);
            }
        });
        generaAdaptador();
        return v;
    }

    public void generaAdaptador(){
        final List list = this.list;
        adaptador = new Adaptador(getActivity(), R.layout.item_friend_preview, list, 3);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getContext(),DetalleAmigos.class);
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
                builder.setMessage(R.string.delete)
                        .setPositiveButton(getResources().getText(R.string.si), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //borrar(position);
                                list.remove(position);
                                adaptador.notifyDataSetChanged();
                                Toast.makeText(getActivity(), R.string.removedFriend, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getResources().getText(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).show();
                return false;
            }
        });
        lv.setAdapter(adaptador);
        registerForContextMenu(lv);
    }

}
