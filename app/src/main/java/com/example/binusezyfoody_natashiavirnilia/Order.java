package com.example.binusezyfoody_natashiavirnilia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binusezyfoody_natashiavirnilia.SharedPref.MoneySharedPref;
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


public class Order extends AppCompatActivity {
    private ImageView img;
    private TextView tv1,tv2;
    private Button btn;
    private Button more;
    private EditText qty;
    private Button addBtn;
    private List<DataOrder> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        img = findViewById(R.id.image);
        tv1 = findViewById(R.id.name);
        tv2 = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        addBtn = findViewById(R.id.addbtn);

        img.setImageResource(getIntent().getIntExtra("img", 0));
        tv1.setText(getIntent().getStringExtra("name"));
        tv2.setText(getIntent().getStringExtra("price"));

        btn = findViewById(R.id.orderBtn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myorder();
            }
        });

        more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               more();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    checkCached();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                DataOrder od = new DataOrder(tv1.getText().toString() ,Integer.parseInt(tv2.getText().toString()) , Integer.parseInt(qty.getText().toString()));
                data.add(od);
                createCachedFile(getBaseContext() , "data" , data);
                Toast.makeText(getBaseContext() , "Item berhasil di add" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void createCachedFile(Context context, String key, List<DataOrder> fileName) {
        try{
            if(fileName.equals(null)){
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


    public void more(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void myorder(){

        if(qty != null) {
            Intent intent = new Intent(Order.this , MyOrder.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "You need to fill quantity", Toast.LENGTH_SHORT).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void checkCached() throws IOException, ClassNotFoundException {
        List<DataOrder> apkCacheList = (ArrayList<DataOrder>)readCachedFile  (Objects.requireNonNull(getBaseContext()), "data");
        if(apkCacheList == null) {
        } else {
            data = apkCacheList;
        }
    }
    public static Object readCachedFile (Context context, String key) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput (key);
        ObjectInputStream ois = new ObjectInputStream (fis);
        return ois.readObject ();
    }
}
