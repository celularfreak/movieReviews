package com.MovieReviews.moviereviews.repositories.series;

import com.MovieReviews.moviereviews.model.series.MiniSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiniSerieRepository extends JpaRepository<MiniSerie, Integer> {

    Optional<Object> findByTitle(String title);
}
