package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.services.FavListService;
import com.music.musicplayer.musicplayer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class FavListController {

    @Autowired
    FavListService favListService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/favlist/public/{id}")
    public ResponseEntity<List<SongInfo>> getPubFavList(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(favListService.getPubFavList(id));
    }
    @GetMapping(value = "/user/favlist/private/{id}")
    public ResponseEntity getPrivFavList(@PathVariable int id, Principal principal){
        if(userService.isPrincipalUser(principal,id)){
            return ResponseEntity.status(HttpStatus.OK).body(favListService.getPrivFavList(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }

    @GetMapping(value = "/user/addpub/{musicId}" )
    public ResponseEntity<ResponseClass> addSongToPub(@PathVariable int musicId, Principal principal){
        return ResponseEntity.status(HttpStatus.OK).body(favListService.addSongToPub(principal,musicId));
    }

    @GetMapping(value = "/user/addpriv/{musicId}" )
    public ResponseEntity<ResponseClass> addSongToPriv(@PathVariable int musicId, Principal principal){
        return ResponseEntity.status(HttpStatus.OK).body(favListService.addSongToPriv(principal,musicId));
    }

    @GetMapping(value = "/user/removepriv/{musicId}" )
    public ResponseEntity<ResponseClass> removeSongFromPriv(@PathVariable int musicId, Principal principal){
        return ResponseEntity.status(HttpStatus.OK).body(favListService.removeSongFromPriv(principal,musicId));
    }

    @GetMapping(value = "/user/removepub/{musicId}" )
    public ResponseEntity<ResponseClass> removeSongFromPub(@PathVariable int musicId, Principal principal){
        return ResponseEntity.status(HttpStatus.OK).body(favListService.removeSongFromPub(principal,musicId));
    }

}
