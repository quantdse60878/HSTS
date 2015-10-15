package com.example.quyhkse61160.hstsapp.Classes;

/**
 * Created by Man Huynh Khuong on 10/14/2015.
 */
public class NavDrawerItem {
    private String title;
    private int icon;
    private boolean notify = false;

    public NavDrawerItem(){}

    public NavDrawerItem(String title, int icon, boolean notify){
        this.title = title;
        this.icon = icon;
        this.notify = notify;
    }
    public NavDrawerItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean count) {
        this.notify = count;
    }
}
