package com.example.drafts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.drafts.DetailedActivity;
import com.example.drafts.R;
import com.example.drafts.ViewAllActivity;
import com.example.drafts.models.MyOrdersModel;

import java.util.List;

public class MyOrdersAdapters extends RecyclerView.Adapter<MyOrdersAdapters.ViewHolder>{

    private final Context context;
    private final List<MyOrdersModel> myOrdersModelList;

    public MyOrdersAdapters(Context context, List<MyOrdersModel> myOrdersModelList) {
        this.context = context;
        this.myOrdersModelList = myOrdersModelList;
    }

    @NonNull
    @Override
    public MyOrdersAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myorders_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(myOrdersModelList.get(position).getproductImg()).into(holder.img);
        holder.name.setText(myOrdersModelList.get(position).getProductName());
        holder.desc.setText(myOrdersModelList.get(position).getProductDesc());
        holder.price.setText(myOrdersModelList.get(position).getProductPrice());
        holder.type.setText(myOrdersModelList.get(position).getProductType());
        holder.quantity.setText("quantity : "+myOrdersModelList.get(position).getTotalQuantity());


    }

    @Override
    public int getItemCount() {
        return myOrdersModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,desc,price,type,quantity;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.myorder_img);
            name = itemView.findViewById(R.id.myorder_name);
            desc = itemView.findViewById(R.id.myorder_desc);
            price = itemView.findViewById(R.id.myorder_price);
            type = itemView.findViewById(R.id.myorder_type);
            quantity = itemView.findViewById(R.id.myorder_quantity);
        }
    }
}
