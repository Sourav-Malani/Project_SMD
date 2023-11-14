package com.ass2.project_smd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class welcome extends AppCompatActivity  {
    Button btn_facebook, btn_google;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView signIn;

    private static final int RC_SIGN_IN = 123; // Use a unique request code
    private static final String TAG = "SignInActivity";
    private SignInClient signInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_google = findViewById(R.id.btn_google);
        btn_facebook = findViewById(R.id.btn_facebook);

        signInClient = Identity.getSignInClient(this);

        signIn = findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //signInWithApp(); // Call your sign-in method when the button is clicked
            }
        });

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleSignInClient with the options specified by gso.
        gsc = GoogleSignIn.getClient(this, gso);
        // [END build_client]

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(); // Call your sign-in method when the Google button is clicked
            }
        });
    }

    // Your existing code for navigation
    void navigateToNextActivity(GoogleSignInAccount account) {
        Intent intent = new Intent(this, homescreen.class);
        intent.putExtra("googleSignInAccount", account); // Pass the GoogleSignInAccount
        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();

        // [START on_start_sign_in]
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        // [END on_start_sign_in]
    }

    void nagivateToSignInActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    // Modified Google Sign-In code
    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.w("GoogleSignIn", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Sign-in failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show();
            Log.w("GoogleSignIn", "signInResult:Name=" + account.getDisplayName());
            navigateToNextActivity(account);
        } else {
            Toast.makeText(this, "Sign-in failed", Toast.LENGTH_SHORT).show();
        }
    }

}