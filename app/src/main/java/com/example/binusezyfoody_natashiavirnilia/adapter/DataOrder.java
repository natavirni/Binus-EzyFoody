package com.example.binusezyfoody_natashiavirnilia.adapter;

import java.io.Serializable;

public class DataOrder implements Serializable {
    private String itemName;
    private int itemPrice;
    private int itemQty;
    protected long date;

    public DataOrder(String itemName, int itemPrice, int itemQty) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public long getDate() {
        return date;
    }
}
