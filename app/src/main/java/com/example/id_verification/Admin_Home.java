package com.example.id_verification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin_Home extends AppCompatActivity
{
    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvAdminHome1, tvAdminHome2;
    Button btnAdminAdd, btnAdminUpdate, btnAdminDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Admin Home");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvAdminHome1 = findViewById(R.id.tvAdmin);
        tvAdminHome2 = findViewById(R.id.tvAdminHome2);

        btnAdminAdd = findViewById(R.id.btnAdminAdd);
        btnAdminUpdate = findViewById(R.id.btnAdminUpdate);
        btnAdminDelete = findViewById(R.id.btnAdminDelete);



        btnAdminAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Whenever the Admin wants to add new user, this code will be invoked
                startActivity(new Intent(Admin_Home.this, Register.class));


            }
        });

        btnAdminUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Whenever the Admin wants to update the users info, this code will be invoked

            }
        });

        btnAdminDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Whenever the Admin wants to delete user, this code will be invoked

            }
        });



    }
}
