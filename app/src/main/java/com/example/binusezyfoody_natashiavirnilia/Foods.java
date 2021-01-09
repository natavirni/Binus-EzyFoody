package com.example.binusezyfoody_natashiavirnilia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Foods extends AppCompatActivity {
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
                new ItemData("Nasi putih","6500",R.drawable.rice),
                new ItemData("Taco","20000",R.drawable.taco),
                new ItemData("Ramen","35000",R.drawable.ramen),
                new ItemData("Teriyaki","43000",R.drawable.food),
                new ItemData("Sandwich","21000",R.drawable.taco),
                new ItemData("Bakmie Ayam","12000",R.drawable.ramen),
        };

        AdapterMain adapters = new AdapterMain(data,Foods.this);
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