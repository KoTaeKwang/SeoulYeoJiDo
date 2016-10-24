package com.example.rhxorhkd.android_seoulyeojido;

/**
 * Created by rhxorhkd on 2016-10-15.
 */

public class Listviewitem {
    private int icon;
    private String name;
    private String subname;
    private String thirdname;
    private int hearticon;


    public int getIcon() {
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

    public String getName() {
        return name;
    }
    public Listviewitem(int icon,String name,String subname, String thirdname, int hearticon){
        this.icon=icon;
        this.name=name;
        this.subname=subname;
        this.thirdname=thirdname;
        this.hearticon=hearticon;
    }

}
