package com.MovieReviews.moviereviews.repositories;

import com.MovieReviews.moviereviews.model.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TvSeriesRepository extends JpaRepository<TvSeries, Integer> {

    Optional<Object> findByTitle(String title);

    List<TvSeries> findByTitleContaining(String title);

    List<TvSeries> findByNumberEpisodes(int episodes);

    List<TvSeries> findByNumberSeasons(int seasons);

    List<TvSeries> findByGenre(String genre);

    @Query("SELECT AVG(ts.numberSeasons) FROM TvSeries ts")
    Integer calculateAverageSeasons();

    @Query("SELECT AVG(ts.numberEpisodes) FROM TvSeries ts")
    Integer calculateAverageEpisodes();

}
