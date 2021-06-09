package com.music.musicplayer.musicplayer.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class FavListDao {//extends JpaRepository<FavSong, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> getPublicFavSongById(int id){
        return jdbcTemplate.queryForList("select * from user_pub where uid = (?)", id);
    }

    public List<Map<String, Object>> getPrivateFavSongById(int id){
        return jdbcTemplate.queryForList("select * from user_priv where uid = (?)", id);
    }

    public boolean isSongInPub(int userId, int musicId){
        String checkExistsQuery = "SELECT EXISTS(SELECT * from user_pub where uid = (?) and musicid = (?))";
        return jdbcTemplate.queryForObject(checkExistsQuery, new Object[]{userId, musicId}, Boolean.class);
    }

    public int addSongtoPub(int userId, int musicId){
        return jdbcTemplate.update("INSERT INTO user_pub (uid, musicid, isstared) VALUES((?), (?), (?))", userId, musicId, false);
    }

    public boolean isSongInPriv(int userId, int musicId) {
        String checkExistsQuery = "SELECT EXISTS(SELECT * from user_priv where uid = (?) and musicid = (?))";
        return jdbcTemplate.queryForObject(checkExistsQuery, new Object[]{userId, musicId}, Boolean.class);
    }

    public int addSongtoPriv(int userId, int musicId) {
        return jdbcTemplate.update("INSERT INTO user_priv (uid, musicid, isstared) VALUES((?), (?), (?))", userId, musicId, false);
    }

    public int removeSongFromPub(int uid, int musicId) {
        return jdbcTemplate.update("DELETE from user_pub where uid = ? and musicid = ?",uid,musicId);
    }

    public int removeSongFromPriv(int uid, int musicId) {
        return jdbcTemplate.update("DELETE from user_priv where uid = ? and musicid = ?",uid,musicId);
    }
}
