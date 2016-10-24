package com.example.rhxorhkd.android_seoulyeojido.Model;

/**
 * Created by 병윤 on 2016-10-24.
 */

public class VisitedItem {
    String photo;
    String name;
    String cnt;

    public VisitedItem(String photo, String name, String cnt) {
        this.photo = photo;
        this.name = name;
        this.cnt = cnt;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getCnt() {
        return cnt;
    }
}
