package com.example.voicebasedprescription;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Locale;


public class Voice_Prescription extends AppCompatActivity {
    Button generatePDFbtn;
    int pageHeight = 1120;
    int pagewidth = 792;
    Bitmap bmp, scaledbmp;
    Date dateobj;
    DateFormat dateFormat;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private String name;
    //views from activity
    private TextInputLayout txtName,txtGender,txtAge,txtDig,txtMed,txtAdv;
    EditText Pname, Pgender,Page,Pdiagnosis,Pmed,Padvice;
    /*Button pdfbtn;
    Bitmap bmp,scaledbmp;
    int pageWidth=1200;
    Date dateobj;
    DateFormat dateFormat;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice__prescription);

        //txvResult = (TextView)findViewById(R.id.txvResult);
        txtName = (TextInputLayout)findViewById(R.id.pname);

        txtGender = (TextInputLayout)findViewById(R.id.pgender);

        txtAge = (TextInputLayout)findViewById(R.id.page);

        txtDig = (TextInputLayout)findViewById(R.id.dignosis);

        txtMed = (TextInputLayout)findViewById(R.id.medicine);

        txtAdv = (TextInputLayout)findViewById(R.id.description);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.med_symbol);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }


    }
    public void generatePDF(View view) {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint title = new Paint();
        Paint contact = new Paint();
        Paint subTitle = new Paint();
        Paint pName = new Paint();
        Paint pAge = new Paint();
        Paint pDig = new Paint();
        Paint pMed = new Paint();
        Paint pAdv = new Paint();
        Paint date=new Paint();
        dateobj = new Date();

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(scaledbmp, 56, 40, paint);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        title.setTextSize(25);
        title.setColor(ContextCompat.getColor(this, R.color.design_default_color_primary));

        subTitle.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        subTitle.setTextSize(15);
        subTitle.setColor(ContextCompat.getColor(this, R.color.purple_200));

        contact.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        contact.setTextSize(15);
        contact.setColor(ContextCompat.getColor(this, R.color.purple_200));
        canvas.drawText("0123456789", 209, 120, contact);
        canvas.drawText("Dr.Ritu Kataria (MBBS)", 209, 100, subTitle);
        canvas.drawText("Health Choice Clinic", 209, 80, title);

        dateFormat=new SimpleDateFormat("dd/mm/yy");
        canvas.drawText("Date: "+dateFormat.format(dateobj),1160,20, date);

//
        pName.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        pName.setColor(ContextCompat.getColor(this, R.color.purple_200));
        pName.setTextSize(17);
        pName.setTextAlign(Paint.Align.LEFT);
        String tempName = txtName.getEditText().getText().toString();
        canvas.drawText("Name : "+tempName, 100, 210, pName);

        pAge.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        pAge.setColor(ContextCompat.getColor(this, R.color.purple_200));
        pAge.setTextSize(17);
        pAge.setTextAlign(Paint.Align.LEFT);
        String tempAge = txtAge.getEditText().getText().toString();
        canvas.drawText("Age : "+tempAge, 100, 240, pAge);

        pDig.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        pDig.setColor(ContextCompat.getColor(this, R.color.purple_200));
        pDig.setTextSize(17);
        pDig.setTextAlign(Paint.Align.LEFT);
        String tempDig = txtDig.getEditText().getText().toString();
        canvas.drawText("Diagnosis : "+tempDig, 100, 270, pDig);

        pMed.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        pMed.setColor(ContextCompat.getColor(this, R.color.purple_200));
        pMed.setTextSize(17);
        pMed.setTextAlign(Paint.Align.LEFT);
        String tempMed = txtMed.getEditText().getText().toString();
        canvas.drawText("Medicines : "+tempMed, 100, 300, pMed);


        pAdv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        pAdv.setColor(ContextCompat.getColor(this, R.color.purple_200));
        pAdv.setTextSize(17);
        pAdv.setTextAlign(Paint.Align.LEFT);
        String tempAdv = txtAdv.getEditText().getText().toString();
        canvas.drawText("Advice : "+tempAdv, 100, 330, pAdv);


        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));
        title.setTextSize(12);
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("This is a E-Prescription, please check date on the top", 396, 560, title);
        pdfDocument.finishPage(myPage);
        File file = new File(Environment.getExternalStorageDirectory(), "Ritu_22_04_2021.pdf");

        try {

            pdfDocument.writeTo(new FileOutputStream(file));

            Toast.makeText(Voice_Prescription.this, "PDF file generated succesfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {

            e.printStackTrace();
        }
        pdfDocument.close();
    }

    private boolean checkPermission() {
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }



    public void getSpeechInput(View view) {
        name = view.getTag().toString();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if(name.equals("txtName"))
                        txtName.getEditText().setText(result.get(0));
                    if(name.equals("txtGender"))
                        txtGender.getEditText().setText(result.get(0));
                    if(name.equals("txtAge"))
                        txtAge.getEditText().setText(result.get(0));
                    if(name.equals("txtDig"))
                        txtDig.getEditText().setText(result.get(0));
                    if(name.equals("txtMed"))
                        txtMed.getEditText().setText(result.get(0));
                    if(name.equals("txtAdv"))
                        txtAdv.getEditText().setText(result.get(0));
                }
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    /*private void createPDF() {
        pdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateobj=new Date();


                if (Pname.getText().toString().length() == 0 ||
                        Page.getText().toString().length() == 0 ||
                        Pgender.getText().toString().length() == 0 ||
                        Pdiagnosis.getText().toString().length() == 0 ||
                        Pmed.getText().toString().length() == 0 ||
                        Padvice.getText().toString().length() == 0) {
                    Toast.makeText(Voice_Prescription.this, "Some fields not filled", Toast.LENGTH_LONG).show();

                } else {

                    PdfDocument mypdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();

                    PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
                    PdfDocument.Page myPage = mypdfDocument.startPage(myPageInfo);
                    Canvas canvas = myPage.getCanvas();


                    canvas.drawBitmap(scaledbmp, 0, 0, myPaint);

                    dateFormat=new SimpleDateFormat("dd/mm/yy");
                    canvas.drawText("Date: "+dateFormat.format(dateobj), pageWidth-20,40,myPaint);
                    myPaint.setTextAlign(Paint.Align.CENTER);
                    myPaint.setTextSize(35f);
                    myPaint.setColor(Color.rgb(128, 0, 128));
                    canvas.drawText("Patient Name: " + Pname.getText(), 20, 450, myPaint);
                    canvas.drawText("Patient Gender: " + Pgender.getText(), 20, 500, myPaint);
                    canvas.drawText("Patient Age: " + Page.getText(), 20, 550, myPaint);
                    canvas.drawText("Diagnosis: " + Pdiagnosis.getText(), 20, 600, myPaint);
                    canvas.drawText("Medicine " + Pmed.getText(), 20, 650, myPaint);
                    canvas.drawText("Advice: " + Padvice.getText(), 20, 700, myPaint);


                    mypdfDocument.finishPage(myPage);

                    File file = new File(Environment.getExternalStorageDirectory(), "/Hello.pdf");

                    try {
                        mypdfDocument.writeTo(new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mypdfDocument.close();
                }}

            });
    }*/
   // private void createPdf(String PName, String PGender, int PAge, String PDiagnosis, String PMed, String PAdvice) throws FileNotFoundException{
        //String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        //File file=new File(pdfPath, "myPDF.pdf");
        //OutputStream outputStream=new FileOutputStream(file);

        //PdfWriter writer=new PdfWriter(file);
       // PdfDocument pdfDocument=new PdfDocument(writer);
       // Document document=new Document(pdfDocument);

       // document.close();

   // }

}
