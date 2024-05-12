package com.MovieReviews.moviereviews.repositories.series;

import com.MovieReviews.moviereviews.model.series.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    Optional<Object> findByTitle(String title);

    List<Anime> findByAnimationStudio(String animationStudio);
}
