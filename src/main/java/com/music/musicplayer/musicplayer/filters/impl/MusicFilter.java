package com.music.musicplayer.musicplayer.filters.impl;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MusicFilter {
    private int musicid;
    private String musicName;
    private String artist;
    private String movie;
    private String mName;
    private boolean isenabled;
    private int count;
    private int stars;
    private String keywords;
    private LocalDate equalsReleaseDate;
    private LocalDate greaterReleaseDate;
    private LocalDate lessReleaseDate;
}
