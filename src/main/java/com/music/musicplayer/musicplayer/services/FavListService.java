package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.FavSong;
import com.music.musicplayer.musicplayer.entity.SongInfo;

import java.util.List;

public interface FavListService {

    List<SongInfo> getPubFavList(int id);
    List<SongInfo> getPrivFavList(int id);

}
