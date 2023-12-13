package com.bomin.final_2021;

public class PlaylistItem {

    private String title;
    private String youtubeLink;

    public PlaylistItem(String title, String youtubeLink) {
        this.title = title;
        this.youtubeLink = youtubeLink;
    }

    public String getTitle() {
        return title;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }
}