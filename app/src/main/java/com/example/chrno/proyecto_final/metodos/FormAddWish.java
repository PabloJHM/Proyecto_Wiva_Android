package com.example.chrno.proyecto_final.metodos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Deseo;
import com.example.chrno.proyecto_final.clases.Localization;
import com.example.chrno.proyecto_final.clases.Producto;
import com.example.chrno.proyecto_final.interfaz.ApiRest;
import com.example.chrno.proyecto_final.metodos.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FormAddWish extends AppCompatActivity implements LocationListener {
    private EditText wishTitle, wishPrice, wishDescription;
    private ImageView wishPhoto;
    private Spinner wishListSpiner, wishCategorySpinner;
    private CheckBox wishCheckPrivate;
    private RatingBar wishRate;
    private ArrayList arrayWishSpinner, categoryList;
    private float latitude = 0, longitude = 0;
    private ApiRest api;
    private Retrofit retrofit;
    private Bitmap myBitmap;
    private Intent i;
    private Uri uriUrlFoto;
    private Boolean existeFoto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_wish);
        i=getIntent();
        existeFoto=true;
        uriUrlFoto= (Uri) i.getExtras().get("url");


        System.out.println("XXXXXX-" + uriUrlFoto);
        try {
            init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() throws FileNotFoundException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiRest.class);
        wishTitle = (EditText)findViewById(R.id.etWishTitle);
        wishPrice = (EditText)findViewById(R.id.etWishPrice);
        wishDescription = (EditText)findViewById(R.id.etWishDescription);
        wishPhoto = (ImageView)findViewById(R.id.ivWishPhoto);
        wishCheckPrivate = (CheckBox)findViewById(R.id.cbWishPrivate);
        wishRate = (RatingBar)findViewById(R.id.rbWishRating);
        wishListSpiner= (Spinner)findViewById(R.id.spWishlistSpiner);
        wishCategorySpinner = (Spinner)findViewById(R.id.spCategorySpinner);
        wishPhoto = (ImageView)findViewById(R.id.ivWishPhoto);
        generaLista();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayWishSpinner);
        wishListSpiner.setAdapter(adapter);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoryList);
        wishCategorySpinner.setAdapter(adapterCategory);

//        File imgf= new File(uriUrlFoto.toString());
////        if(imgf.exists()){
//        if(existeFoto){
//            InputStream imageStream = getContentResolver().openInputStream(uriUrlFoto);
//            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
//            wishPhoto.setImageBitmap(yourSelectedImage);
//                System.out.println("tam: "+wishPhoto.getMaxWidth());
//        }
//        }
        Uri uri = uriUrlFoto;
        if (uri != null) {
            Bitmap b = null;
            try {
                b = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(), uri);
                if(b.getWidth()>=4096 || b.getHeight()>=4096)
                {
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    int width = metrics.widthPixels;
                    int height = metrics.heightPixels;
                    b =Bitmap.createScaledBitmap(b, width, height, true);
                }
                wishPhoto.setImageBitmap(b);
                System.out.println("width: "+b.getWidth() + " / height: " + b.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveWish(View v) {
        float price=-1;
        int wishList, category, wishValue; wishList = category = wishValue =-1;

        String title=wishTitle.getText().toString();
        String description=wishDescription.getText().toString();
        Boolean wishPrivate = wishCheckPrivate.isChecked();

        try{
            price = Float.parseFloat(wishPrice.getText().toString());
            wishList = Integer.parseInt(wishListSpiner.getSelectedItem().toString());
            category = Integer.parseInt(wishCategorySpinner.getSelectedItem().toString());
            wishValue = wishRate.getNumStars();
        }catch (NumberFormatException n){}
        Producto product = new Producto(Util.sacaProductos(this, 0).size() ,title, description, "", category,
                new ArrayList<Localization>(), new Date(), 0, 0, myBitmap);

        Deseo wish = new Deseo(0, wishList, new Date(), wishValue, "Anotacion de prueba");
        setResult(RESULT_OK);
        finish();
    }

    public void cancelWish(View v){
        finish();
    }

    public void generaLista(){
        categoryList=new ArrayList<>();
//        Call<List<Integer>> call = api.getCategory();
//        call.enqueue(new Callback<List<Integer>>() {
//            @Override
//            public void onResponse(Response<List<Integer>> response, Retrofit retrofit) {
//                for (Integer a : response.body()) {
//                    categoryList.add(a);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });

        arrayWishSpinner=new ArrayList<>();
//        Call<List<Integer>> callTwo = api.getCategory();
//        callTwo.enqueue(new Callback<List<Integer>>() {
//            @Override
//            public void onResponse(Response<List<Integer>> response, Retrofit retrofit) {
//                for (Integer a : response.body()) {
//                    arrayWishSpinner.add(a);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });
    }


    @Override
    public void onLocationChanged(Location location) {
        latitude = (float)location.getLatitude();
        longitude = (float)location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
