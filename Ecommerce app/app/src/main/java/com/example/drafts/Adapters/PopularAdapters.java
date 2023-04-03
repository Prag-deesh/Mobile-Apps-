package com.example.drafts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.drafts.DetailedActivity;
import com.example.drafts.R;
import com.example.drafts.models.NavNewpModel;
import com.example.drafts.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    public PopularAdapters(FragmentActivity activity, List<NavNewpModel> navNewpModelList) {
    }

    @NonNull
    @Override
    public PopularAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.PopImg);
        holder.PopName.setText(popularModelList.get(position).getName());
        holder.PopDesc.setText(popularModelList.get(position).getDescription());
        holder.PopType.setText(popularModelList.get(position).getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail",popularModelList.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView PopImg;
        TextView PopName,PopDesc,PopType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            PopImg = itemView.findViewById(R.id.pop_img);
            PopName = itemView.findViewById(R.id.pop_name);
            PopDesc = itemView.findViewById(R.id.pop_desc);
            PopType = itemView.findViewById(R.id.pop_type);



        }
    }
}
