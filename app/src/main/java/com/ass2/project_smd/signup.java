package com.ass2.project_smd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.ass2.HttpService.ApiCallback;
import com.ass2.HttpService.ApiHelper;
import com.ass2.HttpService.HttpService;
import com.ass2.HttpService.RetrofitBuilder;
import com.ass2.HttpService.UserProfileModel;
import com.ass2.Models.UserModel;
import com.ass2.config.Config;
import com.ass2.project_smd.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {

    private ApiHelper apiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize ApiHelper with the base URL of your API
        HttpService httpService = RetrofitBuilder.getClient(Config.API_BASE_URL).create(HttpService.class);
        apiHelper = new ApiHelper(httpService, this);

        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String fullName = ((EditText) findViewById(R.id.usernameText)).getText().toString();
                String email = ((EditText) findViewById(R.id.emailText)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

                // Create a new UserModel with signup information
                UserModel user = new UserModel(fullName, email, password);

                // Register the user using the ApiHelper
                registerUser(user);
            }
        });
    }

    private void registerUser(UserModel user) {
        // Call the registerUser method of the ApiHelper
        apiHelper.registerUser(user.getFullName(), user.getEmail(), user.getPassword(), new ApiCallback<UserProfileModel>() {
            @Override
            public void onSuccess(UserProfileModel result) {
                // Handle registration success, e.g., show a success message
                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                // Handle registration error, e.g., show an error message
                Toast.makeText(signup.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
