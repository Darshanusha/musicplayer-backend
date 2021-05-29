package com.music.musicplayer.musicplayer.repo;

import com.music.musicplayer.musicplayer.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUidAndIsEnabled(int userId, boolean isEnabled);
}
