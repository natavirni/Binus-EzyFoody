package com.example.binusezyfoody_natashiavirnilia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.example.binusezyfoody_natashiavirnilia.adapter.DataOrder;
import com.example.binusezyfoody_natashiavirnilia.adapter.MyOrderAdapter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Objects;

public class Histories extends AppCompatActivity {

    private List<DataOrder> data;
    private MyOrderAdapter adapter;
    private RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histories);
        recyclerView = findViewById(R.id.orderView);
        try {
            checkCached();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void checkCached() throws IOException, ClassNotFoundException {
        List<DataOrder> apkCacheList = (List<DataOrder>) readCachedFile(Objects.requireNonNull(getBaseContext()), "transaction");
        if(apkCacheList == null) {
        } else {
            data = apkCacheList;
            adapter = new MyOrderAdapter(getBaseContext(), data, "transaction");
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
    public static Object readCachedFile (Context context, String key) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput (key);
        ObjectInputStream ois = new ObjectInputStream (fis);
        return ois.readObject ();
    }
}