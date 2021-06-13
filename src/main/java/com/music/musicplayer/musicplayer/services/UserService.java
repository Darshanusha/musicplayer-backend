package com.music.musicplayer.musicplayer.services;


import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.UserInfo;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    Optional<UserInfo> findUserById(int id);
    Optional<UserInfo> findUserByName(String name);
    boolean isPrincipalUser(Principal principal, int id);
    UserInfo addUser(UserInfo userInfo);
    Optional<UserInfo> findUserByNameAndDisabledUser(String name);
    ResponseClass disableUser(int uid);
    ResponseClass enableUser(int uid);
}
