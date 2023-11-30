package com.ass2.project_smd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ass2.Adapters.CartAdapter;
import com.ass2.Adapters.MainAdapter;
import com.ass2.Helper.DBHelper;
import com.ass2.Models.CartModel;
import com.ass2.Models.MainModel;

import java.util.ArrayList;
import java.util.Arrays;

public class cart extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    ImageButton backButton;

    // Define arrays for sauce names and topping names
    private String[] sauceNames = {"BBQ", "Tomato", "Garlic Herb"};
    private String[] toppingsNames = {
            "Pineapple", "Jalapenos", "Sweet Corn", "Pepperoni", "Red Onions",
            "Anchovies", "Ground Beef", "Chicken Tikka", "Mushroom", "Tuna"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        backButton = findViewById(R.id.back);
        ArrayList<CartModel> list = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null) {
            int selectedSize = intent.getIntExtra("SELECTED_SIZE", -1);
            int selectedCrust = intent.getIntExtra("SELECTED_CRUST", -1);
            int selectedToppingsImage = intent.getIntExtra("SELECTED_PIZZA_TOPPINGS_IMAGE", -1);
            int selectedSauceLeft = intent.getIntExtra("SELECTED_SAUCE_LEFT", -1);
            int selectedSauceRight = intent.getIntExtra("SELECTED_SAUCE_RIGHT", -1);
            boolean[] selectedToppingsLeft = intent.getBooleanArrayExtra("SELECTED_TOPPINGS_LEFT");
            boolean[] selectedToppingsRight = intent.getBooleanArrayExtra("SELECTED_TOPPINGS_RIGHT");

            // Check if data exists from intents and add to the list accordingly
            ArrayList<String> selectedToppingsLeftList = new ArrayList<>();
            ArrayList<String> selectedToppingsRightList = new ArrayList<>();

            if (selectedToppingsLeft != null && selectedToppingsLeft.length > 0) {
                for (int i = 0; i < selectedToppingsLeft.length; i++) {
                    if (selectedToppingsLeft[i]) {
                        // Ensure index i corresponds to a valid topping name
                        if (i < toppingsNames.length) {
                            selectedToppingsLeftList.add(toppingsNames[i]);
                        }
                    }
                }
            }

            if (selectedToppingsRight != null && selectedToppingsRight.length > 0) {
                for (int i = 0; i < selectedToppingsRight.length; i++) {
                    if (selectedToppingsRight[i]) {
                        // Ensure index i corresponds to a valid topping name
                        if (i < toppingsNames.length) {
                            selectedToppingsRightList.add(toppingsNames[i]);
                        }
                    }
                }
            }

            // Add data to the list if available from intents
            list.add(new CartModel(
                    "1",
                    "Create Your own",
                    "Rs. 200",
                    "Cheese and Tomato",
                    R.drawable.pizza4,
                    1,
                    selectedSauceLeft != -1 && selectedSauceLeft < sauceNames.length ? sauceNames[selectedSauceLeft] : null,
                    selectedSauceRight != -1 && selectedSauceRight < sauceNames.length ? sauceNames[selectedSauceRight] : null,
                    !selectedToppingsLeftList.isEmpty() ? selectedToppingsLeftList : null,
                    !selectedToppingsRightList.isEmpty() ? selectedToppingsRightList : null));
        }

//       // Inside cart activity's onCreate method
//        list.add(new CartModel(
//                "1",
//                "Create Your own",
//                "Rs. 200",
//                "Cheese and Tomato",
//                R.drawable.pizza4,
//                1,
//                "BBQ", // Example sauce name for the left side
//                "Marinara", // Example sauce name for the right side
//                new ArrayList<>(Arrays.asList("Pineapple", "Jalapenos")), // Example selected toppings for the left side
//                new ArrayList<>(Arrays.asList("Pepperoni", "Red Onions")))); // Example selected toppings for the right side
//        list.add(new CartModel(
//                "3",
//                "Fajita",
//                "Rs. 300",
//                "crisp capsicum, succulent mushrooms and fresh tomatoes",
//                R.drawable.pizza2,
//                0));

        CartAdapter adapter = new CartAdapter(list, this);
        cartRecyclerView.setAdapter(adapter);


        backButton.setOnClickListener(v -> {
            // Go back to previous activity
            finish();
        });








    }
}