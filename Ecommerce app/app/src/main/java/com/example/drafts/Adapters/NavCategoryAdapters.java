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
import com.example.drafts.R;
import com.example.drafts.ViewAllActivity;
import com.example.drafts.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapters extends RecyclerView.Adapter<NavCategoryAdapters.ViewHolder> {

    private Context context;
    private List<NavCategoryModel> navCategoryModelList;

    public NavCategoryAdapters(Context context, List<NavCategoryModel> navCategoryModelList) {
        this.context = context;
        this.navCategoryModelList = navCategoryModelList;
    }

    @NonNull
    @Override
    public NavCategoryAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryAdapters.ViewHolder holder, int position) {
        Glide.with(context).load(navCategoryModelList.get(position).getImg_url()).into(holder.CatImg);
        holder.CatName.setText(navCategoryModelList.get(position).getName());
        holder.CatDesc.setText(navCategoryModelList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",navCategoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return navCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView CatImg;
        TextView CatName,CatDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CatImg = itemView.findViewById(R.id.cat_nav_img);
            CatName = itemView.findViewById(R.id.cat_nav_name);
            CatDesc = itemView.findViewById(R.id.cat_nav_desc);
        }
    }
}
