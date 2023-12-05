package com.ass2.project_smd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.concurrent.futures.AbstractResolvableFuture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ass2.Helper.UserDBHelper;
import com.ass2.Models.UserModel;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    private UserDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new UserDBHelper(this);

        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String fullName = ((EditText) findViewById(R.id.usernameText)).getText().toString();
                String email = ((EditText) findViewById(R.id.emailText)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
                String deliveryAddress = ""; // Get delivery address input
                String phoneNo = ""; // Get phone number input
                String imageURL = ""; // Get image URL input

                // Create a new UserModel with signup information
                UserModel user = new UserModel(fullName, email, password, deliveryAddress, phoneNo, imageURL);

                // Insert user data into the online database (MySQL and phpMyAdmin)
                insertUserOnline(user);
            }
        });
    }
    private void insertUserOnline(UserModel user) {
        String url = "http://cheesybites.infinityfreeapp.com/insert.php"; // Replace with your InfinityFree website URL

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the server response, e.g., show a success message
                        Toast.makeText(signup.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error, e.g., show an error message
                        Toast.makeText(signup.this, "Signup failed"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fullName", user.getFullName());
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());
                params.put("deliveryAddress", user.getDeliveryAddress());
                params.put("phoneNo", user.getPhoneNo());
                params.put("imageURL", user.getImageURL());
                return params;
            }
        };

        // Add the request to the Volley queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}

