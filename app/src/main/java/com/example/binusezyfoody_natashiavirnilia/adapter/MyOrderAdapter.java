package com.example.binusezyfoody_natashiavirnilia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.binusezyfoody_natashiavirnilia.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder>{

    Context mContext;
    List<DataOrder> data = new ArrayList<>();
    String type;
    OnClickDelete onClickDelete;

    public MyOrderAdapter(Context mContext , List<DataOrder> data1 , String type)  {
        this.mContext = mContext;
        this.data = data1;
        this.type = type;
    }

    public MyOrderAdapter(Context mContext , List<DataOrder> data1 , String type, OnClickDelete onClickDelete)  {
        this.mContext = mContext;
        this.data = data1;
        this.type = type;
        this.onClickDelete = onClickDelete;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(mContext);
        View v = inflate.inflate(R.layout.list_order , parent ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(type != "myorder") {
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setVisibility(View.VISIBLE);
            holder.tanggal.setVisibility(View.GONE);
        }
        if(type == "orderComplete") {
            holder.tanggal.setVisibility(View.GONE);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/y");
        Long timestamp = data.get(position).getDate();
        String date = sdf.format(new Date(timestamp));
        holder.tanggal.setText(date);
        holder.name.setText(data.get(position).getItemName());
        holder.price.setText("Rp. "+Integer.toString(data.get(position).getItemPrice()));
        holder.qty.setText(Integer.toString(data.get(position).getItemQty()));
        int sum1 = data.get(position).getItemPrice() * data.get(position).getItemQty();
        holder.sum.setText("Total Harga: Rp. " +Integer.toString(sum1));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelete.OnDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        else return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , price , qty , sum , tanggal;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);
            sum = itemView.findViewById(R.id.sum);
            delete = itemView.findViewById(R.id.delete);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
    }

    public interface OnClickDelete {
        void OnDeleteClick(int position);
    }
}
