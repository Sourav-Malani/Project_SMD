package com.ass2.Models;

import java.util.ArrayList;

public class CartModel {

    String itemCount, itemName, itemPrice, itemDescription;

    int itemImage;
    private int viewType;
    private String selectedSauceLeft;
    private String selectedSauceRight;
    private ArrayList<String> selectedToppingsLeft;
    private ArrayList<String> selectedToppingsRight;

    public CartModel(String itemCount, String itemName, String itemPrice, String itemDescription, int itemImage,
                     int viewType, String selectedSauceLeft, String selectedSauceRight,
                     ArrayList<String> selectedToppingsLeft, ArrayList<String> selectedToppingsRight) {
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
        this.viewType = viewType;
        this.selectedSauceLeft = selectedSauceLeft;
        this.selectedSauceRight = selectedSauceRight;
        this.selectedToppingsLeft = selectedToppingsLeft;
        this.selectedToppingsRight = selectedToppingsRight;
    }

    public CartModel(String itemCount, String itemName, String itemPrice, String itemDescription, int itemImage,
                     int viewType) {
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
        this.viewType = viewType;
    }

    public String getItemCount() {
        return itemCount;
    }
    public int getViewType() {
        return viewType;
    }

    public String getSelectedSauceLeft() {
        return selectedSauceLeft;
    }
    public String getSelectedSauceRight() {
        return selectedSauceRight;
    }
    public ArrayList<String> getSelectedToppingsLeft() {
        return selectedToppingsLeft;
    }
    public ArrayList<String> getSelectedToppingsRight() {
        return selectedToppingsRight;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        itemName = name;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }
    public int getItemImage() {
        return itemImage;
    }

}
