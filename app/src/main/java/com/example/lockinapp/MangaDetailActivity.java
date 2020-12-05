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
import com.example.lockinapp.Model.MangaItemDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangaDetailActivity extends AppCompatActivity {

    ImageView iv_image;
    TextView tv_manga_title, tv_rating, tv_synopsis, tv_status;
    int manga_mal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_detail);

        iv_image = findViewById(R.id.MangaImageDetail);
        tv_manga_title = findViewById(R.id.MangaTitleDetail);
        tv_rating = findViewById(R.id.MangaRatingDetail);
        tv_synopsis = findViewById(R.id.MangaSynopsis);
        tv_status = findViewById(R.id.MangaStatusDetail);

        //Mengambil data

        final Intent intent = getIntent();

        //Data BackEnd
        manga_mal_id =  intent.getExtras().getInt("MANGA_MAL");

        //Data UI
        byte[] bytes = intent.getByteArrayExtra("MANGA_IMAGE_DETAIL");
        String mangaTitle = intent.getStringExtra("MANGA_TITLE_DETAIL");
        double score = intent.getExtras().getDouble("MANGA_SCORE_DETAIL");

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        String scoreString = Double.toString(score);

        iv_image.setImageBitmap(bitmap);
        tv_manga_title.setText(mangaTitle);
        tv_rating.setText(scoreString);
        ResponseData(manga_mal_id);

    }

    private void ResponseData(int manga_mal_id) {
        ApiService apiService = DataRetrofit.getData().create(ApiService.class);
        apiService.getMangaDetail(manga_mal_id)
                .enqueue(new Callback<MangaItemDetail>() {
                    @Override
                    public void onResponse(Call<MangaItemDetail> call, Response<MangaItemDetail> response) {
                        String synopsis = response.body().getSynopsis();
                        String status = response.body().getStatus();

                        if(!synopsis.isEmpty()){
                            tv_synopsis.setText(synopsis);
                            tv_status.setText(status);
                        }else{
                            Toast.makeText(MangaDetailActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MangaItemDetail> call, Throwable t) {

                    }
                });
    }
}