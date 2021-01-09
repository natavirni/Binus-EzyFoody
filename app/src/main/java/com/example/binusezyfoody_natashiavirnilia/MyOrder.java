package com.example.binusezyfoody_natashiavirnilia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binusezyfoody_natashiavirnilia.SharedPref.MoneySharedPref;
import com.example.binusezyfoody_natashiavirnilia.adapter.DataOrder;
import com.example.binusezyfoody_natashiavirnilia.adapter.MyOrderAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyOrder extends AppCompatActivity implements MyOrderAdapter.OnClickDelete {
    private Button btn2;
    private RecyclerView orderView;

    private List<DataOrder> data;
    private MyOrderAdapter adapter;
    private MoneySharedPref msp;
    private TextView saldo1;
    private int totalHarga;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        data = new ArrayList<>();
        orderView = findViewById(R.id.orderView);
        msp = new MoneySharedPref(getBaseContext());
        saldo1 = findViewById(R.id.saldoTv);
        saldo1.setText("Saldo : Rp. "+Integer.toString(msp.getMoney()));
        try {
            checkCached();
            for (DataOrder order: data) {
                int total = order.getItemPrice() * order.getItemQty();
                totalHarga+=total;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        btn2 = findViewById(R.id.pay);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(msp.getMoney() >= totalHarga){
                    Toast.makeText(getApplicationContext(), "Success pay "+totalHarga, Toast.LENGTH_SHORT).show();
                    msp.removeMoney(totalHarga);
                    Intent intent = new Intent(getBaseContext() , OrderComplete.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext() , "Saldo anda tidak mencukupi" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void checkCached() throws IOException, ClassNotFoundException {
        List<DataOrder> apkCacheList = (ArrayList<DataOrder>)readCachedFile  (Objects.requireNonNull(getBaseContext()), "data");
        if(apkCacheList == null) {
        } else {
            data = apkCacheList;
            adapter = new MyOrderAdapter(getBaseContext() , data , "myorder" , this);
            orderView.setAdapter(adapter);
            orderView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
    public static Object readCachedFile (Context context, String key) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput (key);
        ObjectInputStream ois = new ObjectInputStream (fis);
        return ois.readObject ();
    }

    @Override
    public void OnDeleteClick(int position) {
        data.remove(position);
        adapter.notifyDataSetChanged();
        Order.createCachedFile(getBaseContext() , "data" , data);
    }
}