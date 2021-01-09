package com.example.binusezyfoody_natashiavirnilia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    ItemData[] data;
    Context context;

    public AdapterMain(ItemData[] data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemData dataList = data[position];
        holder.textViewName.setText(dataList.getItemName());
        holder.textViewPrice.setText(dataList.getItemPrice());
        holder.itemImage.setImageResource(dataList.getItemImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataList.getItemName(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(context,Order.class);
                in.putExtra("img",dataList.getItemImage());
                in.putExtra("name",dataList.getItemName());
                in.putExtra("price",dataList.getItemPrice());
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView textViewName;
        TextView textViewPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.foto);
            textViewName = itemView.findViewById(R.id.name);
            textViewPrice = itemView.findViewById(R.id.price);

        }
    }

}