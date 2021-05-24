package com.music.musicplayer.musicplayer.services;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.repo.FavListDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class FavListServiceImplTest {

    @Value("${common_music_path}")
    String additionalPath;

    @Mock
    FavListDao favListDao;

    @Mock
    SongService songService;

    @InjectMocks
    FavListServiceImpl favListService;

    int id = 1;
    List<Map<String, Object>> maps = new ArrayList<>();
    Map<String, Object> map= new HashMap<>();

    @Test
    public void getPubFavList_Should_Return_ListOf_SongInfo(){
        map.put("pub_id","1");
        map.put("uid", "1");
        map.put("musicid","1");
        map.put("isstared","false");
        maps.add(map);
        SongInfo songInfo = new SongInfo();
        songInfo.setMusicLink(additionalPath + "//test");
        songInfo.setArtist("Darshan");
        songInfo.setCount(0);
        songInfo.setEnabled(true);
        songInfo.setMovie("Darshan");
        List<SongInfo> favSongs = new ArrayList<>();
        favSongs.add(songInfo);
        when(favListDao.getPublicFavSongById(id)).thenReturn(maps);
        when(songService.getSongById(id)).thenReturn(Optional.of(songInfo));
        assertEquals(favSongs,favListService.getPubFavList(id));
    }

    @Test
    public void getPrivFavList_Should_Return_ListOf_SongInfo(){
        map.put("pub_id","1");
        map.put("uid", "1");
        map.put("musicid","1");
        map.put("isstared","false");
        maps.add(map);
        SongInfo songInfo = new SongInfo();
        songInfo.setMusicLink(additionalPath + "//test");
        songInfo.setArtist("Darshan");
        songInfo.setCount(0);
        songInfo.setEnabled(true);
        songInfo.setMovie("Darshan");
        List<SongInfo> favSongs = new ArrayList<>();
        favSongs.add(songInfo);
        when(favListDao.getPrivateFavSongById(id)).thenReturn(maps);
        when(songService.getSongById(id)).thenReturn(Optional.of(songInfo));
        assertEquals(favSongs,favListService.getPrivFavList(id));
    }

    @Test
    public void getPubFavList_Should_Throw_Exception(){
        map.put("pubId","1");
        map.put("uid", "1");
        map.put("musicid","1");
        map.put("isstared","false");
        maps.add(map);
        SongInfo songInfo = new SongInfo();
        songInfo.setMusicLink(additionalPath + "//test");
        songInfo.setArtist("Darshan");
        songInfo.setCount(0);
        songInfo.setEnabled(true);
        songInfo.setMovie("Darshan");
        when(favListDao.getPublicFavSongById(id)).thenReturn(maps);
        when(songService.getSongById(id)).thenReturn(Optional.of(songInfo));
        assertThrows(Exception.class,()->favListService.getPubFavList(id));
    }

    @Test
    public void getPubFavList_Should_return_Empty_SongList_DueTo_Song_NotFound_By_Id(){
        map.put("pub_id","1");
        map.put("uid", "1");
        map.put("musicid","1");
        map.put("isstared","false");
        maps.add(map);
        SongInfo songInfo = new SongInfo();
        songInfo.setMusicLink(additionalPath + "//test");
        songInfo.setArtist("Darshan");
        songInfo.setCount(0);
        songInfo.setEnabled(true);
        songInfo.setMovie("Darshan");
        when(favListDao.getPublicFavSongById(id)).thenReturn(maps);
        when(songService.getSongById(id)).thenReturn(Optional.ofNullable(null));
        assertEquals(new ArrayList<>(),favListService.getPubFavList(id));
    }
}