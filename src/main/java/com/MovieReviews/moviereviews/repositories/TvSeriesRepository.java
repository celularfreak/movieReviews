package com.MovieReviews.moviereviews.repositories;

import com.MovieReviews.moviereviews.model.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvSeriesRepository extends JpaRepository<TvSeries, Long> {

}
