package com.example.id_verification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Register extends AppCompatActivity
{
    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvR, tvR_Type, tvR_PersalNumber,tvR_Title, tvR_Name, tvR_MiddleName, tvR_Surname,tvR_ID, tvR_Gender, tvR_Rank,tvR_Province, tvR_Station, tvR_Email, tvR_Password, tvR_ConfirmPasswod, tvR_Selfie1,tvR_Selfie2;

    EditText etR_PersalNumber, etR_Name, etR_MiddleName, etR_Surname, etR_ID, etR_Email, etR_Password, etR_ConformPassword;

    Spinner sR_Type, sR_Title, sR_Gender, sR_Rank, sR_Station, sR_Province;

    ImageView imPicture;

    Button btnR_Camera, btnR_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvR = findViewById(R.id.tvR);
        tvR_Type = findViewById(R.id.tvR_Type);
        tvR_Title = findViewById(R.id.tvR_Title);
        tvR_PersalNumber = findViewById(R.id.tvR_PersalNumber);
        tvR_Name = findViewById(R.id.tvR_Name);
        tvR_MiddleName = findViewById(R.id.tvR_MiddleName);
        tvR_Surname = findViewById(R.id.tvR_Surname);
        tvR_ID = findViewById(R.id.tvR_ID);
        tvR_Gender = findViewById(R.id.tvR_Gender);
        tvR_Rank = findViewById(R.id.tvR_Rank);
        tvR_Email = findViewById(R.id.tvR_Email);
        tvR_Password = findViewById(R.id.tvR_Password);
        tvR_ConfirmPasswod = findViewById(R.id.tvR_ConfirmPassword);
        tvR_Selfie1 = findViewById(R.id.tvR_Selfie1);
        tvR_Selfie2 = findViewById(R.id.tvR_Selfie2);
        tvR_Province = findViewById(R.id.tvR_Province);
        tvR_Station = findViewById(R.id.tvR_Station);

        etR_PersalNumber = findViewById(R.id.etR_PersalNumber);
        etR_Name = findViewById(R.id.etR_Name);
        etR_MiddleName = findViewById(R.id.etR_MiddleName);
        etR_Surname = findViewById(R.id.etR_Surname);
        etR_ID = findViewById(R.id.etR_ID);
        etR_Email = findViewById(R.id.etR_Email);
        etR_Password = findViewById(R.id.etR_Password);
        etR_ConformPassword = findViewById(R.id.etR_ConfirmPassword);

        imPicture = findViewById(R.id.imPicture);

        sR_Type = findViewById(R.id.sR_Type);
        sR_Title = findViewById(R.id.sR_Title);
        sR_Gender = findViewById(R.id.sR_Gender);
        sR_Rank = findViewById(R.id.sR_Rank);
        sR_Province = findViewById(R.id.sR_Province);
        sR_Station = findViewById(R.id.sR_Station);

        btnR_Camera = findViewById(R.id.btnR_Camera);
        btnR_Register = findViewById(R.id.btnR_Register);


        btnR_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

        btnR_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Have to first test if all fields have been entered and the passwords match
                //then the user will be successfully registered and send the admin back to their homepage
                //. It should also show an toast that confirms the registration

                startActivity(new Intent(Register.this, Admin_Home.class));

            }
        });



    }
}
