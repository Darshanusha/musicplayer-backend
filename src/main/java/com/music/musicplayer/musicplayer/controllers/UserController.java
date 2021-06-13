package com.music.musicplayer.musicplayer.controllers;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.UserInfo;
import com.music.musicplayer.musicplayer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/myinfo")
    public UserInfo getUserInfo(Principal principal){
        return userService.findUserByName(principal.getName()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }

    @PostMapping("/user/signup")
    @PreAuthorize("permitAll()")
    public UserInfo addUser(@RequestBody UserInfo userInfo){
        UserInfo responseUserInfo = userService.addUser(userInfo);
        if(responseUserInfo.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User already exists, please use different username");
        }
        else {
            return responseUserInfo;
        }
    }

    @GetMapping("/admin/disable/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseClass disableUser(@PathVariable int id){
        return userService.disableUser(id);
    }

    @GetMapping("/admin/enable/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseClass enableUser(@PathVariable int id){
        return userService.enableUser(id);
    }
}
