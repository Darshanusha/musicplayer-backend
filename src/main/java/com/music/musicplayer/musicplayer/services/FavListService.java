package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.FavSong;
import com.music.musicplayer.musicplayer.entity.SongInfo;

import java.security.Principal;
import java.util.List;

public interface FavListService {

    List<SongInfo> getPubFavList(int id);
    List<SongInfo> getPrivFavList(int id);
    ResponseClass addSongToPub(Principal principal, int MusicId);
    ResponseClass addSongToPriv(Principal principal, int MusicId);
    boolean checkSongInPub(int userId,int musicId);
    boolean checkSongInPriv(int userId,int musicId);
    ResponseClass removeSongFromPriv(Principal principal, int musicId);
    ResponseClass removeSongFromPub(Principal principal, int musicId);
}
