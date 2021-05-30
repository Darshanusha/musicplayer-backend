package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.services.FavListService;
import com.music.musicplayer.musicplayer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
public class FavListController {

    @Autowired
    FavListService favListService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/favlist/public/{id}")
    public List<SongInfo> getPubFavList(@PathVariable int id){
        return favListService.getPubFavList(id);
    }
    @GetMapping(value = "/user/favlist/private/{id}")
    public List<SongInfo> getPrivFavList(@PathVariable int id, Principal principal){
        if(userService.isPrincipalUser(principal,id)){
            return favListService.getPrivFavList(id);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,String.format("Id %s and the principal %s do not match",id,principal));
    }

}
