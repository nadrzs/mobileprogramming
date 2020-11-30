package com.example.lockinapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lockinapp.Model.MangaItem;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MyViewHolder> {

    List<MangaItem> mangaItems;

    public MangaAdapter(List<MangaItem> mangaItems) {
        this.mangaItems = mangaItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final MangaItem item = mangaItems.get(position);

        DecimalFormat members = (DecimalFormat) DecimalFormat.getNumberInstance();
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setGroupingSeparator('.');

        members.setDecimalFormatSymbols(formatSymbols);

        Picasso.get().load(item.getManga_image_url()).into(holder.mangaImage);
        holder.mangaTitle.setText(item.getManga_Title());
        holder.mangaVolumes.setText(String.format("%s (%d)", item.getManga_Type(), item.getManga_Volumes()));
        holder.mangaStartEnd.setText(String.format("%s - %s", item.getManga_Start_date(), item.getManga_End_date()));
        holder.mangaMembers.setText(String.format("%s Members", members.format(item.getManga_Members())));
        holder.mangaRating.setText(String.valueOf(item.getManga_Score()));

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mangaItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.manga_image)
        ImageView mangaImage;
        @BindView(R.id.manga_title)
        TextView mangaTitle;
        @BindView(R.id.manga_volumes)
        TextView mangaVolumes;
        @BindView(R.id.manga_startEnd)
        TextView mangaStartEnd;
        @BindView(R.id.manga_members)
        TextView mangaMembers;
        @BindView(R.id.manga_rating)
        TextView mangaRating;
        @BindView(R.id.manga_rating_image)
        ImageView ratingImage;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

    }
}
