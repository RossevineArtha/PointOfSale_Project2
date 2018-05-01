package com.rossevineartha.pointofsale_project2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rossevineartha.pointofsale_project2.Entity.Barang;
import com.rossevineartha.pointofsale_project2.Entity.User;
import com.rossevineartha.pointofsale_project2.MainActivity;
import com.rossevineartha.pointofsale_project2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BarangFragment extends Fragment {
    @BindView(R.id.txtNamaBarang_MasterDataBarang)
    TextView txtNama;
    @BindView(R.id.txtHargaBeli_MasterDataBarang)
    TextView txtHargaBeli;
    @BindView(R.id.txtHargaJual_MasterDataBarang)
    TextView txtHargaJual;
    @BindView(R.id.txtStock_MasterDataBarang)
    TextView txtStock;
    @BindView(R.id.btnUpdate_MasterDataBarang)
    TextView btnUpdate;

    public  DatabaseReference databaseBarang;
    public  String key;
    public MainActivity mainActivity;
    public Barang selectedBarang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_barang, container, false);
        ButterKnife.bind(this, view);
        btnUpdate.isEnabled();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        databaseBarang =  FirebaseDatabase.getInstance().getReference("Barang");
        if (getArguments() != null && getArguments().containsKey("selectedBarang")) {
            Barang barang = getArguments().getParcelable("selectedBarang");
            key = getArguments().getString("keyBarang");
            System.out.println("key barang "+ key);
            System.out.println(barang.getStock());
            txtNama.setText(barang.getNamaBarang());
            int stock = barang.getStock();
            txtStock.setText(stock+"");
            txtNama.setText(barang.getNamaBarang()+"");
            txtHargaJual.setText(barang.getHargaJual()+"");
            txtHargaBeli.setText(barang.getHargaBeli()+"");
            selectedBarang = barang;

        }
    }

    @OnClick(R.id.btnAdd_MasterDataBarang)
    void btnAddMasterBarang() {
        System.out.println(mainActivity.getKeyBarang()+"heheh");
        key = mainActivity.getKeyBarang();
        if (!TextUtils.isEmpty(txtHargaBeli.getText().toString().trim()) && !TextUtils.isEmpty(txtHargaJual.getText().toString().trim()) &&
                !TextUtils.isEmpty(txtNama.getText().toString().trim()) && !TextUtils.isEmpty(txtStock.getText().toString().trim()))

        {
            String nama = txtNama.getText().toString();
            int hargabeli = Integer.valueOf(txtHargaBeli.getText().toString());
            int hargajual = Integer.valueOf(txtHargaJual.getText().toString());
            int stock = Integer.valueOf(txtStock.getText().toString());

            Barang barang = new Barang();
            barang.setHargaBeli(hargabeli);
            barang.setHargaJual(hargajual);
            barang.setStock(stock);
            barang.setNamaBarang(nama);

            String id="";

            int keyBarang =Integer.valueOf(key);

            if(keyBarang<10){
                id="00"+(keyBarang+1);
            }else if(keyBarang<100){
                id="0"+(keyBarang+1);
            }
            else{
                id = (keyBarang+1)+"";
            }
            barang.setIdBarang(id);
            databaseBarang.child(String.valueOf(keyBarang)).setValue(barang);
        }

    }

    @OnClick(R.id.btnUpdate_MasterDataBarang)
    void  btnUpdate_MasterBarang(){
        if(selectedBarang != null){

        }
    }
}
