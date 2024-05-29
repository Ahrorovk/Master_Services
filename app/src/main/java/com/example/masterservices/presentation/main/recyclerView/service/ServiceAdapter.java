package com.example.masterservices.presentation.main.recyclerView.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterservices.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {
    private List<ServiceItem> itemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ServiceItem item);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView master;
        public TextView price;
        public ImageView imageView;
        public TextView description;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            master = v.findViewById(R.id.master);
            price = v.findViewById(R.id.price);
            description = v.findViewById(R.id.description);
            imageView = v.findViewById(R.id.image);
        }

        public void bind(final ServiceItem item, final OnItemClickListener listener) {
            name.setText(item.getName());
            description.setText(item.getDescription());
            master.setText(item.getMaster());
            price.setText(item.getPrice().toString());
            imageView.setImageResource(item.getImage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public ServiceAdapter(List<ServiceItem> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ServiceItem currentItem = itemList.get(position);
        holder.name.setText(currentItem.getName());
        holder.master.setText(currentItem.getMaster());
        holder.description.setText(currentItem.getDescription());
        holder.price.setText(currentItem.getPrice().toString());
        holder.imageView.setImageResource(currentItem.getImage());
        holder.bind(currentItem, listener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}