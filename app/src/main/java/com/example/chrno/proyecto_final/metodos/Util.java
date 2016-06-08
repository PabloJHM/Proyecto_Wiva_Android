package com.example.chrno.proyecto_final.metodos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Deseo;
import com.example.chrno.proyecto_final.clases.ListaDeseo;
import com.example.chrno.proyecto_final.clases.Login;
import com.example.chrno.proyecto_final.clases.Producto;
import com.example.chrno.proyecto_final.clases.Usuario;
import com.example.chrno.proyecto_final.interfaz.ApiRest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Chrno on 28/05/2016.
 */
public class Util {
    private ApiRest api;
    private Retrofit retrofit;


//        Usuario u=new Usuario();
//            u.setNombre("Fran");
//            u.setEmail(email);
//            u.setPassword(pass);
//            u.setTelefono("1234");
//
//        return u;
//    }
private static String urlBase="http://wiva.esy.es/";

    public static Usuario login(String email, String pass) {
        final Usuario u;
        final int[] autentificado = new int[1];
        String mensaje="";
        int errorCode;
        int id;
        final String[] nombre=new String[1],
                correo=new String[1],
                apellido=new String[1],
                password=new String[1],
                estado=new String[1],
                estadoModificado=new String[1],
                fechaNacimiento=new String[1],
                fechaCreacion=new String[1],
                telefono=new String[1],
                imagen=new String[1];
        final Bitmap[] bm = new Bitmap[1];
        Gson gson = new GsonBuilder().create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl(urlBase)
//                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create(gson))
                .build();
        ApiRest apiRest = retrofit.create(ApiRest.class);
        email = "cba";
        pass = "cba";
//
        RequestBody remail= RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody rpass=RequestBody.create(MediaType.parse("text/plain"), pass);
        retrofit2.Call<Login> call = apiRest.login(remail, rpass);

        call.enqueue(new retrofit2.Callback<Login>() {
            //        Content-Json →{"autentificado":"1","message":"","errorCode":0,

            @Override
            public void onResponse(retrofit2.Call<Login> call, retrofit2.Response<Login> response) {
                Headers headerList = response.headers();
                try {
                    JSONObject jsonObject=new JSONObject(headerList.get("Content-json"));
                    autentificado[0] =jsonObject.getInt("autentificado");
                    if(autentificado[0]!=0){
                        System.out.println("Respuesta " + response.isSuccessful() + " " + response.raw());

                        System.out.println("XXXXXXX ERROR " + jsonObject.getString("errorCode"));
                        System.out.println("XXXXXXX getMessage " + jsonObject.getString("message"));
                        System.out.println("XXXXXXX getAutentificado " + autentificado[0]);

                        jsonObject=new JSONObject(jsonObject.get("usuario").toString());

                        nombre[0]=jsonObject.getString("nombre");
                        correo[0]=jsonObject.getString("email");
                        apellido[0]=jsonObject.getString("apellidos");
                        password[0]=jsonObject.getString("password");
                        estado[0]=jsonObject.getString("estado");
                        estadoModificado[0]=jsonObject.getString("estado modificado");
                        fechaNacimiento[0]=jsonObject.getString("fechaNacimiento");
                        fechaCreacion[0]=jsonObject.getString("fechaCreacion");
                        telefono[0]=jsonObject.getString("telefono");
                        imagen[0]=jsonObject.getString("imagen");

                        bm[0] = BitmapFactory.decodeStream(response.body());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Login> call, Throwable t) {
                System.out.println("FAIL");
                System.out.println(t.getMessage());
            }
        });
        if(autentificado[0]!=0){
            u=new Usuario();

            u.setNombre(nombre[0]);
            u.setEmail(correo[0]);
            u.setApellidos(apellido[0]);
            u.setPassword(password[0]);
            u.setApellidos(estado[0]);
            u.setApellidos(estadoModificado[0]);
            u.setFechaNacimiento(formateaFecha(fechaNacimiento[0]));
            u.setFechaCreacion(formateaFecha(fechaCreacion[0]));
            u.setTelefono(telefono[0]);
            u.setStrImage(imagen[0]);
            u.setBmImage(bm[0]);
            return u;
        }
        return null;
    }

    public static Date formateaFecha(String str){
        Date d=new Date();
        d.setYear(pasaANUM(str.substring(0,3)));
        d.setMonth(pasaANUM(str.substring(6,7)));
        d.setDate(pasaANUM(str.substring(10,11)));
        return d;
    }
    public static int pasaANUM(String str){
        int i=-1;
        try{
            i=Integer.parseInt(str);
        }catch (NumberFormatException e){}
        return i;
    }

    public static List sacaListaDeseos(int idUsuario){
        final List listaDeseos=new ArrayList();
        listaDeseos.add(new ListaDeseo("Vault",new Date(),false,idUsuario));
        listaDeseos.add(new ListaDeseo("My wishs",new Date(),true,idUsuario));
//        listaDeseos.add(new ListaDeseo());
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://url-der-ruben/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        ApiRest apiRest = retrofit.create(ApiRest.class);
//        Call<List<Deseo>> call = apiRest.getListaDeseos();
//        call.enqueue(new Callback<List<Deseo>>() {
//            @Override
//            public void onResponse(Response<List<Deseo>> response, Retrofit retrofit) {
//                for (Deseo a : response.body()) {
//                    listaDeseos.add(a);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });
        return listaDeseos;
    }

    //(int idDeseo, int idProducto, Date fechaCreacion, int nivel, String anotacion)
    public static List sacaDeseos(int usuario, String nombreLista){
        List deseos=new ArrayList();
        deseos.add(new Deseo(0, 0 ,new Date(), 2, "Ta bien y eso"));
        deseos.add(new Deseo(1, 1, new Date(), 3, "Cool"));
        deseos.add(new Deseo(2, 2, new Date(), 5, "Soooo Cool"));
        deseos.add(new Deseo(3, 3, new Date(), 0, "meh"));

        return deseos;
    }

    public static List sacaProductos(Context c, int asd){
        final List productos= new ArrayList();
        Bitmap bm;
        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.camisetadelgrana);
        bm=Bitmap.createScaledBitmap(bm, 50, 50, true);

        productos.add(new Producto(0,"producto 1", "Novedoso", "null", 0, null, new Date(), 1, 12 ,bm));
        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.iphonediez);
        bm=Bitmap.createScaledBitmap(bm, 50, 50, true);
        productos.add(new Producto(1,"producto 2", "Antiguo", "null", 1, null, new Date(), 13, 66 ,bm));
        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.gps);
        productos.add(new Producto(2,"producto 3", "Brillanteo", "null", 2, null, new Date(), 34, 44 ,bm));
        bm=Bitmap.createScaledBitmap(bm, 50, 50, true);
        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.play4);
        productos.add(new Producto(3,"producto 4", "Oscuro", "null", 3, null, new Date(), 1, 23 ,bm));
        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.tablet);
        bm=Bitmap.createScaledBitmap(bm, 50, 50, true);
        productos.add(new Producto(4,"producto 5", "Raro", "null", 4, null, new Date(), 67, 100 ,bm));
