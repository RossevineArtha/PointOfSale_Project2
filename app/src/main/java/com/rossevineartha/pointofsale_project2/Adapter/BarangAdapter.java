package com.rossevineartha.pointofsale_project2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rossevineartha.pointofsale_project2.Entity.Barang;
import com.rossevineartha.pointofsale_project2.MainActivity;
import com.rossevineartha.pointofsale_project2.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {
    private ArrayList<Barang> barangArrayList;
    private BarangDataListener barangDataListener;

    public void setBarangDataListener(BarangDataListener b) {
        this.barangDataListener = b;
    }

    public ArrayList<Barang> getBarangArrayList() {
        if (barangArrayList == null) {
            barangArrayList = new ArrayList<>();
        }

        return barangArrayList;
    }

    public void setBarangArrayList(ArrayList<Barang> barangArrayList) {

        this.getBarangArrayList().clear();
        this.getBarangArrayList().addAll(barangArrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        final Barang barang = getBarangArrayList().get(position);

        holder.txtid.setText(barang.getIdBarang());
        holder.txtNama.setText(barang.getNamaBarang());
        holder.txtHargaJual.setText(String.valueOf(barang.getHargaJual()));
        holder.txtStock.setText(String.valueOf(barang.getStock()));
        holder.txtHargaBeli.setText(String.valueOf(barang.getHargaBeli()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barangDataListener.onBarangDataClicked(barang);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getBarangArrayList().size();
    }

    class BarangViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtID_barang_rowbarang)
        TextView txtid;
        @BindView(R.id.txtNama_barang_rowbarang)
        TextView txtNama;
        @BindView(R.id.txtHargaBeli_barang_rowbarang)
        TextView txtHargaBeli;
        @BindView(R.id.txtHargaJual_barang_rowbarang)
        TextView txtHargaJual;
        @BindView(R.id.txtStock_barang_rowbarang)
        TextView txtStock;

        public BarangViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }


    public interface BarangDataListener {
        void onBarangDataClicked(Barang Barang);

    }
}
