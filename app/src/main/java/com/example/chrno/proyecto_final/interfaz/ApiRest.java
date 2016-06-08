package com.example.chrno.proyecto_final.interfaz;

import com.example.chrno.proyecto_final.clases.Deseo;
import com.example.chrno.proyecto_final.clases.Login;
import com.example.chrno.proyecto_final.clases.Producto;
import com.example.chrno.proyecto_final.clases.Usuario;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiRest {
    @POST("productos/")
    Call<List<Producto>> getProducto();

    @POST("usuario/")
    Call<List<Usuario>> getUsuarios();

    @POST("deseos/")
    Call<List<Deseo>> getDeseos(@Part("idUsuario") int idUsuario);

    @POST("lista-deseos/")
    Call<List<Deseo>> getListaDeseos(@Part("idUsuario") int idUsuario);

    @Multipart
    @POST("lista-amigos/")
    Call<List<Usuario>> getAmigos(@Part("idUsuario") int idUsuario);

    @Multipart
    @POST("lista-deseos-amigo/")
    Call<List<Deseo>> getListaDeseosAmigos(@Part("amigo") String nombreAmigo, @Part("lista") String nombreLista);

    @Multipart
    @POST("lista-deseo-amigo/")
    Call<List<Deseo>> getDeseosAmigos(@Part("idUsuario") int idUsuario);

    @Multipart
    @POST("addFriend/")
    Call<String> addFriend(@Part("idUsuario") int idUsuario, @Part("id") int id);

    @Multipart
    @POST("editarDeseo/")
    Call<String> editarDeseo(@Part("idUsuario") int idUsuario,
                             @Part("nivel") int nivel);
    @Multipart
    @POST("editarUsuario/")
        Call<String> editarUsuarioEstadoTelefono(@Part("idUsuario") int idUsuario,
                                                 @Part("estado") String estado,
                                                 @Part("telefono") String telefono);
    @Multipart
    @POST("editarUsuario/")
        Call<String> editarUsuarioImagen(@Part("idUsuario") int idUsuario, @Part("imagen") RequestBody file);
    @Multipart
    @POST("editarUsuario/")
        Call<String> editarPass(@Part("idUsuario") int idUsuario, @Part("password") String password);

    @POST("addWishFromProduct/")
    Call<String> addWishFromProduct(int idUser, Producto p);

    @Multipart
    @POST("login/")
    Call<Login> login(
            @Part(value = "email") RequestBody email,
            @Part(value = "password") RequestBody pass
    );

//    @FormUrlEncoded
//    @POST("/")
//    Call<Response> login(@Field("email") String email, @Field("pass") String pass);
/*    @POST("restful/api/actividad")
    Call<Actividad> createActividad(@Body Actividad actividad);

    @PUT("restful/api/actividad")
    Call<Actividad> editaActividad(@Body Actividad actividad);

    @DELETE("restful/api/actividad/{id}")
    Call<Actividad> deleteActividad(@Path("id") String id);*/
}
