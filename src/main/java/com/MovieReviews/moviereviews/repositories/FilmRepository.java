package com.MovieReviews.moviereviews.repositories;

import com.MovieReviews.moviereviews.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<Object> findByTitle(String title);
}
