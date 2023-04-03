package com.example.drafts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drafts.R;
import com.example.drafts.models.MyCartModel;

import java.util.List;

public class MyCartAdapters extends RecyclerView.Adapter<MyCartAdapters.ViewHolder> {

    private final Context context;
    private final List<MyCartModel> myCartModelList;
    int totPrice = 0;

    public MyCartAdapters(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;
    }

    @NonNull
    @Override
    public MyCartAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapters.ViewHolder holder, int position) {

        holder.name.setText(myCartModelList.get(position).getProductName());
        holder.price.setText(myCartModelList.get(position).getProductPrice());
        holder.type.setText(myCartModelList.get(position).getProductType());
        holder.date.setText(myCartModelList.get(position).getCurrentDate());
        holder.time.setText(myCartModelList.get(position).getCurrentTime());
        holder.quantity.setText(myCartModelList.get(position).getTotalQuantity());
        holder.totprice.setText(" ₹ "+String.valueOf(myCartModelList.get(position).getTotalprice()));

        //pass total price to cart fragment
        totPrice = totPrice + myCartModelList.get(position).getTotalprice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totPrice);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,price,type,date,time,quantity,totprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            type = itemView.findViewById(R.id.product_type);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.tot_quantity);
            totprice = itemView.findViewById(R.id.tot_price);



        }
    }
}
