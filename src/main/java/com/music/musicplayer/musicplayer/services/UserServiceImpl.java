package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.UserInfo;
import com.music.musicplayer.musicplayer.repo.RoleDao;
import com.music.musicplayer.musicplayer.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleDao roleDao;

    @Override
    public Optional<UserInfo> findUserById(int id) {
        return userRepo.findByUidAndIsEnabled(id,true);
    }

    @Override
    public Optional<UserInfo> findUserByName(String name) {
        return userRepo.findByNameAndIsEnabled(name,true);
    }

    @Override
    public Optional<UserInfo> findUserByNameAndDisabledUser(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public ResponseClass disableUser(int uid) {
        Optional<UserInfo> enabledUser = userRepo.findByUidAndIsEnabled(uid, true);
        if (!enabledUser.isPresent()){
            return ResponseClass.builder().message("User Does not exist/disabled")
                    .queryTime(LocalDateTime.now()).build();
        }
        enabledUser.get().setEnabled(false);
        UserInfo userInfo = userRepo.saveAndFlush(enabledUser.get());
        if(!userInfo.isEnabled()){
            return ResponseClass.builder().queryTime(LocalDateTime.now()).message("User disabled successfully").build();
        }
        return ResponseClass.builder().queryTime(LocalDateTime.now()).message("User disable failure").build();
    }

    @Override
    public ResponseClass enableUser(int uid) {
        Optional<UserInfo> enabledUser = userRepo.findByUidAndIsEnabled(uid, false);
        if (!enabledUser.isPresent()){
            return ResponseClass.builder().message("User Does not exist/enabled")
                    .queryTime(LocalDateTime.now()).build();
        }
        enabledUser.get().setEnabled(true);
        UserInfo userInfo = userRepo.saveAndFlush(enabledUser.get());
        if(userInfo.isEnabled()){
            return ResponseClass.builder().queryTime(LocalDateTime.now()).message("User enabled successfully").build();
        }
        return ResponseClass.builder().queryTime(LocalDateTime.now()).message("User enable failure").build();
    }


    @Override
    public boolean isPrincipalUser(Principal principal, int id) {
        Optional<UserInfo> user = findUserById(id);
        return user.isPresent() && principal.getName().equals(user.get().getName());
    }

    @Override
    public UserInfo addUser(UserInfo userInfo){
        Optional<UserInfo> userByName = findUserByNameAndDisabledUser(userInfo.getName());
        if(userByName.isPresent()){
            LOGGER.warn("User {} already exists",userByName.get().getName());
            return new UserInfo();
        }
        UserInfo responseUserInfo = userRepo.saveAndFlush(userInfo);
        roleDao.addUserRoleForId(userInfo.getUid());
        LOGGER.warn("User {} created successfully",responseUserInfo.getName());
        return responseUserInfo;
    }

}
