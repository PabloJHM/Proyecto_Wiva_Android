package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Usuario;

public class EditPassword extends AppCompatActivity {

    private EditText etOldPass, etNewPass, etConfirmNewPAss;
    private Usuario usuarioTest;
    private String previousPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        etOldPass = (EditText)findViewById(R.id.etCurrentPass);
        etNewPass = (EditText)findViewById(R.id.etNewPass);
        etConfirmNewPAss = (EditText)findViewById(R.id.etConfirmNewPass);
        Intent i=getIntent();
        Bundle b = i.getExtras();
        usuarioTest=b.getParcelable("key");
        previousPassword = usuarioTest.getPassword();
    }

    public void saveChangesPassword(View v){
        String oldPass=etOldPass.getText().toString();
        String newPass=etNewPass.getText().toString();
        String ConfirmNewPass=etConfirmNewPAss.getText().toString();
//        boolean check=Util.checkPassword(u, oldPass);
        if(oldPass.isEmpty() || newPass.isEmpty() || ConfirmNewPass.isEmpty()){
            Toast.makeText(this,R.string.stringEmpty,Toast.LENGTH_LONG).show();
        }else{
            if(!checkOldPass(oldPass)){
                Toast.makeText(this,R.string.oldPassError,Toast.LENGTH_LONG).show();
            }else{
                if(!oldPass.equals(newPass)) {
                    if (newPass.equals(ConfirmNewPass)) {
                        Toast.makeText(this,R.string.passChanged,Toast.LENGTH_LONG).show();
                        usuarioTest.setPassword(newPass);
                        Log.v("Passwords: ","Old: "+oldPass+" / New: "+newPass);
                        finish();
//                        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://url-der-ruben/")
//                                .addConverterFactory(GsonConverterFactory.create()).build();
//                        ApiRest api = retrofit.create(ApiRest.class);
//                        api.sendNewPass(newPass,u.getId());
                    } else {
                        Toast.makeText(this,R.string.newPassNotEquals,Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this,R.string.newPassEqualsOldOne,Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public boolean checkOldPass(String oldPass){
        if(oldPass.equals(previousPassword)){
            return true;
        }
        return false;
    }
}
