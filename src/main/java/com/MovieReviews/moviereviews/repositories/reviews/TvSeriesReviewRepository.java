package com.MovieReviews.moviereviews.repositories.reviews;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface TvSeriesReviewRepository extends JpaRepository<TvSeriesReview, Integer> {

    Optional<Object> findByUsernameAndTvSeriesId(String username, int tvSeriesId);

    Collection<Object> findByTvSeriesId(int tvSeriesId);

    @Query("SELECT AVG(fr.rating) FROM TvSeriesReview fr WHERE fr.tvSeriesId = : tvSeriesId")
    double findAverageRatingByTvSeriesId(int tvSeriesId);
}

