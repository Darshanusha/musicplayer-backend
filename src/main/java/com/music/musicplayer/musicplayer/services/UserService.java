package com.music.musicplayer.musicplayer.services;


import com.music.musicplayer.musicplayer.entity.UserInfo;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    Optional<UserInfo> findUserById(int id);
    Optional<UserInfo> findUserByName(String name);
    boolean isPrincipalUser(Principal principal, int id);
}
