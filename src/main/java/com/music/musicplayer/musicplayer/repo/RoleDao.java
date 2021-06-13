package com.music.musicplayer.musicplayer.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {

    public static int USER_ROLE = 2;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addUserRoleForId(int uid){
        return jdbcTemplate.update("INSERT into user_role(uid,role_id) values (? ,?) ",uid,USER_ROLE);
    }
}
