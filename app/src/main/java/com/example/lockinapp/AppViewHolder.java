package com.example.lockinapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppViewHolder extends RecyclerView.ViewHolder {

    public ImageView lock_icon, app_icon;
    public TextView app_name;

    public AppViewHolder(@NonNull View itemView) {
        super(itemView);

//        lock_icon = itemView.findViewById(R.id.lock_icon);
        app_icon = itemView.findViewById(R.id.app_icon);
        app_name = itemView.findViewById(R.id.app_name);
    }
}
