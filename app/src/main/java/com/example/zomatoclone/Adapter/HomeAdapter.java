package com.example.zomatoclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoclone.Model.RestaurantItem;
import com.example.zomatoclone.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private ArrayList<RestaurantItem> items;
    private Context mContext;

    public HomeAdapter(ArrayList<RestaurantItem> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ViewHolder(inflater.inflate(R.layout.recycler_home_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.roundedImageView.setImageResource(items.get(position).getImage());
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvSubTitle.setText(items.get(position).getSubtitle());
        holder.tvTime.setText(items.get(position).getTime());
        holder.tvPrice.setText(items.get(position).getPrice());
        holder.tvRating.setText(items.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RoundedImageView roundedImageView;
        public TextView tvTitle,tvSubTitle,tvPrice,tvTime,tvRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.imageItem);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
