package com.example.id_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class DisplayCivilian extends AppCompatActivity
{
    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    Button btnCivilian;

    TextView Display_Civilian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_civilian);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Display Civilian");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        btnCivilian = findViewById(R.id.btnCivilian);

        Display_Civilian = findViewById(R.id.Display_Civilian);

        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String id = getIntent().getStringExtra("id");

        Display_Civilian.setText("Registered Civilian \n\n\nCivilian ID Number: " +id+ "\nCivilian Name: "
                +name + "\nCivilian Surname: " +surname);

        btnCivilian.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DisplayCivilian.this, Scan.class);
                startActivity(intent);

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
                        Toast.makeText(DisplayCivilian.this, "User signed out successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DisplayCivilian.this, Login.class));
                        DisplayCivilian.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(DisplayCivilian.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            case R.id.home:

                startActivity(new Intent(DisplayCivilian.this, MainActivity.class));

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}