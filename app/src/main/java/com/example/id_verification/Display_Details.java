package com.example.id_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Display_Details extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvDisplayInfo;

    ImageView qrCode;

    Button printQR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Display Details");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvDisplayInfo = findViewById(R.id.tvDisplayInfo);

        qrCode = findViewById(R.id.qrCode);

        printQR = findViewById(R.id.printQR);


        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String id = getIntent().getStringExtra("id");
        String DOI = getIntent().getStringExtra("DOI");
        String DOB = getIntent().getStringExtra("DOB");
        String picture = getIntent().getStringExtra("picture");

        tvDisplayInfo.setText("Scanned ID \n\n\nCivilian ID Number: " +id+
                "\nCivilian Name: " +name +
                "\nCivilian Surname: " +surname+
                "\nCivilian DOI: " +DOI+
                "\nCivilian DOB: " +DOB+
                "\nCivilian Picture: " +picture);

        String qr = "ID number: "+id+
                "Name: "+ name+
                "\nSurname: " + surname+
                "\nDOI: " + DOI+
                "\nDOB: "+ DOB;

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try
        {
            BitMatrix bitMatrix = multiFormatWriter.encode(qr, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);


        }catch (Exception e)
        {
            e.printStackTrace();
        }




        printQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logout:
                Backendless.UserService.logout(new AsyncCallback<Void>()
                {
                    @Override
                    public void handleResponse(Void response)
                    {
                        Toast.makeText(Display_Details.this, "User signed out successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Display_Details.this, Login.class));
                        Display_Details.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(Display_Details.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            case R.id.home:

                startActivity(new Intent(Display_Details.this, MainActivity.class));

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
