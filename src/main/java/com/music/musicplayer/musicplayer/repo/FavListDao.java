package com.music.musicplayer.musicplayer.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
}
