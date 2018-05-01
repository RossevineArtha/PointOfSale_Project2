package com.rossevineartha.pointofsale_project2.Recycler;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rossevineartha.pointofsale_project2.Adapter.BarangAdapter;
import com.rossevineartha.pointofsale_project2.Adapter.UserAdapter;
import com.rossevineartha.pointofsale_project2.Entity.Barang;
import com.rossevineartha.pointofsale_project2.Entity.User;
import com.rossevineartha.pointofsale_project2.Fragment.BarangFragment;
import com.rossevineartha.pointofsale_project2.Fragment.UserFragment;
import com.rossevineartha.pointofsale_project2.MainActivity;
import com.rossevineartha.pointofsale_project2.R;

import java.util.ArrayList;

public class RecyclerBarangs implements BarangAdapter.BarangDataListener {

    private DatabaseReference databaseBarang;
    private BarangAdapter barangAdapter;
    private ArrayList<Barang> barangs;
    private MainActivity mainActivity;
    private String keyBarang;

    public RecyclerBarangs(DatabaseReference database) {
        barangs = new ArrayList<>();
        databaseBarang = database;
        populateDataBarang();
//        mainActivity.setKeyBarang(getKeyBarang());
    }

    public BarangAdapter getBarangAdapter() {
        if (barangAdapter == null) {
            barangAdapter = new BarangAdapter();
            barangAdapter.setBarangDataListener(this);
        }
        return barangAdapter;
    }

    public ArrayList<Barang> getBarangs() {
        return barangs;
    }

    public void setBarangs(ArrayList<Barang> barangs) {

        this.getBarangs().clear();
        this.getBarangs().addAll(barangs);

    }

    public int populateDataBarang() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseBarang = database.getReference();
        barangs.clear();

        databaseBarang.child("Barang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                barangs.clear();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Barang barang = new Barang();
                    barang = noteDataSnapshot.getValue(Barang.class);
                    barangs.add(barang);
                }
                getBarangAdapter().setBarangArrayList(barangs);
                mainActivity.setKeyBarang(barangs.size() + "");
                System.out.println(mainActivity.getKeyBarang());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });

        return barangs.size();
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getKeyBarang() {
        return keyBarang;
    }

    public void setKeyBarang(String keyBarang) {
        this.keyBarang = keyBarang;
    }

    @Override
    public void onBarangDataClicked(Barang barang) {

        mainActivity.setKeyBarang(keyBarang);
        mainActivity.tableSelectedBarang(barang);
    }
}
