package com.music.musicplayer.musicplayer.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "songs_details")
public class SongInfo {

     public SongInfo(int id,String musicName){
       this.musicId = musicId;
        this.musicName = musicName;
    }

    public SongInfo(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "musicid")
    public int musicId;

    @Column(name = "music_name")
    public String musicName;

    String artist;

    String movie;

    @Column(name = "release_date")
    Date releaseDate;

    @Column(name = "m_name")
    String musicianName;

    @Column(name = "m_link")
    String musicLink;

    @Column(name = "isenabled")
    boolean isEnabled;
    long count;
    String keywords;
    int stars;

    @Column(name = "creation_date")
    @CreationTimestamp
    LocalDateTime creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    LocalDateTime updateDate;


}
