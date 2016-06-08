package com.example.chrno.proyecto_final.Pestanias;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrno.proyecto_final.Adaptador.Adaptador;
import com.example.chrno.proyecto_final.metodos.DetalleListaDeseo;
import com.example.chrno.proyecto_final.metodos.FormAddWish;
import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.metodos.Camera;
import com.example.chrno.proyecto_final.metodos.Util;

import java.io.IOException;
import java.util.List;

public class Tab1 extends Fragment {
    private List list;
    private View v;
    private ListView lv;
    private TextView tvNombreLista;
    private Adaptador adaptador;
    private int usuario;
    private static final int FORMULARIO_ADD_DESEO = 300;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Genero el listvew en el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab1, container, false);
        lv=(ListView) v.findViewById(R.id.lvTab1);
        list= Util.sacaListaDeseos(usuario);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogoCreaDeseo = new AlertDialog.Builder(v.getContext());
                dialogoCreaDeseo.setTitle(R.string.choose);
                dialogoCreaDeseo.setPositiveButton(R.string.gallery, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        llamaGaleria(v.getContext());
                    }
                });

                dialogoCreaDeseo.setNegativeButton(R.string.camera, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(v.getContext(), Camera.class);
                        startActivityForResult(i, 1);
                    }
                });

                dialogoCreaDeseo.show();
            }
        });
        generaAdaptador();
        return v;
    }

    public void llamaGaleria(Context c){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(c.getPackageManager()) != null)
            startActivityForResult(intent, 0);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == 0) {
                Uri uri = data.getData();
                if (uri != null) { //Obtiene la Uri de la imagen
                    Uri imageUri = data.getData();
                    Bitmap b = null;
                    try {
                        b = MediaStore.Images.Media.getBitmap(
                                v.getContext().getContentResolver(), uri);
                        Intent i=new Intent(v.getContext(), FormAddWish.class);
                        i.putExtra("url",imageUri);
                        startActivityForResult(i, FORMULARIO_ADD_DESEO);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void generaAdaptador(){
        final List list = this.list;
        adaptador = new Adaptador(getActivity(), R.layout.item_wish_list, list, 0);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getContext(),DetalleListaDeseo.class);
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
                builder.setMessage(R.string.deleteWishList)
                        .setPositiveButton(getResources().getText(R.string.si), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //borrar(position);
                                list.remove(position);
                                adaptador.notifyDataSetChanged();
                                Toast.makeText(getActivity(), R.string.removedWishList, Toast.LENGTH_LONG).show();
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
/*    public void borrar(int position){
        list.remove(position);
        Toast.makeText(v.getContext(),"asfaffs",Toast.LENGTH_LONG).show();
        adaptador.notifyDataSetChanged();
    }*/

}



