package com.example.voicebasedprescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome_page extends AppCompatActivity {
    Button btn_welc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        getSupportActionBar().setTitle("Welcome Page");
        btn_welc = (findViewById(R.id.btn_welcome));

        btn_welc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Welcome_page.this,Login_Form.class);
                startActivity(intent);
                finish();
            }
        });
    }

}