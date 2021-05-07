package com.example.voicebasedprescription;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ShareFile extends AppCompatActivity {

    private String stringFile= Environment.getExternalStorageDirectory().getPath()+ File.separator+"E-Prescription.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_file);

        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, getPackageManager().PERMISSION_GRANTED);

        StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


    }

    public void buttonShareFile(View view){

        File file = new File(stringFile);

        if(!file.exists()){
            Toast.makeText(this,"File doesn't exist..",Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+file));

        startActivity(Intent.createChooser(intent,"Share the File..."));
    }

}