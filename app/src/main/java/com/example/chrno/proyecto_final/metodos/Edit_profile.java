package com.example.chrno.proyecto_final.metodos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Usuario;

import java.io.IOException;

public class Edit_profile extends AppCompatActivity {
    private ImageView iv;
    private EditText edStatut, edPhoneNumber;
    private Usuario usuarioTest;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        iv = (ImageView) findViewById(R.id.ivImagenUsuario);
        edStatut = (EditText) findViewById(R.id.etStatus);
        edPhoneNumber = (EditText)findViewById(R.id.etPhone);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        usuarioTest=b.getParcelable("key");

        edStatut.setText(usuarioTest.getEstado());
        edPhoneNumber.setText(usuarioTest.getTelefono());
    }
    public void llamaGaleria(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 0);
    }
    public void saveChanged(View v){
        String status="", phoneNumber="";
        status=edStatut.getText().toString();
        phoneNumber=edPhoneNumber.getText().toString();

        usuarioTest.setEstado(status);
        usuarioTest.setTelefono(phoneNumber);

//        Util.editarUsuario(usuarioTest, uri);
//        Toast.makeText(this,"Cambios "+status+" - "+phoneNumber, Toast.LENGTH_LONG).show();
        Log.v("Edit profile: ","Status: "+usuarioTest.getEstado()+ " / Phone: "+usuarioTest.getTelefono());
        finish();
    }

    public void editPassword(View v){
        Intent i = new Intent(this,EditPassword.class);
        Bundle b = new Bundle();
        b.putParcelable("key",usuarioTest);
        Log.v("USER: ", usuarioTest.toString());
        i.putExtras(b);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == 0) {
                uri = data.getData();
                if (uri != null) { //Obtiene la Uri de la imagen
                    Bitmap b = null;
                    try {
                        b = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(), uri);
                        iv.setImageBitmap(b);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
