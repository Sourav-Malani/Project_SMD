package com.ass2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ass2.project_smd.R;

import java.util.HashMap;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> cardList;

    // Define constants for different card types
    private static final int CARD_TYPE_1 = 1;
    private static final int CARD_TYPE_2 = 2;

    public CardAdapter(List<Object> cardList) {
        this.cardList = cardList;
    }

    @Override
    public int getItemViewType(int position) {
        // Return different view types based on position or data
        // Here, you can return different types based on the card list content
        // For example:
        return position % 2 == 0 ? CARD_TYPE_1 : CARD_TYPE_2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case CARD_TYPE_1:
                view = inflater.inflate(R.layout.card_item_layout_1, parent, false);
                viewHolder = new CardType1ViewHolder(view);
                break;
            case CARD_TYPE_2:
                view = inflater.inflate(R.layout.card_item_layout_2, parent, false);
                viewHolder = new CardType2ViewHolder(view);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {
            case CARD_TYPE_1:
                // Bind data for CardType1ViewHolder
                ((CardType1ViewHolder) holder).bindData((HashMap<String, Object>) cardList.get(position));
                break;
            case CARD_TYPE_2:
                // Bind data for CardType2ViewHolder
                ((CardType2ViewHolder) holder).bindData((HashMap<String, Object>) cardList.get(position));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    // ViewHolder for CardType1
    private static class CardType1ViewHolder extends RecyclerView.ViewHolder {
        // Implement ViewHolder for CardType1 layout

        public CardType1ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views for CardType1ViewHolder
        }

        public void bindData(HashMap<String, Object> cardData) {
            // Bind data to views for CardType1ViewHolder
            // Example:
             TextView textView = itemView.findViewById(R.id.textCreatePizza1);
             textView.setText(cardData.get("text_key").toString());
            // ... (bind other views)
        }
    }

    // ViewHolder for CardType2
    private static class CardType2ViewHolder extends RecyclerView.ViewHolder {
        // Implement ViewHolder for CardType2 layout

        public CardType2ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views for CardType2ViewHolder
        }

        public void bindData(HashMap<String, Object> cardData) {
            // Bind data to views for CardType2ViewHolder
            // Example:
            // TextView textView = itemView.findViewById(R.id.textCreatePizza3);
            // textView.setText(cardData.get("text_key").toString());
            // ... (bind other views)
        }
    }
}
