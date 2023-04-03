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
import com.example.drafts.models.PopularModel;
import com.example.drafts.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapters extends RecyclerView.Adapter<RecommendedAdapters.ViewHolder> {

    private Context context;
    private List<RecommendedModel> recommendedModelList;

    public RecommendedAdapters(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public RecommendedAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendedAdapters.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(recommendedModelList.get(position).getImg_url()).into(holder.RecomImg);
        holder.RecomName.setText(recommendedModelList.get(position).getName());
        holder.RecomDesc.setText(recommendedModelList.get(position).getDescription());
        holder.RecomType.setText(recommendedModelList.get(position).getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail",recommendedModelList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView RecomImg;
        TextView RecomName,RecomDesc,RecomType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            RecomImg = itemView.findViewById(R.id.recom_img);
            RecomName = itemView.findViewById(R.id.recom_name);
            RecomDesc = itemView.findViewById(R.id.recom_desc);
            RecomType = itemView.findViewById(R.id.recom_type);

        }
    }
}
