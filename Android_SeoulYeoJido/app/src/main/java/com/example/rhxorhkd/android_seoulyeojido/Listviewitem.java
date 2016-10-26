package com.example.rhxorhkd.android_seoulyeojido;

/**
 * Created by rhxorhkd on 2016-10-15.
 */

public class Listviewitem {
    private String icon;
    private String name;
    private String subname;
    private String reviewcount;
    private String thirdname;
    private String fourthname;
    private int hearticon;


    public String getIcon() {
        return icon;
    }

    public String getSubname() {
        return subname;
    }

    public int getHearticon() {
        return hearticon;
    }

    public String getThirdname() {
        return thirdname;
    }

    public String getReviewcount() {
        return reviewcount;
    }

    public String getFourthname() {
        return fourthname;
    }

    public String getName() {
        return name;
    }
    public Listviewitem(String icon,String name,String subname, String reviewcount,String thirdname, String fourthname,int hearticon){
        this.icon=icon;
        this.name=name;
        this.subname=subname;
        this.thirdname=thirdname;
        this.reviewcount=reviewcount;
        this.fourthname=fourthname;
        this.hearticon=hearticon;
    }

}
