package com.example.chrno.proyecto_final.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Localization implements Parcelable{
    private float latitude, longitude, price;

    public Localization(float latitude, float longitude, float price) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
    }

    protected Localization(Parcel in) {
        latitude = in.readFloat();
        longitude = in.readFloat();
        price = in.readFloat();
    }

    public static final Creator<Localization> CREATOR = new Creator<Localization>() {
        @Override
        public Localization createFromParcel(Parcel in) {
            return new Localization(in);
        }

        @Override
        public Localization[] newArray(int size) {
            return new Localization[size];
        }
    };

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeFloat(price);
    }
}
