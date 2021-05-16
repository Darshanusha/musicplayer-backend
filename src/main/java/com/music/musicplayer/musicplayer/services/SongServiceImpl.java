package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.repo.SongsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
        if(save.getMusicLink() == null){
            LOGGER.error("Error inserting Song {} ",songInfo);
            return save;
        }
        LOGGER.error("Song id {} successfully added ",songInfo.getMusicId());
        return save;
    }

    public Optional<SongInfo> getSongById(int id) {
        Optional<SongInfo> optSongInfo = songsRepo.findById(id);
        optSongInfo.ifPresentOrElse(this::addPath, ()->songNotFound(id));
        return optSongInfo;
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
