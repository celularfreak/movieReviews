package com.MovieReviews.moviereviews.repositories;

import com.MovieReviews.moviereviews.model.Series.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

    Optional<Object> findByTitle(String title);
}
