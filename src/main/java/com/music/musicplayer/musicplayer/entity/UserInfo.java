package com.music.musicplayer.musicplayer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "user_table")
public class UserInfo {
    public UserInfo(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int uid;

    @Column(name = "uname")
    String name;

    @Column(name = "u_auth")
    String userAuth;

    @Column(name = "pwd")
    String password;

    @Column(name = "isenabled")
    boolean isEnabled;

    @Column(name = "creation_date")
    LocalDateTime creationDate;


    @Column(name = "update_date")
    LocalDateTime updateDate;

    Date dob;

    @Column(name = "phone_no")
    String phoneNumber;

    String email;

    String state;

    String country;

}
