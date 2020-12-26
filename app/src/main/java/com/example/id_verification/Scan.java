package com.example.id_verification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Scan extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvScaID, tvScan;
    Button btnScan1, btnScan2;
    EditText etScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Scan Screen");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvScaID = findViewById(R.id.tvScanID);
        tvScan = findViewById(R.id.tvScan);

        btnScan1 = findViewById(R.id.btnScan1);
        btnScan2 = findViewById(R.id.btnScan2);

        etScan = findViewById(R.id.etScan);

        btnScan1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //After a picture has been scanned, it will send the officer to Display_Details class
                //to show results, whether it was found or not.

                startActivity(new Intent(Scan.this, Display_Details.class));

            }
        });

        btnScan2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //After the ID number was searched, it will send the officer to Display_Details class
                //to show results, whether it was found or not.

                startActivity(new Intent(Scan.this, Display_Details.class));

            }
        });


    }
}
