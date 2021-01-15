package com.example.id_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

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
                startActivity(new Intent(Admin_Home.this, UserList.class));

            }
        });

        btnAdminDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Whenever the Admin wants to delete user, this code will be invoked
                startActivity(new Intent(Admin_Home.this, UserInfo.class));

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
                        Toast.makeText(Admin_Home.this, "User signed out successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Admin_Home.this, Admin.class));
                        Admin_Home.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(Admin_Home.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            case R.id.home:

                startActivity(new Intent(Admin_Home.this, Admin_Home.class));

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
