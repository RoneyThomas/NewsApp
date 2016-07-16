package me.roneythomas.newsapp;

import android.net.Uri;

public class News {
    private String title;
    private String sectionName;
    private Uri link;

    News(String title, String sectionName, String link) {
        this.title = title;
        setSectionName(sectionName);
        setLink(link);
    }

    public String getTitle() {
        return title;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Uri getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = Uri.parse(link);
    }
}
