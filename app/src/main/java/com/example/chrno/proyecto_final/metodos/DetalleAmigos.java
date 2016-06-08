package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chrno.proyecto_final.Adaptador.Adaptador;
import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.ListaDeseo;
import com.example.chrno.proyecto_final.clases.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalleAmigos extends AppCompatActivity {
    private List deseos;
    private Adaptador adaptador;
    private ListView lv;
    private FrameLayout flBd;
    private ImageView ivFriendPhoto;
    private Usuario amigo;
    TextView tvFriendName, tvFriendStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_detail);
        setTitle(R.string.friendWish);
        lv = (ListView) findViewById(R.id.lvFriendsWhishList);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        amigo = b.getParcelable("key");
        tvFriendName = (TextView)findViewById(R.id.tvFriendName);
        tvFriendStatus = (TextView)findViewById(R.id.tvFriendStatus);
        flBd = (FrameLayout)findViewById(R.id.flBdComing);
        ivFriendPhoto = (ImageView)findViewById(R.id.ivFriendPhoto);
        ivFriendPhoto.setImageBitmap(amigo.getBmImage());
        tvFriendName.setText(amigo.getNombre());
        tvFriendStatus.setText(amigo.getEstado());

        if(isCloseToBirthday(amigo.getFechaNacimiento()))
        {
            flBd.setVisibility(View.VISIBLE);
        } else {
            flBd.setVisibility(View.GONE);
        }
//        deseos = Util.sacaListaDeseosAmigos(usuario);

        deseos = new ArrayList<>();
        deseos.add(new ListaDeseo("My wishs", new Date(), false, amigo.getId()));
        generaAdaptador();
    }
    public void generaAdaptador(){
        adaptador = new Adaptador(this, R.layout.item_wish_list, deseos, 0);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), ListaDeseosAmigo.class);
                Bundle b = new Bundle();
                ListaDeseo ld = (ListaDeseo) deseos.get(position);
                System.out.println(ld);
                b.putParcelable("key", (Parcelable) deseos.get(position));
                i.putExtras(b);
                startActivity(i);
            }
        });
        lv.setAdapter(adaptador);
        registerForContextMenu(lv);
    }

    public boolean isCloseToBirthday(Date Birthday){
        long diff = Birthday.getTime() - new Date().getTime();
        if (diff < 7) {
            return true;
        }
        return false;
    }

}
