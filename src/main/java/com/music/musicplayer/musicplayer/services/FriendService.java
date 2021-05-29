package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.UserInfo;

import java.util.List;

public interface FriendService {
    boolean isFriend(int userId,int friendId);
    List<UserInfo> getAllMyFriends(int userId);
}
