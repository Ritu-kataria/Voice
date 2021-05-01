package com.example.voicebasedprescription;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Myprofile extends AppCompatActivity {

    TextInputLayout fullname,email,phoneNo,password;
    TextView fullnamelabel;
    String phone1,pswd1;
    FirebaseDatabase database;
    DatabaseReference userRef;
    private static final String USER="user";
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        fullname = findViewById(R.id.Full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_profile);
        password = findViewById(R.id.password_profile);
        fullnamelabel = findViewById(R.id.label_profile);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USER);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    if(ds.child("phone").getValue().equals(phone)){

                        fullnamelabel.setText(ds.child("name").getValue(String.class));
                        fullname.getEditText().setText(ds.child("name").getValue(String.class));
                        phoneNo.getEditText().setText(phone);
                        email.getEditText().setText(ds.child("email").getValue(String.class));
                        password.getEditText().setText(ds.child("password").getValue(String.class));


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }




}