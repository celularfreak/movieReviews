package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.repository.TvSeriesRepository;
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

    public TvSeries getTvSeriesById(Long id) {
        return tvSeriesRepository.findById(id).orElse(null);
    }

    public TvSeries addTvSeries(TvSeries tvSeries) {
        return tvSeriesRepository.save(tvSeries);
    }

    public TvSeries updateTvSeries(Long id, TvSeries tvSeries) {
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

    public void deleteTvSeries(Long id) {
        tvSeriesRepository.deleteById(id);
    }
}
