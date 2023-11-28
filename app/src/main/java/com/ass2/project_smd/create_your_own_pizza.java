package com.ass2.project_smd;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class create_your_own_pizza extends AppCompatActivity {

    Button back;
    RelativeLayout selectedSize, selectedCrust;
    RelativeLayout[] sizeOptions, crustOptions;
    ImageButton pizzaImage1, pizzaImage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own_pizza);

        back = findViewById(R.id.back);
        selectedSize = findViewById(R.id.create_your_own_rectangle_selected_1_rl);
        selectedCrust = findViewById(R.id.create_your_own_rectangle_crust_selected_1_rl);
        pizzaImage1 = findViewById(R.id.pizzaImage1);
        pizzaImage2 = findViewById(R.id.pizzaImage2);

        pizzaImage1.setOnClickListener(v -> {
            // Set pizzaImage1 as selected and pizzaImage2 as unselected
            pizzaImage1.setBackgroundResource(R.drawable.create_your_own_pizza1_selected);
            pizzaImage2.setBackgroundResource(R.drawable.create_your_own_pizza2_unselected);

            // Perform actions based on pizzaImage1 selection
            // ...
        });

        pizzaImage2.setOnClickListener(v -> {
            // Set pizzaImage2 as selected and pizzaImage1 as unselected
            pizzaImage2.setBackgroundResource(R.drawable.create_your_own_pizza2_selected);
            pizzaImage1.setBackgroundResource(R.drawable.create_your_own_pizza1_unselected);

            // Perform actions based on pizzaImage2 selection
            // ...
        });
        // Initialize size options
        sizeOptions = new RelativeLayout[]{
                findViewById(R.id.create_your_own_rectangle_selected_1_rl),
                findViewById(R.id.create_your_own_rectangle_selected_2_rl),
                findViewById(R.id.create_your_own_rectangle_selected_3_rl),
                findViewById(R.id.create_your_own_rectangle_selected_4_rl)
        };

        // Initialize crust options
        crustOptions = new RelativeLayout[]{
                findViewById(R.id.create_your_own_rectangle_crust_selected_1_rl),
                findViewById(R.id.create_your_own_rectangle_crust_selected_2_rl)
        };

        // Set click listeners for size options
        for (int i = 0; i < sizeOptions.length; i++) {
            int finalI = i;
            sizeOptions[i].setOnClickListener(v -> onSizeOptionSelected(finalI));
        }

        // Set click listeners for crust options
        for (int i = 0; i < crustOptions.length; i++) {
            int finalI = i;
            crustOptions[i].setOnClickListener(v -> onCrustOptionSelected(finalI));
        }

        back.setOnClickListener(v -> {
            // Navigate to the previous activity
            finish();
        });
    }

    private void resetSizeOptions() {
    for (int i = 0; i < sizeOptions.length; i++) {
                if (i == 0) {
                    // Access TextViews for the first RelativeLayout
                    TextView sizeTextView1 = sizeOptions[i].findViewById(R.id.sizeTextView1);
                    TextView priceTextView1 = sizeOptions[i].findViewById(R.id.priceTextView1);
                    // Modify TextView properties here
                    sizeTextView1.setAlpha(1f);
                    priceTextView1.setTextColor(Color.parseColor("#FE724C"));
                } else if (i == 1) {
                    // Access TextViews for the second RelativeLayout
                    TextView sizeTextView2 = sizeOptions[i].findViewById(R.id.sizeTextView2);
                    TextView priceTextView2 = sizeOptions[i].findViewById(R.id.priceTextView2);
                    // Modify TextView properties here
                    sizeTextView2.setAlpha(1f);
                    priceTextView2.setTextColor(Color.parseColor("#FE724C"));
                } else if (i == 2) {
                    // Access TextViews for the third RelativeLayout
                    TextView sizeTextView3 = sizeOptions[i].findViewById(R.id.sizeTextView3);
                    TextView priceTextView3 = sizeOptions[i].findViewById(R.id.priceTextView3);
                    // Modify TextView properties here
                    sizeTextView3.setAlpha(1f);
                    priceTextView3.setTextColor(Color.parseColor("#FE724C"));
                } else if (i == 3) {
                    // Access TextViews for the fourth RelativeLayout
                    TextView sizeTextView4 = sizeOptions[i].findViewById(R.id.sizeTextView4);
                    TextView priceTextView4 = sizeOptions[i].findViewById(R.id.priceTextView4);
                    // Modify TextView properties here
                    sizeTextView4.setAlpha(1f);
                    priceTextView4.setTextColor(Color.parseColor("#FE724C"));
                }
            }
    }
    private void resetCrustOptions(){
        for (int i = 0; i < crustOptions.length; i++) {
            if (i == 0) { //
                // Access TextViews for the first RelativeLayout
                TextView crust_text_1 = crustOptions[i].findViewById(R.id.crust_text_1);
                // Modify TextView properties here
                crust_text_1.setAlpha(1f);
                crust_text_1.setTextColor(Color.parseColor("#FE724C"));

            } else if (i == 1) {
                TextView crust_text_2_1 = crustOptions[i].findViewById(R.id.crust_text_2_1);
                TextView crust_text_2_2 = crustOptions[i].findViewById(R.id.crust_text_2_2);
                crust_text_2_1.setAlpha(1f);
                crust_text_2_2.setAlpha(1f);

            }
        }
    }
    private void onSizeOptionSelected(int selectedSizeIndex) {
        // Reset all size options to their default state
        resetSizeOptions();
        for (int i = 0; i < sizeOptions.length; i++) {
            if (i == selectedSizeIndex) {
                sizeOptions[i].setBackgroundResource(R.drawable.create_your_own_rectangle_selected);
            } else {
                sizeOptions[i].setBackgroundResource(R.drawable.create_your_own_rectangle_unselected);

                TextView sizeTextView = sizeOptions[i].findViewById(getResources().getIdentifier("sizeTextView" + (i + 1), "id", getPackageName()));
                TextView priceTextView = sizeOptions[i].findViewById(getResources().getIdentifier("priceTextView" + (i + 1), "id", getPackageName()));

                if (sizeTextView != null && priceTextView != null) {
                    sizeTextView.setAlpha(i == selectedSizeIndex ? 1.0f : 0.5f);
                    priceTextView.setTextColor(i == selectedSizeIndex ? Color.parseColor("#FFFFFF") : Color.parseColor("#24262F"));
                }
            }
        }
        // Perform actions based on the selected size, if needed
    }

    private void onCrustOptionSelected(int selectedCrustIndex) {
        // Reset all crust options to their default state
        resetCrustOptions();
        for (int i = 0; i < crustOptions.length; i++) {
            if (i == selectedCrustIndex) {
                crustOptions[i].setBackgroundResource(R.drawable.create_your_own_rectangle_crust_selected);
            } else {
                crustOptions[i].setBackgroundResource(R.drawable.create_your_own_rectangle_crust_unselected);

                TextView crustText = crustOptions[i].findViewById(getResources().getIdentifier("crust_text_" + (i + 1), "id", getPackageName()));

                if (crustText != null) {
                    crustText.setAlpha(i == selectedCrustIndex ? 1.0f : 0.5f);
                    crustText.setTextColor(i == selectedCrustIndex ? Color.parseColor("#FFFFFF") : Color.parseColor("#24262F"));
                }
            }
        }
        // Perform actions based on the selected crust, if needed
    }

}
