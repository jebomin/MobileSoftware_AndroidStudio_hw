package com.bomin.myseventhapp;

public class Schools {
    String school_name;
    int image_id;
    String school_url;

    public Schools(int id, String name, String url){
        this.image_id = id;
        this.school_name = name;
        this.school_url = url;
    }

    public String getSchool_name(){
        return school_name;
    }

    public int getImage_id(){
        return image_id;
    }

    public String getURL(){
        return school_url;
    }
}
