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
import com.backendless.persistence.DataQueryBuilder;
import com.example.id_verification.Classes.Civilian;
import com.example.id_verification.Classes.HomeAffairs;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvWelcomeHome, tvRegisterHome;

    EditText etID;

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


        btnRegister = findViewById(R.id.btnRegister);


        final HomeAffairs homeAffairs = new  HomeAffairs();

        btnRegister.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(etID.getText().toString().trim().isEmpty() )
                {
                    Toast.makeText(MainActivity.this, "Please make sure all fields are not empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String searchID = etID.getText().toString().trim();
                    String whereClause = "ID_Number = '"+searchID+"'";

                    DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                    queryBuilder.setWhereClause(whereClause);
                    queryBuilder.setPageSize(100).setOffset(0);
                    queryBuilder.setSortBy("Names");


                    Backendless.Data.of(HomeAffairs.class).find(queryBuilder, new AsyncCallback<List<HomeAffairs>>() {

                        public void handleResponse(List<HomeAffairs> response)
                        {
                            for (int i=0; i < response.size(); i++)
                            {



                                Toast.makeText(MainActivity.this, "ID: "+ response.get(i).getID_Number()
                                        +"\n"+
                                        "Name: "+ response.get(i).getNames() +"\n"+
                                        "Surname: "+ response.get(i).getSurname(), Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(MainActivity.this, DisplayCivilian.class);
                                intent.putExtra("id", response.get(i).getID_Number());
                                intent.putExtra("name", response.get(i).getNames());
                                intent.putExtra("surname", response.get(i).getSurname());
                                startActivity(intent);

                                Civilian civilian = new Civilian();
                                civilian.setId(response.get(i).getID_Number());
                                civilian.setName(response.get(i).getNames());
                                civilian.setSurname(response.get(i).getSurname());

                                Backendless.Data.of(Civilian.class).save(civilian, new AsyncCallback<Civilian>() {
                                    @Override
                                    public void handleResponse(Civilian response)
                                    {
                                        Toast.makeText(MainActivity.this, "Civilian saved on the database!",
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
                            Toast.makeText(MainActivity.this, "Error: "+fault.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });





                }


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
                        Toast.makeText(MainActivity.this, "User signed out successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Login.class));
                        MainActivity.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(MainActivity.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            case R.id.home:

                startActivity(new Intent(MainActivity.this, MainActivity.class));

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
