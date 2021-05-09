package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.repo.SongsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {

    @Autowired
    SongsRepo songsRepo;

    public Optional<SongInfo> getSongById(int id) {
        return songsRepo.findById(id);
    }
}
