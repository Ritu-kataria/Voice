package com.example.voicebasedprescription;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Signup_Form extends AppCompatActivity {

    EditText name, email, phone, pass, con_pass;
    CheckBox doc, patient;
    RadioButton RbtnM, RbtnF;
    String text;
    Button reg1btn;
    FirebaseAuth fauth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");

        //for the getting the ref of database
        reference = FirebaseDatabase.getInstance().getReference();

        name = (findViewById(R.id.name));
        email = (findViewById(R.id.email));
        phone = (findViewById(R.id.phone));
        pass = (findViewById(R.id.pswd));
        con_pass = (findViewById(R.id.c_pswd));
        RbtnF = (findViewById(R.id.RbtnF));
        RbtnM = (findViewById(R.id.RbtnM));
        doc = (findViewById(R.id.CboxD));
        patient = (findViewById(R.id.CboxP));
        reg1btn =(findViewById(R.id.reg1btn));

        fauth = FirebaseAuth.getInstance();

        if (fauth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        reg1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                String Password = pass.getText().toString().trim();
                String ConPass = con_pass.getText().toString().trim();

                if (TextUtils.isEmpty(Name)) {
                    name.setError("Enter your Name");
                    return;
                } else if (TextUtils.isEmpty(Email)) {
                    email.setError("Enter your Email");
                    return;
                } else if (TextUtils.isEmpty(Phone)) {
                    phone.setError("Enter your Number");
                    return;
                } else if (Phone.length() < 10) {
                    phone.setError("Enter valid Phone Number");
                    return;
                } else if (TextUtils.isEmpty(Password)) {
                    pass.setError("Enter your Password");
                    return;
                } else if (!Password.contentEquals(ConPass)) {
                    pass.setError("Password does not Match");
                    return;

                } else {
                    if (doc.isChecked()) {

                        text = "1";

                    } else {

                        text = "0";

                    }
                    validateDetails(Name,Email,Phone,Password);
                }


            }
             private void validateDetails(String name, String email, String phone, String password){
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.child("Users").child(phone).exists()){
                            HashMap<String,Object>userdatamap =new HashMap<>();
                            userdatamap.put("Phone",phone);
                            userdatamap.put("Name",name);
                            userdatamap.put("Email",email);
                            userdatamap.put("Password",password);


                            reference.child("User").child(phone).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(Signup_Form.this,Login_Form.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Signup_Form.this,"Fail to Register",Toast.LENGTH_SHORT).show();

                    }
                });
             }
        });


    }
}