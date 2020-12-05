package com.example.lockinapp.Model;

import com.google.gson.annotations.SerializedName;

public class MangaItemDetail {

    public MangaItemDetail() {

    }

    public MangaItemDetail(String synopsis, String status) {
        this.synopsis = synopsis;
        this.status = status;
    }

    @SerializedName("synopsis")
    private String synopsis;

    @SerializedName("status")
    private String status;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
