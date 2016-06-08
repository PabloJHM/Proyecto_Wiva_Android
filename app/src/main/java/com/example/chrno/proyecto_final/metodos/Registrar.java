package com.example.chrno.proyecto_final.metodos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.metodos.Util;

public class Registrar extends AppCompatActivity {
    EditText edNombre, edAp, edEmail, edPass, edConfirmPass, edTelefono, edFechaNac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrar);
        edNombre =(EditText) findViewById(R.id.edNombreRegistro);
        edAp = (EditText) findViewById(R.id.edApellidosRegistro);
        edEmail = (EditText) findViewById(R.id.edEmailRegistro);
        edPass =(EditText) findViewById(R.id.edPassRegistro);
        edConfirmPass =(EditText) findViewById(R.id.edConfirmPassRegistro);
        edTelefono =(EditText) findViewById(R.id.edTelefonoRegistro);
        edFechaNac =(EditText) findViewById(R.id.edFechaNacRegistro);

    }

    public void aceptar(View v){
        String nombre, ap, email, pass, confirmPass, telefono, fechaNac;

        nombre=edNombre.getText().toString();
        ap=edAp.getText().toString();
        email=edEmail.getText().toString();
        pass=edPass.getText().toString();
        confirmPass=edConfirmPass.getText().toString();
        telefono=edTelefono.getText().toString();
        fechaNac=edFechaNac.getText().toString();

        Util.registrar(nombre, ap, email, pass, confirmPass, telefono, fechaNac);

        Toast.makeText(getBaseContext(), "me da peresha filtrar", Toast.LENGTH_LONG).show();
    }

    public void cancelar(View v){ finish(); }
}
