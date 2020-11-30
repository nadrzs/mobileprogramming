package com.example.lockinapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Anime {

    @SerializedName("top")
    private List<AnimItem> top;

    public List<AnimItem> getTopAnime(){
        return top;
    }

    public void setTop(List<AnimItem> top){
        this.top = top;
    }


}
