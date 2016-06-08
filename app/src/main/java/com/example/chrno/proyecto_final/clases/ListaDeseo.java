package com.example.chrno.proyecto_final.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ListaDeseo implements Parcelable {
    private String nombre;
    private Date fecha;
    private boolean publico;
    private int idUsuario;

    public ListaDeseo() {
        this.nombre = "";
        this.fecha = new Date();
        this.publico = false;
        this.idUsuario = -1;
    }

    protected ListaDeseo(Parcel in) {
        nombre = in.readString();
        publico = in.readByte() != 0;
        idUsuario = in.readInt();
    }

    public ListaDeseo(String nombre, Date fecha, boolean publico, int idUsuario) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.publico = publico;
        this.idUsuario = idUsuario;
    }

    public static final Creator<ListaDeseo> CREATOR = new Creator<ListaDeseo>() {
        @Override
        public ListaDeseo createFromParcel(Parcel in) {
            return new ListaDeseo(in);
        }

        @Override
        public ListaDeseo[] newArray(int size) {
            return new ListaDeseo[size];
        }
    };

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeByte((byte) (publico ? 1 : 0));
        dest.writeInt(idUsuario);
    }

    @Override
    public String toString() {
        return "ListaDeseo{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
