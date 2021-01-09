package com.example.binusezyfoody_natashiavirnilia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.binusezyfoody_natashiavirnilia.adapter.DataOrder;
import com.example.binusezyfoody_natashiavirnilia.adapter.MyOrderAdapter;
import com.example.binusezyfoody_natashiavirnilia.adapter.Transaction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderComplete extends AppCompatActivity {

    private Button btn;
    private TextView name;
    private TextView price;
    private TextView qty;
    private TextView sum;
    private List<DataOrder> data;
    private MyOrderAdapter adapter;
    private RecyclerView orderView;
    private List<Transaction> listTr = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        orderView = findViewById(R.id.orderView);
        data = new ArrayList<>();
        try {
            checkTransaction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            checkCached();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        sum = findViewById(R.id.sum);
        btn = findViewById(R.id.home);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                main();
            }
        });
    }

    public void main(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("type", "orderComplete");
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void checkTransaction()  throws IOException, ClassNotFoundException {
        List<Transaction> apkCacheList = (ArrayList<Transaction>)readCachedFile  (Objects.requireNonNull(getBaseContext()), "transaction");
        if(apkCacheList == null) {
        } else {
          listTr = apkCacheList;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void checkCached() throws IOException, ClassNotFoundException {
        List<DataOrder> apkCacheList = (ArrayList<DataOrder>)readCachedFile  (Objects.requireNonNull(getBaseContext()), "data");
        if(apkCacheList == null) {
        } else {
            data = apkCacheList;
            adapter = new MyOrderAdapter(getBaseContext() , data , "orderComplete");
            orderView.setAdapter(adapter);
            orderView.setLayoutManager(new LinearLayoutManager(this));
            for (DataOrder order : data) {
                Transaction tr = new Transaction(order.getItemName(), order.getItemPrice(), order.getItemQty());
                listTr.add(tr);
            }
            createCachedFile(getBaseContext() , "transaction" , listTr);
            data = null;
            createCachedFile1(getBaseContext() , "data" , data);
        }
    }
    public static Object readCachedFile (Context context, String key) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput (key);
        ObjectInputStream ois = new ObjectInputStream (fis);
        return ois.readObject ();
    }

    public void createCachedFile(Context context, String key, List<Transaction> fileName) {
        try{
            if(fileName.size() == 0){
                FileOutputStream fos = context.openFileOutput (key, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream (fos);
                oos.writeObject(fileName);
                oos.close ();
                fos.close ();
            }else {
                for (Transaction file : fileName) {
                    FileOutputStream fos = context.openFileOutput (key, Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream (fos);
                    oos.writeObject(fileName);
                    oos.close ();
                    fos.close ();
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void createCachedFile1(Context context, String key, List<DataOrder> fileName) {
        try{
            if(fileName == null){
                FileOutputStream fos = context.openFileOutput (key, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream (fos);
                oos.writeObject(fileName);
                oos.close ();
                fos.close ();
            }else {
                for (DataOrder file : fileName) {
                    FileOutputStream fos = context.openFileOutput (key, Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream (fos);
                    oos.writeObject(fileName);
                    oos.close ();
                    fos.close ();
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}