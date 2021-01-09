package com.example.binusezyfoody_natashiavirnilia.adapter;

import java.io.Serializable;
import java.util.Date;

public class Transaction extends  DataOrder implements Serializable {
    public Transaction(String itemName, int itemPrice, int itemQty) {
        super(itemName, itemPrice, itemQty);
        this.date = new Date().getTime();
    }
}
