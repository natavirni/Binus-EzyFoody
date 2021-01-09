package com.example.binusezyfoody_natashiavirnilia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Snacks extends AppCompatActivity {
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
                new ItemData("Pocky","5000",R.drawable.pocky),
                new ItemData("Candy","1200",R.drawable.candy),
                new ItemData("Chitato","7000",R.drawable.snack),
                new ItemData("Popcorn","12900",R.drawable.popcorn),
                new ItemData("Keripik","9000",R.drawable.snack),
                new ItemData("Candy","1230",R.drawable.candy),
        };

        AdapterMain adapters = new AdapterMain(data,Snacks.this);
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