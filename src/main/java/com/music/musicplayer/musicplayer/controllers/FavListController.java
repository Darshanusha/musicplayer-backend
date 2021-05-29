package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.services.FavListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FavListController {

    @Autowired
    FavListService favListService;

    @GetMapping(value = "/user/favlist/public/{id}")
    public List<SongInfo> getPubFavList(@PathVariable int id){
        return favListService.getPubFavList(id);
    }
    @GetMapping(value = "/user/favlist/private/{id}")
    public List<SongInfo> getPrivFavList(@PathVariable int id){
        return favListService.getPrivFavList(id);
    }

}
