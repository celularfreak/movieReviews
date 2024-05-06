package com.MovieReviews.moviereviews.repository;

import com.MovieReviews.moviereviews.model.Series.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
