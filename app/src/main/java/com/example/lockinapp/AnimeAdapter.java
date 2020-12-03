package com.example.lockinapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockinapp.Data.DataBaseHelper;
import com.example.lockinapp.Model.AnimItem;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.MyViewHolder> {

    List<AnimItem> animeList;
    Context context;

    public AnimeAdapter(Context context, List<AnimItem> animItems) {
        this.context = context;
        this.animeList = animItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final AnimItem item = animeList.get(position);
        AnimItem animItem;

        DecimalFormat members = (DecimalFormat) DecimalFormat.getNumberInstance();
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setGroupingSeparator('.');

        members.setDecimalFormatSymbols(formatSymbols);

        Picasso.get().load(item.getImageUrl()).into(holder.animeImage);
        holder.animeTitle.setText(item.getTitle());
        holder.animeType.setText(String.format("%s (%d)", item.getType(), item.getEpisodes()));
        holder.animeStartEnd.setText(String.format("%s - %s", item.getStart_date(), item.getEnd_date()));
        holder.animeMembers.setText(String.format("%s Members", members.format(item.getMembers())));
        holder.animeRating.setText(String.valueOf(item.getScore()));

//        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
//        dataBaseHelper.addOne();

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.anime_image)
        ImageView animeImage;
        @BindView(R.id.anime_title)
        TextView animeTitle;
        @BindView(R.id.anime_type)
        TextView animeType;
        @BindView(R.id.anime_startEnd)
        TextView animeStartEnd;
        @BindView(R.id.anime_members)
        TextView animeMembers;
        @BindView(R.id.anime_rating)
        TextView animeRating;
        @BindView(R.id.rating_image)
        ImageView ratingImage;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

    }
}
