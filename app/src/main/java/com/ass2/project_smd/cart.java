package com.ass2.project_smd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ass2.Adapters.CartAdapter;
import com.ass2.Adapters.MainAdapter;
import com.ass2.Helper.DBHelper;
import com.ass2.Models.CartModel;
import com.ass2.Models.MainModel;

import java.util.ArrayList;
import java.util.Arrays;
import com.ass2.Helper.CartDBHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class cart extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    ImageButton backButton;
    TextView noCartItems;



    final int itemCountCreateYourOwn = 0;
    int selectedSize = -1, selectedCrust = -1, selectedToppingsImage = -1,
            selectedSauceLeft = -1, selectedSauceRight = -1;
    boolean[] selectedToppingsLeft = null, selectedToppingsRight = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        backButton = findViewById(R.id.back);
        noCartItems = findViewById(R.id.noCartItems);

        // Create an instance of CartDBHelper to access the database
        CartDBHelper dbHelper = new CartDBHelper(this);

        // Get all cart items from the database
        ArrayList<CartModel> cartItems = dbHelper.getAllCartItems();

        if(cartItems.size()==0){
           noCartItems.setVisibility(TextView.VISIBLE);
           cartRecyclerView.setVisibility(RecyclerView.GONE);
        }

        // Set up RecyclerView with CartAdapter
        CartAdapter adapter = new CartAdapter(cartItems, this);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null) {
            selectedSize = intent.getIntExtra("SELECTED_SIZE", -1);
            selectedCrust = intent.getIntExtra("SELECTED_CRUST", -1);
            selectedToppingsImage = intent.getIntExtra("SELECTED_PIZZA_TOPPINGS_IMAGE", -1);
            selectedSauceLeft = intent.getIntExtra("SELECTED_SAUCE_LEFT", -1);
            selectedSauceRight = intent.getIntExtra("SELECTED_SAUCE_RIGHT", -1);
            selectedToppingsLeft = intent.getBooleanArrayExtra("SELECTED_TOPPINGS_LEFT");
            selectedToppingsRight = intent.getBooleanArrayExtra("SELECTED_TOPPINGS_RIGHT");


            // Add data to the list if available from intents

//            if (selectedSize != -1 && selectedCrust != -1 && selectedToppingsImage != -1 &&
//                    selectedSauceLeft != -1 && selectedSauceRight != -1 &&
//                    selectedToppingsLeft != null && selectedToppingsRight != null) {
//                // Add the item to the database
//                addItemCreateYourOwnPizzaToLocalDB(selectedSize, selectedCrust, selectedToppingsImage,
//                        selectedSauceLeft, selectedSauceRight,
//                        selectedToppingsLeft, selectedToppingsRight, itemCountCreateYourOwn,R.drawable.pizza4);
//            }

            // Add the item to the recycler view





        }


        backButton.setOnClickListener(v -> {
            // Go back to previous activity
            finish();
        });








    }
    // Inside your cart activity class
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

//    private boolean addItemCreateYourOwnPizzaToLocalDB(int Size, int Crust , int ToppingsImage,
//                                    int SauceLeft, int SauceRight,
//    boolean[] ToppingsLeft, boolean[] ToppingsRight,int quantity,int image){
//
//         float totalPizzaPrice = 0;
//
//
//        // Check if data exists from intents and add to the list accordingly
//        ArrayList<String> returnToppingsLeftList = new ArrayList<>();
//        ArrayList<String> returnToppingsRightList = new ArrayList<>();
//
//        float returnSize;
//        switch (Size) {
//            case 0: returnSize = 7.0f;  totalPizzaPrice += 5.99;  break; // 7 inch pizza
//            case 1: returnSize = 9.0f;  totalPizzaPrice += 7.99;  break; // 9 inch pizza
//            case 2: returnSize = 12.0f; totalPizzaPrice += 9.99;  break; // 12 inch pizza
//            case 3: returnSize = 13.5f; totalPizzaPrice += 15.99; break; // 13.5 inch pizza
//            default: returnSize = -1; break; // invalid size
//        }
//
//
//
//        // Process Crust
//        String returnCrust;
//        switch (Crust) {
//            case 0: returnCrust = "Classic Crust"; break;
//            case 1:
//                returnCrust = "Italian Crust"; //add extra 1.00 if italian crust
//                totalPizzaPrice += 1.00;
//                break;
//            default: returnCrust = "Unknown Crust"; break;
//        }
//
//        // Process ToppingsImage
//        String returnToppingsImage;
//        switch (ToppingsImage) {
//            case 0: returnToppingsImage = "Vegetable Toppings"; break;
//            case 1: returnToppingsImage = "Cheese toppings"; break;
//            default: returnToppingsImage = "Unknown toppings"; break;
//        }
//
//        // Process SauceLeft
//        String returnSauceLeft;
//        switch (SauceLeft) {
//            case 0: returnSauceLeft = "BBQ"; break;
//            case 1: returnSauceLeft = "Tomato"; break;
//            case 2: returnSauceLeft = "Garlic Herb"; break;
//            default: returnSauceLeft = "Unknown Sauce"; break;
//        }
//
//        // Process SauceRight
//        String returnSauceRight;
//        switch (SauceRight) {
//            case 0: returnSauceRight = "BBQ"; break;
//            case 1: returnSauceRight = "Tomato"; break;
//            case 2: returnSauceRight = "Garlic Herb"; break;
//            default: returnSauceRight = "Unknown Sauce"; break;
//        }
//
//        // Process ToppingsLeft
//        if (ToppingsLeft != null && ToppingsLeft.length > 0) {
//            for (int i = 0; i < ToppingsLeft.length; i++) {
//                if (ToppingsLeft[i]) {
//                    // Ensure index i corresponds to a valid topping name
//                    if (i < toppingsNames.length) {
//                        returnToppingsLeftList.add(toppingsNames[i]);
//                    }
//                }
//            }
//        }
//
//        // Process ToppingsRight
//        if (ToppingsRight != null && ToppingsRight.length > 0) {
//            for (int i = 0; i < ToppingsRight.length; i++) {
//                if (ToppingsRight[i]) {
//                    // Ensure index i corresponds to a valid topping name
//                    if (i < toppingsNames.length) {
//                        returnToppingsRightList.add(toppingsNames[i]);
//                    }
//                }
//            }
//        }
//
//
//        ArrayList<CartModel> dbList = new ArrayList<>();
//        totalPizzaPrice *= quantity;
//
//        CartModel newCartItem = new CartModel(
//                String.valueOf(quantity),
//                "Create Your Own",
//                String.format(Locale.getDefault(), "%.2f", totalPizzaPrice),
//                image,
//                0,
//                String.valueOf(returnSize),
//                returnCrust,
//                returnToppingsImage,
//                returnSauceLeft,
//                returnSauceRight,
//                returnToppingsLeftList,
//                returnToppingsRightList);
//        // Add the item to the database
//        CartDBHelper dbHelper = new CartDBHelper(this);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        long insertSuccess = dbHelper.insertCartItem(newCartItem);
//
//        db.close(); // Close the database connection
//        if(insertSuccess == -1)
//            return false;
//        else
//            return true;
//
//
//    }


}