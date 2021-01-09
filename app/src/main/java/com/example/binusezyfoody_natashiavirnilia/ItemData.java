package com.example.binusezyfoody_natashiavirnilia;


public class ItemData {
    private String itemName;
    private String itemPrice;
    private Integer itemImage;

    public ItemData(String itemName, String itemPrice, Integer itemImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemImage () {
        return itemImage ;
    }

    public void setItemImage (Integer itemImage ) {
        this.itemImage  = itemImage ;
    }
}