package com.example.lockinapp.Model;

import com.google.gson.annotations.SerializedName;

public class MangaItem {

    @SerializedName("score")
    private double manga_score;

    @SerializedName("end_date")
    private String manga_end_date;

    @SerializedName("image_url")
    private String manga_image_url;

    @SerializedName("start_date")
    private String manga_start_date;

    @SerializedName("members")
    private int manga_members;

    @SerializedName("rank")
    private int manga_rank;

    @SerializedName("mal_id")
    private int manga_malId;

    @SerializedName("title")
    private String manga_title;

    @SerializedName("type")
    private String manga_type;

    @SerializedName("url")
    private String manga_url;

    @SerializedName("volumes")
    private int manga_volumes;

    @Override
    public String toString() {
        return "MangaItem{" +
                "manga_score=" + manga_score +
                ", manga_end_date='" + manga_end_date + '\'' +
                ", manga_image_url='" + manga_image_url + '\'' +
                ", manga_start_date='" + manga_start_date + '\'' +
                ", manga_members=" + manga_members +
                ", manga_rank=" + manga_rank +
                ", manga_malId=" + manga_malId +
                ", manga_title='" + manga_title + '\'' +
                ", manga_type='" + manga_type + '\'' +
                ", manga_url='" + manga_url + '\'' +
                ", manga_volumes=" + manga_volumes +
                '}';
    }

    public double getManga_Score() {
        return manga_score;
    }

    public String getManga_End_date() {
        return manga_end_date;
    }

    public String getManga_image_url() {
        return manga_image_url;
    }

    public String getManga_Start_date() {
        return manga_start_date;
    }

    public int getManga_Members() {
        return manga_members;
    }

    public int getManga_Rank() {
        return manga_rank;
    }

    public int getManga_MalId() {
        return manga_malId;
    }

    public String getManga_Title() {
        return manga_title;
    }

    public void setManga_Title(String title) {
        this.manga_title = title;
    }

    public String getManga_Type() {
        return manga_type;
    }

    public void setManga_Type(String type) {
        this.manga_type = type;
    }

    public String getManga_Url() {
        return manga_url;
    }

    public int getManga_Volumes() {
        return manga_volumes;
    }
}