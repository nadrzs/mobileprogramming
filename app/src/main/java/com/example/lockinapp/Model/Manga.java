package com.example.lockinapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Manga {

    @SerializedName("top")
    private List<MangaItem> mangaItems;

    public List<MangaItem> getTopMangaItems() {
        return mangaItems;
    }

    public void setMangaItems(List<MangaItem> mangaItems) {
        this.mangaItems = mangaItems;
    }
}
