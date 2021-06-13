package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.UserInfo;
import com.music.musicplayer.musicplayer.repo.FriendsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendsDao friendsDao;

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendServiceImpl.class);

    @Override
    public boolean isFriend(int userId, int friendId) {
        return friendsDao.isFriend(userId, friendId);
    }

    @Override
    public List<UserInfo> getAllMyFriends(int userId) {
        List<Integer> friendsId = new ArrayList<>();
        List<UserInfo> friendsList = new ArrayList<>();
        List<Map<String, Object>> maps = friendsDao.getFrendsUserId(userId);
        maps.forEach(map -> friendsId.add(Integer.parseInt(map.get("fid").toString())));
        LOGGER.info("User Friends fetched successfully for user id {}, friend list {}",userId,friendsId);
        friendsId
                .forEach(fid -> userService.findUserById(fid)
                        .ifPresentOrElse(usr-> friendsList.add(usr),
                                ()-> LOGGER.warn("user id {} not found or disabled",fid) ));
        return friendsList;
    }

    @Override
    public ResponseClass removeFriend(int friendId, String userName) {
        Optional<UserInfo> userNameOpt = userService.findUserByName(userName);
        if(!userNameOpt.isPresent()){
            LOGGER.info("Friend {} does not exist",userName);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("friend "+userName+" does not exist").build();
        }
        int userId = userNameOpt.get().getUid();
        boolean isFriend = isFriend(userId,friendId);
        if(!isFriend){
            LOGGER.info("{} is not friend of user id -> {}",friendId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message(friendId+ " is not friend of user id -> "+userId).build();
        }
        int response = friendsDao.removeFriend(userId, friendId);
        if(response == 0 || response == 1){
            LOGGER.info("{} friend id is removed successfully from user id -> {}",friendId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message(friendId+ " is removed successfully from user id -> "+userId).build();
        }
        LOGGER.error("Error removing friend id {} from user id -> {}",friendId,userId);
        return ResponseClass.builder().queryTime(LocalDateTime.now())
                .message("Error removing friend id"+friendId+" from user = "+userId).build();
    }
    public ResponseClass addFriend(int friendId, String userName){
        Optional<UserInfo> userNameOpt = userService.findUserByName(userName);
        if(!userNameOpt.isPresent()){
            LOGGER.info("Friend {} does not exist",userName);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("friend "+userName+" does not exist").build();
        }
        int userId = userNameOpt.get().getUid();
        boolean isFriend = isFriend(userId,friendId);
        if(isFriend){
            LOGGER.info("{} is already friend of user id -> {} ",friendId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message(friendId+ " is already friend of user id -> "+userId).build();
        }
        int response = friendsDao.addFriend(userId, friendId);
        if(response == 0 || response == 1){
            LOGGER.info("{} is added successfully for user id -> {} ",friendId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message(friendId+ " is added successfully for user id -> "+userId).build();
        }
        LOGGER.error("Error adding friend id {} to user id -> {} ",friendId,userId);
        return ResponseClass.builder().queryTime(LocalDateTime.now())
                .message("Error adding friend id"+friendId+" to user userId = "+userId).build();

    }
}
