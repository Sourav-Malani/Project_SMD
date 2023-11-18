package com.ass2.project_smd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ass2.Adapters.MainAdapter;
import com.ass2.Models.MainModel;
import com.ass2.project_smd.databinding.DashboardFragmentBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private static final int RC_SIGN_IN = 123; // Use a unique request code
    private static final String TAG = "SignInActivity";

    DashboardFragmentBinding binding;
    RecyclerView recyclerViewCards;
    TextView address;
    ImageView profilePic;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DashboardFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewCards = view.findViewById(R.id.recyclerViewCards);
        address = view.findViewById(R.id.address);
        profilePic = view.findViewById(R.id.profile_image);

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(
                R.drawable.pizza_image,
                "Create Your Own Pizza",
                "$ 10",
                "Choose From Our Options Of Designa And Make Your Own Pizza.",
                "pizza",
                1));

        list.add(new MainModel(
                R.drawable.pizza2,
                "Fresh Farm House",
                "Rs 1400",
                "crisp capsicum, succulent mushrooms and fresh tomatoes",
                "pizza",
                2));
        list.add(new MainModel(
                R.drawable.pizza3,
                "Peppy Paneer",
                "Rs. 5900",
                "Chunky paneer with crisp capsicum and spicy red pepperr",
                "pizza",
                3));
        list.add(new MainModel(
                R.drawable.pizza4,
                "Mexican Green Wave",
                "Rs. 1400",
                "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes",
                "pizza",
                4));
        list.add(new MainModel(
                R.drawable.pizza5,
                "Peppy Paneer",
                "$ 15",
                "Chunky paneer with crisp capsicum and spicy red pepper",
                "pizza",
                5));
        list.add(new MainModel(
                R.drawable.pizza6,
                "Mexican Green Wave",
                "Rs 1700",
                "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes",
                "pizza",
                6));
        list.add(new MainModel(
                R.drawable.pizza6,
                "Mexican Green Wave",
                "Rs 1700",
                "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes",
                "pizza",
                7));
        list.add(new MainModel(
                R.drawable.pizza6,
                "Mexican Green Wave",
                "Rs 1700",
                "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes",
                "pizza",
                8));
        list.add(new MainModel(
                R.drawable.pizza6,
                "Mexican Green Wave",
                "Rs 1700",
                "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes",
                "pizza",
                9));
        MainAdapter adapter = new MainAdapter(list, requireActivity());
        recyclerViewCards.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2); // 2 columns
        recyclerViewCards.setLayoutManager(layoutManager);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(requireContext(), gso);

        Intent intent = requireActivity().getIntent();
        GoogleSignInAccount receivedAccount = intent.getParcelableExtra("googleSignInAccount");
        if (receivedAccount != null) {
            updateUI(receivedAccount);
        }
    }
    @Override
    public void onStart() {
        super.onStart();

        // [START on_start_sign_in]
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireContext());
        updateUI(account);
        // [END on_start_sign_in]
    }
    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    // [END handleSignInResult]
    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                requireActivity().getSupportFragmentManager().popBackStack();
                // Alternatively, if you have a specific destination Fragment to navigate after signing out:
                // requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new YourDestinationFragment()).commit();
            }
        });
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            //String personEmail = acct.getEmail();
            address.setText(account.getGivenName());
            if (account.getPhotoUrl() != null) {
                String photoUrl = account.getPhotoUrl().toString();

                // Load the image using an image loading library like Picasso or Glide
                // For example, using Picasso:
                Picasso.get().load(photoUrl).into(profilePic);

                // If you want to download and display the image manually, you can use:
                /* new DownloadImageTask(yourImageView).execute(photoUrl); */
            }


        } else {
        }
    }
}