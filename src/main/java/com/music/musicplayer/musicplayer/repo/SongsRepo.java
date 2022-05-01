package com.music.musicplayer.musicplayer.repo;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.filters.MusicCustomRepo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongsRepo extends JpaRepository<SongInfo, Integer>, JpaSpecificationExecutor<SongInfo> {

    Optional<SongInfo> findAllByMusicIdAndIsEnabled(Integer integer, boolean isEnabled);

}
