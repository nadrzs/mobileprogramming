package com.example.lockinapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lockinapp.Model.MangaItem;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MyViewHolder> {

    List<MangaItem> mangaItems;
    Context context;

    public MangaAdapter(Context context,List<MangaItem> mangaItems) {
        this.context = context;
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

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable mFoto;
                mFoto = holder.mangaImage.getDrawable();

                Bitmap mFotoBitmap;
                mFotoBitmap = ((BitmapDrawable)mFoto).getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mFotoBitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream);
                byte[] bytes = stream.toByteArray();

                Intent intent = new Intent(context.getApplicationContext(), MangaDetailActivity.class);
                intent.putExtra("MANGA_MAL", mangaItems.get(position).getManga_MalId());
                intent.putExtra("MANGA_IMAGE_DETAIL", bytes);
                intent.putExtra("MANGA_TITLE_DETAIL", mangaItems.get(position).getManga_Title());
                intent.putExtra("MANGA_SCORE_DETAIL", mangaItems.get(position).getManga_Score());
                context.startActivity(intent);
            }
        });

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
        @BindView(R.id.mangaCard)
        CardView card;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

    }
}
