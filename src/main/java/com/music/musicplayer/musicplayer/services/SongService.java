package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.SongInfo;


import java.util.Optional;

public interface SongService {
    public SongInfo postSong(SongInfo songInfo);
    public Optional<SongInfo> getSongById(int id);
}
