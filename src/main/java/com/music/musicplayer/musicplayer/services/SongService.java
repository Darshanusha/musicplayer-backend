package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.SongInfo;

import java.util.Optional;

public interface SongService {
    SongInfo postSong(SongInfo songInfo);
    Optional<SongInfo> getSongById(int id);
    ResponseClass disableSong(int id);
    ResponseClass enableSong(int id);
}
