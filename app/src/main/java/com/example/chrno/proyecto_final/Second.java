package com.example.chrno.proyecto_final;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrno.proyecto_final.metodos.Edit_profile;
import com.example.chrno.proyecto_final.metodos.FormAddWish;
import com.example.chrno.proyecto_final.metodos.FormAddWishList;
import com.example.chrno.proyecto_final.Pestanias.Tab1;
import com.example.chrno.proyecto_final.Pestanias.Tab2;
import com.example.chrno.proyecto_final.Pestanias.Tab3;
import com.example.chrno.proyecto_final.clases.ListaDeseo;
import com.example.chrno.proyecto_final.clases.Usuario;
import com.example.chrno.proyecto_final.metodos.Camera;
import com.example.chrno.proyecto_final.metodos.Util;

import java.io.IOException;
import java.util.ArrayList;

public class Second extends FragmentActivity  {
    private FragmentTabHost tabHost;
    private EditText etSearch;
    private TextView tvUserName,tvUserStatus;
    private ImageView ivFondoFotoUsuario;
    private Usuario usuarioTest;
    private ArrayList<ListaDeseo> wishLists;
    private static final int FORMULARIO_ADD_DESEO = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        generateTestData();

        Intent i=getIntent();
        Bundle b=i.getExtras();
        usuarioTest=b.getParcelable("usuarioTest");
        Log.v("USER","Usuario: "+usuarioTest);
        // Creo y asigno las pestañas
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getString(R.string.tab1)),
                Tab1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getString(R.string.tab2)),
                Tab2.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getString(R.string.tab3)),
                Tab3.class, null);
        ivFondoFotoUsuario = (ImageView) findViewById(R.id.ivFotoUsuario);
        setTabColor();
        Bitmap bm = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.wivalogo);
        ivFondoFotoUsuario.setImageBitmap(bm);
        tvUserName = (TextView)findViewById(R.id.tvNombreUsuario);
        tvUserStatus = (TextView)findViewById(R.id.tvUserStatus);
        tvUserName.setText(usuarioTest.getNombre() + " " +usuarioTest.getApellidos());
        tvUserStatus.setText(usuarioTest.getEstado());
    }


    public void setTabColor() {
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor(getString(R.color.colorPrimary))); //unselected
        }
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor(getString(R.color.colorPrimary))); // selected
    }
//
//    public void buscar(View v){
//        String str=textoBusca.getText().toString();
//        Util.buscar(str);
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == 0) {
                Uri uri = data.getData();
                if (uri != null) { //Obtiene la Uri de la imagen
                    Uri imageUri = data.getData();
                    Bitmap b = null;
                    try {
                        b = MediaStore.Images.Media.getBitmap(
                                getBaseContext().getContentResolver(), uri);
                        Intent i=new Intent(getBaseContext(), FormAddWish.class);
                        i.putExtra("url",imageUri);
                        startActivityForResult(i, FORMULARIO_ADD_DESEO);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addDeseo(View v){
        AlertDialog.Builder dialogoCreaDeseo = new AlertDialog.Builder(v.getContext());
        dialogoCreaDeseo.setTitle(getString(R.string.choose));
        dialogoCreaDeseo.setPositiveButton(getString(R.string.gallery), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                llamaGaleria(getBaseContext());
            }
        });

        dialogoCreaDeseo.setNegativeButton(getString(R.string.camera), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), Camera.class);
                startActivityForResult(i, 1);
            }
        });

        dialogoCreaDeseo.show();
    }
    public void llamaGaleria(Context c){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(c.getPackageManager()) != null)
            startActivityForResult(intent, 0);
    }
    public void addListaDeseo(View v){
        Intent i = new Intent(this, FormAddWishList.class);
//        Bundle b = new Bundle();
//        b.putParcelableArrayList("wishList",wishLists);
        startActivity(i);
    }
    public void ajustes(View v){
        Intent i = new Intent(this, Edit_profile.class);
        Bundle b=new Bundle();
        b.putParcelable("key", usuarioTest);
        i.putExtras(b);
        startActivity(i);
    }
    public void search(View v){
        etSearch = (EditText)findViewById(R.id.etSearchProduct);
        String chainToSearch=etSearch.getText().toString();
        if(chainToSearch.length()>0) {
            Toast.makeText(getBaseContext(), R.string.searching, Toast.LENGTH_LONG).show();
            Util.search(chainToSearch, 1);
        } else {
            Toast.makeText(v.getContext(), R.string.stringEmpty, Toast.LENGTH_SHORT).show();
        }
    }

    public void generateTestData(){
//        wishLists.add(new ListaDeseo("Vault",new Date(),false,0));
    }
}
