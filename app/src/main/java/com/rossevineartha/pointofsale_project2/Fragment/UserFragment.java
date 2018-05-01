package com.rossevineartha.pointofsale_project2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.rossevineartha.pointofsale_project2.Entity.User;
import com.rossevineartha.pointofsale_project2.MainActivity;
import com.rossevineartha.pointofsale_project2.R;
import com.rossevineartha.pointofsale_project2.Recycler.RecyclerUsers;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFragment extends Fragment {
    @BindView(R.id.txtNama_MasterDataUserActivity)
    TextView txtNama;
    @BindView(R.id.txtUsername_MasterDataUserActivity)
    TextView txtUsername;
    @BindView(R.id.txtAlamat_MasterDataUserActivity)
    TextView txtAlamat;
    @BindView(R.id.txtNoTelp_MasterDataUserActivity)
    TextView txtNoTelp;
    @BindView(R.id.txtPassword_MasterDataUserActivity)
    TextView txtPassword;
    @BindView(R.id.spinnerRole_MasterData)
    Spinner spinner;
    public String keyUser;
    public DatabaseReference databaseUsers;
    public MainActivity mainActivity;
    private List<String> rolelist;
    public User selectedUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.table_user,container,false);
        ButterKnife.bind(this,view);
        return  view;
    }


    @Override
    public void onStart() {
        super.onStart();

//        rolelist = new ArrayList<>();
//        rolelist.add("Admin");
//        rolelist.add("Kasir");
//        ArrayAdapter<String> adapterCities = new ArrayAdapter<>(this, spinner,rolelist); //(this, android.R.layout.simple_spinner_dropdown_item, rolelist);
//        spinner.setAdapter(adapterCities);

        if(getArguments() != null && getArguments().containsKey("cek")){
            User user = getArguments().getParcelable("cek");
            keyUser = getArguments().getString("keyUser");
            txtAlamat.setText(user.getAlamatUser());
            txtNama.setText(user.getNamaUser());
            txtNoTelp.setText(user.getNoTelpUser());
            txtPassword.setText(user.getPassword());
            txtUsername.setText(user.getUsername());
            if(user.getAdmin()==1){
                spinner.setSelection(1);
            }
            else{
                spinner.setSelection(0);
            }

        }
        databaseUsers = FirebaseDatabase.getInstance().getReference("User");


    }

    @OnClick(R.id.btnAdd_MasterDataUserActivity)
    void  btnAddUser(){

        keyUser = mainActivity.getKeyUser();
        System.out.println("fragmet "+keyUser);
        if (!TextUtils.isEmpty(txtUsername.getText().toString().trim()) && !TextUtils.isEmpty(txtPassword.getText().toString().trim()) &&
                !TextUtils.isEmpty(txtNama.getText().toString().trim()) && !TextUtils.isEmpty(txtNoTelp.getText().toString().trim())&& !TextUtils.isEmpty(txtAlamat.getText().toString().trim()))

        {
            String nama = txtNama.getText().toString();
            User user = new User();
            user.setAdmin(1);
            user.setAlamatUser(txtAlamat.getText().toString());
            user.setNamaUser(txtNama.getText().toString());
            user.setNoTelpUser(txtNoTelp.getText().toString());
            user.setPassword(txtPassword.getText().toString());
            user.setUsername(txtUsername.getText().toString());
            String id="";


            int key =Integer.valueOf(keyUser);

            if(key<10){
                id="00"+(key+1);
            }else if(key<100){
                id="0"+(key+1);
            }
            else{
                id = (key+1)+"";
            }
            user.setIdUser(id);
            databaseUsers = FirebaseDatabase.getInstance().getReference("User");
            databaseUsers.child(String.valueOf(key)).setValue(user);

        }
    }
}
