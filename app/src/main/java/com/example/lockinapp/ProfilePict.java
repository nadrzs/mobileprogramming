package com.example.lockinapp;

public class ProfilePict {
    private String imageUrl;

    public ProfilePict(){

    }

    public ProfilePict(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
