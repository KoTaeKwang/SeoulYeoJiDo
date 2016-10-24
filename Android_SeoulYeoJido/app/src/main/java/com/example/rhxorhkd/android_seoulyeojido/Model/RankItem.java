package com.example.rhxorhkd.android_seoulyeojido.Model;

/**
 * Created by 병윤 on 2016-10-24.
 */

public class RankItem {
    String rank;
    String nickname;
    String chk_cnt;
    String ect;
    int img;

    public RankItem(String rank, String nickname, String chk_cnt, String ect, int img) {
        this.rank = rank;
        this.nickname = nickname;
        this.chk_cnt = chk_cnt;
        this.ect = ect;
        this.img = img;
    }

    public String getRank() {
        return rank;
    }

    public String getNickname() {
        return nickname;
    }

    public String getChk_cnt() {
        return chk_cnt;
    }

    public String getEct() {
        return ect;
    }

    public int getImg() {
        return img;
    }
}
