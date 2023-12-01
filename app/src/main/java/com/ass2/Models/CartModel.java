package com.ass2.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CartModel {

    String itemCount, itemName, itemPrice, itemDescription, itemSize;

    int itemImage,itemID;

    int viewType;
    String date;
    String itemCrust,itemToppings;
    private String selectedSauceLeft;
    private String selectedSauceRight;
    private ArrayList<String> selectedToppingsLeft;
    private ArrayList<String> selectedToppingsRight;

    public CartModel(String itemCount, String itemName, String itemPrice,int itemImage,
                     int viewType,String itemSize,String itemCrust,String itemToppings, String selectedSauceLeft, String selectedSauceRight,
                     ArrayList<String> selectedToppingsLeft, ArrayList<String> selectedToppingsRight) {
        this.itemCount = "1"; // one item is added by default
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.viewType = viewType;
        this.selectedSauceLeft = selectedSauceLeft;
        this.selectedSauceRight = selectedSauceRight;
        this.selectedToppingsLeft = selectedToppingsLeft;
        this.selectedToppingsRight = selectedToppingsRight;
        this.itemSize = itemSize;
        this.itemCrust = itemCrust;
        this.itemToppings = itemToppings;

    }

    public CartModel() {
        // Default constructor
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

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public int getViewType() {
        return viewType;
    }
    public int getId() {
        return itemID;
    }

    public void setId(int id) {
        this.itemID = id;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getSelectedSauceLeft() {
        return selectedSauceLeft;
    }

    public void setSelectedSauceLeft(String selectedSauceLeft) {
        this.selectedSauceLeft = selectedSauceLeft;
    }

    public String getSelectedSauceRight() {
        return selectedSauceRight;
    }

    public void setSelectedSauceRight(String selectedSauceRight) {
        this.selectedSauceRight = selectedSauceRight;
    }

    public ArrayList<String> getSelectedToppingsLeft() {
        return selectedToppingsLeft;
    }

    public void setSelectedToppingsLeft(ArrayList<String> selectedToppingsLeft) {
        this.selectedToppingsLeft = selectedToppingsLeft;
    }

    public ArrayList<String> getSelectedToppingsRight() {
        return selectedToppingsRight;
    }

    public void setSelectedToppingsRight(ArrayList<String> selectedToppingsRight) {
        this.selectedToppingsRight = selectedToppingsRight;
    }

    public String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
}
