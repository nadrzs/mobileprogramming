package com.example.lockinapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.MyViewHolder> {

    Context context;
    List<AppItem> appItemsList;

    public AppAdapter(Context context, List<AppItem> appItems) {
        this.context = context;
        this.appItemsList = appItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(context).inflate(R.layout.apps_layout, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.app_name.setText(appItemsList.get(position).getName());
        holder.app_icon.setImageDrawable(appItemsList.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return appItemsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView lock_icon, app_icon;
        private TextView app_name;

        public MyViewHolder(View itemView) {
            super(itemView);

//            lock_icon = itemView.findViewById(R.id.lock_icon);
            app_icon = itemView.findViewById(R.id.app_icon);
            app_name = itemView.findViewById(R.id.app_name);
        }
    }
}
