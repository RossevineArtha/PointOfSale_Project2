package com.rossevineartha.pointofsale_project2.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Barang implements Parcelable{
    private String namaBarang;
    private String idBarang;
    private  int stock;
    private  int hargaBeli;
    private  int hargaJual;

    public Barang() {
    }

    public Barang(String namaBarang, String idBarang, int stock, int hargaBeli, int hargaJual) {
        this.namaBarang = namaBarang;
        this.idBarang = idBarang;
        this.stock = stock;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    protected Barang(Parcel in) {
        namaBarang = in.readString();
        idBarang = in.readString();
        stock = in.readInt();
        hargaBeli = in.readInt();
        hargaJual = in.readInt();
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel in) {
            return new Barang(in);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(int hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(int hargaJual) {
        this.hargaJual = hargaJual;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaBarang);
        dest.writeString(idBarang);
        dest.writeInt(stock);
        dest.writeInt(hargaBeli);
        dest.writeInt(hargaJual);
    }
}
