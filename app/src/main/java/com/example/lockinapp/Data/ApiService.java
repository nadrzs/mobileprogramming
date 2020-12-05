package com.example.lockinapp.Data;

import com.example.lockinapp.Model.Anime;
import com.example.lockinapp.Model.AnimeItemDetail;
import com.example.lockinapp.Model.Manga;
import com.example.lockinapp.Model.MangaItemDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    // Untuk mengambil data top anime, dengan page adalah 1
    @GET("/v3/top/anime/{page}")
    Call<Anime> getAllAnime(@Path("page") int page);

    @GET("/v3/anime/{anime_id}")
    Call<AnimeItemDetail> getAnimeDetail(@Path("anime_id") int anime_id);

    @GET("/v3/top/anime/1/airing")
    Call<Anime> getTopAiring();

    @GET("/v3/top/anime/1/movie")
    Call<Anime> getTopMovie();

    @GET("/v3/top/anime/1/tv")
    Call<Anime> getTopTv();

    @GET("/v3/top/anime/1/upcoming")
    Call<Anime> getTopUpcoming();



    // Untuk mengambil data top mange, dengan page adalah 1
    @GET("/v3/top/manga/{manga_page}")
    Call<Manga> getAllManga(@Path("manga_page") int manga_page);

    @GET("/v3/manga/{manga_id}")
    Call<MangaItemDetail> getMangaDetail(@Path("manga_id") int manga_id);

    @GET("/v3/top/manga/1/novels")
    Call<Manga> getTopNovels();

    @GET("/v3/top/manga/1/oneshots")
    Call<Manga> getTopOneshots();

    @GET("/v3/top/manga/1/favorite")
    Call<Manga> getFavorite();


}
