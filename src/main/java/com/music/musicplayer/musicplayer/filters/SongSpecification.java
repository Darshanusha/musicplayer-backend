package com.music.musicplayer.musicplayer.filters;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import com.music.musicplayer.musicplayer.filters.impl.MusicFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SongSpecification {
    public static Specification<SongInfo> byMusicId(int id) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id));
    }

    public static Specification<SongInfo> byMusicNameLike(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("musicName"), "%" + name + "%"));
    }

    public static Specification<SongInfo> byMusicArtist(String artist) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("artist"), artist));
    }

    public static Specification<SongInfo> byMovie(String movie) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("movie"), movie));
    }

    public static Specification<SongInfo> byMusicianName(String musicianName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("mName"), musicianName));
    }


    public Specification<SongInfo> getSongsByFilter(MusicFilter musicFilter) {

        List<Predicate> predicates = new ArrayList<>();

        return new Specification<SongInfo>() {
            @Override
            public Predicate toPredicate(Root<SongInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                log.info("------------------start search----------------------------");
                if (musicFilter.getArtist() != null && !musicFilter.getArtist().isEmpty()) {
                    log.info("searing by artist name eq \"{}\"", musicFilter.getArtist());
                    predicates.add(criteriaBuilder.equal(root.get("artist"), musicFilter.getArtist()));
                }
                if (musicFilter.getMusicName() != null && !musicFilter.getMusicName().isEmpty()) {
                    log.info("searing by music name like \"%{}%\"", musicFilter.getMusicName());
                    predicates.add(criteriaBuilder.like(root.get("musicName"), "%" + musicFilter.getMusicName() + "%"));
                }
                if (musicFilter.getMovie() != null && !musicFilter.getMovie().isEmpty()) {
                    log.info("searing by movie name eq \"{}\"", musicFilter.getMovie());
                    predicates.add(criteriaBuilder.like(root.get("movie"), musicFilter.getMovie()));
                }
                log.info("---------------------end search------------------------------");
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
