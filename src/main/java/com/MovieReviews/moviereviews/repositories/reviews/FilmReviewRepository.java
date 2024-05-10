package com.MovieReviews.moviereviews.repositories.reviews;

import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

public interface FilmReviewRepository extends JpaRepository<FilmReview, Integer> {

    Optional<Object> findByUsernameAndFilmId(String username, int filmId);

    Collection<Object> findByFilmId(int filmId);
}

