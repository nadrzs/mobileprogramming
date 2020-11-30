package com.example.lockinapp.Data;

import com.example.lockinapp.Model.Anime;
import com.example.lockinapp.Model.Manga;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    // Untuk mengambil data top anime, dengan page adalah 1
    @GET("/v3/top/anime/{page}")
    Call<Anime> getAllAnime(@Path("page") int page);

    // Untuk mengambil data top mange, dengan page adalah 1
    @GET("/v3/top/manga/{manga_page}")
    Call<Manga> getAllManga(@Path("manga_page") int manga_page);

}
