package com.example.chrno.proyecto_final.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Deseo implements Parcelable {
    private int idDeseo;
    private int idProducto;
    private Date fechaCreacion;
    private int nivel;
    private String anotacion;

    public Deseo() {
        this.idDeseo = this.idProducto = this.nivel = -1;
        this.fechaCreacion = null;
    }

    public Deseo(int idDeseo, int idProducto, Date fechaCreacion, int nivel, String anotacion) {
        this.idDeseo = idDeseo;
        this.idProducto = idProducto;
        this.fechaCreacion = fechaCreacion;
        this.nivel = nivel;
        this.anotacion = anotacion;
    }

    protected Deseo(Parcel in) {
        idDeseo=in.readInt();
        idProducto = in.readInt();
        nivel = in.readInt();
        anotacion = in.readString();
    }

    public static final Creator<Deseo> CREATOR = new Creator<Deseo>() {
        @Override
        public Deseo createFromParcel(Parcel in) {
            return new Deseo(in);
        }

        @Override
        public Deseo[] newArray(int size) {
            return new Deseo[size];
        }
    };

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getIdDeseo() {
        return idDeseo;
    }

    public void setIdDeseo(int idDeseo) {
        this.idDeseo = idDeseo;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idDeseo);
        dest.writeInt(idProducto);
        dest.writeInt(nivel);
        dest.writeString(anotacion);
    }
}
