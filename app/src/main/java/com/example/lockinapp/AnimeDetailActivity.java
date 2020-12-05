package com.example.lockinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lockinapp.Data.ApiService;
import com.example.lockinapp.Data.DataRetrofit;
import com.example.lockinapp.Model.AnimeItemDetail;
import com.example.lockinapp.Model.Manga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeDetailActivity extends AppCompatActivity {

    ImageView iv_image;
    TextView tv_anime_title, tv_rating, tv_synopsis, tv_status;
    int anime_mal_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);

        iv_image = findViewById(R.id.AnimeImageDetail);
        tv_anime_title = findViewById(R.id.AnimeTitleDetail);
        tv_rating = findViewById(R.id.AnimeRatingDetail);
        tv_synopsis = findViewById(R.id.AnimeSynopsis);
        tv_status = findViewById(R.id.AnimeStatusDetail);

        //Mengambil data

        final Intent intent = getIntent();

        //Data BackEnd
        anime_mal_id =  intent.getExtras().getInt("ANIME_MAL");

        //Data UI
        byte[] bytes = intent.getByteArrayExtra("ANIME_IMAGE_DETAIL");
        String animeTitle = intent.getStringExtra("ANIME_TITLE_DETAIL");
        double score = intent.getExtras().getDouble("ANIME_SCORE_DETAIL");

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        String scoreString = Double.toString(score);

        iv_image.setImageBitmap(bitmap);
        tv_anime_title.setText(animeTitle);
        tv_rating.setText(scoreString);
        ResponseData(anime_mal_id);

    }

    private void ResponseData(int id) {
        ApiService apiService = DataRetrofit.getData().create(ApiService.class);
        apiService.getAnimeDetail(id)
                .enqueue(new Callback<AnimeItemDetail>() {
                    @Override
                    public void onResponse(Call<AnimeItemDetail> call, Response<AnimeItemDetail> response) {
                        String synopsis = response.body().getSynopsis();
                        String status = response.body().getStatus();

                        if(!synopsis.isEmpty()){
                            tv_synopsis.setText(synopsis);
                            tv_status.setText(status);
                        }else{
                            Toast.makeText(AnimeDetailActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AnimeItemDetail> call, Throwable t) {

                    }
                });

    }
}