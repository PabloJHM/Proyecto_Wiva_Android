package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.chrno.proyecto_final.Pestanias.Tab1;
import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.Second;
import com.example.chrno.proyecto_final.clases.ListaDeseo;
import com.example.chrno.proyecto_final.clases.Usuario;
import com.example.chrno.proyecto_final.metodos.Util;

import java.util.Date;
import java.util.ArrayList;

public class FormAddWishList extends AppCompatActivity {

    private EditText etListName;
    private CheckBox cbPrivate;
    private ArrayList<ListaDeseo> wishLists;
    private Usuario usuarioTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_wish_list);
        etListName = (EditText)findViewById(R.id.etWishListName);
        cbPrivate = (CheckBox)findViewById(R.id.cbPrivate);
    }

    public void create(View v){
        if(etListName.getText().toString().length()>0) {
            String listname = etListName.getText().toString();
            Boolean isprivate = cbPrivate.isChecked();
            wishLists.add(new ListaDeseo(listname,new Date(),isprivate,usuarioTest.getId()));
        }

    }
}
