package com.example.id_verification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Login extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView  tvWelcome, tvForgotPassword, tvRegister;
    ImageView ivStar;
    EditText etPersalNumber, etPassword, etResetMail;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login User");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegister = findViewById(R.id.tvRegister);

        ivStar = findViewById(R.id.ivStar);

        btnLogin = findViewById(R.id.btnLogin);

        etPersalNumber = findViewById(R.id.etPassword);
        etPassword = findViewById(R.id.etPassword);
        etResetMail = findViewById(R.id.etResetMail);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //The users persal number and password has to be tested before they are granted access
                //if it a valid, they are granted access
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

            }

        });

        //Whenever a user wants to reset their password, this code will be invoked
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Login.this);
                dialog.setTitle("Reset password");
                dialog.setMessage("Please enter the email address related to the account you want" +
                        "to reset the password for. A link will be sent to the email address:");

                View dialogView = getLayoutInflater().inflate(R.layout.reset_password, null);
                dialog.setView(dialogView);

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This button sends the user to Admin class so that they can login
                startActivity(new Intent(Login.this, Admin.class));
            }
        });

    }
}
