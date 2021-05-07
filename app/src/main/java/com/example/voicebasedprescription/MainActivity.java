package com.example.voicebasedprescription;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.core.Context;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button btnprofile,btnmic,btnqrscan,btnpresc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnprofile=(Button)findViewById(R.id.buttonprofile);
        btnmic=(Button)findViewById(R.id.buttonmic);
        btnqrscan=(Button)findViewById(R.id.buttonqrscan);
        btnpresc=(Button)findViewById(R.id.buttonpresc);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Myprofile.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
            }
        });

        btnqrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRcode_Scanner.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Scan QR Code", Toast.LENGTH_SHORT).show();
            }
        });

        btnmic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Voice_Prescription.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Record prescription", Toast.LENGTH_SHORT).show();
            }
        });

        btnpresc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShareFile.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Share prescription", Toast.LENGTH_SHORT).show();
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);



        Menu menu =navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_prescription:
                Intent intent = new Intent(MainActivity.this,Voice_Prescription.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                Intent intent1 = new Intent(MainActivity.this, Login_Form.class);
                startActivity(intent1);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}