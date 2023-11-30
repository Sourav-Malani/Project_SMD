package com.ass2.project_smd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class create_your_own_step_2 extends AppCompatActivity {

    Button back;
    ImageView leftHalfPizza,rightHalfPizza;

    TextView txt_left_half_pizza,txt_right_half_pizza;
    TextView priceTextView1, priceTextView2, priceTextView3;
    RelativeLayout create_your_own_rectangle_selected_1_rl, create_your_own_rectangle_selected_2_rl, create_your_own_rectangle_selected_3_rl;

    RelativeLayout floating_cart_rl;
    private RelativeLayout pineappleRl, jalapenosRl, sweetCornRl, pepperoniRl, redOnionsRl, anchoviesRl, groundBeefRl, chickenTikkaRl, mushroomRl, tunaRl;
    private TextView pineappleText, jalapenosText, sweetCornText, pepperoniText, redOnionsText, anchoviesText, groundBeefText, chickenTikkaText, mushroomText, tunaText;

    private int selectedSizeIndex = -1;
    private int selectedCrustIndex = -1;
    private int selectedPizzaToppingsImageIndex = -1;

    private boolean isLeftHalfSelected = true; // Variable to track if the left half is selected
    private int selectedSauceIndexLeft = -1; // Variable to store the selected sauce index
    private int selectedSauceIndexRight = -1; // Variable to store the selected sauce index
    // Variables to store selected toppings for left and right halves
    private boolean[] selectedToppingsLeft = new boolean[10];
    private boolean[] selectedToppingsRight = new boolean[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own_step2);

        // Retrieve the selected data from the intent
        Intent intentTwo = getIntent();
        if (intentTwo != null) {
            selectedSizeIndex = intentTwo.getIntExtra("SELECTED_SIZE", -1);
            selectedCrustIndex = intentTwo.getIntExtra("SELECTED_CRUST", -1);
            selectedPizzaToppingsImageIndex = intentTwo.getIntExtra("SELECTED_PIZZA_TOPPINGS_IMAGE", -1);

            // Use the selected data as needed
            // ...
        }

        back = findViewById(R.id.back);

        leftHalfPizza = findViewById(R.id.img_left_half_pizza);
        rightHalfPizza = findViewById(R.id.img_right_half_pizza);
        txt_left_half_pizza = findViewById(R.id.txt_left_half_pizza);
        txt_right_half_pizza = findViewById(R.id.txt_right_half_pizza);

        priceTextView1 = findViewById(R.id.priceTextView1);
        priceTextView2 = findViewById(R.id.priceTextView2);
        priceTextView3 = findViewById(R.id.priceTextView3);
        create_your_own_rectangle_selected_1_rl = findViewById(R.id.create_your_own_rectangle_selected_1_rl);
        create_your_own_rectangle_selected_2_rl = findViewById(R.id.create_your_own_rectangle_selected_2_rl);
        create_your_own_rectangle_selected_3_rl = findViewById(R.id.create_your_own_rectangle_selected_3_rl);

        pineappleRl = findViewById(R.id.pineaple_rl);
        jalapenosRl = findViewById(R.id.jalapenos_rl);
        sweetCornRl = findViewById(R.id.sweet_corn_rl);
        pepperoniRl = findViewById(R.id.pepperoni_rl);
        redOnionsRl = findViewById(R.id.red_onions_rl);
        anchoviesRl = findViewById(R.id.anchovies_rl);
        groundBeefRl = findViewById(R.id.ground_beef_rl);
        chickenTikkaRl = findViewById(R.id.chicken_tikka_rl);
        mushroomRl = findViewById(R.id.mushroom_rl);
        tunaRl = findViewById(R.id.tuna_rl);

        pineappleText = findViewById(R.id.pineaple_text);
        jalapenosText = findViewById(R.id.jalapenos_text);
        sweetCornText = findViewById(R.id.sweet_corn_text);
        pepperoniText = findViewById(R.id.pepperoni_text);
        redOnionsText = findViewById(R.id.red_onions_text);
        anchoviesText = findViewById(R.id.anchovies_text);
        groundBeefText = findViewById(R.id.ground_beef_text);
        chickenTikkaText = findViewById(R.id.chicken_tikka_text);
        mushroomText = findViewById(R.id.mushroom_text);
        tunaText = findViewById(R.id.tuna_text);

        pineappleRl.setOnClickListener(view -> toggleToppingsSelectionSecond(pineappleRl, pineappleText, true));
        jalapenosRl.setOnClickListener(view -> toggleToppingsSelectionSecond(jalapenosRl, jalapenosText, true));
        sweetCornRl.setOnClickListener(view -> toggleToppingsSelectionSecond(sweetCornRl, sweetCornText, true));
        pepperoniRl.setOnClickListener(view -> toggleToppingsSelectionSecond(pepperoniRl, pepperoniText, true));
        redOnionsRl.setOnClickListener(view -> toggleToppingsSelectionSecond(redOnionsRl, redOnionsText, true));
        anchoviesRl.setOnClickListener(view -> toggleToppingsSelectionSecond(anchoviesRl, anchoviesText, true));
        groundBeefRl.setOnClickListener(view -> toggleToppingsSelectionSecond(groundBeefRl, groundBeefText, true));
        chickenTikkaRl.setOnClickListener(view -> toggleToppingsSelectionSecond(chickenTikkaRl, chickenTikkaText, true));
        mushroomRl.setOnClickListener(view -> toggleToppingsSelectionSecond(mushroomRl, mushroomText, true));
        tunaRl.setOnClickListener(view -> toggleToppingsSelectionSecond(tunaRl, tunaText, true));


        floating_cart_rl = findViewById(R.id.floating_cart_rl);

        floating_cart_rl.setOnClickListener(view -> {
            // Navigate to the cart activity
            Intent intent = new Intent(this, cart.class);

            if (selectedPizzaToppingsImageIndex == -1) {
                selectedPizzaToppingsImageIndex = 0; // Set the default size index to the first item
            }
            if(selectedSauceIndexLeft == -1){
                selectedSauceIndexLeft = 0;
            }
            if(selectedSauceIndexRight == -1){
                selectedSauceIndexRight = 0;
            }



            // Pass the selected data to the cart activity
            intent.putExtra("SELECTED_SIZE", selectedSizeIndex);
            intent.putExtra("SELECTED_CRUST", selectedCrustIndex);
            intent.putExtra("SELECTED_PIZZA_TOPPINGS_IMAGE", selectedPizzaToppingsImageIndex);
            intent.putExtra("SELECTED_SAUCE_LEFT", selectedSauceIndexLeft);
            intent.putExtra("SELECTED_SAUCE_RIGHT", selectedSauceIndexRight);
            intent.putExtra("SELECTED_TOPPINGS_LEFT", selectedToppingsLeft);
            intent.putExtra("SELECTED_TOPPINGS_RIGHT", selectedToppingsRight);

            startActivity(intent);
        });
        if (selectedPizzaToppingsImageIndex == -1) {
            selectedPizzaToppingsImageIndex = 0; // Set the default size index to the first item
        }
        if(selectedSauceIndexLeft == -1){
            selectedSauceIndexLeft = 0;
        }
        if(selectedSauceIndexRight == -1){
            selectedSauceIndexRight = 0;
        }




        leftHalfPizza.setOnClickListener(view -> toggleToppingsSelection(true));
        rightHalfPizza.setOnClickListener(view -> toggleToppingsSelection(false));

        back.setOnClickListener(v -> {
            // Navigate to the previous activity
            finish();
        });
        priceTextView1.setOnClickListener(view -> toggleSauceSelection(1));
        priceTextView2.setOnClickListener(view -> toggleSauceSelection(2));
        priceTextView3.setOnClickListener(view -> toggleSauceSelection(3));
    }

    private void toggleToppingsSelection(boolean isLeftSelected) {

        isLeftHalfSelected = isLeftSelected;
        if (isLeftSelected) {
            leftHalfPizza.setAlpha(1.0f);
            rightHalfPizza.setAlpha(0.5f);
            txt_right_half_pizza.setTextColor(Color.parseColor("#24262F"));
            txt_left_half_pizza.setTextColor(Color.parseColor("#FE724C"));

            //selectedSauceIndexLeft = 1; // Set the default sauce(BBQ) for left half

        } else {
            leftHalfPizza.setAlpha(0.5f);
            rightHalfPizza.setAlpha(1.0f);
            txt_left_half_pizza.setTextColor(Color.parseColor("#24262F"));
            txt_right_half_pizza.setTextColor(Color.parseColor("#FE724C"));

            //selectedSauceIndexRight = 1; // Set the default sauce(BBQ) for right half
        }
    }
    private void toggleToppingsSelectionSecond(RelativeLayout selectedLayout, TextView selectedTextView, boolean isLeftHalfSelected) {
        boolean isSelected = selectedLayout.getTag() != null && (boolean) selectedLayout.getTag();
        int index = -1;

        if (selectedLayout.getId() == R.id.pineaple_rl) index = 0; // Pineapple
        else if (selectedLayout.getId() == R.id.jalapenos_rl) index = 1; // Jalapenos
        else if (selectedLayout.getId() == R.id.sweet_corn_rl) index = 2; // Sweet Corn
        else if (selectedLayout.getId() == R.id.pepperoni_rl) index = 3; // Pepperoni
        else if (selectedLayout.getId() == R.id.red_onions_rl) index = 4; // Red Onions
        else if (selectedLayout.getId() == R.id.anchovies_rl) index = 5; // Anchovies
        else if (selectedLayout.getId() == R.id.ground_beef_rl) index = 6; // Ground Beef
        else if (selectedLayout.getId() == R.id.chicken_tikka_rl) index = 7; // Chicken Tikka
        else if (selectedLayout.getId() == R.id.mushroom_rl) index = 8; // Mushroom
        else if (selectedLayout.getId() == R.id.tuna_rl) index = 9; // Tuna

        if (index != -1) {
            if (isLeftHalfSelected) {
                // For the left half of the pizza
                if (isSelected) {
                    // Deselect
                    selectedLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_unselected));
                    selectedTextView.setTextColor(Color.parseColor("#24262F"));
                    selectedTextView.setAlpha(0.5f);
                    selectedLayout.setTag(false); // Set tag to indicate unselected state
                    selectedToppingsLeft[index] = false; // Update the selectedToppingsLeft array for the respective topping
                } else {
                    // Select
                    selectedLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_selected));
                    selectedTextView.setTextColor(Color.parseColor("#FE724C"));
                    selectedTextView.setAlpha(1.0f);
                    selectedLayout.setTag(true); // Set tag to indicate selected state
                    selectedToppingsLeft[index] = true; // Update the selectedToppingsLeft array for the respective topping
                }
            } else {
                // For the right half of the pizza
                if (isSelected) {
                    // Deselect
                    selectedLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_unselected));
                    selectedTextView.setTextColor(Color.parseColor("#24262F"));
                    selectedTextView.setAlpha(0.5f);
                    selectedLayout.setTag(false); // Set tag to indicate unselected state
                    selectedToppingsRight[index] = false; // Update the selectedToppingsRight array for the respective topping
                } else {
                    // Select
                    selectedLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_selected));
                    selectedTextView.setTextColor(Color.parseColor("#FE724C"));
                    selectedTextView.setAlpha(1.0f);
                    selectedLayout.setTag(true); // Set tag to indicate selected state
                    selectedToppingsRight[index] = true; // Update the selectedToppingsRight array for the respective topping
                }
            }
        }
    }



    private void toggleSauceSelection(int sauceNumber) {
        switch (sauceNumber) {
            case 1:
                setSauceSelection(create_your_own_rectangle_selected_1_rl, priceTextView1);
                updateSauceIndex(0); // Assuming sauceNumber 1 corresponds to the first sauce option
                break;
            case 2:
                setSauceSelection(create_your_own_rectangle_selected_2_rl, priceTextView2);
                updateSauceIndex(1); // Assuming sauceNumber 2 corresponds to the second sauce option
                break;
            case 3:
                setSauceSelection(create_your_own_rectangle_selected_3_rl, priceTextView3);
                updateSauceIndex(2); // Assuming sauceNumber 3 corresponds to the third sauce option
                break;
        }
    }

    private void updateSauceIndex(int sauceIndex) {
        if (isLeftHalfSelected) {
            selectedSauceIndexLeft = sauceIndex;
        } else {
            selectedSauceIndexRight = sauceIndex;
        }
    }



    private void setSauceSelection(RelativeLayout selectedLayout, TextView selectedTextView) {
        create_your_own_rectangle_selected_1_rl.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_unselected));
        create_your_own_rectangle_selected_2_rl.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_unselected));
        create_your_own_rectangle_selected_3_rl.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_unselected));

        priceTextView1.setTextColor(Color.parseColor("#24262F"));
        priceTextView2.setTextColor(Color.parseColor("#24262F"));
        priceTextView3.setTextColor(Color.parseColor("#24262F"));

        selectedLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_selected));
        selectedTextView.setTextColor(Color.parseColor("#FE724C"));

        priceTextView1.setAlpha(0.5f);
        priceTextView2.setAlpha(0.5f);
        priceTextView3.setAlpha(0.5f);

        selectedTextView.setAlpha(1.0f);
    }

}