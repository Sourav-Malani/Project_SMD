package com.ass2.project_smd;

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
    private RelativeLayout pineappleRl, jalapenosRl, sweetCornRl, pepperoniRl, redOnionsRl, anchoviesRl, groundBeefRl, chickenTikkaRl, mushroomRl, tunaRl;
    private TextView pineappleText, jalapenosText, sweetCornText, pepperoniText, redOnionsText, anchoviesText, groundBeefText, chickenTikkaText, mushroomText, tunaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own_step2);

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

        pineappleRl.setOnClickListener(view -> toggleToppingsSelectionSecond(pineappleRl, pineappleText));
        jalapenosRl.setOnClickListener(view -> toggleToppingsSelectionSecond(jalapenosRl, jalapenosText));
        sweetCornRl.setOnClickListener(view -> toggleToppingsSelectionSecond(sweetCornRl, sweetCornText));
        pepperoniRl.setOnClickListener(view -> toggleToppingsSelectionSecond(pepperoniRl, pepperoniText));
        redOnionsRl.setOnClickListener(view -> toggleToppingsSelectionSecond(redOnionsRl, redOnionsText));
        anchoviesRl.setOnClickListener(view -> toggleToppingsSelectionSecond(anchoviesRl, anchoviesText));
        groundBeefRl.setOnClickListener(view -> toggleToppingsSelectionSecond(groundBeefRl, groundBeefText));
        chickenTikkaRl.setOnClickListener(view -> toggleToppingsSelectionSecond(chickenTikkaRl, chickenTikkaText));
        mushroomRl.setOnClickListener(view -> toggleToppingsSelectionSecond(mushroomRl, mushroomText));
        tunaRl.setOnClickListener(view -> toggleToppingsSelectionSecond(tunaRl, tunaText));




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
        if (isLeftSelected) {
            leftHalfPizza.setAlpha(1.0f);
            rightHalfPizza.setAlpha(0.5f);
            txt_right_half_pizza.setTextColor(Color.parseColor("#24262F"));
            txt_left_half_pizza.setTextColor(Color.parseColor("#FE724C"));

        } else {
            leftHalfPizza.setAlpha(0.5f);
            rightHalfPizza.setAlpha(1.0f);
            txt_left_half_pizza.setTextColor(Color.parseColor("#24262F"));
            txt_right_half_pizza.setTextColor(Color.parseColor("#FE724C"));
        }
    }
    private void toggleToppingsSelectionSecond(RelativeLayout selectedLayout, TextView selectedTextView) {
        boolean isSelected = selectedLayout.getTag() != null && (boolean) selectedLayout.getTag();

        if (isSelected) {
            selectedLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_unselected));
            selectedTextView.setTextColor(Color.parseColor("#24262F"));
            selectedTextView.setAlpha(0.5f);
            selectedLayout.setTag(false); // Set tag to indicate unselected state
        } else {
            selectedLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.create_your_own_rectangle_selected));
            selectedTextView.setTextColor(Color.parseColor("#FE724C"));
            selectedTextView.setAlpha(1.0f);
            selectedLayout.setTag(true); // Set tag to indicate selected state
        }
    }




    private void toggleSauceSelection(int sauceNumber) {
        switch (sauceNumber) {
            case 1:
                setSauceSelection(create_your_own_rectangle_selected_1_rl, priceTextView1);
                break;
            case 2:
                setSauceSelection(create_your_own_rectangle_selected_2_rl, priceTextView2);
                break;
            case 3:
                setSauceSelection(create_your_own_rectangle_selected_3_rl, priceTextView3);
                break;
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