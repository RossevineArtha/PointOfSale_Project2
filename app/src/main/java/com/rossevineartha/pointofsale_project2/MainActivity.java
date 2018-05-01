package com.rossevineartha.pointofsale_project2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rossevineartha.pointofsale_project2.Adapter.BarangAdapter;
import com.rossevineartha.pointofsale_project2.Fragment.BarangFragment;
import com.rossevineartha.pointofsale_project2.Fragment.UserFragment;
import com.rossevineartha.pointofsale_project2.Recycler.RecyclerBarangs;
import com.rossevineartha.pointofsale_project2.Recycler.RecyclerUsers;

import com.rossevineartha.pointofsale_project2.Entity.Barang;
import com.rossevineartha.pointofsale_project2.Entity.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    User user;

    @BindView(R.id.recyclerView_User)
    RecyclerView recyclerUsers;
    ///Barang
    private DatabaseReference databaseBarang;
    private DatabaseReference databaseUsers;
    private BarangAdapter barangAdapter;

    FragmentTransaction transaction;

    String keyUser;
    String keyBarang;

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public String getKeyBarang() {
        return keyBarang;
    }

    public void setKeyBarang(String keyBarang) {
        this.keyBarang = keyBarang;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);

        transaction = getSupportFragmentManager().beginTransaction();

        ButterKnife.bind(this);

        user = getIntent().getParcelableExtra("userYgLogin");
        databaseUsers = FirebaseDatabase.getInstance().getReference("User");

        databaseBarang = FirebaseDatabase.getInstance().getReference("Barang");



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration did = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerUsers.setLayoutManager(linearLayoutManager);
        recyclerUsers.addItemDecoration(did);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_barang) {

            RecyclerBarangs recycler = new RecyclerBarangs(databaseBarang);
            recyclerUsers.setAdapter(recycler.getBarangAdapter());
            recycler.setMainActivity(this);



            BarangFragment fragment = new BarangFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            fragment.mainActivity = this;
            transaction.replace(R.id.fragment_form_main, fragment);
            transaction.commit();




        } else if (id == R.id.nav_user) {
            RecyclerUsers recycler = new RecyclerUsers(databaseUsers);
            System.out.println("key user di MAIn" + keyUser);
            recyclerUsers.setAdapter(recycler.getUserAdapter());
            recycler.setMainActivity(this);

            UserFragment fragment = new UserFragment();
            fragment.mainActivity=this;
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_form_main, fragment);
            transaction.commit();

            //



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void tableSelectedUser(User user) {
        transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("cek", user);

        UserFragment fragment = new UserFragment();
        System.out.println("kosong ga ya"+ this.keyUser);
        bundle.putString("keyUser", keyUser);
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragment_form_main, fragment);
        transaction.commit();
    }

    public void tableSelectedBarang(Barang barang) {
        transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedBarang", barang);
        bundle.putString("keyBarang",keyBarang);
        BarangFragment fragment = new BarangFragment();
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragment_form_main, fragment);
        transaction.commit();
    }
}
