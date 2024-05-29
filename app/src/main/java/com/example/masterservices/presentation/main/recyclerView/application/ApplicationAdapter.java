package com.example.masterservices.presentation.main.recyclerView.application;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterservices.R;

import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder> {
    private final List<ApplicationItem> itemList;
    private final ApplicationAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ApplicationItem item);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView master;
        public TextView price;
        public TextView date;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.application_name);
            master = v.findViewById(R.id.application_master);
            price = v.findViewById(R.id.application_price);
            date = v.findViewById(R.id.application_date);
        }

        public void bind(final ApplicationItem item, final ApplicationAdapter.OnItemClickListener listener) {
            name.setText(item.getName());
            date.setText(item.getDate());
            master.setText(item.getMaster());
            price.setText(item.getPrice());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public ApplicationAdapter(List<ApplicationItem> itemList, ApplicationAdapter.OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ApplicationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.application_item, parent, false);
        return new ApplicationAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationAdapter.MyViewHolder holder, int position) {
        ApplicationItem currentItem = itemList.get(position);
        holder.name.setText(currentItem.getName());
        holder.master.setText(currentItem.getMaster().toString());
        holder.date.setText(currentItem.getDate().toString());
        holder.price.setText(currentItem.getPrice().toString());
        holder.bind(currentItem, listener);
    }

    @Override
    public int getItemCount() {
        Log.e("TAG", "ItemSize->" + itemList.size());
        return itemList.size();
    }
}