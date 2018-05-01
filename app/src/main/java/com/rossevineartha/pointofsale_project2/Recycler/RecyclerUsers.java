package com.rossevineartha.pointofsale_project2.Recycler;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rossevineartha.pointofsale_project2.Adapter.UserAdapter;
import com.rossevineartha.pointofsale_project2.Entity.User;
import com.rossevineartha.pointofsale_project2.Fragment.UserFragment;
import com.rossevineartha.pointofsale_project2.MainActivity;
import com.rossevineartha.pointofsale_project2.R;

import java.util.ArrayList;

public class RecyclerUsers implements UserAdapter.UserDataListener {

    private DatabaseReference databaseUser;
    private UserAdapter userAdapter;
    private ArrayList<User> users;
    private MainActivity mainActivity;
    private String keyUser;


    public RecyclerUsers(DatabaseReference database) {
        users = new ArrayList<>();
        databaseUser = database;
        populateDataUser();
    }

    public UserAdapter getUserAdapter() {
        if (userAdapter == null) {
            userAdapter = new UserAdapter();
            userAdapter.setUserDataListener(this);
        }
        return userAdapter;
    }

    public void populateDataUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseUser = database.getReference();

        databaseUser.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    User user = new User();
                    user = noteDataSnapshot.getValue(User.class);
                    users.add(user);
                }
                getUserAdapter().setUserArrayList(users);
                mainActivity.setKeyUser( users.size() + "");
                System.out.println("key User"+users.size()+"");

        }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });

    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getKeyUser() {
        return keyUser;
    }

    @Override
    public void onUserDataClicked(User user) {
        mainActivity.setKeyUser(keyUser);
        mainActivity.tableSelectedUser(user);
        System.out.println(keyUser + "kosongg");


    }
}
