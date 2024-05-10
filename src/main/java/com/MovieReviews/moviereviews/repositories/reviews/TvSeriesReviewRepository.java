package com.MovieReviews.moviereviews.repositories.reviews;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

public interface TvSeriesReviewRepository extends JpaRepository<TvSeriesReview, Integer> {

    Optional<Object> findByUsernameAndTvSeriesId(String username, int tvSeriesId);

    Collection<Object> findByTvSeriesId(int tvSeriesId);
}
