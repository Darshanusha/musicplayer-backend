package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.UserInfo;

import java.util.List;

public interface FriendService {
    boolean isFriend(int userId,int friendId);
    List<UserInfo> getAllMyFriends(int userId);
    ResponseClass removeFriend(int userId, String name);
    ResponseClass addFriend(int userId, String friendName);
}
