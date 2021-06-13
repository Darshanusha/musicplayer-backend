package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.repo.SongsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongServiceImpl.class);

    @Value("${common_music_path}")
    String additionalPath;

    @Autowired
    SongsRepo songsRepo;

    @Override
    public SongInfo postSong(SongInfo songInfo) {
        SongInfo save = songsRepo.saveAndFlush(songInfo);
        if(save == null){
            LOGGER.error("Error inserting Song {} ",songInfo);
            return save;
        }
        LOGGER.error("Song id {} successfully added ",songInfo.getMusicId());
        return save;
    }

    public Optional<SongInfo> getSongById(int id) {
        Optional<SongInfo> optSongInfo = songsRepo.findAllByMusicIdAndIsEnabled(id,true);
        optSongInfo.ifPresentOrElse(this::addPath, ()->songNotFound(id));
        return optSongInfo;
    }

    @Override
    public ResponseClass disableSong(int id) {
        Optional<SongInfo> songInfo = songsRepo.findAllByMusicIdAndIsEnabled(id,true);
        if(!songInfo.isPresent()){
            LOGGER.info("Song id {} not found/disabled",id);
            return ResponseClass.builder().queryTime(LocalDateTime.now()).message("song id not present/disabled")
                    .build();
        }
        songInfo.get().setEnabled(false);
        SongInfo responseSongInfo = songsRepo.saveAndFlush(songInfo.get());
        if(responseSongInfo.isEnabled()){
            LOGGER.info("Song id {} disabling failed",id);
            return ResponseClass.builder().queryTime(LocalDateTime.now()).message("song id "+id+" disabling failed").build();
        }
        LOGGER.info("Song id {} disabling success",id);
        return ResponseClass.builder().queryTime(LocalDateTime.now()).message("song id "+id+" disabling success").build();
    }

    @Override
    public ResponseClass enableSong(int id) {
        Optional<SongInfo> songInfo = songsRepo.findAllByMusicIdAndIsEnabled(id,false);
        if(!songInfo.isPresent()){
            LOGGER.info("Song id {} not found/enabled",id);
            return ResponseClass.builder().queryTime(LocalDateTime.now()).message("song id not present/enabled")
                    .build();
        }
        songInfo.get().setEnabled(true);
        SongInfo responseSongInfo = songsRepo.saveAndFlush(songInfo.get());
        if(!responseSongInfo.isEnabled()){
            LOGGER.info("Song id {} enabling failed",id);
            return ResponseClass.builder().queryTime(LocalDateTime.now()).message("song id "+id+" enabling failed").build();
        }
        LOGGER.info("Song id {} disabling success",id);
        return ResponseClass.builder().queryTime(LocalDateTime.now()).message("song id "+id+" enabling success").build();
    }

    private void songNotFound(int id){
        LOGGER.error("Song id {} not found",id);
    }

    /*

    TODO: if needed SongInfoDto use below
    private SongInfoDto convertToSongInfoDto(SongInfo songInfo){
        SongInfoDto songInfoDto = new SongInfoDto();
        songInfoDto.setMusicId(songInfo.getMusicId());
        songInfoDto.setMusicName(songInfo.getMusicName());
        songInfoDto.setArtist(songInfo.getArtist());
        songInfoDto.setMovie(songInfoDto.getMovie());
        songInfoDto.setMusicName(songInfo.getMusicName());
        songInfoDto.setMusicLink(songInfo.getMusicLink());
        songInfoDto.setEnabled(songInfo.isEnabled());
        songInfoDto.setCount(songInfo.getCount());
        songInfoDto.setKeywords(songInfo.getKeywords());
        songInfoDto.setStars(songInfo.getStars());
        songInfoDto.setCreationDate(songInfo.getCreationDate());
        songInfoDto.setUpdateDate(songInfo.getUpdateDate());
        return songInfoDto;
    }
     */

    private void addPath(SongInfo songInfo) {
         songInfo.setMusicLink(additionalPath + songInfo.getMusicLink());
         LOGGER.info("Song id {} found",songInfo.getMusicId());
    }
}
