package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.UserInfo;
import com.music.musicplayer.musicplayer.repo.FriendsDao;
import com.music.musicplayer.musicplayer.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendsDao friendsDao;

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendServiceImpl.class);

    @Override
    public boolean isFriend(int userId, int friendId) {
        return false;
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

}
