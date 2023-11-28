package com.ass2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ass2.Helper.DBHelper;
import com.ass2.Models.MainModel;
import com.ass2.project_smd.R;
import com.ass2.project_smd.cart;
import com.ass2.project_smd.create_your_own_pizza;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_LAYOUT_1 = 0;
    private static final int VIEW_TYPE_LAYOUT_2 = 1;

    private ArrayList<MainModel> list;
    private Context context;
    final DBHelper helper; // Remove the initialization here
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.helper = new DBHelper(context); // Initialize DBHelper using the provided context
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_LAYOUT_1 : VIEW_TYPE_LAYOUT_2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case VIEW_TYPE_LAYOUT_1:
                View layout1View = inflater.inflate(R.layout.card_item_layout_1, parent, false);
                return new Layout1ViewHolder(layout1View);
            case VIEW_TYPE_LAYOUT_2:
            default:
                View layout2View = inflater.inflate(R.layout.card_item_layout_2, parent, false);
                return new Layout2ViewHolder(layout2View);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MainModel model = list.get(position);


        if (holder instanceof Layout1ViewHolder) {
            ((Layout1ViewHolder) holder).bindLayout1Data(model);
            if (model.getViewType() == VIEW_TYPE_LAYOUT_1) {
                ((Layout1ViewHolder) holder).crateNowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click for card_item_layout_1.xml here
                        // You can perform actions like starting a new activity
                        Intent intent = new Intent(context, create_your_own_pizza.class);
                        context.startActivity(intent);
                    }
                });
            }
        } else if (holder instanceof Layout2ViewHolder) {
            ((Layout2ViewHolder) holder).bindLayout2Data(model);
            ((Layout2ViewHolder) holder).pizzaImage.setImageResource(model.getPizzaImage());
            ((Layout2ViewHolder) holder).pizzaName.setText(model.getName());
            ((Layout2ViewHolder) holder).pizzaPrice.setText(model.getPrice());
            ((Layout2ViewHolder) holder).pizzaDescription.setText(model.getDescription());

            if (model.getViewType() == VIEW_TYPE_LAYOUT_2) {
                ((Layout2ViewHolder) holder).addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click for card_item_layout_2.xml here
                        // You can perform actions like starting a new activity
                        boolean isInserted = helper.insertOrder(
                                model.getName(),
                                "abc@gmail.com",
                                model.getPrice(),
                                model.getPizzaImage(),
                                model.getDescription(),
                                model.getName(),
                                1
                        );
                        if(isInserted){
                            Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                        }


                        Intent intent = new Intent(context, cart.class);
//                        intent.putExtra("pizzaName",model.getName());
//                        intent.putExtra("pizzaImage",model.getPizzaImage());
//                        intent.putExtra("pizzaPrice",model.getPrice());
//                        intent.putExtra("pizzaDescription",model.getDescription());

                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ViewHolder for layout 1
    private static class Layout1ViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImage;
        TextView pizzaText,pizzaDescription;
        Button crateNowButton;

        Layout1ViewHolder(View itemView) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);
            pizzaText = itemView.findViewById(R.id.pizzaText);
            pizzaDescription = itemView.findViewById(R.id.pizzaDescription);
            crateNowButton = itemView.findViewById(R.id.btn_createNow);
        }

        void bindLayout1Data(MainModel model) {
            // Bind data for layout 1
            pizzaImage.setImageResource(model.getPizzaImage());
            pizzaText.setText(model.getName());
            pizzaDescription.setText(model.getDescription());
        }
    }

    // ViewHolder for layout 2
    private static class Layout2ViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImage;
        TextView pizzaName, pizzaPrice, pizzaDescription;

        Button addButton;

        Layout2ViewHolder(View itemView) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaPrice = itemView.findViewById(R.id.pizzaPrice);
            pizzaDescription = itemView.findViewById(R.id.pizzaDescription);
            addButton = itemView.findViewById(R.id.btn_add);
        }

        void bindLayout2Data(MainModel model) {
            // Bind data for layout 2
            pizzaImage.setImageResource(model.getPizzaImage());
            pizzaName.setText(model.getName());
            pizzaPrice.setText(model.getPrice());
            pizzaDescription.setText(model.getDescription());
        }
    }
}
