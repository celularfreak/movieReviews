package com.MovieReviews.moviereviews.repositories;

import com.MovieReviews.moviereviews.model.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TvSeriesRepository extends JpaRepository<TvSeries, Integer> {

    Optional<Object> findByTitle(String title);
}
