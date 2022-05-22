package com.music.musicplayer.musicplayer.filters;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.filters.impl.MusicFilter;

import java.util.List;

public interface MusicCustomRepo {
    List<SongInfo> findByArtistName(String artistName);

    List<SongInfo> findByFilters(MusicFilter musicFilter);
}
