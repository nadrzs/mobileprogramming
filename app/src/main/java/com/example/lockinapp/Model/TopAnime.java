package com.example.lockinapp.Model;

public class TopAnime {

    int id;
    String top_type;

    public TopAnime() {

    }

    public TopAnime(int id, String top_type) {
        this.id = id;
        this.top_type = top_type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTop_type() {
        return top_type;
    }

    public void setTop_type(String top_type) {
        this.top_type = top_type;
    }

}
