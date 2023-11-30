package com.ass2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ass2.Models.CartModel;
import com.ass2.Models.MainModel;
import com.ass2.project_smd.R;
import com.ass2.project_smd.cart;
import com.ass2.project_smd.create_your_own_pizza;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LAYOUT_1 = 0;
    private static final int VIEW_TYPE_LAYOUT_2 = 1;
    private ArrayList<CartModel> list;
    private Context context;

    public CartAdapter(ArrayList<CartModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_LAYOUT_1 : VIEW_TYPE_LAYOUT_2;
    }

//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);
//        return new CartViewHolder(view);
//    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case VIEW_TYPE_LAYOUT_1:
                View layout2View = inflater.inflate(R.layout.create_your_own_pizza_layout, parent, false);
                return new CartAdapter.Layout2ViewHolder(layout2View, this);
            case VIEW_TYPE_LAYOUT_2:
            default:
                View layout1View = inflater.inflate(R.layout.cart_layout, parent, false);
                return new CartAdapter.Layout1ViewHolder(layout1View, this);
        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof CartViewHolder) {
//            CartViewHolder cartViewHolder = (CartViewHolder) holder;
//            CartModel item = list.get(position);
//
//            // Set data to views in your ViewHolder here
//            cartViewHolder.bind(item);
//        }
//    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final CartModel model = list.get(position);

        if (holder instanceof CartAdapter.Layout1ViewHolder) {
            ((CartAdapter.Layout1ViewHolder) holder).bindLayout1Data(model);
            if (model.getViewType() == VIEW_TYPE_LAYOUT_1) {

            }
        } else if (holder instanceof CartAdapter.Layout2ViewHolder) {
            ((CartAdapter.Layout2ViewHolder) holder).bindLayout2Data(model);
            if (model.getViewType() == VIEW_TYPE_LAYOUT_2) {

            }
        }
    }

    private static class Layout1ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName,itemPrice,itemDescription,itemCount;
        ImageButton minusButton,plusButton,crossButton;

        private CartAdapter adapter;

        Layout1ViewHolder(View itemView, CartAdapter adapter) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemCount = itemView.findViewById(R.id.itemCount);
            minusButton = itemView.findViewById(R.id.btn_minus_item);
            plusButton = itemView.findViewById(R.id.btn_plus_item);
            crossButton = itemView.findViewById(R.id.cross);

            this.adapter = adapter;
        }

        void bindLayout1Data(CartModel item) {
            // Bind data for layout 1
            itemImage.setImageResource(item.getItemImage());


            itemName.setText(item.getItemName());
            itemPrice.setText(item.getItemPrice());
            itemDescription.setText(item.getItemDescription());
            itemCount.setText(item.getItemCount());


            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(itemCount.getText().toString());
                    if (count > 1) {
                        count--;
                        itemCount.setText(String.valueOf(count));
                    } else {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            // Access list and notifyItemRemoved through the adapter instance
                            adapter.removeItem(adapterPosition);
                        }
                    }
                }
            });

            crossButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        // Access list and notifyItemRemoved through the adapter instance
                        adapter.removeItem(adapterPosition);
                    }
                }
            });

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(itemCount.getText().toString());
                    count++;
                    itemCount.setText(String.valueOf(count));
                }
            });
        }
    }
    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
    // ViewHolder for layout 2
    private static class Layout2ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName,itemPrice,itemDescription,itemCount;
        TextView sauceLeft, sauceRight;
        TextView toppingsLeft, toppingsRight;
        ImageButton minusButton,plusButton;
        private CartAdapter adapter;

        Layout2ViewHolder(View itemView, CartAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            //itemDescription = itemView.findViewById(R.id.itemDescription);
            itemCount = itemView.findViewById(R.id.itemCount);
            sauceLeft = itemView.findViewById(R.id.itemSauce_left);
            sauceRight = itemView.findViewById(R.id.itemSauce_right);
            toppingsLeft = itemView.findViewById(R.id.itemToppings_left);
            toppingsRight = itemView.findViewById(R.id.itemToppings_right);



            minusButton = itemView.findViewById(R.id.btn_minus_item);
            plusButton = itemView.findViewById(R.id.btn_plus_item);
        }

        void bindLayout2Data(CartModel item) {
            // Bind data for layout 1
            itemImage.setImageResource(item.getItemImage());


            itemName.setText(item.getItemName());
            itemPrice.setText(item.getItemPrice());
            //itemDescription.setText(item.getItemDescription());
            itemCount.setText(item.getItemCount());
            sauceLeft.setText(item.getSelectedSauceLeft());
            sauceRight.setText(item.getSelectedSauceRight());
            //toppingsLeft.setText(item.getSelectedToppingsLeft().toString());
            //toppingsRight.setText(item.getSelectedToppingsRight().toString());
            // Bind toppings data
            if (item.getSelectedToppingsLeft() != null) {
                StringBuilder leftToppingsBuilder = new StringBuilder();
                for (String topping : item.getSelectedToppingsLeft()) {
                    leftToppingsBuilder.append(topping).append(", ");
                }
                String leftToppings = leftToppingsBuilder.toString();
                // Remove the trailing comma and space
                if (leftToppings.length() > 2) {
                    leftToppings = leftToppings.substring(0, leftToppings.length() - 2);
                }
                toppingsLeft.setText(leftToppings);
            }

            if (item.getSelectedToppingsRight() != null) {
                StringBuilder rightToppingsBuilder = new StringBuilder();
                for (String topping : item.getSelectedToppingsRight()) {
                    rightToppingsBuilder.append(topping).append(", ");
                }
                String rightToppings = rightToppingsBuilder.toString();
                // Remove the trailing comma and space
                if (rightToppings.length() > 2) {
                    rightToppings = rightToppings.substring(0, rightToppings.length() - 2);
                }
                toppingsRight.setText(rightToppings);
            }



            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(itemCount.getText().toString());
                    if (count > 1) {
                        count--;
                        itemCount.setText(String.valueOf(count));
                    } else {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            // Access list and notifyItemRemoved through the adapter instance
                            adapter.removeItem(adapterPosition);
                        }
                    }
                }
            });

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(itemCount.getText().toString());
                    count++;
                    itemCount.setText(String.valueOf(count));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ViewHolder for your CartItem
    public static class CartViewHolder extends RecyclerView.ViewHolder {

        // Declare your views here (e.g., TextViews, ImageViews)
        // Example:
        ImageView itemImage;
        TextView itemName,itemPrice,itemDescription,itemCount;
        ImageButton minusButton,plusButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemCount = itemView.findViewById(R.id.itemCount);
            minusButton = itemView.findViewById(R.id.btn_minus_item);
            plusButton = itemView.findViewById(R.id.btn_plus_item);




        }

        public void bind(CartModel item) {
            // itemNameTextView.setText(item.getItemName());
            // itemPriceTextView.setText(item.getItemPrice());
            // itemImageView.setImageResource(item.getItemImage());

            itemImage.setImageResource(item.getItemImage());


            itemName.setText(item.getItemName());
            itemPrice.setText(item.getItemPrice());
            itemDescription.setText(item.getItemDescription());
            itemCount.setText(item.getItemCount());

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(itemCount.getText().toString());
                    if(count>1){
                        count--;
                        itemCount.setText(String.valueOf(count));
                    }
                }
            });

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(itemCount.getText().toString());
                    count++;
                    itemCount.setText(String.valueOf(count));
                }
            });

;


        }
    }
}