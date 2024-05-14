package com.MovieReviews.moviereviews.repositories.reviews;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TvSeriesReviewRepository extends JpaRepository<TvSeriesReview, Integer> {

    Optional<Object> findByUsernameAndTvSeriesId(String username, int tvSeriesId);

    Collection<Object> findByTvSeriesId(int tvSeriesId);

    List<TvSeriesReview> findByUsername(String username);

    @Query("SELECT AVG(r.rating) FROM TvSeriesReview r WHERE r.tvSeriesId = :seriesId")
    Optional<Double> findAverageRatingBySeriesId(@Param("seriesId") int seriesId);
}

