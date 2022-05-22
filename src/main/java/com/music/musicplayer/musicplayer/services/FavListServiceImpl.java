package com.music.musicplayer.musicplayer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.musicplayer.musicplayer.dto.ResponseClass;
import com.music.musicplayer.musicplayer.entity.FavSong;
import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.entity.UserInfo;
import com.music.musicplayer.musicplayer.repo.FavListDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FavListServiceImpl implements FavListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FavListServiceImpl.class);

    @Autowired
    FavListDao favListDao;

    @Autowired
    SongService songService;

    @Autowired
    UserService userService;

    @Override
    public List<SongInfo> getPubFavList(int id) {
        List<FavSong> songIds = new ArrayList<>();
        List<Map<String, Object>> maps = favListDao.getPublicFavSongById(id);
        maps.forEach(map -> songIds.add(mapJdbcToFavSong(map)));
        LOGGER.info("getting public fav list id's for user id {}",id);
        return getSongListByIds(songIds);
    }

    @Override
    public List<SongInfo> getPrivFavList(int id) {
        List<FavSong> songIds = new ArrayList<>();
        List<Map<String, Object>> maps = favListDao.getPrivateFavSongById(id);
        maps.forEach(map -> songIds.add(mapJdbcToFavSong(map)));
        LOGGER.info("getting private fav list id's for user id {}",id);
        return getSongListByIds(songIds);
    }


    @Override
    public ResponseClass addSongToPub(Principal principal , int musicId) {
        Optional<UserInfo> userInfo = userService.findUserByName(principal.getName());
        Optional<SongInfo> songInfo = songService.getSongById(musicId);
        if(!songInfo.isPresent()){
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id {} is not present").build();
        }
        if(!userInfo.isPresent()){
            LOGGER.warn("user info is empty");
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("User Does not Exists").build();
        }
        int userId = userInfo.get().getUid();
        if(checkSongInPub(userId,musicId)){
            LOGGER.info("music id {} is already in Public Favourite List of user {}",musicId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music is already in Public Favourite List").build();
        }else {
            int isSuccess = favListDao.addSongtoPub(userId,musicId);
            if(isSuccess==1 || isSuccess == 0){
                LOGGER.info("music id {} added to Public Favourite List of user {}",musicId,userId);
                return ResponseClass.builder().queryTime(LocalDateTime.now())
                        .message("music added to Public Favourite List").build();
            }
            LOGGER.info("music id {} unable to add in Public Favourite List for user {} ",musicId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id "+musicId+" unable to add in Public Favourite List").build();
        }

    }

    @Override
    public ResponseClass addSongToPriv(Principal principal, int musicId) {
        Optional<UserInfo> userInfo = userService.findUserByName(principal.getName());
        Optional<SongInfo> songInfo = songService.getSongById(musicId);
        if(!songInfo.isPresent()){
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id "+musicId+" is not present").build();
        }
        if(!userInfo.isPresent()){
            LOGGER.warn("user info is empty");
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("User Does not Exists").build();
        }
        int userId = userInfo.get().getUid();

        if(checkSongInPriv(userId,musicId)){
            LOGGER.info("music id {} is already in Private Favourite List of user {}",musicId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music is already in Private Favourite List").build();
        }else {
            int isSuccess = favListDao.addSongtoPriv(userId,musicId);
            if(isSuccess==1 || isSuccess == 0){
                LOGGER.info("music id {} added to Private Favourite List of user {}",musicId,userId);
                return ResponseClass.builder().queryTime(LocalDateTime.now())
                        .message("music added to Private Favourite List").build();
            }
            LOGGER.info("music id {} unable to add in Private Favourite List for user {} ",musicId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id "+musicId+" unable to add in Public Favourite List").build();
        }
    }

    @Override
    public boolean checkSongInPub(int userId,int musicId){
        return favListDao.isSongInPub(userId,musicId);
    }

    @Override
    public boolean checkSongInPriv(int userId, int musicId) {
        return favListDao.isSongInPriv(userId,musicId);
    }

    @Override
    public ResponseClass removeSongFromPub(Principal principal, int musicId) {

        Optional<UserInfo> userInfo = userService.findUserByName(principal.getName());
        Optional<SongInfo> songInfo = songService.getSongById(musicId);
        if(!songInfo.isPresent()){
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id {} is not present").build();
        }
        if(!userInfo.isPresent()){
            LOGGER.warn("user info is empty");
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("User Does not Exists").build();
        }
        int userId = userInfo.get().getUid();
        if(checkSongInPub(userId,musicId)){
            int isSuccess = favListDao.removeSongFromPub(userId, musicId);
            if(isSuccess==1 || isSuccess == 0){
                LOGGER.info("music id {} removed from Public Favourite List of user {}",musicId,userId);
                return ResponseClass.builder().queryTime(LocalDateTime.now())
                        .message("music removed from Public Favourite List").build();
            }
            LOGGER.info("music id {} unable to remove from Public Favourite List for user {} ",musicId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id "+musicId+" unable to remove from Public Favourite List").build();

        }
        return ResponseClass.builder().queryTime(LocalDateTime.now())
                .message("music does not exist in Public Favourite List").build();
    }


    @Override
    public ResponseClass removeSongFromPriv(Principal principal, int musicId) {

        Optional<UserInfo> userInfo = userService.findUserByName(principal.getName());
        Optional<SongInfo> songInfo = songService.getSongById(musicId);
        if(!songInfo.isPresent()){
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id {} is not present").build();
        }
        if(!userInfo.isPresent()){
            LOGGER.warn("user info is empty");
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("User Does not Exists").build();
        }
        int userId = userInfo.get().getUid();
        if(checkSongInPriv(userId,musicId)){
            int isSuccess = favListDao.removeSongFromPriv(userId, musicId);
            if(isSuccess==1 || isSuccess == 0){
                LOGGER.info("music id {} removed from Private Favourite List of user {}",musicId,userId);
                return ResponseClass.builder().queryTime(LocalDateTime.now())
                        .message("music removed from Private Favourite List").build();
            }
            LOGGER.info("music id {} unable to remove from Private Favourite List for user {} ",musicId,userId);
            return ResponseClass.builder().queryTime(LocalDateTime.now())
                    .message("music id "+musicId+" unable to remove from Private Favourite List").build();

        }
        return ResponseClass.builder().queryTime(LocalDateTime.now())
                .message("music does not exist in Private Favourite List").build();
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
            e.printStackTrace();
            LOGGER.error("Error mapping to FavSong.class for values {} , check if table user_pub columns are changed",map.values());
        }
        return favSong;
    }
}
