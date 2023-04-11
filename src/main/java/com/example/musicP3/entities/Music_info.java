package com.example.musicP3.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Music_info {
    //constructor
    public Music_info(String music_id, String title, String description) {
        this.music_id = music_id;
        this.title = title;
        this.description = description;
    }
    public Music_info() {

    }
    private String music_id;

    @Column("music_id")
    private String title;

    @Column ("description")
    private String description;

    @Id
    @Column("title")
    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
