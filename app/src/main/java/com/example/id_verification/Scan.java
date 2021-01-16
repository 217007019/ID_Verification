package com.example.id_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.id_verification.Classes.Civilian;
import com.example.id_verification.Classes.HomeAffairs;
import com.example.id_verification.Classes.Scanned_ID;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class Scan extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvScaID, tvScan;
    Button btnScan1, btnScan2;
    EditText etScan;
    ImageView qrCode;
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

        qrCode = findViewById(R.id.qrCode);

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

                String searchID = etScan.getText().toString().trim();
                String whereClause = "ID_Number = '"+searchID+"'";

                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                queryBuilder.setWhereClause(whereClause);
                queryBuilder.setPageSize(100).setOffset(0);
                queryBuilder.setSortBy("Names");


                Backendless.Data.of(HomeAffairs.class).find(queryBuilder, new AsyncCallback<List<HomeAffairs>>()
                {

                    public void handleResponse(List<HomeAffairs> response)
                    {
                        for (int i=0; i < response.size(); i++)
                        {



                            Toast.makeText(Scan.this, "ID: "+ response.get(i).getID_Number()
                                    +"\n"+
                                    "Name: "+ response.get(i).getNames() +"\n"+
                                    "Surname: "+ response.get(i).getSurname(), Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(Scan.this, Display_Details.class);
                            intent.putExtra("id", response.get(i).getID_Number());
                            intent.putExtra("name", response.get(i).getNames());
                            intent.putExtra("surname", response.get(i).getSurname());
                            intent.putExtra("DOI", response.get(i).getDOI());
                            intent.putExtra("DOB", response.get(i).getDOB());
                            intent.putExtra("picture", response.get(i).getPicture());
                            startActivity(intent);

                            Scanned_ID scanned_id = new Scanned_ID();
                            scanned_id.setId(response.get(i).getID_Number());
                            scanned_id.setName(response.get(i).getNames());
                            scanned_id.setSurname(response.get(i).getSurname());
                            scanned_id.setDOB(response.get(i).getDOB());
                            scanned_id.setDOI(response.get(i).getDOI());
                            scanned_id.setPicture(response.get(i).getPicture());

                            Backendless.Data.of(Scanned_ID.class).save(scanned_id, new AsyncCallback<Scanned_ID>()
                            {
                                @Override
                                public void handleResponse(Scanned_ID response)
                                {
                                    Toast.makeText(Scan.this, "ID saved on the database!",
                                            Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {

                                }
                            });


                        }


                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(Scan.this, "Error: "+fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });





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
