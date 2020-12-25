package com.example.id_verification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvWelcomeHome, tvRegisterHome;

    EditText etID, etName, etSurname;

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home Screen");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvWelcomeHome = findViewById(R.id.tvWelcomeHome);
        tvRegisterHome = findViewById(R.id.tvRegisterHome);

        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Scan.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       // getMenuInflater().inflate();
        return super.onCreateOptionsMenu(menu);
    }
}
