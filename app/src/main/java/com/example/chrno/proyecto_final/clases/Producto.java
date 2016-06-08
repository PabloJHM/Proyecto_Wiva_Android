package com.example.chrno.proyecto_final.clases;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Producto  implements Parcelable {
    private String nombre, descripcion,codigoBarras;
    private int idCategoria,id;
    private ArrayList<Localization> locations;
    private Date fecha;
    private long likes, numVisitas;
    private Bitmap bm;

    public Producto() {
        this.id=-1;
        this.nombre = this.descripcion =  this.codigoBarras = "";
        this.idCategoria = -1;
        this.locations=new ArrayList<>();
        this.locations.add(new Localization(-1,-1,-1));
        this.fecha = null;
        this.likes = this.numVisitas = -1;
        this.bm=null;
    }

    public Producto(int id, String nombre, String descripcion, String codigoBarras,
                    int idCategoria, ArrayList<Localization> locations, Date fecha, long likes, long numVisitas,
                    Bitmap bm) {
        this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigoBarras = codigoBarras;
        this.idCategoria = idCategoria;
        this.locations = locations;
        this.fecha = fecha;
        this.likes = likes;
        this.numVisitas = numVisitas;
        this.bm=bm;
    }


    protected Producto(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        codigoBarras = in.readString();
        idCategoria = in.readInt();
        locations = in.createTypedArrayList(Localization.CREATOR);
        likes = in.readLong();
        numVisitas = in.readLong();
        bm = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public int getId() {
        return id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public ArrayList<Localization> getLocations() {
        return locations;
    }

    public void setPrecio(ArrayList<Localization> locations) {
        this.locations = locations;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getNumVisitas() {
        return numVisitas;
    }

    public void setNumVisitas(long numVisitas) {
        this.numVisitas = numVisitas;
    }

    public void setLocations(ArrayList<Localization> locations) {
        this.locations = locations;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(codigoBarras);
        dest.writeInt(idCategoria);
        dest.writeTypedList(locations);
        dest.writeLong(likes);
        dest.writeLong(numVisitas);
        dest.writeParcelable(bm, flags);
    }
}
