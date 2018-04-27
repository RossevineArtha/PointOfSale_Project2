package com.rossevineartha.pointofsale_project2.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String idUser;
    private String namaUser;
    private String noTelpUser;
    private String alamatUser;
    private String password;
    private String username;
    public  int admin;

    public User() {
    }

    public User(String idUser, String namaUser, String noTelpUser, String alamatUser, String password, String username, int admin) {
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.noTelpUser = noTelpUser;
        this.alamatUser = alamatUser;
        this.password = password;
        this.username = username;
        this.admin = admin;
    }

    protected User(Parcel in) {
        idUser = in.readString();
        namaUser = in.readString();
        noTelpUser = in.readString();
        alamatUser = in.readString();
        password = in.readString();
        username = in.readString();
        admin = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNoTelpUser() {
        return noTelpUser;
    }

    public void setNoTelpUser(String noTelpUser) {
        this.noTelpUser = noTelpUser;
    }

    public String getAlamatUser() {
        return alamatUser;
    }

    public void setAlamatUser(String alamatUser) {
        this.alamatUser = alamatUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUser);
        dest.writeString(namaUser);
        dest.writeString(noTelpUser);
        dest.writeString(alamatUser);
        dest.writeString(password);
        dest.writeString(username);
        dest.writeInt(admin);
    }
}
