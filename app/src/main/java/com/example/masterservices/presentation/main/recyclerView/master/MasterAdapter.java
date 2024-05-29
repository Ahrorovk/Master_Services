package com.example.masterservices.presentation.main.recyclerView.master;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterservices.R;
import com.example.masterservices.presentation.main.recyclerView.service.ServiceAdapter;

import java.util.List;

public class MasterAdapter extends RecyclerView.Adapter<MasterAdapter.MyViewHolder> {
    private List<MasterItem> itemList;
    private MasterAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MasterItem item);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView master;
        public TextView job;

        public MyViewHolder(View v) {
            super(v);
            master = v.findViewById(R.id.master_masters);
            job = v.findViewById(R.id.job_masters);
        }

        public void bind(final MasterItem item, final MasterAdapter.OnItemClickListener listener) {
            master.setText(item.getMaster());
            job.setText(item.getJob());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public MasterAdapter(List<MasterItem> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.master_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MasterItem currentItem = itemList.get(position);
        holder.master.setText(currentItem.getMaster());
        holder.job.setText(currentItem.getJob());
        holder.bind(currentItem, listener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}