package com.rossevineartha.pointofsale_project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rossevineartha.pointofsale_project2.Entity.User;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtUsername_LoginActivity)
    EditText txtUsername;
    @BindView(R.id.txtPassword_LoginActivity)
    EditText txtPassword;
    DatabaseReference databaseUsers;
    boolean salah = true;
    String username, password;
    User userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseUsers = FirebaseDatabase.getInstance().getReference("User");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin_LoginActivity)
    public void loginBtn() {

        ValueEventListener valueEventListener = databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                username = txtUsername.getText().toString().trim();
                password = txtPassword.getText().toString().trim();


                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    User user = d.getValue(User.class);
                    if (username.equals(user.getUsername().trim()) && password.equals(user.getPassword().trim())) {
                        userLogin = user;
                        salah = false;
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (!salah) {
            System.out.println("salah");
            if (userLogin.admin == 1) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("userYgLogin",userLogin);
                startActivity(intent);
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            System.out.println("ga jalan");
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show();
        }
    }
}

