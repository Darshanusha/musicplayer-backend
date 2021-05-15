package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping(value = "/api/songs/{id}")
    public SongInfo getSong(@PathVariable int id){
        return songService.getSongById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Id with %s not found",id)));
    }

    @PostMapping(value = "/api/songs")
    public SongInfo postSong(@RequestBody SongInfo songInfo ){
        return songService.postSong(songInfo);//.orElseThrow(()-> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,String.format("Error while posting %s", songInfo.getMusicName())));
    }

}
