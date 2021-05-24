package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.services.FavListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FavListController {

    @Autowired
    FavListService publicFavListService;

    @GetMapping(value = "/api/user/favlist/public/{id}")
    public List<SongInfo> getPubFavList(@PathVariable int id){
        return publicFavListService.getPubFavList(id);
    }
    @GetMapping(value = "/api/user/favlist/private/{id}")
    public List<SongInfo> getPrivFavList(@PathVariable int id){
        return publicFavListService.getPrivFavList(id);
    }
}
