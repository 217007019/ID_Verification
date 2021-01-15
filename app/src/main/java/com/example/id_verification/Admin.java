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
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Admin extends AppCompatActivity
{
    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvAdmin, tvA_ForgotPassword;
    Button btnA_Login;
    EditText etA_Email, etAPassword, etResetMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login Admin");

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvA_ForgotPassword = findViewById(R.id.tvA_ForgotPassword);
        tvAdmin = findViewById(R.id.tvAdmin);


        btnA_Login = findViewById(R.id.btnA_Login);


        etA_Email = findViewById(R.id.etA_Email);
        etAPassword = findViewById(R.id.etA_Password);
        etResetMail = findViewById(R.id.etResetMail);


        //Check is the user attempting to login is admin or even allowed to user the application

        btnA_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //This button needs to first test is the user who's trying to login is the Admin.
                //If they are not, they wont be allowed in. If they are admin they will be sent to admin_home

                if (etA_Email.getText().toString().trim().isEmpty() || etAPassword.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(Admin.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                        String mail = etA_Email.getText().toString().trim();
                        String password = etAPassword.getText().toString().trim();



                        Backendless.UserService.login(mail, password, new AsyncCallback<BackendlessUser>()
                        {

                            @Override
                            public void handleResponse(BackendlessUser response)
                            {
                                //retrieving 'User' property from backendless so that the person trying to login
                                //can be tested if they are admin on not before logging in

                                BackendlessUser backendlessUser = Backendless.UserService.CurrentUser();

                                String userPermission = (String) backendlessUser.getProperty("User");

                                if(userPermission.equals("Admin"))
                                {
                                    ApplicationClass.user = response;
                                    Toast.makeText(Admin.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Admin.this,Admin_Home.class));

                                }
                                else
                                {
                                    Toast.makeText(Admin.this, "Only Admins Are Allowed Access!!! Please make sure you're registered as one",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void handleFault(BackendlessFault fault)
                            {
                                Toast.makeText(Admin.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }, true);

                    }

            }
        });

        tvA_ForgotPassword.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //this sends them to the reset password view
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin.this);
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

                            Toast.makeText(Admin.this, "Please enter mail address!", Toast.LENGTH_SHORT).show();

                        } else
                        {
                            tvLoad.setText("Busy sending reset instructions...please wait...");

                            Backendless.UserService.restorePassword(etResetMail.getText().toString().trim(), new AsyncCallback<Void>()
                            {
                                @Override
                                public void handleResponse(Void response)
                                {


                                    Toast.makeText(Admin.this, "Reset instruction sent to mail address!", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void handleFault(BackendlessFault fault)
                                {


                                    Toast.makeText(Admin.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

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




    }
}
