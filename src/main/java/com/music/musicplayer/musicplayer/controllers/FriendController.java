package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.entity.UserInfo;
import com.music.musicplayer.musicplayer.services.FavListService;
import com.music.musicplayer.musicplayer.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @Autowired
    FavListService favListService;

    @GetMapping(value = "/user/friendlist/{userId}")
    public List<UserInfo> getMyFriends(@PathVariable int userId){
        return friendService.getAllMyFriends(userId);
    }
    @GetMapping(value = "/user/friendlist/songs/{friendId}")
    public List<SongInfo> getFriendSongs(@PathVariable int friendId){
        return favListService.getPubFavList(friendId);
    }
    @GetMapping(value = "/user/removeFriend/{userId}")
    public ResponseClass addRemove(@PathVariable int userId, Principal principal){
        return friendService.removeFriend(userId, principal.getName());
    }
    @GetMapping(value = "/user/addFriend/{userId}")
    public ResponseClass addFriend(@PathVariable int userId, Principal principal){
        return friendService.addFriend(userId, principal.getName());
    }
}
