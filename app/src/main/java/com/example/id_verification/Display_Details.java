package com.example.id_verification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Display_Details extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvDisplayInfo;


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
    }
}
