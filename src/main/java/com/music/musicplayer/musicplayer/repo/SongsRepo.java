package com.music.musicplayer.musicplayer.repo;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongsRepo extends JpaRepository<SongInfo, Integer> {
    @Override
    Optional<SongInfo> findById(Integer integer);

}
