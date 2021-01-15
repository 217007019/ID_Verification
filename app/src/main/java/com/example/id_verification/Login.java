package com.example.id_verification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Login extends AppCompatActivity
{

    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView  tvWelcome, tvForgotPassword, tvRegister;
    ImageView ivStar;
    EditText etEmail, etPassword, etResetMail;
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

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etResetMail = findViewById(R.id.etResetMail);

        //check if the user is allowed to use the app

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //The users persal number and password has to be tested before they are granted access
                //if it a valid, they are granted access
                if (etEmail.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(Login.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {

                        String mail = etEmail.getText().toString().trim();
                        String password = etPassword.getText().toString().trim();

                    Backendless.UserService.login(mail, password, new AsyncCallback<BackendlessUser>()
                    {
                        @Override
                        public void handleResponse(BackendlessUser response)
                        {
                            //retrieving 'User' property from backendless so that the person trying to login
                            //can be tested if they are admin on not before logging in

                            BackendlessUser backendlessUser = Backendless.UserService.CurrentUser();

                            String userPermission = (String) backendlessUser.getProperty("Permission");

                            if(userPermission.equals("Allowed"))
                            {
                                String name = backendlessUser.getUserId();

                                ApplicationClass.user = response;
                                Toast.makeText(Login.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("ID", name);
                                startActivity(intent);

                                //Save Active User whenever they login to do the scan
                                String activeUser = backendlessUser.getEmail();

                                ActiveUser au = new ActiveUser();
                                au.setEmail(activeUser);

                                Backendless.Data.of(ActiveUser.class).save(au, new AsyncCallback<ActiveUser>() {
                                    @Override
                                    public void handleResponse(ActiveUser response)
                                    {
                                        Toast.makeText(Login.this, "Active User Save!", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault)
                                    {
                                        Toast.makeText(Login.this, "Error: " + fault.getMessage(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(Login.this, "This user is currently not given access!!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void handleFault(BackendlessFault fault)
                        {
                            Toast.makeText(Login.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }, true);


                }

            }

        });

        //Whenever a user wants to reset their password, this code will be invoked
        tvForgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Reset password");
                builder.setMessage("Please enter the email address related to the account you want" +
                        "to reset the password for. A link will be sent to the email address:");

                View dialogView = getLayoutInflater().inflate(R.layout.reset_password, null);
                builder.setView(dialogView);

                etResetMail = dialogView.findViewById(R.id.etResetMail);

                builder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                        if (etResetMail.getText().toString().isEmpty())
                        {

                            Toast.makeText(Login.this, "Please enter mail address!", Toast.LENGTH_SHORT).show();

                        } else
                            {
                            tvLoad.setText("Busy sending reset instructions...please wait...");

                            Backendless.UserService.restorePassword(etResetMail.getText().toString().trim(), new AsyncCallback<Void>()
                            {
                                @Override
                                public void handleResponse(Void response)
                                {


                                    Toast.makeText(Login.this, "Reset instruction sent to mail address!", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void handleFault(BackendlessFault fault)
                                {


                                    Toast.makeText(Login.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }


                    }

        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

            }
            });

                builder.show();
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
