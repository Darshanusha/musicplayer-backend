package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.repo.SongsRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SongServiceImplTest {
    @Value("${common_music_path}")
    String additionalPath;

    @Mock
    SongsRepo songsRepo;

    @InjectMocks
    SongServiceImpl songService;
    int id = 1;

    @Test
    public void getSongById_Should_return_songInfoObject(){
        SongInfo songInfo = new SongInfo();
        songInfo.setMusicLink(additionalPath + "//test");
        songInfo.setArtist("Darshan");
        songInfo.setCount(0);
        songInfo.setEnabled(true);
        songInfo.setMovie("Darshan");
        when(songsRepo.findById(id)).thenReturn(Optional.of(songInfo));
        assertEquals(songInfo,songService.getSongById(id).get());
    }

    @Test
    public void getSongById_Should_return_emptyObject(){
        when(songsRepo.findById(id)).thenReturn(Optional.ofNullable(null));
        assertFalse(songService.getSongById(id).isPresent());
    }

    @Test
    public void postSong_Success(){
        SongInfo songInfo = new SongInfo();
        songInfo.setMusicLink(additionalPath + "//test");
        songInfo.setArtist("Darshan");
        when(songsRepo.saveAndFlush(any(SongInfo.class))).thenReturn(songInfo);
        assertEquals(songInfo,songService.postSong(songInfo));
    }
}