package com.music.musicplayer.musicplayer.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FriendsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Map<String,Object>> getFrendsUserId(int userId){
        return jdbcTemplate.queryForList("select fid from friends_list where uid = (?)", userId);
    }

}
