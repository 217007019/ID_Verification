package com.example.id_verification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity  {
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    private View mProgressView;
    private View mRegisterFormView;
    private TextView tvLoad;

    TextView tvR, tvR_Type, tvR_PersalNumber,tvR_Title, tvR_Name, tvR_MiddleName, tvR_Surname,tvR_ID, tvR_Gender, tvR_Rank,tvR_Province, tvR_Station, tvR_Email, tvR_Password, tvR_ConfirmPasswod, tvR_Selfie1,tvR_Selfie2;

    EditText etR_PersalNumber, etR_Name, etR_MiddleName, etR_Surname, etR_ID, etR_Email, etR_Password, etR_ConformPassword;

    Spinner sR_User, sR_Title, sR_Gender, sR_Rank, sR_Station, sR_Province, sR_Permission;

    ImageView imPicture;

    Button btnR_Camera, btnR_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");

        mRegisterFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.register_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvR = findViewById(R.id.tvR);
        tvR_Type = findViewById(R.id.tvR_User);
        tvR_Title = findViewById(R.id.tvR_Title);
        tvR_PersalNumber = findViewById(R.id.tvR_PersalNumber);
        tvR_Name = findViewById(R.id.tvR_Name);
        tvR_MiddleName = findViewById(R.id.tvR_MiddleName);
        tvR_Surname = findViewById(R.id.tvR_Surname);
        tvR_ID = findViewById(R.id.tvR_ID);
        tvR_Gender = findViewById(R.id.tvR_Gender);
        tvR_Rank = findViewById(R.id.tvR_Rank);
        tvR_Email = findViewById(R.id.tvR_Email);
        tvR_Password = findViewById(R.id.tvR_Password);
        tvR_ConfirmPasswod = findViewById(R.id.tvR_ConfirmPassword);
        tvR_Selfie1 = findViewById(R.id.tvR_Selfie1);
        tvR_Selfie2 = findViewById(R.id.tvR_Selfie2);
        tvR_Province = findViewById(R.id.tvR_Province);
        tvR_Station = findViewById(R.id.tvR_Station);

        etR_PersalNumber = findViewById(R.id.etR_PersalNumber);
        etR_Name = findViewById(R.id.etR_Name);
        etR_MiddleName = findViewById(R.id.etR_MiddleName);
        etR_Surname = findViewById(R.id.etR_Surname);
        etR_ID = findViewById(R.id.etR_ID);
        etR_Email = findViewById(R.id.etR_Email);
        etR_Password = findViewById(R.id.etR_Password);
        etR_ConformPassword = findViewById(R.id.etR_ConfirmPassword);

        imPicture = findViewById(R.id.imPicture);

        sR_User = findViewById(R.id.sR_User);
        sR_Title = findViewById(R.id.sR_Title);
        sR_Gender = findViewById(R.id.sR_Gender);
        sR_Rank = findViewById(R.id.sR_Rank);
        sR_Province = findViewById(R.id.sR_Province);
        sR_Station = findViewById(R.id.sR_Station);
        sR_Permission = findViewById(R.id.sR_Permission);


        btnR_Camera = findViewById(R.id.btnR_Camera);
        btnR_Register = findViewById(R.id.btnR_Register);


        btnR_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Take a picture using the camera
                askCameraPermissions();

            }
        });

        btnR_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Have to first test if all fields have been entered and the passwords match
                //then the user will be successfully registered and send the admin back to their homepage
                //. It should also show an toast that confirms the registration

                if(etR_PersalNumber.getText().toString().trim().isEmpty() || etR_Name.getText().toString().trim().isEmpty() || etR_MiddleName.getText().toString().trim().isEmpty()
                        || etR_Surname.getText().toString().trim().isEmpty() || etR_ID.getText().toString().trim().isEmpty() || etR_Email.getText().toString().trim().isEmpty()
                || etR_Password.getText().toString().trim().isEmpty() || etR_ConformPassword.getText().toString().trim().isEmpty() || sR_Permission.getSelectedItem().toString().trim().isEmpty()
                || sR_Rank.getSelectedItem().toString().trim().isEmpty() || sR_Gender.getSelectedItem().toString().trim().isEmpty() || sR_Title.getSelectedItem().toString().trim().isEmpty()
                || sR_User.getSelectedItem().toString().trim().isEmpty() || sR_Province.getSelectedItem().toString().trim().isEmpty() || sR_Station.getSelectedItem().toString().trim().isEmpty())
                {
                    Toast.makeText(Register.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(etR_ConformPassword.getText().toString().trim().equals(etR_Password.getText().toString().trim()))
                    {

                        Toast.makeText(Register.this, "User successfully registered!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, Admin_Home.class));

                        BackendlessUser user = new BackendlessUser();

                        user.setEmail(etR_Email.getText().toString().trim());
                        user.setPassword(etR_Password.getText().toString().trim());
                        user.setProperty("Personnel",etR_PersalNumber.getText().toString().trim());
                        user.setProperty("User",sR_User.getSelectedItem().toString().trim());
                        user.setProperty("Title",sR_Title.getSelectedItem().toString().trim());
                        user.setProperty("First_Name",etR_Name.getText().toString().trim());
                        user.setProperty("Middle_Name",etR_MiddleName.getText().toString().trim());
                        user.setProperty("Surname",etR_Surname.getText().toString().trim());
                        user.setProperty("ID",etR_ID.getText().toString().trim());
                        user.setProperty("Gender",sR_Gender.getSelectedItem().toString().trim());
                        user.setProperty("Rank",sR_Rank.getSelectedItem().toString().trim());
                        user.setProperty("Permission", sR_Permission.getSelectedItem().toString().trim());
                        user.setProperty("Province", sR_Province.getSelectedItem().toString().trim());
                        user.setProperty("Station", sR_Station.getSelectedItem().toString().trim());




                        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>()
                        {

                            @Override
                            public void handleResponse(BackendlessUser response)
                            {
                                Toast.makeText(Register.this, "User seccessfully registered", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(Register.this, Admin_Home.class));

                                Register.this.finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault)
                            {
                                Toast.makeText(Register.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                    else
                    {
                        Toast.makeText(Register.this, "Confirm Password needs to match Password!", Toast.LENGTH_SHORT).show();
                    }

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
                        Toast.makeText(Register.this, "User signed out successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, Admin.class));
                        Register.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(Register.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            case R.id.home:

                startActivity(new Intent(Register.this, Admin_Home.class));

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //asks for permission to use the camera
    private void askCameraPermissions()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else
        {
            openCamera();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == CAMERA_PERM_CODE)
        {
            //if legnth is greater than 0 that means we do have something
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                openCamera();
            }
            else
            {
                Toast.makeText(this, "Camera Permission is needed to use the Camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera()
    {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);

    }

    /**
     * Dispatch incoming result to the correct fragment.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == CAMERA_REQUEST_CODE)
        {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imPicture.setImageBitmap(image);

        }
    }
}
