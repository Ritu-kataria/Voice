package com.example.voicebasedprescription;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Form extends AppCompatActivity {

    EditText mPhone,mPassword;
    Button loginbtn1,btn_sinup;
    FirebaseAuth fauth;
    DatabaseReference reference;
    Helper helper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        mPhone = (findViewById(R.id.l_phone));
        mPassword = (findViewById(R.id.l_pswd));
        btn_sinup = (findViewById(R.id.regbtn));
        helper= new Helper();

        loginbtn1 =(findViewById(R.id.loginbtn));
        fauth =FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        loginbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mPhone.getText().toString().trim();
                String password = mPassword.getText().toString().trim();



                if (TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone number is required");
                    return;
                }
                else if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }
                else {
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.child("User").child(phone).exists()){
                                System.out.println(phone);
                                System.out.println(password);
                                Helper helper = snapshot.child("User").child(phone).getValue(Helper.class);
                                if(helper.getPhone().equals(phone)){
                                    if (helper.getPassword().equals(password)){


                                        Toast.makeText(Login_Form.this,"Login is Successful",Toast.LENGTH_SHORT).show();



                                        Intent intent = new Intent(Login_Form.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();


                                    }else {
                                        Toast.makeText(Login_Form.this,"Credentials is Mismatch ",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else {
                                Toast.makeText(Login_Form.this,"Mobile Number is not Registered ",Toast.LENGTH_SHORT).show();
                            }
                        }




                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Login_Form.this,"Database Error ",Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });

        btn_sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Form.this,Signup_Form.class);
                startActivity(intent);
            }
        });

    }




}