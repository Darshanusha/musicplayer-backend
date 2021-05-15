package com.music.musicplayer.musicplayer.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class SongInfoDto {

    int musicId;
    String musicName;
    String artist;
    String movie;
    String musicianName;
    String musicLink;
    boolean isEnabled;
    long count;
    String keywords;
    int stars;
    LocalDateTime creationDate;
    LocalDateTime updateDate;
}
