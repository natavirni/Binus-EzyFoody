package com.example.binusezyfoody_natashiavirnilia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.binusezyfoody_natashiavirnilia.SharedPref.MoneySharedPref;
import java.util.ArrayList;
import java.util.List;

public class Topup extends AppCompatActivity {
    private Spinner sp;
    private Button submit;
    private EditText ammount;
    private TextView saldo;
    private MoneySharedPref msp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        msp = new MoneySharedPref(getBaseContext());
        sp = findViewById(R.id.spinner);
        saldo = findViewById(R.id.saldo);
        saldo.setText("Rp. "+Integer.toString(msp.getMoney()));

        List<String> item = new ArrayList<>();
        item.add("~Choose method~");
        item.add("OVO");
        item.add("Gopay");
        item.add("Bank BCA");
        item.add("Bank Mandiri");
        item.add("Bank BNI");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Topup.this,android.R.layout.simple_spinner_dropdown_item,item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        ammount = findViewById(R.id.ammount);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!ammount.getText().toString().isEmpty() ){
                        if(Integer.parseInt(ammount.getText().toString()) <= 2000000) {
                            Toast.makeText(getApplicationContext(), "Success Top up Rp." + ammount.getText().toString(), Toast.LENGTH_SHORT).show();
                            msp.setMoney(Integer.parseInt(ammount.getText().toString()));
                            saldo.setText("Rp. "+Integer.toString(msp.getMoney()));
                        }else{
                            Toast.makeText(getApplicationContext(),"Max top up 2 mio",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"You need to fill amount",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}