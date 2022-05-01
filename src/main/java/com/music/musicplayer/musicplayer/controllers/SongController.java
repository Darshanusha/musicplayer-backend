package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.filters.MusicCustomRepo;
import com.music.musicplayer.musicplayer.filters.impl.MusicFilter;
import com.music.musicplayer.musicplayer.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongService songService;

    @Autowired
    MusicCustomRepo musicCustomRepo;

    @GetMapping(value = "/songs/{id}")
    public SongInfo getSong(@PathVariable int id) {
        return songService.getSongById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Id with %s not found", id)));
    }

    @PostMapping(value = "/songs")
    @PreAuthorize("hasRole('ADMIN')")
    public SongInfo postSong(@RequestBody SongInfo songInfo) {
        return songService.postSong(songInfo);//.orElseThrow(()-> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,String.format("Error while posting %s", songInfo.getMusicName())));
    }

    @GetMapping(value = "/admin/disable/song/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseClass disableSong(@PathVariable int id) {
        return songService.disableSong(id);
    }

    @GetMapping(value = "/admin/enable/song/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseClass enableSong(@PathVariable int id) {
        return songService.enableSong(id);
    }

    @GetMapping(value = "/artist/{name}")
    public List<SongInfo> getSongByArtistName(@PathVariable String name) {
        return musicCustomRepo.findByArtistName(name);
    }

    @GetMapping(value = "/music/filters")
    public List<SongInfo> getSongByFilters(@RequestBody MusicFilter musicFilter){
        return musicCustomRepo.findByFilters(musicFilter);
    }


}
