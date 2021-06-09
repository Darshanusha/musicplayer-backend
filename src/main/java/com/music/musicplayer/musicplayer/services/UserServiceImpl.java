package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.UserInfo;
import com.music.musicplayer.musicplayer.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public Optional<UserInfo> findUserById(int id) {
        return userRepo.findByUidAndIsEnabled(id,true);
    }

    @Override
    public Optional<UserInfo> findUserByName(String name) {
        return userRepo.findByNameAndIsEnabled(name,true);
    }

    @Override
    public boolean isPrincipalUser(Principal principal, int id) {
        Optional<UserInfo> user = findUserById(id);
        return user.isPresent() && principal.getName().equals(user.get().getName());
    }

}
