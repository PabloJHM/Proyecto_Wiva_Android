package com.example.chrno.proyecto_final.clases;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Parcelable{
    private int id;
    private String estado;
    private String nombre, apellidos, email, password,  telefono, strImage;
    private Date fechaNacimiento, fechaCreacion;
    private Bitmap bmImage;

    public Usuario() {
        this.nombre = this.apellidos = this.email = this.password = this.telefono = "";
        this.id =  -1;
        this.fechaNacimiento = new Date();
        this.estado ="";
    }

    public Usuario(int id, String nombre, String apellidos, String email,
                   String password, String telefono, Date fechaNacimiento, String estado, String strImage,
                   Bitmap bmImage, Date fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.strImage = strImage;
        this.bmImage=bmImage;
        this.fechaCreacion=fechaCreacion;
    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        estado = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        email = in.readString();
        password = in.readString();
        telefono = in.readString();
        strImage = in.readString();
        bmImage = in.readParcelable(Bitmap.class.getClassLoader());
        fechaNacimiento = new Date(in.readLong());
        fechaCreacion = new Date(in.readLong());
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getStrImage() {
        return strImage;
    }

    public void setStrImage(String strImage) {
        this.strImage = strImage;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Bitmap getBmImage() {
        return bmImage;
    }

    public void setBmImage(Bitmap bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", strImg='" + strImage + '\'' +
                ", estado='" + estado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(estado);
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(telefono);
        dest.writeString(strImage);
        dest.writeParcelable(bmImage, flags);
        dest.writeLong(fechaNacimiento.getTime());
        dest.writeLong(fechaCreacion.getTime());
    }
}
