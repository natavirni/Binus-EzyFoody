package com.example.binusezyfoody_natashiavirnilia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button history;
    private CardView drinks;
    private CardView snacks;
    private CardView foods;
    private CardView topup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getExtras().getString("type").equals("restaurant")){
            String restaurant = getIntent().getExtras().getString("restaurant").toString();
            Toast.makeText(getBaseContext(), "Nama Restaurant: " +restaurant, Toast.LENGTH_LONG).show();
        }

        btn = findViewById(R.id.orderBtn);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                myorder();
            }
        });

        drinks = findViewById(R.id.drinks);
        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinks();
            }
        });

        snacks = findViewById(R.id.snacks);
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snacks();
            }
        });

        foods = findViewById(R.id.foods);
        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foods();
            }
        });

        topup = findViewById(R.id.topup);
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topup();
            }
        });

        history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history();
            }
        });
    }

    public void myorder(){
        Intent intent = new Intent(this, MyOrder.class);
        startActivity(intent);
    }

    public void drinks(){
        Intent intent = new Intent(this, Drinks.class);
        startActivity(intent);
    }

    public void snacks(){
        Intent intent = new Intent(this, Snacks.class);
        startActivity(intent);
    }

    public void foods(){
        Intent intent = new Intent(this, Foods.class);
        startActivity(intent);
    }

    public void topup(){
        Intent intent = new Intent(this, Topup.class);
        startActivity(intent);
    }

    public void history(){
        Intent intent = new Intent(this, Histories.class);
        startActivity(intent);
    }
}

