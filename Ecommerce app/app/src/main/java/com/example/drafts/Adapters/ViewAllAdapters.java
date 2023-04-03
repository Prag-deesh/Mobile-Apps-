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
import com.example.drafts.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapters extends RecyclerView.Adapter<ViewAllAdapters.ViewHolder> {

    private Context context;
    private List<ViewAllModel> viewAllModelList;

    public ViewAllAdapters(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewAllAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewAllAdapters.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewall_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.image);
        holder.name.setText(viewAllModelList.get(position).getName());
        holder.desc.setText(viewAllModelList.get(position).getDescription());
        holder.type.setText(viewAllModelList.get(position).getType());
        holder.price.setText(String.valueOf(viewAllModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail",viewAllModelList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,desc,type,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.viewall_img);
            name = itemView.findViewById(R.id.viewall_name);
            desc = itemView.findViewById(R.id.viewall_desc);
            type = itemView.findViewById(R.id.viewall_typename);
            price = itemView.findViewById(R.id.viewall_numprice);

        }
    }
}
