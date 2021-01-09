package com.example.binusezyfoody_natashiavirnilia.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

public class MoneySharedPref {
    private Context mContext;
    SharedPreferences sp;

    public MoneySharedPref(Context mContext) {
        this.mContext = mContext;
        this.sp = mContext.getSharedPreferences("money" , Context.MODE_PRIVATE);
    }

    public int getMoney() {
        return sp.getInt("money" , 0);
    }

    public void setMoney(int money) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("money", sp.getInt("money" , 0) + money);
        editor.apply();
    }

    public void removeMoney(int money) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("money", sp.getInt("money" , 0) - money);
        editor.apply();
    }
}
