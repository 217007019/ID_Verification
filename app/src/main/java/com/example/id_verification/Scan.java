package com.example.id_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

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
                        Toast.makeText(Scan.this, "User signed out successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Scan.this, Login.class));
                        Scan.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(Scan.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            case R.id.home:

                startActivity(new Intent(Scan.this, MainActivity.class));

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
