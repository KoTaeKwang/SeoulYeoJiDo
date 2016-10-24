package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

/**
 * Created by YJ on 2016-10-25.
 */

public class DetailReview {
    private String username;
    private String review;
    private int image;

    public String getTitle() {
        return username;
    }

    public void setTitle(String username) {
        this.username = username;
    }

    public String getArtist() {
        return review;
    }

    public void setArtist(String review) {
        this.review = review;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}