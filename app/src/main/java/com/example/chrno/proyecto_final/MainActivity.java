package com.example.chrno.proyecto_final;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrno.proyecto_final.metodos.Registrar;
import com.example.chrno.proyecto_final.clases.Usuario;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/{
    private EditText edEmail, edPass;
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    private Usuario usuarioTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se Instancia el botón de Scan
//        scanBtn = (Button)findViewById(R.id.scan_button);
        //Se Instancia el Campo de Texto para el nombre del formato de código de barra
//        formatTxt = (TextView)findViewById(R.id.scan_format);
        //Se Instancia el Campo de Texto para el contenido  del código de barra
//        contentTxt = (TextView)findViewById(R.id.scan_content);
        //Se agrega la clase MainActivity.java como Listener del evento click del botón de Scan
//        scanBtn.setOnClickListener(this);

        edEmail = (EditText) findViewById(R.id.edEmail);
        edPass = (EditText) findViewById(R.id.edPass);
        createTestData();
    }

    public void createTestData(){
        usuarioTest = new Usuario(0,"Manolo","Fernandez","manoloco@hotmail.es","1234",
                "150261382",new Date(),"Hi, im using Wiva!","",null, new Date());
    }

    public void login(View v){
        String email = edEmail.getText().toString();
        String pass = edPass.getText().toString();
//        if(hasActiveInternetConnection(this)) {
//            Usuario usuario = Util.login(email, pass);
            if (logIn(email,pass)) {
                Intent i = new Intent(this, Second.class);
                Bundle b = new Bundle();
                b.putParcelable("usuarioTest", usuarioTest);
                i.putExtras(b);
                startActivity(i);
            } else {
                Toast.makeText(this, R.string.noLogin, Toast.LENGTH_LONG).show();
            }
//        } else {
//            Toast.makeText(this, R.string.noInternet, Toast.LENGTH_LONG).show();
//        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("HUEHUE", "Error checking internet connection", e);
            }
        } else {
            Log.d("HUEHUE", "No network available!");
        }
        return false;
    }
    public void registrar(View v){
        Intent i=new Intent(this, Registrar.class);
        startActivity(i);
    }

    public boolean logIn(String email, String pass){
        if((email.equals(usuarioTest.getEmail())) && (pass.equals(usuarioTest.getPassword()))){
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public void onClick(View view) {
//        //Se responde al evento click
//        if(view.getId()==R.id.scan_button){
//            //Se instancia un objeto de la clase IntentIntegrator
//            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
//            //Se procede con el proceso de scaneo
//            scanIntegrator.initiateScan();
//        }
//    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado
            String scanContent = scanningResult.getContents();
            contentTxt.setText("Contenido: " + scanContent);
            //Desplegamos en pantalla el nombre del formato del código de barra scaneado
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("Formato: " + scanFormat);
        }else{
            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.noScan, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
