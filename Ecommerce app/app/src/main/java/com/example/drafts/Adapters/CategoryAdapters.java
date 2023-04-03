package com.example.drafts.Adapters;

import android.annotation.SuppressLint;
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
import com.example.drafts.R;
import com.example.drafts.ViewAllActivity;
import com.example.drafts.models.CategoryModel;

import java.util.List;

public class CategoryAdapters extends RecyclerView.Adapter<CategoryAdapters.ViewHolder> {

    private Context context;
    private List<CategoryModel> categoryModelList;

    public CategoryAdapters(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapters.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(categoryModelList.get(position).getImg_url()).into(holder.CatImg);
        holder.CatName.setText(categoryModelList.get(position).getName());

        //to open all products(viewall)from that category
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",categoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView CatImg;
        TextView CatName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CatImg = itemView.findViewById(R.id.cat_img);
            CatName = itemView.findViewById(R.id.cat_name);
        }
    }
}
