package com.music.musicplayer.musicplayer.repo;

import org.springframework.beans.factory.annotation.Autowired;
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
    public int removeFriend(int userId, int fid) {
        return jdbcTemplate.update("delete from friends_list where uid = ? and fid = ? ",userId,fid);
    }
    public int addFriend(int userId, int fid) {
        return jdbcTemplate.update("insert into friends_list (uid,fid) values( ? , ? ) ",userId,fid);
    }
    public boolean isFriend(int userId, int friendId){
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT * from friends_list where uid = (?) and fid = (?))", new Object[]{userId,friendId}, Boolean.class );
    }
}