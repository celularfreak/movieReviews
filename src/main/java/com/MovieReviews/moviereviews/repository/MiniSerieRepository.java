package com.MovieReviews.moviereviews.repository;

import com.MovieReviews.moviereviews.model.Series.MiniSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniSerieRepository extends JpaRepository<MiniSerie, Long> {

}
