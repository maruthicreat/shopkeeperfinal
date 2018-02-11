package com.example.maruthiraja.shopkeeperapp;


public class ItemShow {

    private String item_description;
    private String item_image;
    private String item_name;
    private String item_price;

    public ItemShow(){
        System.out.println("none constructor");
    }

    public ItemShow(String itemDescription, String itemImage, String itemName, String itemPrice) {
        this.item_description = itemDescription;
        this.item_image = itemImage;
        this.item_name = itemName;
        this.item_price = itemPrice;
        System.out.println("full constructor");
    }

    private String itemPrice;

    public String getItemDescription() {
        return item_description;
    }

    public void setItemDescription(String itemDescription) {
        this.item_description = itemDescription;
    }

    public String getItemImage() {
        return item_image;
    }

    public void setItemImage(String itemImage) {
        this.item_image = itemImage;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String itemName) {
        this.item_name = itemName;
    }

    public String getItemPrice() {
        return item_price;
    }

    public void setItemPrice(String itemPrice) {
        this.item_price = itemPrice;
    }
}