//        /**/
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://url-der-ruben/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        ApiRest apiRest = retrofit.create(ApiRest.class);
//        Call<List<Producto>> call = apiRest.getProducto();
//        call.enqueue(new Callback<List<Producto>>() {
//            @Override
//            public void onResponse(Response<List<Producto>> response, Retrofit retrofit) {
//                for (Producto a : response.body()) {
//                    productos.add(a);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });
//        /**/
        return productos;
    }

    public static List sacaAmigos(Context c,int usuario){
        List amigos = new ArrayList();
        Date d=new Date();
        d.setDate(3);
        d.setMonth(7);
        Bitmap bm;
        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.kaneki);
        bm=Bitmap.createScaledBitmap(bm, 90, 90, true);
        amigos.add(new Usuario(1, "Fran", "Pleguezuelos", "franplor@wiva.com",
                "abc", "000000001", d, "No waifu no laifu", "", bm, new Date()));

        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.onizuka);
        bm=Bitmap.createScaledBitmap(bm, 90, 90, true);
        amigos.add(new Usuario(2, "Ruben", "Abarca", "rubenbarca@wiva.com",
                "xyz", "000000002", new Date(),"Ruben abarca todas","",bm, new Date()));

        bm=BitmapFactory.decodeResource(c.getResources(), R.drawable.neku);
        bm=Bitmap.createScaledBitmap(bm, 90, 90, true);
        amigos.add(new Usuario(3, "Pablo", "Herrera", "pablohmarabo@wiva.com",
                "qwe", "00000003", new Date(), "Soy un ninja de nivel 22","", bm, new Date()));
        Log.v("DATE: ",d+"");
        return amigos;
    }

    public static List sacaListaDeseosAmigos(String amigo){
        List listaDeseoAmigo = new ArrayList();
        listaDeseoAmigo.add(new ListaDeseo());
        listaDeseoAmigo.add(new ListaDeseo());

        return listaDeseoAmigo;
    }

    public static List sacaDeseosAmigos(String nombreAmigo, String nombreLista){
            List deseos=new ArrayList();
            deseos.add(new Deseo());
            deseos.add(new Deseo(0, 2,new Date(),15, "Anotacion de prueba"));
            return deseos;
    }

    public static List buscar(String cad){

        return null;
    }

    public static void registrar(String nombre, String ap, String email, String pass,
                                 String confirmPass, String telefono, String fechaNac){


    }

    public static ArrayList<Object> search(final String chain, int type){
        final String name = chain;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://url-der-ruben/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiRest apiRest = retrofit.create(ApiRest.class);
        switch (type){
            case 0:
                final ArrayList<Object> usersFound = new ArrayList<>();
                Call<List<Usuario>> call = apiRest.getUsuarios();
                call.enqueue(new Callback<List<Usuario>>(){
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        for (Usuario u : response.body()) {
                            if(u.getEmail().contains(name))
                            {
                                usersFound.add(u);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        t.getLocalizedMessage();
                    }
            });
            return usersFound;

            case 1:
                final ArrayList<Object> productsFound = new ArrayList<>();
                Call<List<Producto>> callProduct = apiRest.getProducto();
                callProduct.enqueue(new Callback<List<Producto>>() {
                    @Override
                    public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                        for (Producto p : response.body()) {
                            if(p.getNombre().contains(name))
                            {
                                productsFound.add(p);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Producto>> call, Throwable t) {
                        t.getLocalizedMessage();
                    }
            });
            return productsFound;
        }
        return null;
    }
    private void addFriend(int idUsuario, Usuario u) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiRest apiRest = retrofit.create(ApiRest.class);
        Call<String> call = apiRest.addFriend(idUsuario, u.getId());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("XXXXXXX", "Amigo aniadido");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("XXXXXXX", "No se pudo aniadir a la lista de amigos");

            }
        });
    }
    public static void editarDeseo(int userId, Deseo d){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        ApiRest apiRest = retrofit.create(ApiRest.class);
//        Call<String> call = apiRest.editarDeseo(userId, d.getNivel());
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.v("XXXXXXX", "Deseo aniadido");
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.v("XXXXXXX", "No se pudo aniadir a la lista de amigos");
//
//            }
//        });
    }
    public static void editarUsuario(Usuario usuario) {// estado y telefono
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiRest apiRest = retrofit.create(ApiRest.class);
        Call<String> call =
                apiRest.editarUsuarioEstadoTelefono(usuario.getId(), usuario.getEstado(), usuario.getTelefono());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("XXXXXXX", "Usuario editado");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("XXXXXXX", "No se pudo aniadir a la lista de amigos");
            }
        });
    }
    public void cambiarImgUsuario(Usuario usuario, String ruta) {
        RequestBody rRuta=RequestBody.create(MediaType.parse("File"), ruta);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiRest apiRest = retrofit.create(ApiRest.class);
        Call<String> call =
                apiRest.editarUsuarioImagen(usuario.getId(), rRuta);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("XXXXXXX", "Usuario editado");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("XXXXXXX", "No se pudo aniadir a la lista de amigos");
            }
        });
    }

    public static void cambiarContraseña(Usuario usuario) {
        int idUser=usuario.getId();
        String pass=usuario.getPassword();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiRest apiRest = retrofit.create(ApiRest.class);
        Call<String> call = apiRest.editarPass(idUser, pass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("XXXXXXX", "Usuario editado contraseña");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("XXXXXXX", "No se pudo aniadir a la lista de amigos");

            }
        });
    }
    public static boolean checkPassword(Usuario u, String password) {
        if (u.getPassword().equals(password))
            return true;
        else
            return false;
    }

    public static void addWishFromProduct(Usuario u, Producto p){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiRest apiRest = retrofit.create(ApiRest.class);
        Call<String> call = apiRest.addWishFromProduct(u.getId(), p);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("XXXXXXX", "Usuario editado contraseña");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("XXXXXXX", "No se pudo aniadir a la lista de amigos");
            }
        });
    }


}
