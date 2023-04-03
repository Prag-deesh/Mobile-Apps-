package com.example.drafts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.drafts.DetailedActivity;
import com.example.drafts.R;
import com.example.drafts.ViewAllActivity;
import com.example.drafts.models.NavCategoryModel;
import com.example.drafts.models.NavNewpModel;
import com.example.drafts.models.ViewAllModel;

import java.io.Serializable;
import java.util.List;

public class NavNewpAdapters extends RecyclerView.Adapter<NavNewpAdapters.ViewHolder> {

    private Context context;
    private List<NavNewpModel> navNewpModelList;

    public NavNewpAdapters(Context context, List<NavNewpModel> navNewpModelList) {
        this.context = context;
        this.navNewpModelList = navNewpModelList;
    }

    @NonNull
    @Override
    public NavNewpAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NavNewpAdapters.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_newp_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavNewpAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(navNewpModelList.get(position).getImg_url()).into(holder.NewpImg);
        holder.NewpName.setText(navNewpModelList.get(position).getName());
        holder.NewpDesc.setText(navNewpModelList.get(position).getDescription());
        holder.newpType.setText(navNewpModelList.get(position).getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", navNewpModelList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return navNewpModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView NewpImg;
        TextView NewpName,NewpDesc,newpType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            NewpImg = itemView.findViewById(R.id.newp_img);
            NewpName = itemView.findViewById(R.id.newp_name);
            NewpDesc = itemView.findViewById(R.id.newp_desc);
            newpType = itemView.findViewById(R.id.newp_type);
        }
    }
}
