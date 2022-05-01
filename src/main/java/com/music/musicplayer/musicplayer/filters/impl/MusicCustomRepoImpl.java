package com.music.musicplayer.musicplayer.filters.impl;

import com.music.musicplayer.musicplayer.dto.SongInfoDto;
import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.filters.MusicCustomRepo;
import com.music.musicplayer.musicplayer.filters.SongSpecification;
import com.music.musicplayer.musicplayer.repo.SongsRepo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MusicCustomRepoImpl implements MusicCustomRepo {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SongsRepo songsRepo;

    @Autowired
    SongSpecification songSpecification;

    @Override
    public List<SongInfo> findByArtistName(String artistName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SongInfo> cq = criteriaBuilder.createQuery(SongInfo.class);
        Root<SongInfo> songInfoRoot = cq.from(SongInfo.class);
        Predicate artistPredicate = criteriaBuilder.equal(songInfoRoot.get("artist"), artistName);
        cq.where(artistPredicate);
        TypedQuery<SongInfo> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @SneakyThrows
    public List<SongInfo> findByFilters(MusicFilter musicFilter) {
        Specification<SongInfo> songsByFilter = songSpecification.getSongsByFilter(musicFilter);
        return songsRepo.findAll(songsByFilter);
    }
}
