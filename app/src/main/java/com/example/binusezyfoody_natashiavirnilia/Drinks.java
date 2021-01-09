package com.example.binusezyfoody_natashiavirnilia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Drinks extends AppCompatActivity {
    private Button btn;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recylerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recylerViewLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(recylerViewLayoutManager);


        ItemData[] data = new ItemData[]{
                new ItemData("Air Mineral", "6000",R.drawable.water),
                new ItemData("Jus Apel","11400",R.drawable.juice),
                new ItemData("Teh Manis","7300",R.drawable.tea),
                new ItemData("Coca Cola","5500",R.drawable.coke),
                new ItemData("Jus Alpukat","23000",R.drawable.juice),
                new ItemData("Thai Tea","12500",R.drawable.tea),
        };

        AdapterMain adapters = new AdapterMain(data,Drinks.this);
        recyclerView.setAdapter(adapters);

        btn = findViewById(R.id.orderBtn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myorder();
            }
        });

    }
    public void myorder(){
        Intent intent = new Intent(this, MyOrder.class);
        startActivity(intent);
    }
}