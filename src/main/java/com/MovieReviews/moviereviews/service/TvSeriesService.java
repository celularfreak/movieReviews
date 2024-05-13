package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.repositories.TvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvSeriesService {

    private final TvSeriesRepository tvSeriesRepository;

    @Autowired
    public TvSeriesService(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    public List<TvSeries> getAllTvSeries() {
        return tvSeriesRepository.findAll();
    }

    public TvSeries getTvSeriesById(int id) {
        return tvSeriesRepository.findById(id).orElse(null);
    }

    public TvSeries addTvSeries(TvSeries tvSeries) {
        validateTvSeries(tvSeries);
        return tvSeriesRepository.save(tvSeries);
    }

    public TvSeries updateTvSeries(int id, TvSeries tvSeries) {
        TvSeries existingTvSeries = tvSeriesRepository.findById(id).orElse(null);
        if (existingTvSeries != null) {
            existingTvSeries.setTitle(tvSeries.getTitle());
            existingTvSeries.setLaunchDate(tvSeries.getLaunchDate());
            existingTvSeries.setGenre(tvSeries.getGenre());
            existingTvSeries.setNumberSeasons(tvSeries.getNumberSeasons());
            existingTvSeries.setNumberEpisodes(tvSeries.getNumberEpisodes());
            existingTvSeries.setFinishDate(tvSeries.getFinishDate());
            return tvSeriesRepository.save(existingTvSeries);
        }
        return null;
    }

    public void deleteTvSeries(int id) {
        tvSeriesRepository.deleteById(id);
    }

    private void validateTvSeries(TvSeries tvSeries) {
        if (tvSeriesRepository.findByTitle(tvSeries.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una serie con ese título.");
        }
    }

    public List<TvSeries> searchTvSeries(String title) {
        return tvSeriesRepository.findByTitleContaining(title);
    }

    public List<TvSeries> searchTvSeriesByEpisodes(int episodes) {
        return tvSeriesRepository.findByNumberEpisodes(episodes);
    }

    public List<TvSeries> searchTvSeriesBySeasons(int seasons) {
        return tvSeriesRepository.findByNumberSeasons(seasons);
    }

    public List<TvSeries> searchTvSeriesByGenre(String genre) {
        return tvSeriesRepository.findByGenreContaining(genre);
    }

    public Integer calculateAverageSeasons() {
        return tvSeriesRepository.calculateAverageSeasons();
    }

    public Integer calculateAverageEpisodes() {
        return tvSeriesRepository.calculateAverageEpisodes();
    }

    public Integer calculateAverageEpisodesPerSeason() {
        return calculateAverageEpisodes() / calculateAverageSeasons();
    }
}
