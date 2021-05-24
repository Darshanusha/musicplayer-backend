package com.music.musicplayer.musicplayer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.musicplayer.musicplayer.entity.FavSong;
import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.repo.FavListDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FavListServiceImpl implements FavListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FavListServiceImpl.class);

    @Autowired
    FavListDao favListDao;

    @Autowired
    SongService songService;

    List<FavSong> songIds = new ArrayList<>();

    @Override
    public List<SongInfo> getPubFavList(int id) {
        List<Map<String, Object>> maps = favListDao.getPublicFavSongById(id);
        maps.forEach(map -> songIds.add(mapJdbcToFavSong(map)));
        LOGGER.info("getting public fav list id's for user id {}",id);
        return getSongListByIds(songIds);
    }

    @Override
    public List<SongInfo> getPrivFavList(int id) {
        List<Map<String, Object>> maps = favListDao.getPrivateFavSongById(id);
        maps.forEach(map -> songIds.add(mapJdbcToFavSong(map)));
        LOGGER.info("getting private fav list id's for user id {}",id);
        return getSongListByIds(songIds);
    }

    private List<SongInfo> getSongListByIds(List<FavSong> songIds) {
        List<SongInfo> favSongs = new ArrayList<>();
        for (FavSong favSong: songIds){
            Optional<SongInfo> songById = songService.getSongById(favSong.getMusicId());
            if(songById.isPresent()){
                favSongs.add(songById.get());
            }else{
                LOGGER.warn ("Song with id {} not found", favSong.getMusicId());
            }
        }
        LOGGER.info("getting songs for Music id {}",songIds);
        return favSongs;
    }

    private FavSong mapJdbcToFavSong(Map<String, Object> map) {
        final ObjectMapper mapper = new ObjectMapper();
        FavSong favSong = null ;
        try{
             favSong = mapper.convertValue(map, FavSong.class);
             LOGGER.info("Mapping to FavSong.class success for id {} ",favSong.getMusicId());
        }catch (Exception e){
            LOGGER.error("Error mapping to FavSong.class for values {} , check if table user_pub columns are changed",map.values());
        }
        return favSong;
    }
}
