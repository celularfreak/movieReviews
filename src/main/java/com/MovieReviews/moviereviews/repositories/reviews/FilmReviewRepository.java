package com.MovieReviews.moviereviews.repositories.reviews;

import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FilmReviewRepository extends JpaRepository<FilmReview, Integer> {

    Optional<Object> findByUsernameAndFilmId(String username, int filmId);

    Collection<Object> findByFilmId(int filmId);

    @Query("SELECT AVG(fr.rating) FROM FilmReview fr WHERE fr.filmId = :filmId")
    Double findAverageRatingByFilmId(@Param("filmId") int filmId);

    List<FilmReview> findByUsername(String username);
}

