package com.music.musicplayer.musicplayer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FavSong {
    @JsonProperty("pub_id")
    int pubId;
    int uid;
    @JsonProperty("musicid")
    int musicId;
    @JsonProperty("isstared")
    boolean isStared;
}
