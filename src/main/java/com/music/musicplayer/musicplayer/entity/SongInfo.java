package com.music.musicplayer.musicplayer.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Builder
@Table(name = "songs_details")
public class SongInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "musicid")
    int musicId;

    @Column(name = "music_name")
    String musicName;

    String artist;

    String movie;

    @Column(name = "m_name")
    String musicianName;

    @Column(name = "m_link")
    String musicLink;

    @Column(name = "isenabled")
    boolean isEnabled;
    long count;
    String keywords;
    int stars;
}
