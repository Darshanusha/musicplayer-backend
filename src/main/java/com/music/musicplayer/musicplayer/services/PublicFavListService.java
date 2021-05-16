package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.FavSong;
import com.music.musicplayer.musicplayer.entity.SongInfo;

import java.util.List;

public interface PublicFavListService {

    List<SongInfo> getPubFavList(int id);
}
